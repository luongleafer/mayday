package mayday.medicalrecords;

import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.when;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.BadJwtException;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.jwt;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import org.springframework.test.web.servlet.request.RequestPostProcessor;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(properties = {
        "spring.datasource.url=jdbc:h2:mem:medical-record-tests;DB_CLOSE_DELAY=-1",
        "spring.datasource.username=sa",
        "spring.datasource.password=",
        "spring.datasource.driver-class-name=org.h2.Driver",
        "spring.jpa.hibernate.ddl-auto=none"
})
@AutoConfigureMockMvc
class MedicalRecordApiTests {
    private static final String BASE = "/api/v1/medical_records";

    @Autowired MockMvc mvc;
    @Autowired JdbcTemplate jdbc;
    @MockitoBean JwtDecoder decoder;

    @BeforeEach
    void seedDatabase() {
        jdbc.execute("DROP TABLE IF EXISTS hosobenhan");
        jdbc.execute("DROP TABLE IF EXISTS benhnhan");
        jdbc.execute("DROP TABLE IF EXISTS nguoi");
        jdbc.execute("CREATE TABLE nguoi (ma_nguoi BIGINT PRIMARY KEY, ho_ten VARCHAR(100))");
        jdbc.execute("CREATE TABLE benhnhan (ma_benh_nhan BIGINT PRIMARY KEY REFERENCES nguoi(ma_nguoi))");
        // Các cột giả định bên dưới chỉ được tạo trong H2 để kiểm thử.
        jdbc.execute("""
                CREATE TABLE hosobenhan (
                    ma_ho_so BIGINT PRIMARY KEY,
                    ma_benh_nhan BIGINT NOT NULL REFERENCES benhnhan(ma_benh_nhan),
                    trang_thai VARCHAR(30) DEFAULT 'draft'
                )
                """);
        jdbc.update("INSERT INTO nguoi VALUES (1, 'Patient A'), (2, 'Patient B')");
        jdbc.update("INSERT INTO benhnhan VALUES (1), (2)");
        jdbc.update("""
                INSERT INTO hosobenhan VALUES
                (10, 1, 'pending'),
                (11, 1, 'completed'),
                (12, 2, 'in_progress'),
                (13, 2, 'pending')
                """);
    }

    @Test
    void doctorCanFilterPendingRecordsAndPaginate() throws Exception {
        mvc.perform(get(BASE + "/all").param("status", "pending").param("limit", "1")
                        .with(user("3", "DOCTOR")))
                .andExpect(status().isOk()).andExpect(jsonPath("$.code").value("1000"))
                .andExpect(jsonPath("$.data", hasSize(1)))
                .andExpect(jsonPath("$.data[0].medical_record_id").value(13));
        mvc.perform(get(BASE + "/all").param("status", "pending").param("limit", "1")
                        .param("page", "2").param("patient_id", "1").with(user("3", "DOCTOR")))
                .andExpect(status().isOk()).andExpect(jsonPath("$.data", hasSize(0)));
        mvc.perform(get(BASE + "/all").param("status", "pending").param("patient_id", "1")
                        .with(user("3", "DOCTOR")))
                .andExpect(jsonPath("$.data", hasSize(1)))
                .andExpect(jsonPath("$.data[0].medical_record_id").value(10))
                .andExpect(jsonPath("$.data[0].assigned_doctor_id").isEmpty());
    }

    @Test
    void draftStatusIsPreservedFromDatabase() throws Exception {
        jdbc.update("UPDATE hosobenhan SET trang_thai = 'draft' WHERE ma_ho_so = 10");
        mvc.perform(get(BASE + "/all").param("status", "draft").with(user("3", "DOCTOR")))
                .andExpect(status().isOk()).andExpect(jsonPath("$.data", hasSize(1)))
                .andExpect(jsonPath("$.data[0].status").value("draft"));
        mvc.perform(get(BASE + "/all").param("status", "pending").with(user("3", "DOCTOR")))
                .andExpect(jsonPath("$.data", hasSize(1)))
                .andExpect(jsonPath("$.data[0].medical_record_id").value(13));
    }

    @Test
    void detailContainsRecordAndLinkedIds() throws Exception {
        mvc.perform(get(BASE + "/11").with(user("3", "DOCTOR")))
                .andExpect(status().isOk()).andExpect(jsonPath("$.code").value("1000"))
                .andExpect(jsonPath("$.data.patient_id").value(1))
                .andExpect(jsonPath("$.data.assigned_doctor_id").value(3))
                .andExpect(jsonPath("$.data.created_by").value(5));
    }

    @Test
    void missingRecordReturns4001ForDetailAndSummary() throws Exception {
        for (String path : List.of("/999", "/999/summary")) {
            mvc.perform(get(BASE + path).with(user("3", "DOCTOR")))
                    .andExpect(status().isNotFound()).andExpect(jsonPath("$.code").value("4001"))
                    .andExpect(jsonPath("$.message").value("Medical record not found"));
        }
    }

    @Test
    void summaryOnlyContainsFourFields() throws Exception {
        mvc.perform(get(BASE + "/11/summary").with(user("1", "PATIENT")))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.length()").value(4))
                .andExpect(jsonPath("$.data.medical_record_id").value(11))
                .andExpect(jsonPath("$.data.status").value("completed"))
                .andExpect(jsonPath("$.data.patient_name").value("Patient A"))
                .andExpect(jsonPath("$.data.created_at").exists());
    }

    @Test
    void patientAndDoctorCanReadHistoryInDescendingOrder() throws Exception {
        for (RequestPostProcessor actor : List.of(user("1", "PATIENT"), user("3", "DOCTOR"))) {
            mvc.perform(get(BASE + "/patients/1/history").with(actor))
                    .andExpect(status().isOk()).andExpect(jsonPath("$.code").value("1000"))
                    .andExpect(jsonPath("$.data", hasSize(2)))
                    .andExpect(jsonPath("$.data[0].medical_record_id").value(11))
                    .andExpect(jsonPath("$.data[1].medical_record_id").value(10));
        }
    }

    @Test
    void patientCannotReadOtherPatientsOrListAll() throws Exception {
        for (String path : List.of("/patients/2/history", "/12", "/12/summary", "/all")) {
            mvc.perform(get(BASE + path).with(user("1", "PATIENT")))
                    .andExpect(status().isForbidden()).andExpect(jsonPath("$.code").value("3106"))
                    .andExpect(jsonPath("$.message").value("Cannot access another user's data"));
        }
    }

    @Test
    void unsupportedRoleCannotReadHistory() throws Exception {
        mvc.perform(get(BASE + "/patients/1/history").with(user("1", "UNKNOWN")))
                .andExpect(status().isForbidden());
    }

    @Test
    void rejectsInvalidQueryParameters() throws Exception {
        for (String query : List.of("status=invalid", "page=0", "limit=0", "limit=101",
                "patient_id=-1", "page=abc", "page=2147483648")) {
            mvc.perform(get(BASE + "/all?" + query).with(user("3", "DOCTOR")))
                    .andExpect(status().isBadRequest()).andExpect(jsonPath("$.code").value("4000"));
        }
        mvc.perform(get(BASE + "/patients/0/history").with(user("3", "DOCTOR")))
                .andExpect(status().isBadRequest());
    }

    @Test
    void requiresAuthentication() throws Exception {
        mvc.perform(get(BASE + "/all"))
                .andExpect(status().isUnauthorized()).andExpect(jsonPath("$.code").value("3101"));
    }

    @Test
    void bearerTokenUsesVerifiedSubjectAndRoles() throws Exception {
        when(decoder.decode("valid-token")).thenReturn(Jwt.withTokenValue("valid-token")
                .header("alg", "RS256").subject("1").claim("roles", List.of("PATIENT")).build());
        mvc.perform(get(BASE + "/patients/1/history").header("Authorization", "Bearer valid-token"))
                .andExpect(status().isOk());
        mvc.perform(get(BASE + "/patients/2/history").header("Authorization", "Bearer valid-token"))
                .andExpect(status().isForbidden()).andExpect(jsonPath("$.code").value("3106"));
        when(decoder.decode("invalid-token")).thenThrow(new BadJwtException("Invalid token"));
        mvc.perform(get(BASE + "/all").header("Authorization", "Bearer invalid-token"))
                .andExpect(status().isUnauthorized());
    }

    private RequestPostProcessor user(String id, String role) {
        return jwt().jwt(token -> token.subject(id)).authorities(new SimpleGrantedAuthority("ROLE_" + role));
    }
}

package mayday;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import mayday.entities.cosovatchat.ChiNhanh;
import mayday.entities.cosovatchat.KhoaPhong;
import mayday.entities.cosovatchat.PhongKham;
import mayday.repositories.ChiNhanhRepository;
import mayday.repositories.KhoaPhongRepository;
import mayday.repositories.PhongKhamRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest(properties = {
        "spring.config.import=",
        "spring.datasource.url=jdbc:h2:mem:topology-test;DB_CLOSE_DELAY=-1",
        "spring.datasource.driver-class-name=org.h2.Driver",
        "spring.datasource.username=sa",
        "spring.datasource.password=",
        "spring.jpa.hibernate.ddl-auto=create-drop"
})
@AutoConfigureMockMvc
class ClinicTopologyApiTests {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ChiNhanhRepository branchRepository;

    @Autowired
    private KhoaPhongRepository departmentRepository;

    @Autowired
    private PhongKhamRepository roomRepository;

    @BeforeEach
    void cleanDatabase() {
        roomRepository.deleteAll();
        departmentRepository.deleteAll();
        branchRepository.deleteAll();
    }

    @Test
    void supportsAllFifteenCrudEndpoints() throws Exception {
        mockMvc.perform(post("/api/v1/branches")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content("""
                                                 {"name":"Co so Trung tam","address":"1 Nguyen Trai","phone":"0901000000"}
                                                 """))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value("1001"))
                .andExpect(jsonPath("$.data.is_active").value(true));
        Long branchId = branchRepository.findAll().getFirst().getId();

        mockMvc.perform(get("/api/v1/branches"))
                .andExpect(status().isOk()).andExpect(jsonPath("$.data", hasSize(1)));
        mockMvc.perform(get("/api/v1/branches/{id}", branchId))
                .andExpect(status().isOk()).andExpect(jsonPath("$.data.name").value("Co so Trung tam"));
        mockMvc.perform(put("/api/v1/branches/{id}", branchId)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content("{" + "\"phone\":\"0902000000\"}"))
                .andExpect(status().isOk()).andExpect(jsonPath("$.data.phone").value("0902000000"));

        mockMvc.perform(post("/api/v1/departments")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content("""
                                                 {"name":"Khoa Da lieu","type":"LAM_SANG","description":"Kham da lieu","branchId":%d}
                                                 """.formatted(branchId)))
                .andExpect(status().isCreated()).andExpect(jsonPath("$.code").value("1001"));
        Long departmentId = departmentRepository.findAll().getFirst().getId();

        mockMvc.perform(get("/api/v1/departments").param("branch_id", branchId.toString()))
                .andExpect(status().isOk()).andExpect(jsonPath("$.data", hasSize(1)));
        mockMvc.perform(get("/api/v1/departments/{id}", departmentId))
                .andExpect(status().isOk()).andExpect(jsonPath("$.data.branchId").value(branchId));
        mockMvc.perform(put("/api/v1/departments/{id}", departmentId)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content("{\"description\":\"Da lieu cap nhat\"}"))
                .andExpect(status().isOk()).andExpect(jsonPath("$.data.description").value("Da lieu cap nhat"));

        mockMvc.perform(post("/api/v1/rooms")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content("""
                                                 {"name":"Phong 101","departmentId":%d}
                                                 """.formatted(departmentId)))
                .andExpect(status().isCreated()).andExpect(jsonPath("$.code").value("1001"));
        Long roomId = roomRepository.findAll().getFirst().getId();

        mockMvc.perform(get("/api/v1/rooms").param("department_id", departmentId.toString()))
                .andExpect(status().isOk()).andExpect(jsonPath("$.data", hasSize(1)));
        mockMvc.perform(get("/api/v1/rooms/{id}", roomId))
                .andExpect(status().isOk()).andExpect(jsonPath("$.data.departmentId").value(departmentId));
        mockMvc.perform(put("/api/v1/rooms/{id}", roomId)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content("{\"name\":\"Phong 102\"}"))
                .andExpect(status().isOk()).andExpect(jsonPath("$.data.name").value("Phong 102"));

        mockMvc.perform(delete("/api/v1/rooms/{id}", roomId))
                .andExpect(status().isOk()).andExpect(jsonPath("$.code").value("1000"));
        mockMvc.perform(delete("/api/v1/departments/{id}", departmentId))
                .andExpect(status().isOk()).andExpect(jsonPath("$.code").value("1000"));
        mockMvc.perform(delete("/api/v1/branches/{id}", branchId))
                .andExpect(status().isOk()).andExpect(jsonPath("$.code").value("1000"));
    }

    @Test
    void rejectsInvalidForeignKeysAndDuplicateNames() throws Exception {
        mockMvc.perform(post("/api/v1/departments")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content("""
                                                 {"name":"Khoa Da lieu","type":"LAM_SANG","branchId":999999}
                                                 """))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.code").value("2001"));

        ChiNhanh branch = branchRepository.save(new ChiNhanh(null, "Co so 1", "Dia chi 1", null, true));
        departmentRepository.save(new KhoaPhong("Khoa Da lieu", "LAM_SANG", null, branch));

        mockMvc.perform(post("/api/v1/departments")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content("""
                                                 {"name":"khoa da lieu","type":"LAM_SANG","branchId":%d}
                                                 """.formatted(branch.getId())))
                .andExpect(status().isConflict())
                .andExpect(jsonPath("$.code").value("2004"));
    }

    @Test
    void rejectsBlankBranchFieldsAndInvalidPathIds() throws Exception {
        mockMvc.perform(post("/api/v1/branches")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content("{\"name\":\"  \",\"address\":\"Address\"}"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.code").value("2003"));
        mockMvc.perform(get("/api/v1/rooms/not-a-number"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.code").value("2003"));
    }

    @Test
    void preservesOmittedBranchFieldsAndRejectsBlankUpdates() throws Exception {
        ChiNhanh branch = branchRepository.save(new ChiNhanh(null, "Original", "Address", "0900000000", true));
        mockMvc.perform(put("/api/v1/branches/{id}", branch.getId())
                                .contentType(MediaType.APPLICATION_JSON)
                                .content("{\"name\":\" Updated \"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.name").value("Updated"))
                .andExpect(jsonPath("$.data.address").value("Address"))
                .andExpect(jsonPath("$.data.phone").value("0900000000"));
        mockMvc.perform(put("/api/v1/branches/{id}", branch.getId())
                                .contentType(MediaType.APPLICATION_JSON)
                                .content("{\"name\":\"   \"}"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.code").value("2003"));
    }

    @Test
    void rejectsDeletingBranchWithDepartmentsAndReturnsNotFound() throws Exception {
        ChiNhanh branch = branchRepository.save(new ChiNhanh(null, "Branch", "Address", null, true));
        departmentRepository.save(new KhoaPhong("Department", "LAM_SANG", null, branch));
        mockMvc.perform(delete("/api/v1/branches/{id}", branch.getId()))
                .andExpect(status().isConflict())
                .andExpect(jsonPath("$.code").value("2005"));
        mockMvc.perform(get("/api/v1/branches/999999"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.code").value("2001"));
    }

    @Test
    void rejectsDeletingDepartmentThatStillHasRooms() throws Exception {
        ChiNhanh branch = branchRepository.save(new ChiNhanh(null, "Co so 1", "Dia chi 1", null, true));
        KhoaPhong department = departmentRepository.save(
                new KhoaPhong("Khoa Da lieu", "LAM_SANG", null, branch));
        roomRepository.save(new PhongKham("Phong 101", department));

        mockMvc.perform(delete("/api/v1/departments/{id}", department.getId()))
                .andExpect(status().isConflict())
                .andExpect(jsonPath("$.code").value("2005"));
    }
}

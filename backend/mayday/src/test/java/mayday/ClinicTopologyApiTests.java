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
import org.springframework.security.test.context.support.WithMockUser;

@SpringBootTest(properties = {
        "spring.config.import=",
        "spring.datasource.url=jdbc:h2:mem:topology-test;DB_CLOSE_DELAY=-1",
        "spring.datasource.driver-class-name=org.h2.Driver",
        "spring.datasource.username=sa",
        "spring.datasource.password=",
        "spring.jpa.hibernate.ddl-auto=create-drop"
})
@AutoConfigureMockMvc
@WithMockUser(username = "1", roles = "ADMIN")
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
                .andExpect(jsonPath("$.code").value("1000"))
                .andExpect(jsonPath("$.data.is_active").doesNotExist())
                .andExpect(jsonPath("$.data.created_at").isNotEmpty())
                .andExpect(jsonPath("$.data.updated_at").isNotEmpty());
        Long branchId = branchRepository.findAll().getFirst().getMaChiNhanh();

        mockMvc.perform(get("/api/v1/branches"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data", hasSize(1)))
                .andExpect(jsonPath("$.data[0].is_active").value(true))
                .andExpect(jsonPath("$.data[0].created_at").doesNotExist())
                .andExpect(jsonPath("$.data[0].updated_at").doesNotExist());
        mockMvc.perform(get("/api/v1/branches/{id}", branchId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.name").value("Co so Trung tam"))
                .andExpect(jsonPath("$.data.is_active").doesNotExist())
                .andExpect(jsonPath("$.data.created_at").isNotEmpty())
                .andExpect(jsonPath("$.data.updated_at").isNotEmpty());
        mockMvc.perform(put("/api/v1/branches/{id}", branchId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{" + "\"phone\":\"0902000000\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.phone").value("0902000000"))
                .andExpect(jsonPath("$.data.is_active").doesNotExist())
                .andExpect(jsonPath("$.data.created_at").isNotEmpty())
                .andExpect(jsonPath("$.data.updated_at").isNotEmpty());

        mockMvc.perform(post("/api/v1/departments")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {"name":"Khoa Da lieu","description":"Kham da lieu","branch_id":%d}
                                """.formatted(branchId)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.code").value("1000"))
                .andExpect(jsonPath("$.data.branch_id").value(branchId))
                .andExpect(jsonPath("$.data.type").doesNotExist())
                .andExpect(jsonPath("$.data.branchId").doesNotExist())
                .andExpect(jsonPath("$.data.created_at").isNotEmpty())
                .andExpect(jsonPath("$.data.updated_at").doesNotExist());
        Long departmentId = departmentRepository.findAll().getFirst().getMaKhoaPhong();

        mockMvc.perform(get("/api/v1/departments").param("branch_id", branchId.toString()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data", hasSize(1)))
                .andExpect(jsonPath("$.data[0].created_at").doesNotExist())
                .andExpect(jsonPath("$.data[0].updated_at").doesNotExist());
        mockMvc.perform(get("/api/v1/departments/{id}", departmentId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.branch_id").value(branchId))
                .andExpect(jsonPath("$.data.branchId").doesNotExist())
                .andExpect(jsonPath("$.data.type").doesNotExist())
                .andExpect(jsonPath("$.data.created_at").isNotEmpty())
                .andExpect(jsonPath("$.data.updated_at").isNotEmpty());
        mockMvc.perform(put("/api/v1/departments/{id}", departmentId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"description\":\"Da lieu cap nhat\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.description").value("Da lieu cap nhat"))
                .andExpect(jsonPath("$.data.created_at").isNotEmpty())
                .andExpect(jsonPath("$.data.updated_at").isNotEmpty());

        mockMvc.perform(post("/api/v1/rooms")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {"name":"Phong 101","description":"Phong kham da lieu 1","department_id":%d}
                                """.formatted(departmentId)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.code").value("1000"))
                .andExpect(jsonPath("$.data.description").value("Phong kham da lieu 1"))
                .andExpect(jsonPath("$.data.department_id").value(departmentId))
                .andExpect(jsonPath("$.data.departmentId").doesNotExist())
                .andExpect(jsonPath("$.data.created_at").isNotEmpty())
                .andExpect(jsonPath("$.data.updated_at").doesNotExist());
        Long roomId = roomRepository.findAll().getFirst().getMaPhongKham();

        mockMvc.perform(get("/api/v1/rooms").param("department_id", departmentId.toString()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data", hasSize(1)))
                .andExpect(jsonPath("$.data[0].created_at").isNotEmpty())
                .andExpect(jsonPath("$.data[0].updated_at").doesNotExist());
        mockMvc.perform(get("/api/v1/rooms/{id}", roomId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.department_id").value(departmentId))
                .andExpect(jsonPath("$.data.departmentId").doesNotExist())
                .andExpect(jsonPath("$.data.created_at").isNotEmpty())
                .andExpect(jsonPath("$.data.updated_at").isNotEmpty());
        mockMvc.perform(put("/api/v1/rooms/{id}", roomId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"Phong 102\",\"description\":\"Cap nhat mo ta\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.name").value("Phong 102"))
                .andExpect(jsonPath("$.data.description").value("Cap nhat mo ta"))
                .andExpect(jsonPath("$.data.created_at").isNotEmpty())
                .andExpect(jsonPath("$.data.updated_at").isNotEmpty());

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
                                {"name":"Khoa Da lieu","branchId":999999}
                                """))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.code").value("2003"));

        ChiNhanh branch = branchRepository.save(new ChiNhanh(null, "Co so 1", "Dia chi 1", "0901234567", true));
        mockMvc.perform(post("/api/v1/branches")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {"name":"  co   so 1  ","address":"Dia chi 2","phone":"0909999999"}
                                """))
                .andExpect(status().isConflict())
                .andExpect(jsonPath("$.code").value("2003"));

        // Chan trung dia chi (khong phan biet hoa thuong, khoang trang thua)
        mockMvc.perform(post("/api/v1/branches")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {"name":"Co so 2","address":"  DIA   chi 1 ","phone":"0908888888"}
                                """))
                .andExpect(status().isConflict())
                .andExpect(jsonPath("$.code").value("2003"))
                .andExpect(jsonPath("$.message").value("Branch address already exists"));

        // Chan trung so dien thoai (ke ca khi nhap co dau cach, gach ngang hoac +84)
        mockMvc.perform(post("/api/v1/branches")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {"name":"Co so 2","address":"Dia chi 2","phone":"+84 901-234-567"}
                                """))
                .andExpect(status().isConflict())
                .andExpect(jsonPath("$.code").value("2003"))
                .andExpect(jsonPath("$.message").value("Branch phone already exists"));

        KhoaPhong dept1 = departmentRepository.save(new KhoaPhong("Khoa Da lieu", "LAM_SANG", null, branch));
        KhoaPhong dept2 = departmentRepository.save(new KhoaPhong("Khoa Xet nghiem", "CAN_LAM_SANG", null, branch));

        mockMvc.perform(post("/api/v1/departments")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {"name":"khoa da lieu","branchId":%d}
                                """.formatted(branch.getMaChiNhanh())))
                .andExpect(status().isConflict())
                .andExpect(jsonPath("$.code").value("2003"));

        // Chan trung ten phong kham trong cung chi nhanh (du thuoc 2 khoa khac nhau cua chi nhanh do)
        roomRepository.save(new PhongKham("Phong 101", "Mo ta", dept1));
        mockMvc.perform(post("/api/v1/rooms")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {"name":"  phong   101 ","department_id":%d}
                                """.formatted(dept2.getMaKhoaPhong())))
                .andExpect(status().isConflict())
                .andExpect(jsonPath("$.code").value("2003"))
                .andExpect(jsonPath("$.message").value("Room name already exists in this branch"));
    }

    @Test
    void rejectsBlankBranchFieldsInvalidPhonesAndInvalidIds() throws Exception {
        mockMvc.perform(post("/api/v1/branches")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"  \",\"address\":\"Address\"}"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.code").value("2001"));
        mockMvc.perform(post("/api/v1/branches")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"Co so A\",\"address\":\"Address\",\"phone\":\"abc-invalid\"}"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.code").value("2004"));
        mockMvc.perform(post("/api/v1/branches")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{malformed-json"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.code").value("2007"));
        mockMvc.perform(get("/api/v1/branches/0"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.code").value("2003"));
        mockMvc.perform(get("/api/v1/rooms/not-a-number"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.code").value("2002"));
    }

    @Test
    void preservesOmittedBranchFieldsAndRejectsBlankUpdates() throws Exception {
        ChiNhanh branch = branchRepository.save(new ChiNhanh(null, "Original", "Address", "0900000000", true));
        mockMvc.perform(put("/api/v1/branches/{id}", branch.getMaChiNhanh())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\" Updated \"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.name").value("Updated"))
                .andExpect(jsonPath("$.data.address").value("Address"))
                .andExpect(jsonPath("$.data.phone").value("0900000000"));
        mockMvc.perform(put("/api/v1/branches/{id}", branch.getMaChiNhanh())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"   \"}"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.code").value("2003"));
    }

    @Test
    void rejectsDeletingBranchWithDepartmentsAndReturnsNotFound() throws Exception {
        ChiNhanh branch = branchRepository.save(new ChiNhanh(null, "Branch", "Address", null, true));
        departmentRepository.save(new KhoaPhong("Department", "LAM_SANG", null, branch));
        mockMvc.perform(delete("/api/v1/branches/{id}", branch.getMaChiNhanh()))
                .andExpect(status().isConflict())
                .andExpect(jsonPath("$.code").value("2003"));
        mockMvc.perform(get("/api/v1/branches/999999"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.code").value("2003"));
    }

    @Test
    void deletingDepartmentSetsRoomDepartmentIdToNull() throws Exception {
        ChiNhanh branch = branchRepository.save(new ChiNhanh(null, "Co so 1", "Dia chi 1", null, true));
        KhoaPhong department = departmentRepository.save(
                new KhoaPhong("Khoa Da lieu", "LAM_SANG", null, branch));
        PhongKham room = roomRepository.save(new PhongKham("Phong 101", "Phong kham 1", department));

        mockMvc.perform(delete("/api/v1/departments/{id}", department.getMaKhoaPhong()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value("1000"));

        mockMvc.perform(get("/api/v1/rooms/{id}", room.getMaPhongKham()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.name").value("Phong 101"))
                .andExpect(jsonPath("$.data.department_id").isEmpty());
    }
}

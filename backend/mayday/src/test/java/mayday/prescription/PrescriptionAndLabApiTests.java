package mayday.prescription;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import mayday.entities.hosobenhan.HoSoBenhAn;
import mayday.entities.lab.LabRequest;
import mayday.entities.lab.LabRequestItem;
import mayday.entities.lab.LabResult;
import mayday.repositories.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.OffsetDateTime;

@SpringBootTest
@AutoConfigureMockMvc
class PrescriptionAndLabApiTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private HoSoBenhAnRepository hoSoBenhAnRepository;

    @Autowired
    private PrescriptionRepository prescriptionRepository;

    @Autowired
    private LabRequestRepository labRequestRepository;

    @Autowired
    private LabRequestItemRepository labRequestItemRepository;

    @Autowired
    private LabResultRepository labResultRepository;

    private Long testRecordId;
    private final Long doctorId = 3L;

    @BeforeEach
    void setup() {
        prescriptionRepository.deleteAll();
        labResultRepository.deleteAll();
        labRequestItemRepository.deleteAll();
        labRequestRepository.deleteAll();

        var records = hoSoBenhAnRepository.findAll();
        if (records.iterator().hasNext()) {
            testRecordId = records.iterator().next().getMaHoSo();
        } else {
            throw new IllegalStateException("Test database must contain at least one medical record");
        }
    }

    // =========================================================================
    // rx01: create_prescription
    // =========================================================================
    @Test
    void rx01_createPrescription_success() throws Exception {
        String body = """
                {
                    "medical_record_id": "%d",
                    "note": "Uống đúng giờ",
                    "items": [
                        {
                            "medicine_name": "Cetirizine 10mg",
                            "dosage": "1 viên",
                            "frequency": "1 lần/ngày",
                            "duration": "7 ngày",
                            "instruction": "Uống sau ăn tối"
                        },
                        {
                            "medicine_name": "Fexofenadine 180mg",
                            "dosage": "1 viên",
                            "frequency": "1 lần/ngày",
                            "duration": "5 ngày",
                            "instruction": "Uống sáng"
                        }
                    ]
                }
                """.formatted(testRecordId);

        mockMvc.perform(post("/api/v1/prescriptions")
                        .header("Authorization", "Bearer dev-doctor")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.code").value("1001"))
                .andExpect(jsonPath("$.message").value("Tạo đơn thuốc thành công"))
                .andExpect(jsonPath("$.data.prescription_id").isNotEmpty())
                .andExpect(jsonPath("$.data.medical_record_id").value(String.valueOf(testRecordId)))
                .andExpect(jsonPath("$.data.total_medicines").value("2"))
                .andExpect(jsonPath("$.data.items", hasSize(2)))
                .andExpect(jsonPath("$.data.items[0].medicine_name").value("Cetirizine 10mg"));
    }

    @Test
    void rx01_emptyMedicines_shouldReturn8001() throws Exception {
        String body = """
                {
                    "medical_record_id": "%d",
                    "note": "Uống đúng giờ",
                    "items": []
                }
                """.formatted(testRecordId);

        mockMvc.perform(post("/api/v1/prescriptions")
                        .header("Authorization", "Bearer dev-doctor")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.code").value("8001"))
                .andExpect(jsonPath("$.message").value("Medicines list cannot be empty"));
    }

    @Test
    void rx01_missingMedicineName_shouldReturn8002() throws Exception {
        String body = """
                {
                    "medical_record_id": "%d",
                    "items": [
                        {
                            "medicine_name": "   ",
                            "dosage": "10mg"
                        }
                    ]
                }
                """.formatted(testRecordId);

        mockMvc.perform(post("/api/v1/prescriptions")
                        .header("Authorization", "Bearer dev-doctor")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.code").value("8002"))
                .andExpect(jsonPath("$.message").value("Medicine name is required"));
    }

    @Test
    void rx01_recordNotFound_shouldReturn4001() throws Exception {
        String body = """
                {
                    "medical_record_id": "999999",
                    "items": [
                        {
                            "medicine_name": "Cetirizine 10mg"
                        }
                    ]
                }
                """;

        mockMvc.perform(post("/api/v1/prescriptions")
                        .header("Authorization", "Bearer dev-doctor")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.code").value("4001"))
                .andExpect(jsonPath("$.message").value("Medical record not found"));
    }

    @Test
    void rx01_doctorNotAssigned_shouldReturn3105() throws Exception {
        String body = """
                {
                    "medical_record_id": "%d",
                    "items": [
                        {
                            "medicine_name": "Cetirizine 10mg"
                        }
                    ]
                }
                """.formatted(testRecordId);

        // Bác sĩ 4 không được phân công cho hồ sơ testRecordId (hồ sơ gán cho bác sĩ 3)
        mockMvc.perform(post("/api/v1/prescriptions")
                        .header("Authorization", "Bearer dev-doctor")
                        .header("X-Doctor-Id", "4")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.code").value("3105"))
                .andExpect(jsonPath("$.message").value("Doctor not assigned"));
    }

    // =========================================================================
    // lab01: create_lab_request
    // =========================================================================
    @Test
    void lab01_createLabRequest_success() throws Exception {
        String body = """
                {
                    "medical_record_id": "%d",
                    "note": "Xét nghiệm tổng quát",
                    "items": [
                        {
                            "test_name": "Công thức máu toàn phần (WBC, RBC, PLT)",
                            "description": "Kiểm tra số lượng bạch cầu"
                        },
                        {
                            "test_name": "Định lượng IgE toàn phần",
                            "description": "Đo nồng độ kháng thể dị ứng"
                        }
                    ]
                }
                """.formatted(testRecordId);

        mockMvc.perform(post("/api/v1/lab-requests")
                        .header("Authorization", "Bearer dev-doctor")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.code").value("1001"))
                .andExpect(jsonPath("$.message").value("Tạo yêu cầu xét nghiệm thành công"))
                .andExpect(jsonPath("$.data.lab_request_id").isNotEmpty())
                .andExpect(jsonPath("$.data.status").value("pending"))
                .andExpect(jsonPath("$.data.items", hasSize(2)))
                .andExpect(jsonPath("$.data.items[0].test_name").value("Công thức máu toàn phần (WBC, RBC, PLT)"));
    }

    @Test
    void lab01_emptyTests_shouldReturn8101() throws Exception {
        String body = """
                {
                    "medical_record_id": "%d",
                    "items": []
                }
                """.formatted(testRecordId);

        mockMvc.perform(post("/api/v1/lab-requests")
                        .header("Authorization", "Bearer dev-doctor")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.code").value("8101"))
                .andExpect(jsonPath("$.message").value("Lab tests list cannot be empty"));
    }

    @Test
    void lab01_missingTestName_shouldReturn8102() throws Exception {
        String body = """
                {
                    "medical_record_id": "%d",
                    "items": [
                        {
                            "test_name": "",
                            "description": "Mô tả"
                        }
                    ]
                }
                """.formatted(testRecordId);

        mockMvc.perform(post("/api/v1/lab-requests")
                        .header("Authorization", "Bearer dev-doctor")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.code").value("8102"))
                .andExpect(jsonPath("$.message").value("Test name required"));
    }

    @Test
    void lab01_recordNotFound_shouldReturn4001() throws Exception {
        String body = """
                {
                    "medical_record_id": "888888",
                    "items": [
                        {
                            "test_name": "Xét nghiệm máu"
                        }
                    ]
                }
                """;

        mockMvc.perform(post("/api/v1/lab-requests")
                        .header("Authorization", "Bearer dev-doctor")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.code").value("4001"))
                .andExpect(jsonPath("$.message").value("Medical record not found"));
    }

    @Test
    void lab01_doctorNotAssigned_shouldReturn3105() throws Exception {
        String body = """
                {
                    "medical_record_id": "%d",
                    "items": [
                        {
                            "test_name": "Xét nghiệm máu"
                        }
                    ]
                }
                """.formatted(testRecordId);

        // Bác sĩ 4 không được phân công
        mockMvc.perform(post("/api/v1/lab-requests")
                        .header("Authorization", "Bearer dev-doctor")
                        .header("X-Doctor-Id", "4")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.code").value("3105"))
                .andExpect(jsonPath("$.message").value("Doctor not assigned"));
    }

    // =========================================================================
    // lab02: get_lab_results
    // =========================================================================
    @Test
    void lab02_getLabResults_success() throws Exception {
        LabRequest labReq = LabRequest.builder()
                .medicalRecordId(testRecordId)
                .requestedBy(doctorId)
                .status("completed")
                .note("Yêu cầu xét nghiệm nhanh")
                .createdAt(OffsetDateTime.now())
                .updatedAt(OffsetDateTime.now())
                .build();

        LabRequestItem item = LabRequestItem.builder()
                .testName("Tổng bạch cầu WBC")
                .description("Chỉ số định lượng")
                .createdAt(OffsetDateTime.now())
                .build();
        labReq.addItem(item);
        LabRequest savedReq = labRequestRepository.save(labReq);

        LabRequestItem savedItem = savedReq.getItems().get(0);
        LabResult res = LabResult.builder()
                .labRequestItem(savedItem)
                .resultValue("7.2 G/L")
                .concludedBy(doctorId)
                .resultNote("Bình thường")
                .createdAt(OffsetDateTime.now())
                .updatedAt(OffsetDateTime.now())
                .build();
        labResultRepository.save(res);

        mockMvc.perform(get("/api/v1/lab-results/" + testRecordId)
                        .header("Authorization", "Bearer dev-doctor"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value("1000"))
                .andExpect(jsonPath("$.message").value("Thành công"))
                .andExpect(jsonPath("$.data", hasSize(1)))
                .andExpect(jsonPath("$.data[0].items[0].test_name").value("Tổng bạch cầu WBC"))
                .andExpect(jsonPath("$.data[0].items[0].result_value").value("7.2 G/L"));
    }

    @Test
    void lab02_wrongPatientAccess_shouldReturn3106() throws Exception {
        // Patient 99999 cố tình xem bệnh án của bệnh nhân khác (bệnh án có ma_benh_nhan = 2)
        mockMvc.perform(get("/api/v1/lab-results/" + testRecordId)
                        .header("Authorization", "Bearer patient-99999"))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.code").value("3106"))
                .andExpect(jsonPath("$.message").value("Cannot access another user's data"));
    }

    @Test
    void lab02_recordNotFound_shouldReturn4001() throws Exception {
        mockMvc.perform(get("/api/v1/lab-results/999999")
                        .header("Authorization", "Bearer dev-doctor"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.code").value("4001"))
                .andExpect(jsonPath("$.message").value("Medical record not found"));
    }
}

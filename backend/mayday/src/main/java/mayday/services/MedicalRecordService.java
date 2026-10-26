package mayday.services;

import lombok.RequiredArgsConstructor;
import mayday.dto.*;
import mayday.entities.Benhanmaydaycap;
import mayday.entities.Benhanmaydayman;
import mayday.entities.Benhnhan;
import mayday.entities.Hosobenhan;
import mayday.repositories.BenhnhanRepository;
import mayday.repositories.HosobenhanRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.OffsetDateTime;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class MedicalRecordService {

    private final BenhnhanRepository benhnhanRepository;
    private final HosobenhanRepository hosobenhanRepository;

    public static final String TMPL_URT_ACUTE = "TMPL_URT_ACUTE";
    public static final String TMPL_URT_CHRONIC = "TMPL_URT_CHRONIC";

    // Khóa định danh câu hỏi đồng bộ giữa medrec01 và medrec02
    public static final String Q_DURATION_WEEKS = "duration_weeks";
    public static final String Q_PRIOR_EPISODES = "has_prior_episodes";
    public static final String Q_TREATED_BEFORE = "treated_before";
    public static final String Q_FAMILY_HISTORY = "family_history";

    // =========================================================================
    // 1. medrec01: Lấy danh sách câu hỏi sàng lọc phân loại
    // Endpoint: GET /api/v1/medical_records/classify-questions
    // =========================================================================
    public ClassifyQuestionsResponseDto getClassifyQuestions() {
        List<ClassifyQuestionDto> questions = Arrays.asList(
                ClassifyQuestionDto.builder()
                        .questionId(Q_DURATION_WEEKS)
                        .questionText("Thời gian khởi phát đợt tổn thương da kéo dài bao nhiêu tuần?")
                        .typeQuestion("radio")
                        .options(Arrays.asList(
                                ClassifyQuestionDto.OptionItem.builder()
                                        .id(1)
                                        .optionText("Dưới 6 tuần (Cấp tính)")
                                        .optionValue("5")
                                        .build(),
                                ClassifyQuestionDto.OptionItem.builder()
                                        .id(2)
                                        .optionText("Từ 6 tuần trở lên (Mạn tính)")
                                        .optionValue("6")
                                        .build()
                        ))
                        .build(),

                ClassifyQuestionDto.builder()
                        .questionId(Q_PRIOR_EPISODES)
                        .questionText("Trước đây bạn đã từng mắc các đợt phát ban, mẩn ngứa tương tự chưa?")
                        .typeQuestion("radio")
                        .options(Arrays.asList(
                                ClassifyQuestionDto.OptionItem.builder()
                                        .id(3)
                                        .optionText("Có")
                                        .optionValue("true")
                                        .build(),
                                ClassifyQuestionDto.OptionItem.builder()
                                        .id(4)
                                        .optionText("Không")
                                        .optionValue("false")
                                        .build()
                        ))
                        .build(),

                ClassifyQuestionDto.builder()
                        .questionId(Q_TREATED_BEFORE)
                        .questionText("Bạn đã từng điều trị đợt bệnh này trước khi đến khám chưa?")
                        .typeQuestion("radio")
                        .options(Arrays.asList(
                                ClassifyQuestionDto.OptionItem.builder()
                                        .id(5)
                                        .optionText("Có")
                                        .optionValue("true")
                                        .build(),
                                ClassifyQuestionDto.OptionItem.builder()
                                        .id(6)
                                        .optionText("Không")
                                        .optionValue("false")
                                        .build()
                        ))
                        .build(),

                ClassifyQuestionDto.builder()
                        .questionId(Q_FAMILY_HISTORY)
                        .questionText("Tiền sử gia đình có ai mắc bệnh lý dị ứng hoặc mề đay không?")
                        .typeQuestion("radio")
                        .options(Arrays.asList(
                                ClassifyQuestionDto.OptionItem.builder()
                                        .id(7)
                                        .optionText("Có")
                                        .optionValue("Co")
                                        .build(),
                                ClassifyQuestionDto.OptionItem.builder()
                                        .id(8)
                                        .optionText("Không")
                                        .optionValue("Khong")
                                        .build(),
                                ClassifyQuestionDto.OptionItem.builder()
                                        .id(9)
                                        .optionText("Không rõ")
                                        .optionValue("Khong ro")
                                        .build()
                        ))
                        .build()
        );

        return ClassifyQuestionsResponseDto.builder()
                .code("1000")
                .message("Thành công")
                .data(questions)
                .build();
    }

    // =========================================================================
    // 2. medrec02: Xử lý câu trả lời & trả về template_id tương ứng
    // Endpoint: POST /api/v1/medical_records/classify
    // =========================================================================
    public ClassifyRecordResponseDto classifyRecord(ClassifyRecordRequestDto request) {
        // Kiểm tra request rỗng hoặc mảng answers rỗng -> Mã lỗi 4006
        if (request == null || request.getAnswers() == null || request.getAnswers().isEmpty()) {
            return buildIncompleteResponse();
        }

        // Tìm câu trả lời xác định thời gian khởi phát (câu hỏi bắt buộc để phân loại)
        ClassifyRecordRequestDto.AnswerItem durationAns = request.getAnswers().stream()
                .filter(a -> Q_DURATION_WEEKS.equalsIgnoreCase(a.getQuestionId())
                        && a.getOptionValue() != null
                        && !a.getOptionValue().trim().isEmpty())
                .findFirst()
                .orElse(null);

        // Thiếu lựa chọn phân loại bắt buộc -> Mã lỗi 4006
        if (durationAns == null) {
            return buildIncompleteResponse();
        }

        boolean isChronic = false;

        // Đánh giá thời gian khởi phát: >= 6 tuần là mạn tính
        try {
            double weeks = Double.parseDouble(durationAns.getOptionValue().trim());
            if (weeks >= 6.0) {
                isChronic = true;
            }
        } catch (NumberFormatException ignored) {}

        // Kiểm tra tiền sử từng có đợt tương tự trước đây
        for (ClassifyRecordRequestDto.AnswerItem ans : request.getAnswers()) {
            if (Q_PRIOR_EPISODES.equalsIgnoreCase(ans.getQuestionId())
                    && "true".equalsIgnoreCase(ans.getOptionValue())) {
                isChronic = true;
                break;
            }
        }

        String templateId = isChronic ? TMPL_URT_CHRONIC : TMPL_URT_ACUTE;
        String templateName = isChronic ? "Mẫu bệnh án Mề đay Mạn tính" : "Mẫu bệnh án Mề đay Cấp tính";

        ClassifyRecordResponseDto.TemplateData data = ClassifyRecordResponseDto.TemplateData.builder()
                .templateId(templateId)
                .templateName(templateName)
                .structure(buildTemplateStructure(templateId))
                .build();

        return ClassifyRecordResponseDto.builder()
                .code("1000")
                .message("Thành công")
                .data(data)
                .build();
    }

    private ClassifyRecordResponseDto buildIncompleteResponse() {
        return ClassifyRecordResponseDto.builder()
                .code("4006")
                .message("Classification options incomplete.")
                .data(null)
                .build();
    }

    // =========================================================================
    // Cấu trúc Template mapping với các bảng vật lý tương ứng trong DB
    // =========================================================================
    public Map<String, Object> buildTemplateStructure(String templateId) {
        Map<String, Object> structure = new LinkedHashMap<>();
        structure.put("template_id", templateId);

        if (TMPL_URT_CHRONIC.equals(templateId)) {
            structure.put("sections", List.of(
                    Map.of(
                            "section_id", "benh_su_truoc",
                            "title", "Bệnh sử các đợt bệnh trước",
                            "fields", List.of("tung_co_dot_tuong_tu", "tuoi_khoi_phat", "so_dot_truoc", "da_tung_nhap_vien")
                    ),
                    Map.of(
                            "section_id", "dac_diem_san_phu",
                            "title", "Đặc điểm sẩn phù & Phù mạch mạn",
                            "fields", List.of("hien_tai_co_san_phu", "trong_dot_co_phu_mach", "diem_aas7")
                    ),
                    Map.of(
                            "section_id", "nghiem_phap",
                            "title", "Nghiệm pháp kích thích",
                            "fields", List.of("ngung_khang_histamine", "thoi_gian_ngung_histamine")
                    )
            ));
        } else {
            structure.put("sections", List.of(
                    Map.of(
                            "section_id", "sinh_ton_trieu_chung",
                            "title", "Dấu hiệu sinh tồn & Triệu chứng toàn thân",
                            "fields", List.of("tri_giac", "nhiet_do", "huyet_ap_tam_thu", "huyet_ap_tam_truong", "nhap_vien")
                    ),
                    Map.of(
                            "section_id", "ton_thuong_cap",
                            "title", "Chi tiết tổn thương cấp tính",
                            "fields", List.of("dang_ton_thuong", "so_luong_san_phu", "kich_thuoc_max_mm", "vi_tri")
                    ),
                    Map.of(
                            "section_id", "tinh_trang_nhiem_trung",
                            "title", "Tình trạng nhiễm trùng đi kèm",
                            "fields", List.of("ho_hap_ho", "ho_hap_dau_hong", "tiet_nieu_tieu_buot")
                    )
            ));
        }
        return structure;
    }

    @Transactional
    public CreateMedicalRecordResponseDto createMedicalRecord(CreateMedicalRecordRequestDto req) {
        if (req == null || req.getPatientId() == null) {
            throw new IllegalArgumentException("patient_id is required");
        }

        String templateId = req.getTemplateId();
        String loaiBenhAn;
        if ("TMPL_URT_ACUTE".equalsIgnoreCase(templateId)) {
            loaiBenhAn = "CAP";
        } else if ("TMPL_URT_CHRONIC".equalsIgnoreCase(templateId)) {
            loaiBenhAn = "MAN";
        } else {
            return CreateMedicalRecordResponseDto.builder()
                    .code("4101")
                    .message("Template not found")
                    .data(null)
                    .build();
        }

        Benhnhan benhNhan = benhnhanRepository.findById(req.getPatientId())
                .orElseThrow(() -> new IllegalArgumentException("Không tìm thấy bệnh nhân có ID: " + req.getPatientId()));

        Hosobenhan hoSo = Hosobenhan.builder()
                .benhnhan(benhNhan)
                .loaiBenhAn(loaiBenhAn)
                .trangThai("pending")
                .thoiDiemKhamLanDau(OffsetDateTime.now())
                .build();

        // Bóc tách mảng answers để lưu vào các cột tương ứng trong CSDL
        if (req.getAnswers() != null) {
            for (CreateMedicalRecordRequestDto.AnswerItem ans : req.getAnswers()) {
                String qId = ans.getQuestionId();
                String val = ans.getAnswerText() != null ? ans.getAnswerText()
                        : (ans.getAnswerValue() != null ? ans.getAnswerValue().toString() : null);

                if (qId != null && val != null) {
                    if (qId.contains(Q_DURATION_WEEKS)) {
                        try {
                            hoSo.setThoiGianKhoiPhatTuan(new java.math.BigDecimal(val.trim()));
                        } catch (Exception ignored) {}
                    } else if (qId.contains(Q_TREATED_BEFORE)) {
                        hoSo.setDaDieuTriTruocKham(Boolean.parseBoolean(val.trim()) || "Có".equalsIgnoreCase(val.trim()));
                    } else if (qId.contains(Q_FAMILY_HISTORY)) {
                        if (val.contains("Co") || val.contains("Có")) {
                            hoSo.setTienSuGiaDinh("Co");
                        } else if (val.contains("Khong") || val.contains("Không")) {
                            hoSo.setTienSuGiaDinh("Khong");
                        } else {
                            hoSo.setTienSuGiaDinh("Khong ro");
                        }
                    }
                }
            }
        }

        // Gắn subtype để Hibernate Cascade tự động lưu cùng bảng cha
        if ("CAP".equals(loaiBenhAn)) {
            hoSo.setBenhanmaydaycap(Benhanmaydaycap.builder().hoSoBenhAn(hoSo).build());
        } else {
            hoSo.setBenhanmaydayman(Benhanmaydayman.builder().hoSoBenhAn(hoSo).build());
        }

        Hosobenhan savedHoSo = hosobenhanRepository.save(hoSo);

        return CreateMedicalRecordResponseDto.builder()
                .code("1001")
                .message("Tạo hồ sơ bệnh án thành công")
                .data(CreateMedicalRecordResponseDto.MedicalRecordData.builder()
                        .medicalRecordId(savedHoSo.getMaHoSo())
                        .patientId(benhNhan.getMaBenhNhan())
                        .templateId(templateId)
                        .status(savedHoSo.getTrangThai())
                        .createdAt(savedHoSo.getThoiDiemKhamLanDau().toString())
                        .build())
                .build();
    }








}
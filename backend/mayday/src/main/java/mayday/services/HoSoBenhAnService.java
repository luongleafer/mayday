package mayday.services;

import lombok.RequiredArgsConstructor;
import mayday.dto.ClassifyQuestionDto;
import mayday.dto.MedicalRecord;
import mayday.dto.MedicalRecordQuery;
import mayday.dto.MedicalRecordSummary;
import mayday.dto.requests.ClassifyRecordRequestDto;
import mayday.dto.requests.CreateMedicalRecordRequestDto;
import mayday.dto.responses.ApiResponse;
import mayday.dto.responses.BenhAnResponse;
import mayday.dto.responses.ChotPhienKhamResponse;
import mayday.dto.responses.ClassifyQuestionsResponseDto;
import mayday.dto.responses.ClassifyRecordResponseDto;
import mayday.dto.responses.CreateMedicalRecordResponseDto;
import mayday.dto.responses.NhanPhienKhamResponse;
import mayday.entities.hosobenhan.BenhAnMayDayCap;
import mayday.entities.hosobenhan.BenhAnMayDayMan;
import mayday.entities.hosobenhan.HoSoBenhAn;
import mayday.entities.nguoi.BenhNhan;
import mayday.entities.phienkham.PhienKham;
import mayday.exceptions.MedicalRecordException;
import mayday.repositories.BenhNhanRepository;
import mayday.repositories.HoSoBenhAnRepository;
import mayday.repositories.MedicalRecordRepository;
import mayday.repositories.PhienKhamRepository;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class HoSoBenhAnService {
    private static final Set<String> STAFF = Set.of("ROLE_ADMIN", "ROLE_DOCTOR", "ROLE_RECEPTIONIST");
    private static final Set<String> TERMINAL = Set.of("completed", "cancelled");

    public static final String TMPL_URT_ACUTE = "TMPL_URT_ACUTE";
    public static final String TMPL_URT_CHRONIC = "TMPL_URT_CHRONIC";

    // Khóa định danh câu hỏi đồng bộ giữa medrec01 và medrec02
    public static final String Q_DURATION_WEEKS = "duration_weeks";
    public static final String Q_PRIOR_EPISODES = "has_prior_episodes";
    public static final String Q_TREATED_BEFORE = "treated_before";
    public static final String Q_FAMILY_HISTORY = "family_history";

    private final BenhNhanRepository benhNhanRepository;
    private final HoSoBenhAnRepository hoSoBenhAnRepository;
    private final PhienKhamRepository phienKhamRepository;
    private final MedicalRecordRepository medicalRecordRepository;

    // =========================================================================
    // medrec01: get_medicalrecords_classifyquestions
    // Endpoint: GET /api/v1/medical_records/classify-questions
    // =========================================================================
    public ClassifyQuestionsResponseDto getMedicalrecordsClassifyquestions() {
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
    // medrec02: set_medicalrecords_classify
    // Endpoint: POST /api/v1/medical_records/classify
    // =========================================================================
    public ClassifyRecordResponseDto setMedicalrecordsClassify(ClassifyRecordRequestDto request, Authentication user) {
        requireStaff(user);
        if (request == null || request.getAnswers() == null || request.getAnswers().isEmpty()) {
            return buildIncompleteResponse();
        }

        ClassifyRecordRequestDto.AnswerItem durationAns = request.getAnswers().stream()
                .filter(a -> Q_DURATION_WEEKS.equalsIgnoreCase(a.getQuestionId())
                        && a.getOptionValue() != null
                        && !a.getOptionValue().trim().isEmpty())
                .findFirst()
                .orElse(null);

        if (durationAns == null) {
            return buildIncompleteResponse();
        }

        boolean isChronic = false;

        try {
            double weeks = Double.parseDouble(durationAns.getOptionValue().trim());
            if (weeks >= 6.0) {
                isChronic = true;
            }
        } catch (NumberFormatException exception) {
            throw new IllegalArgumentException("duration_weeks must be a number");
        }

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

    // =========================================================================
    // medrec03: set_medicalrecords_create
    // Endpoint: POST /api/v1/medical_records
    // =========================================================================
    @Transactional
    public CreateMedicalRecordResponseDto setMedicalrecordsCreate(CreateMedicalRecordRequestDto req,
            Authentication user) {
        requireStaff(user);
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

        BenhNhan benhNhan = benhNhanRepository.findById(req.getPatientId())
                .orElseThrow(() -> new IllegalArgumentException("Không tìm thấy bệnh nhân có ID: " + req.getPatientId()));

        HoSoBenhAn hoSo = HoSoBenhAn.builder()
                .benhNhan(benhNhan)
                .loaiBenhAn(loaiBenhAn)
                .trangThai("pending")
                .thoiDiemKhamLanDau(OffsetDateTime.now())
                .build();

        if (req.getAnswers() != null) {
            for (CreateMedicalRecordRequestDto.AnswerItem ans : req.getAnswers()) {
                String qId = ans.getQuestionId();
                String val = ans.getAnswerText() != null ? ans.getAnswerText()
                        : (ans.getAnswerValue() != null ? ans.getAnswerValue().toString() : null);

                if (qId != null && val != null) {
                    if (qId.contains(Q_DURATION_WEEKS)) {
                        try {
                            hoSo.setThoiGianKhoiPhatTuan(new BigDecimal(val.trim()));
                        } catch (NumberFormatException exception) {
                            throw new IllegalArgumentException("duration_weeks must be a number");
                        }
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

        if ("CAP".equals(loaiBenhAn)) {
            hoSo.setBenhAnMayDayCap(BenhAnMayDayCap.builder().hoSoBenhAn(hoSo).build());
        } else {
            hoSo.setBenhAnMayDayMan(BenhAnMayDayMan.builder().hoSoBenhAn(hoSo).build());
        }

        HoSoBenhAn savedHoSo = hoSoBenhAnRepository.save(hoSo);

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

    // =========================================================================
    // medrec04: get_medicalrecords_all
    // Endpoint: GET /api/v1/medical_records/all
    // =========================================================================
    @Transactional(readOnly = true)
    public List<MedicalRecord> getMedicalrecordsAll(MedicalRecordQuery query, Authentication user) {
        if (!isStaff(user)) {
            throw MedicalRecordException.forbidden();
        }
        return medicalRecordRepository.findAll(query);
    }

    // =========================================================================
    // medrec05: get_medicalrecords_id
    // Endpoint: GET /api/v1/medical_records/{id}
    // =========================================================================
    @Transactional(readOnly = true)
    public MedicalRecord getMedicalrecordsId(long id, Authentication user) {
        if (id <= 0) {
            throw MedicalRecordException.invalid("id must be positive");
        }
        MedicalRecord record = medicalRecordRepository.findById(id).orElseThrow(MedicalRecordException::notFound);
        requirePatientAccess(record.patient_id(), user);
        return record;
    }

    // =========================================================================
    // medrec06: get_medicalrecords_summary
    // Endpoint: GET /api/v1/medical_records/{id}/summary
    // =========================================================================
    @Transactional(readOnly = true)
    public MedicalRecordSummary getMedicalrecordsSummary(long id, Authentication user) {
        getMedicalrecordsId(id, user);
        return medicalRecordRepository.findSummary(id).orElseThrow(MedicalRecordException::notFound);
    }

    // =========================================================================
    // medrec13: get_history_medicalrecords
    // Endpoint: GET /api/v1/medical_records/patients/{id}/history
    // =========================================================================
    @Transactional(readOnly = true)
    public List<MedicalRecord> getHistoryMedicalrecords(MedicalRecordQuery query, Authentication user) {
        requirePatientAccess(query.patientId(), user);
        return medicalRecordRepository.findAll(query);
    }

    // =========================================================================
    // medrec07: set_accept_medicalrecords
    // Endpoint: POST /api/v1/medical_records/{id}/accept
    // =========================================================================
    @Transactional
    public ApiResponse<NhanPhienKhamResponse> setAcceptMedicalrecords(Long maPhienKham, Authentication user) {
        requireStaff(user);
        PhienKham phienKham = phienKhamRepository.findById(maPhienKham).orElse(null);
        if (phienKham == null) return new ApiResponse<>("4001", "Medical record not found", null);
        HoSoBenhAn benhAn = phienKham.getHoSoBenhAn();
        if ("in_progress".equals(benhAn.getTrangThai()))
            return new ApiResponse<>("4005", "Already accepted by another doctor", null);
        if (!"pending".equals(benhAn.getTrangThai()))
            return new ApiResponse<>("4004", "Medical record not in pending status", null);
        benhAn.setTrangThai("in_progress");
        hoSoBenhAnRepository.save(benhAn);
        // TODO: cập nhật mã bác sĩ trong phiên khám tùy theo người dùng gọi API
        return ApiResponse.ok(new NhanPhienKhamResponse(benhAn));
    }

    // =========================================================================
    // medrec11: credit_medicalrecords
    // Endpoint: PUT /api/v1/medical_records/{id}
    // =========================================================================
    @Transactional
    public ApiResponse<ChotPhienKhamResponse> creditMedicalrecords(Long maPhienKham, String trangThai,
            String chiDan, Authentication user) {
        requireStaff(user);
        PhienKham phienKham = phienKhamRepository.findById(maPhienKham).orElse(null);
        if (phienKham == null) return new ApiResponse<>("4001", "Medical record not found", null);
        HoSoBenhAn benhAn = phienKham.getHoSoBenhAn();
        if (!TERMINAL.contains(trangThai) || !"in_progress".equals(benhAn.getTrangThai())) {
            throw MedicalRecordException.invalid("status must transition from in_progress to completed or cancelled");
        }
        benhAn.setTrangThai(trangThai);
        hoSoBenhAnRepository.save(benhAn);
        phienKham.setDanDo(chiDan);
        phienKhamRepository.save(phienKham);
        return ApiResponse.ok(new ChotPhienKhamResponse(phienKham));
    }

    // =========================================================================
    // medrec14: delete_medicalrecords
    // Endpoint: DELETE /api/v1/medical_records/{id}
    // =========================================================================
    @Transactional
    public ApiResponse<Void> deleteMedicalrecords(Long maBenhAn, Authentication user) {
        requireAdmin(user);
        HoSoBenhAn benhAn = hoSoBenhAnRepository.findById(maBenhAn)
                .orElseThrow(MedicalRecordException::notFound);
        hoSoBenhAnRepository.delete(benhAn);
        return ApiResponse.ok(null);
    }

    // =========================================================================
    // medrec08: get_medicalrecords_answers
    // Endpoint: GET /api/v1/medical_records/{id}/answers
    // =========================================================================
    @Transactional(readOnly = true)
    public ApiResponse<BenhAnResponse> getMedicalrecordsAnswers(Long maBenhAn, Authentication user) {
        HoSoBenhAn benhAn = hoSoBenhAnRepository.findById(maBenhAn).orElse(null);
        if (benhAn == null) return new ApiResponse<>("4001", "Medical record not found", null);
        if (!isStaff(user) && (!hasPatientRole(user)
                || !String.valueOf(benhAn.getMaBenhNhan()).equals(user.getName()))) {
            throw MedicalRecordException.forbidden();
        }
        return ApiResponse.ok(new BenhAnResponse(benhAn));
    }

    private void requirePatientAccess(Long patientId, Authentication user) {
        if (isStaff(user)) {
            return;
        }
        if (!hasPatientRole(user) || !String.valueOf(patientId).equals(user.getName())) {
            throw MedicalRecordException.forbidden();
        }
    }

    private void requireStaff(Authentication user) {
        if (!isStaff(user)) {
            throw MedicalRecordException.forbidden();
        }
    }

    private void requireAdmin(Authentication user) {
        if (user == null || user.getAuthorities().stream()
                .noneMatch(a -> "ROLE_ADMIN".equals(a.getAuthority()))) {
            throw MedicalRecordException.forbidden();
        }
    }

    private boolean isStaff(Authentication user) {
        return user != null && user.getAuthorities().stream()
                .anyMatch(a -> STAFF.contains(a.getAuthority()));
    }

    private boolean hasPatientRole(Authentication user) {
        return user != null && user.getAuthorities().stream()
                .anyMatch(a -> "ROLE_PATIENT".equals(a.getAuthority()));
    }
}

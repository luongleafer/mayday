# Từ điển thuật ngữ chuyên ngành & Ánh xạ đặt tên (DrMayDay Backend)

Tài liệu này quy định bảng tra cứu thuật ngữ chuyên ngành Y tế / Nghiệp vụ hệ thống **DrMayDay**, ánh xạ giữa **Bảng CSDL (`snake_case`) — Lớp Entity (`PascalCase` tiếng Việt không dấu) — Thuật ngữ Tiếng Anh**, cũng như danh sách chuẩn hóa **Tên hàm xử lý API (Controller / Service Handler Methods)** theo `SLIDE 1` – `SLIDE 10`.

---

## 1. Thực thể CSDL & Thuật ngữ Nghiệp vụ Cốt lõi (Entities & Domain Concepts)

| Tiếng Việt | Tên bảng CSDL | Tên lớp Entity (`PascalCase`) | Tiếng Anh (DTO / API / Thuật ngữ) |
| :--- | :--- | :--- | :--- |
| Người (Thông tin cá nhân) | `nguoi` | `Nguoi` | `Person` / `UserProfile` |
| Tài khoản | `taikhoan` | `TaiKhoan` | `Account` / `UserAccount` |
| Vai trò | `vaitro` | `VaiTro` | `Role` |
| Nhân viên y tế | `nhanvien` | `NhanVien` | `Staff` / `MedicalStaff` / `Doctor` |
| Bệnh nhân | `benhnhan` | `BenhNhan` | `Patient` |
| Chi nhánh / Cơ sở khám | `chinhanh` | `ChiNhanh` | `Branch` |
| Khoa / Phòng ban | `khoaphong` | `KhoaPhong` | `Department` |
| Phòng khám | `phongkham` | `PhongKham` | `Room` / `ClinicRoom` |
| Hồ sơ bệnh án | `hosobenhan` | `HoSoBenhAn` | `MedicalRecord` |
| Bệnh án mày đay cấp tính | `benhanmaydaycap` | `BenhAnMayDayCap` | `AcuteUrticariaRecord` |
| Bệnh án mày đay mạn tính | `benhanmaydayman` | `BenhAnMayDayMan` | `ChronicUrticariaRecord` |
| Hoàn cảnh khởi phát dị ứng | `hoancanhkhoiphatdiung` | `HoanCanhKhoiPhatDiUng` | `AllergyOnsetContext` |
| Tình trạng nhiễm trùng | `tinhtrangnhiemtrung` | `TinhTrangNhiemTrung` | `InfectionStatus` |
| Tiền sử bản thân | `tiensubanthan` | `TienSuBanThan` | `PersonalMedicalHistory` |
| Bệnh sử các đợt bệnh trước | `benhsucacdotbenhtruoc` | `BenhSuCacDotBenhTruoc` | `PriorEpisodeHistory` |
| Phiên khám (Lượt khám) | `phienkham` | `PhienKham` | `ConsultationSession` / `VisitSession` |
| Phiên khám mày đay cấp | `phienkhamcap` | `PhienKhamCap` | `AcuteConsultationSession` |
| Phiên khám mày đay mạn | `phienkhamman` | `PhienKhamMan` | `ChronicConsultationSession` |
| Dấu hiệu sinh tồn | `dauhieusinhton` | `DauHieuSinhTon` | `VitalSigns` |
| Chi tiết tổn thương cấp tính | `chitiettonthuongcaptinh` | `ChiTietTonThuongCapTinh` | `AcuteLesionDetail` |
| Chi tiết tổn thương sẩn phù | `chitiettonthuongsanphu` | `ChiTietTonThuongSanPhu` | `WhealLesionDetail` |
| Đơn thuốc | `donthuoc` | `DonThuoc` | `Prescription` |
| Chỉ định xét nghiệm | `chidinhxetnghiem` | `ChiDinhXetNghiem` | `LabOrder` / `MedicalTestOrder` |
| Kết quả xét nghiệm | `ketquaxetnghiem` | `KetQuaXetNghiem` | `LabResult` / `TestResult` |
| Ảnh bệnh lý / Lâm sàng | `anhbenhly` | `AnhBenhLy` | `ClinicalImage` / `MedicalImage` |
| Bộ câu hỏi theo dõi (UCT / UAS7) | `bocauhoitheodoi` | `BoCauHoiTheoDoi` | `TrackingQuestionnaire` (`UCT`, `UAS7`) |
| Thông báo | `thongbao` | `ThongBao` | `Notification` |

---

## 2. Thuật ngữ Y khoa Chuyên sâu (Bệnh lý Mày đay & Dị ứng)

| Tiếng Việt | Tên trường Entity (`camelCase`) | Tiếng Anh chuyên ngành |
| :--- | :--- | :--- |
| Mày đay (Mề đay) | `mayDay` | Urticaria |
| Mày đay cấp tính | `mayDayCap` (`CAP`) | Acute Urticaria (`TMPL_URT_ACUTE`) |
| Mày đay mạn tính | `mayDayMan` (`MAN`) | Chronic Urticaria (`TMPL_URT_CHRONIC`) |
| Mày đay mạn tính tự phát | `csu` | Chronic Spontaneous Urticaria (`CSU`) |
| Mày đay mạn tính cảm ứng | `cindu` | Chronic Inducible Urticaria (`CIndU`) |
| Sẩn phù | `sanPhu` | Wheal / Hive |
| Phù mạch | `phuMach` | Angioedema |
| Phản vệ | `phanVe` | Anaphylaxis |
| Sốc phản vệ | `socPhanVe` | Anaphylactic Shock |
| Chứng da vẽ nổi | `chungDaVeNoi` | Symptomatic Dermographism |
| Mày đay do lạnh | `mayDayDoLanh` | Cold Urticaria |
| Mày đay do nước | `mayDayDoNuoc` | Aquagenic Urticaria |
| Mày đay do ánh sáng | `mayDayDoAnhSang` | Solar Urticaria |
| Mày đay Cholinergic | `mayDayCholinergic` | Cholinergic Urticaria |
| Mày đay Adrenergic | `mayDayAdrenergic` | Adrenergic Urticaria |
| Kháng Histamine | `khangHistamine` | Antihistamine |
| Nghiệm pháp kích thích | `nghiemPhap` | Provocation Test |
| Huyết thanh tự thân (ASST) | `huyetThanhTuThan` / `luuHuyetThanh` | Autologous Serum Skin Test (ASST) / Serum Storage |
| Điểm kiểm soát mày đay (UCT) | `diemUct` | Urticaria Control Test (`UCT`) |
| Điểm hoạt động mày đay 7 ngày (UAS7) | `diemUas7` | Urticaria Activity Score 7 (`UAS7`) |
| Điểm hoạt động phù mạch 7 ngày (AAS7) | `diemAas7` | Angioedema Activity Score 7 (`AAS7`) |
| Tri giác | `triGiac` | Consciousness |
| Nhiệt độ / Sốt | `nhietDo` / `sot` (`sotNhietDoC`) | Body Temperature / Fever |
| Huyết áp tâm thu / tâm trương | `huyetApTamThu` / `huyetApTamTruong` | Systolic / Diastolic Blood Pressure |
| Nhịp tim (Mạch) | `nhipTim` | Heart Rate / Pulse |
| Nhịp thở | `nhipTho` | Respiratory Rate |
| SpO2 | `spo2` | Oxygen Saturation (`SpO2`) |
| Tiền sử gia đình | `tienSuGiaDinh` | Family Medical History |
| Tiền sử dị ứng | `tienSuDiUng` | Allergy History |
| Chẩn đoán tuyến trước | `chanDoanTuyenTruoc` | Referral / Prior Facility Diagnosis |
| Chẩn đoán ban đầu | `chanDoanBanDauPhongKham` | Initial Clinic Diagnosis |
| Dặn dò / Hướng xử trí | `danDo` / `huongXuTriLanSau` | Doctor's Advice / Follow-up Plan |

---

## 3. Quy chuẩn Tên hàm Xử lý theo Danh mục API (`SLIDE 2` – `SLIDE 10`)

Theo quy tắc tại [`NAMING_CONVENTION.md`](./NAMING_CONVENTION.md), tên phương thức xử lý trong `Controller` và `Service` được chuyển từ tên `snake_case` của API trong Slide sang `camelCase`:

### 3.1. Nhóm Xác thực & Tài khoản (`SLIDE 2` — Auth)
| Mã API | Tên API (`snake_case`) | Tên hàm Handler (`camelCase`) | Endpoint |
| :--- | :--- | :--- | :--- |
| `auth01` | `login` | `login` | `POST /api/v1/auth/login` |
| `auth02` | `logout` | `logout` | `POST /api/v1/auth/logout` |
| `auth03` | `refresh_token` | `refreshToken` | `POST /api/v1/auth/refresh` |
| `auth04` | `change_password` | `changePassword` | `POST /api/v1/auth/change-password` |
| `auth05` | `get_me` | `getMe` | `GET /api/v1/auth/me` |
| `auth06` | `update_me` | `updateMe` | `PUT /api/v1/auth/me` |
| `auth07` | `update_avatar` | `updateAvatar` | `PUT /api/v1/auth/me/avatar` |

### 3.2. Nhóm Quản trị Người dùng & Phân quyền (`SLIDE 3` — Admin Users & Roles)
| Mã API | Tên API (`snake_case`) | Tên hàm Handler (`camelCase`) | Endpoint |
| :--- | :--- | :--- | :--- |
| `user01` | `get_list_users` | `getListUsers` | `GET /api/v1/admin/users` |
| `user02` | `get_user_detail` | `getUserDetail` | `GET /api/v1/admin/users/{id}` |
| `user03` | `create_user` | `createUser` | `POST /api/v1/admin/users` |
| `user04` | `update_user` | `updateUser` | `PUT /api/v1/admin/users/{id}` |
| `user05` | `delete_user` | `deleteUser` | `DELETE /api/v1/admin/users/{id}` |
| `user06` | `reset_user_password` | `resetUserPassword` | `PUT /api/v1/admin/users/{id}/reset-password` |
| `role01` | `get_list_roles` | `getListRoles` | `GET /api/v1/roles` |
| `role02` | `get_role_detail` | `getRoleDetail` | `GET /api/v1/roles/{id}` |

### 3.3. Nhóm Cơ cấu Tổ chức Phòng khám (`SLIDE 4` — Branches, Departments, Rooms)
| Mã API | Tên API (`snake_case`) | Tên hàm Handler (`camelCase`) | Endpoint |
| :--- | :--- | :--- | :--- |
| `branch01` | `set_branch` | `setBranch` | `POST /api/v1/branches` |
| `branch02` | `get_list_branches` | `getListBranches` | `GET /api/v1/branches` |
| `branch03` | `get_branch` | `getBranch` | `GET /api/v1/branches/{id}` |
| `branch04` | `edit_branch` | `editBranch` | `PUT /api/v1/branches/{id}` |
| `branch05` | `delete_branch` | `deleteBranch` | `DELETE /api/v1/branches/{id}` |
| `dept01` | `set_departments` | `setDepartments` | `POST /api/v1/departments` |
| `dept02` | `get_list_departments` | `getListDepartments` | `GET /api/v1/departments` |
| `dept03` | `get_departments` | `getDepartments` | `GET /api/v1/departments/{id}` |
| `dept04` | `edit_departments` | `editDepartments` | `PUT /api/v1/departments/{id}` |
| `dept05` | `delete_departments` | `deleteDepartments` | `DELETE /api/v1/departments/{id}` |
| `room01` | `set_rooms` | `setRooms` | `POST /api/v1/rooms` |
| `room02` | `get_list_rooms` | `getListRooms` | `GET /api/v1/rooms` |
| `room03` | `get_rooms` | `getRooms` | `GET /api/v1/rooms/{id}` |
| `room04` | `edit_rooms` | `editRooms` | `PUT /api/v1/rooms/{id}` |
| `room05` | `delete_rooms` | `deleteRooms` | `DELETE /api/v1/rooms/{id}` |

### 3.4. Nhóm Quản lý Bệnh nhân & Hồ sơ Bệnh án (`SLIDE 5` — Doctor Patients & Medical Records)
| Mã API | Tên API (`snake_case`) | Tên hàm Handler (`camelCase`) | Endpoint |
| :--- | :--- | :--- | :--- |
| `docpat01` | `search_patients` | `searchPatients` | `GET /api/v1/doctor/patients` |
| `docpat02` | `create_patient` | `createPatient` | `POST /api/v1/doctor/patients` |
| `medrec01` | `get_medicalrecords_classifyquestions` | `getMedicalrecordsClassifyquestions` | `GET /api/v1/medical_records/classify-questions` |
| `medrec02` | `set_medicalrecords_classify` | `setMedicalrecordsClassify` | `POST /api/v1/medical_records/classify` |
| `medrec03` | `set_medicalrecords_create` | `setMedicalrecordsCreate` | `POST /api/v1/medical_records` |
| `medrec04` | `get_medicalrecords_all` | `getMedicalrecordsAll` | `GET /api/v1/medical_records/all` |
| `medrec05` | `get_medicalrecords_id` | `getMedicalrecordsId` | `GET /api/v1/medical_records/{id}` |
| `medrec06` | `get_medicalrecords_summary` | `getMedicalrecordsSummary` | `GET /api/v1/medical_records/{id}/summary` |
| `medrec07` | `set_accept_medicalrecords` | `setAcceptMedicalrecords` | `POST /api/v1/medical_records/{id}/accept` |
| `medrec08` | `get_medicalrecords_answers` | `getMedicalrecordsAnswers` | `GET /api/v1/medical_records/{id}/answers` |
| `medrec10` | `update_medicalrecords_answers` | `updateMedicalrecordsAnswers` | `PUT /api/v1/medical_records/{id}/answers` |
| `medrec11` | `credit_medicalrecords` | `creditMedicalrecords` | `PUT /api/v1/medical_records/{id}` |
| `medrec13` | `get_history_medicalrecords` | `getHistoryMedicalrecords` | `GET /api/v1/medical_records/patients/{id}/history` |
| `medrec14` | `delete_medicalrecords` | `deleteMedicalrecords` | `DELETE /api/v1/medical_records/{id}` |

### 3.5. Nhóm Đơn thuốc, Xét nghiệm, Ảnh bệnh lý, Theo dõi & Thông báo (`SLIDE 6` – `SLIDE 10`)
| Nhóm Slide | Tên API (`snake_case`) | Tên hàm Handler (`camelCase`) | Ý nghĩa |
| :--- | :--- | :--- | :--- |
| `SLIDE 6` (Đơn thuốc) | `create_prescription` | `createPrescription` | Tạo đơn thuốc cho hồ sơ/phiên khám |
| `SLIDE 6` (Đơn thuốc) | `get_prescription_by_record` | `getPrescriptionByRecord` | Lấy đơn thuốc theo hồ sơ bệnh án |
| `SLIDE 6` (Đơn thuốc) | `get_prescription_detail` | `getPrescriptionDetail` | Xem chi tiết đơn thuốc |
| `SLIDE 6` (Đơn thuốc) | `update_prescription` | `updatePrescription` | Cập nhật đơn thuốc |
| `SLIDE 6` (Đơn thuốc) | `delete_prescription` | `deletePrescription` | Xóa đơn thuốc |
| `SLIDE 7` (Xét nghiệm) | `create_lab_order` | `createLabOrder` | Tạo chỉ định xét nghiệm |
| `SLIDE 7` (Xét nghiệm) | `get_lab_orders_by_record` | `getLabOrdersByRecord` | Lấy danh sách xét nghiệm theo bệnh án |
| `SLIDE 7` (Xét nghiệm) | `update_lab_result` | `updateLabResult` | Cập nhật kết quả xét nghiệm |
| `SLIDE 8` (Ảnh bệnh lý) | `upload_medical_images` | `uploadMedicalImages` | Tải lên ảnh tổn thương da / xét nghiệm |
| `SLIDE 8` (Ảnh bệnh lý) | `get_medical_images` | `getMedicalImages` | Lấy danh sách ảnh của bệnh án |
| `SLIDE 8` (Ảnh bệnh lý) | `delete_medical_image` | `deleteMedicalImage` | Xóa ảnh bệnh lý |
| `SLIDE 9` (Theo dõi UCT/UAS7) | `submit_tracking_assessment` | `submitTrackingAssessment` | Gửi kết quả đánh giá UCT / UAS7 |
| `SLIDE 9` (Theo dõi UCT/UAS7) | `get_tracking_history` | `getTrackingHistory` | Xem lịch sử điểm theo dõi của bệnh nhân |
| `SLIDE 10` (Thông báo) | `get_list_notifications` | `getListNotifications` | Lấy danh sách thông báo của người dùng |
| `SLIDE 10` (Thông báo) | `mark_notification_read` | `markNotificationRead` | Đánh dấu đã đọc thông báo |
| `SLIDE 10` (Thông báo) | `mark_all_notifications_read` | `markAllNotificationsRead` | Đánh dấu đã đọc tất cả thông báo |

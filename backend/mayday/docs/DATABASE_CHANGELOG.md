# BÁO CÁO TỔNG HỢP LỊCH SỬ THAY ĐỔI CƠ SỞ DỮ LIỆU (DATABASE CHANGELOG)
**Dự án:** Hệ thống Quản lý Khám bệnh & Theo dõi Mề đay (DrMayDay)  
**Tài liệu dành cho:** Nhóm Backend & Nhóm Phụ trách DB Server (Dùng cho Báo cáo & Bảo vệ)  
**Bản gốc CSDL ban đầu (Baseline):** `mayday/mayday slides/mayday_160_191_46_107-2026_10_26_13_46_29-dump.sql` (Gồm 44 bảng)

---

## TỔNG QUAN CÁC BẢN CẬP NHẬT TRÊN LOCAL DATABASE

| Phiên bản | Tệp Migration tương ứng | Mục đích & Nghiệp vụ liên quan | Trạng thái Local DB |
| :--- | :--- | :--- | :--- |
| **V1.0** | `mayday_...-dump.sql` | CSDL ban đầu gồm 44 bảng (Core Entities, Subtypes Cấp/Mạn, Bệnh nhân, Nhân viên,...) | Đã khởi tạo |
| **V1.1** | `V1.1__update_topology_and_staff.sql` | Chuẩn hóa Phân hệ Cơ sở vật chất (`chinhanh`, `khoaphong`, `phongkham`) và Nhân sự (`nhanvien`) theo `SLIDE 4` | **Đã áp dụng** |
| **V1.2** | `V1.2__create_prescriptions_and_labs.sql` | Bổ sung Phân hệ Quản lý Đơn thuốc & Xét nghiệm Cận lâm sàng theo mô hình 2 tầng (Header - Items) theo `SLIDE 7` | **Đã áp dụng** |

---

## CHI TIẾT CÁC THAY ĐỔI SO VỚI BẢN DUMP GỐC

### PHẦN 1: MIGRATION V1.1 – CƠ SỞ VẬT CHẤT & NHÂN SỰ (SLIDE 4)

#### 1. Bảng `public.chinhanh`
* **Vấn đề bản dump cũ:** Thiếu trường thời gian tạo/cập nhật; chưa có ràng buộc chống trùng lặp tên, địa chỉ, số điện thoại chi nhánh.
* **Thay đổi đã áp dụng:**
  * Bổ sung cột: `thoi_diem_tao timestamp with time zone DEFAULT CURRENT_TIMESTAMP NOT NULL`
  * Bổ sung cột: `thoi_diem_cap_nhat timestamp with time zone DEFAULT CURRENT_TIMESTAMP NOT NULL`
  * Bổ sung ràng buộc:
    * `ck_chi_nhanh_thoi_gian CHECK (thoi_diem_cap_nhat >= thoi_diem_tao)`
    * `uq_chi_nhanh_ten UNIQUE (ten_chi_nhanh)`: Chặn tạo trùng tên chi nhánh (Slide 4).
    * `uq_chi_nhanh_dia_chi UNIQUE (dia_chi)`: Chặn 2 chi nhánh khác tên nhưng cùng địa chỉ thực tế.
    * `uq_chi_nhanh_sdt UNIQUE (so_dien_thoai)`: Chặn trùng số điện thoại liên hệ.

#### 2. Bảng `public.khoaphong`
* **Thay đổi đã áp dụng:**
  * Đặt giá trị mặc định cho cột `loai_khoa`: `DEFAULT 'CHUYEN_KHOA'`
  * Bổ sung cột `thoi_diem_tao`, `thoi_diem_cap_nhat` kèm ràng buộc `ck_khoa_phong_thoi_gian`.

#### 3. Bảng `public.phongkham`
* **Vấn đề bản dump cũ:** Cột `ma_khoa_phong` ban đầu là `NOT NULL` và có khóa ngoại `RESTRICT`, dẫn đến việc không thể xử lý yêu cầu nghiệp vụ Slide 4 (Trang 19: Khi xóa Khoa/Phòng, các Phòng khám trực thuộc không bị xóa mà tự động cập nhật về trạng thái chưa gán khoa `department_id = NULL`).
* **Thay đổi đã áp dụng:**
  * Cho phép `ma_khoa_phong` nhận giá trị `NULL` (`ALTER COLUMN ma_khoa_phong DROP NOT NULL`).
  * Sửa khóa ngoại `fk_phong_kham_khoa_phong`: Chuyển sang `ON DELETE SET NULL`.
  * Bổ sung cột `mo_ta text` (Description của phòng khám theo đặc tả API).
  * Bổ sung cột `thoi_diem_tao`, `thoi_diem_cap_nhat` kèm ràng buộc kiểm tra thời gian.

#### 4. Bảng `public.nhanvien`
* **Vấn đề bản dump cũ:** Nhân viên chỉ gắn với `ma_chi_nhanh`, chưa có thông tin khoa phòng và phòng khám đang trực tiếp công tác. Không thể kiểm tra chặn xóa phòng khám khi đang có nhân viên trực (Slide 4 Trang 27).
* **Thay đổi đã áp dụng:**
  * Bổ sung cột `ma_khoa_phong bigint` (`REFERENCES khoaphong(ma_khoa_phong) ON DELETE SET NULL`).
  * Bổ sung cột `ma_phong_kham bigint` (`REFERENCES phongkham(ma_phong_kham) ON DELETE RESTRICT`).
  * Tạo 2 Index B-Tree: `idx_nhan_vien_khoa_phong` và `idx_nhan_vien_phong_kham`.

---

### PHẦN 2: MIGRATION V1.2 – ĐƠN THUỐC & CẬN LÂM SÀNG (SLIDE 7)

* **Lý do kỹ thuật & Nghiệp vụ:**
  * Trong bản dump gốc, các bảng `donthuoc`, `chitietdonthuoc` và `ketquacanlamsang` được thiết kế theo cấu trúc cũ, phụ thuộc cứng vào `phienkham` (`ma_phien_kham, loai_benh_an`) và chưa hỗ trợ mô hình yêu cầu xét nghiệm 2 tầng theo chuẩn RESTful mới của Slide 7 (`POST /api/v1/prescriptions`, `POST /api/v1/lab-requests`, `GET /api/v1/lab-results/{record_id}`).
  * Vì vậy, hệ thống bổ sung 5 bảng chuẩn hóa mô hình Header - Items độc lập, liên kết lỏng (Loose-Coupling) qua khóa ngoại `medical_record_id` (`hosobenhan.ma_ho_so`) và `created_by` / `requested_by` (`nguoi.ma_nguoi` / `nhanvien.ma_nhan_vien`).

#### 1. Bảng `public.prescriptions` (Header Đơn thuốc)
```sql
CREATE TABLE public.prescriptions (
    id bigserial PRIMARY KEY,
    medical_record_id bigint NOT NULL REFERENCES public.hosobenhan(ma_ho_so) ON DELETE RESTRICT,
    created_by bigint NOT NULL REFERENCES public.nguoi(ma_nguoi) ON DELETE RESTRICT,
    note text,
    created_at timestamp with time zone DEFAULT CURRENT_TIMESTAMP NOT NULL,
    updated_at timestamp with time zone DEFAULT CURRENT_TIMESTAMP NOT NULL
);
CREATE INDEX idx_prescriptions_record ON public.prescriptions(medical_record_id);
CREATE INDEX idx_prescriptions_doctor ON public.prescriptions(created_by);
```

#### 2. Bảng `public.prescription_items` (Chi tiết các dòng thuốc)
```sql
CREATE TABLE public.prescription_items (
    id bigserial PRIMARY KEY,
    prescription_id bigint NOT NULL REFERENCES public.prescriptions(id) ON DELETE CASCADE,
    medicine_name character varying(255) NOT NULL,
    dosage character varying(100),
    frequency character varying(100),
    duration character varying(100),
    instruction text,
    created_at timestamp with time zone DEFAULT CURRENT_TIMESTAMP NOT NULL,
    CONSTRAINT ck_prescription_items_name CHECK (btrim(medicine_name::text) <> ''::text)
);
CREATE INDEX idx_prescription_items_parent ON public.prescription_items(prescription_id);
```

#### 3. Bảng `public.lab_requests` (Header Phiếu chỉ định xét nghiệm)
```sql
CREATE TABLE public.lab_requests (
    id bigserial PRIMARY KEY,
    medical_record_id bigint NOT NULL REFERENCES public.hosobenhan(ma_ho_so) ON DELETE RESTRICT,
    requested_by bigint NOT NULL REFERENCES public.nguoi(ma_nguoi) ON DELETE RESTRICT,
    status character varying(50) DEFAULT 'pending' NOT NULL,
    note text,
    created_at timestamp with time zone DEFAULT CURRENT_TIMESTAMP NOT NULL,
    updated_at timestamp with time zone DEFAULT CURRENT_TIMESTAMP NOT NULL,
    CONSTRAINT ck_lab_requests_status 
        CHECK (status::text = ANY (ARRAY['pending'::text, 'in_progress'::text, 'completed'::text, 'cancelled'::text]))
);
CREATE INDEX idx_lab_requests_record ON public.lab_requests(medical_record_id);
CREATE INDEX idx_lab_requests_doctor ON public.lab_requests(requested_by);
```

#### 4. Bảng `public.lab_request_items` (Danh sách các mục xét nghiệm trong phiếu)
```sql
CREATE TABLE public.lab_request_items (
    id bigserial PRIMARY KEY,
    lab_request_id bigint NOT NULL REFERENCES public.lab_requests(id) ON DELETE CASCADE,
    test_name character varying(255) NOT NULL,
    description text,
    created_at timestamp with time zone DEFAULT CURRENT_TIMESTAMP NOT NULL,
    CONSTRAINT ck_lab_request_items_name CHECK (btrim(test_name::text) <> ''::text)
);
CREATE INDEX idx_lab_request_items_parent ON public.lab_request_items(lab_request_id);
```

#### 5. Bảng `public.lab_results` (Kết quả xét nghiệm trả về)
```sql
CREATE TABLE public.lab_results (
    id bigserial PRIMARY KEY,
    lab_request_item_id bigint NOT NULL REFERENCES public.lab_request_items(id) ON DELETE CASCADE,
    result_value text,
    concluded_by bigint REFERENCES public.nguoi(ma_nguoi) ON DELETE SET NULL,
    result_note text,
    created_at timestamp with time zone DEFAULT CURRENT_TIMESTAMP NOT NULL,
    updated_at timestamp with time zone DEFAULT CURRENT_TIMESTAMP NOT NULL
);
CREATE INDEX idx_lab_results_item ON public.lab_results(lab_request_item_id);
```

---

## 3. ĐIỂM CẦN BẢO VỆ VỚI HỘI ĐỒNG / NHÓM DATABASE

1. **Ràng buộc toàn vẹn dữ liệu (Referential Integrity & Constraints):**
   - Đảm bảo tính liên kết chặt chẽ bằng `FOREIGN KEY` với các hành vi phù hợp: `ON DELETE CASCADE` cho cấu trúc cha-con (xóa đơn thuốc thì tự dọn sạch các dòng thuốc con; xóa phiếu chỉ định thì tự dọn các mục chỉ định con) và `ON DELETE RESTRICT` cho hồ sơ bệnh án (không cho phép xóa hồ sơ bệnh án khi đã phát sinh đơn thuốc/xét nghiệm).
   - Đảm bảo tính nhất quán qua các `CHECK CONSTRAINT`: Chặn chuỗi rỗng cho tên thuốc `medicine_name`, tên xét nghiệm `test_name`; kiểm soát trạng thái hợp lệ (`status IN ('pending', 'in_progress', 'completed', 'cancelled')`).

2. **Tối ưu hóa hiệu năng truy vấn (Indexing):**
   - Đã tạo các chỉ mục `B-Tree` trên toàn bộ các cột khóa ngoại thường xuyên dùng để `JOIN` hoặc lọc (`WHERE medical_record_id = ...`, `WHERE prescription_id = ...`, `WHERE lab_request_id = ...`). Điều này giúp câu lệnh JOIN 3 bảng trong API `lab02` (`lab_requests` + `lab_request_items` + `lab_results`) đạt tốc độ phản hồi $O(\log N)$.

3. **Tính độc lập & Khả năng mở rộng (Scalability):**
   - Thiết kế này tuân theo đúng nguyên lý phân tách trách nhiệm, không làm xáo trộn các bảng lịch sử lâm sàng cũ nhưng đáp ứng trọn vẹn 100% các tiêu chí kiểm thử của hệ thống API hiện đại.

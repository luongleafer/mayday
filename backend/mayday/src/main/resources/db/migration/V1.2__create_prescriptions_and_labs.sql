-- Migration V1.2: Bổ sung các bảng Quản lý Đơn thuốc & Cận lâm sàng theo Slide 7 (Prescriptions & Labs)
BEGIN;

-- 1. Bảng quản lý đơn thuốc (Header)
CREATE TABLE IF NOT EXISTS public.prescriptions (
    id bigserial PRIMARY KEY,
    medical_record_id bigint NOT NULL,
    created_by bigint NOT NULL,
    note text,
    created_at timestamp with time zone DEFAULT CURRENT_TIMESTAMP NOT NULL,
    updated_at timestamp with time zone DEFAULT CURRENT_TIMESTAMP NOT NULL,
    CONSTRAINT fk_prescriptions_medical_record 
        FOREIGN KEY (medical_record_id) REFERENCES public.hosobenhan(ma_ho_so) ON DELETE RESTRICT,
    CONSTRAINT fk_prescriptions_created_by 
        FOREIGN KEY (created_by) REFERENCES public.nguoi(ma_nguoi) ON DELETE RESTRICT
);

CREATE INDEX IF NOT EXISTS idx_prescriptions_record ON public.prescriptions(medical_record_id);
CREATE INDEX IF NOT EXISTS idx_prescriptions_doctor ON public.prescriptions(created_by);

-- 2. Bảng chi tiết các loại thuốc trong đơn (Items)
CREATE TABLE IF NOT EXISTS public.prescription_items (
    id bigserial PRIMARY KEY,
    prescription_id bigint NOT NULL,
    medicine_name character varying(255) NOT NULL,
    dosage character varying(100),
    frequency character varying(100),
    duration character varying(100),
    instruction text,
    created_at timestamp with time zone DEFAULT CURRENT_TIMESTAMP NOT NULL,
    CONSTRAINT fk_prescription_items_parent 
        FOREIGN KEY (prescription_id) REFERENCES public.prescriptions(id) ON DELETE CASCADE,
    CONSTRAINT ck_prescription_items_name CHECK (btrim(medicine_name::text) <> ''::text)
);

CREATE INDEX IF NOT EXISTS idx_prescription_items_parent ON public.prescription_items(prescription_id);

-- 3. Bảng yêu cầu xét nghiệm cận lâm sàng (Header)
CREATE TABLE IF NOT EXISTS public.lab_requests (
    id bigserial PRIMARY KEY,
    medical_record_id bigint NOT NULL,
    requested_by bigint NOT NULL,
    status character varying(50) DEFAULT 'pending' NOT NULL,
    note text,
    created_at timestamp with time zone DEFAULT CURRENT_TIMESTAMP NOT NULL,
    updated_at timestamp with time zone DEFAULT CURRENT_TIMESTAMP NOT NULL,
    CONSTRAINT fk_lab_requests_medical_record 
        FOREIGN KEY (medical_record_id) REFERENCES public.hosobenhan(ma_ho_so) ON DELETE RESTRICT,
    CONSTRAINT fk_lab_requests_doctor 
        FOREIGN KEY (requested_by) REFERENCES public.nguoi(ma_nguoi) ON DELETE RESTRICT,
    CONSTRAINT ck_lab_requests_status 
        CHECK (status::text = ANY (ARRAY['pending'::text, 'in_progress'::text, 'completed'::text, 'cancelled'::text]))
);

CREATE INDEX IF NOT EXISTS idx_lab_requests_record ON public.lab_requests(medical_record_id);
CREATE INDEX IF NOT EXISTS idx_lab_requests_doctor ON public.lab_requests(requested_by);

-- 4. Bảng chi tiết các mục chỉ định xét nghiệm (Items)
CREATE TABLE IF NOT EXISTS public.lab_request_items (
    id bigserial PRIMARY KEY,
    lab_request_id bigint NOT NULL,
    test_name character varying(255) NOT NULL,
    description text,
    created_at timestamp with time zone DEFAULT CURRENT_TIMESTAMP NOT NULL,
    CONSTRAINT fk_lab_request_items_parent 
        FOREIGN KEY (lab_request_id) REFERENCES public.lab_requests(id) ON DELETE CASCADE,
    CONSTRAINT ck_lab_request_items_name CHECK (btrim(test_name::text) <> ''::text)
);

CREATE INDEX IF NOT EXISTS idx_lab_request_items_parent ON public.lab_request_items(lab_request_id);

-- 5. Bảng lưu kết quả xét nghiệm thực tế
CREATE TABLE IF NOT EXISTS public.lab_results (
    id bigserial PRIMARY KEY,
    lab_request_item_id bigint NOT NULL,
    result_value text,
    concluded_by bigint,
    result_note text,
    created_at timestamp with time zone DEFAULT CURRENT_TIMESTAMP NOT NULL,
    updated_at timestamp with time zone DEFAULT CURRENT_TIMESTAMP NOT NULL,
    CONSTRAINT fk_lab_results_item 
        FOREIGN KEY (lab_request_item_id) REFERENCES public.lab_request_items(id) ON DELETE CASCADE,
    CONSTRAINT fk_lab_results_doctor 
        FOREIGN KEY (concluded_by) REFERENCES public.nguoi(ma_nguoi) ON DELETE SET NULL
);

CREATE INDEX IF NOT EXISTS idx_lab_results_item ON public.lab_results(lab_request_item_id);

COMMIT;

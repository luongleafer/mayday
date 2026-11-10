-- Migration V1.1: Bổ sung timestamps, mô tả phòng khám, liên kết nhân viên và cập nhật cơ chế xóa phòng khám theo Slide 4
BEGIN;

-- 1. Bổ sung thoi_diem_tao, thoi_diem_cap_nhat cho chinhanh
ALTER TABLE public.chinhanh
    ADD COLUMN IF NOT EXISTS thoi_diem_tao timestamp with time zone DEFAULT CURRENT_TIMESTAMP NOT NULL,
    ADD COLUMN IF NOT EXISTS thoi_diem_cap_nhat timestamp with time zone DEFAULT CURRENT_TIMESTAMP NOT NULL;

DO $$
BEGIN
    IF NOT EXISTS (SELECT 1 FROM pg_constraint WHERE conname = 'ck_chi_nhanh_thoi_gian') THEN
        ALTER TABLE public.chinhanh ADD CONSTRAINT ck_chi_nhanh_thoi_gian CHECK (thoi_diem_cap_nhat >= thoi_diem_tao);
    END IF;
    IF NOT EXISTS (SELECT 1 FROM pg_constraint WHERE conname = 'uq_chi_nhanh_ten') THEN
        ALTER TABLE public.chinhanh ADD CONSTRAINT uq_chi_nhanh_ten UNIQUE (ten_chi_nhanh);
    END IF;
    IF NOT EXISTS (SELECT 1 FROM pg_constraint WHERE conname = 'uq_chi_nhanh_dia_chi') THEN
        ALTER TABLE public.chinhanh ADD CONSTRAINT uq_chi_nhanh_dia_chi UNIQUE (dia_chi);
    END IF;
    IF NOT EXISTS (SELECT 1 FROM pg_constraint WHERE conname = 'uq_chi_nhanh_sdt') THEN
        ALTER TABLE public.chinhanh ADD CONSTRAINT uq_chi_nhanh_sdt UNIQUE (so_dien_thoai);
    END IF;
END $$;

-- 2. Bổ sung default cho loai_khoa, thoi_diem_tao, thoi_diem_cap_nhat cho khoaphong
ALTER TABLE public.khoaphong
    ALTER COLUMN loai_khoa SET DEFAULT 'CHUYEN_KHOA',
    ADD COLUMN IF NOT EXISTS thoi_diem_tao timestamp with time zone DEFAULT CURRENT_TIMESTAMP NOT NULL,
    ADD COLUMN IF NOT EXISTS thoi_diem_cap_nhat timestamp with time zone DEFAULT CURRENT_TIMESTAMP NOT NULL;

DO $$
BEGIN
    IF NOT EXISTS (SELECT 1 FROM pg_constraint WHERE conname = 'ck_khoa_phong_thoi_gian') THEN
        ALTER TABLE public.khoaphong ADD CONSTRAINT ck_khoa_phong_thoi_gian CHECK (thoi_diem_cap_nhat >= thoi_diem_tao);
    END IF;
END $$;

-- 3. Bảng phongkham: thêm mo_ta, timestamps, cho phép ma_khoa_phong NULL và đổi FK sang ON DELETE SET NULL
ALTER TABLE public.phongkham
    ALTER COLUMN ma_khoa_phong DROP NOT NULL,
    ADD COLUMN IF NOT EXISTS mo_ta text,
    ADD COLUMN IF NOT EXISTS thoi_diem_tao timestamp with time zone DEFAULT CURRENT_TIMESTAMP NOT NULL,
    ADD COLUMN IF NOT EXISTS thoi_diem_cap_nhat timestamp with time zone DEFAULT CURRENT_TIMESTAMP NOT NULL;

DO $$
BEGIN
    IF NOT EXISTS (SELECT 1 FROM pg_constraint WHERE conname = 'ck_phong_kham_thoi_gian') THEN
        ALTER TABLE public.phongkham ADD CONSTRAINT ck_phong_kham_thoi_gian CHECK (thoi_diem_cap_nhat >= thoi_diem_tao);
    END IF;
END $$;

ALTER TABLE public.phongkham
    DROP CONSTRAINT IF EXISTS fk_phong_kham_khoa_phong,
    ADD CONSTRAINT fk_phong_kham_khoa_phong
        FOREIGN KEY (ma_khoa_phong) REFERENCES public.khoaphong(ma_khoa_phong)
        ON DELETE SET NULL;

-- 4. Bảng nhanvien: bổ sung phân công khoa phòng và phòng khám (ON DELETE RESTRICT để chặn xóa phòng đang có nhân sự)
ALTER TABLE public.nhanvien
    ADD COLUMN IF NOT EXISTS ma_khoa_phong bigint,
    ADD COLUMN IF NOT EXISTS ma_phong_kham bigint;

DO $$
BEGIN
    IF NOT EXISTS (SELECT 1 FROM pg_constraint WHERE conname = 'fk_nhan_vien_khoa_phong') THEN
        ALTER TABLE public.nhanvien
            ADD CONSTRAINT fk_nhan_vien_khoa_phong
                FOREIGN KEY (ma_khoa_phong) REFERENCES public.khoaphong(ma_khoa_phong)
                ON DELETE SET NULL;
    END IF;
    IF NOT EXISTS (SELECT 1 FROM pg_constraint WHERE conname = 'fk_nhan_vien_phong_kham') THEN
        ALTER TABLE public.nhanvien
            ADD CONSTRAINT fk_nhan_vien_phong_kham
                FOREIGN KEY (ma_phong_kham) REFERENCES public.phongkham(ma_phong_kham)
                ON DELETE RESTRICT;
    END IF;
END $$;

CREATE INDEX IF NOT EXISTS idx_nhan_vien_khoa_phong ON public.nhanvien USING btree (ma_khoa_phong);
CREATE INDEX IF NOT EXISTS idx_nhan_vien_phong_kham ON public.nhanvien USING btree (ma_phong_kham);

COMMIT;

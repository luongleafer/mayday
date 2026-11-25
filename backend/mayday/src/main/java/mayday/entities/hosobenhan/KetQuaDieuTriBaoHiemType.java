package mayday.entities.hosobenhan;

import jakarta.persistence.EnumeratedValue;

public enum KetQuaDieuTriBaoHiemType {
    KHOI_BENH("Khoi benh"),
    DO("Do"),
    KHONG_DO("Khong do"),
    NANG_HON("Nang hon"),
    CHET("Chet"),
    BIEN_CHUNG("Bien chung");

    @EnumeratedValue
    final String value;

    KetQuaDieuTriBaoHiemType(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}

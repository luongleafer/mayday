package mayday.entities.hosobenhan;

import jakarta.persistence.EnumeratedValue;

public enum PhanNhomCsuType {
    TYPE_I("CSU Type I"),
    TYPE_IIB("CSU Type IIb"),
    TYPE_I_IIB("Chong lap Type I + Type IIb"),
    KHONG_XAC_DINH("Khong xac dinh");

    @EnumeratedValue
    final String value;

    PhanNhomCsuType(String value) {
        this.value = value;
    }
}

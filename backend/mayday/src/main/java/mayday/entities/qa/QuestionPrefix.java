package mayday.entities.qa;

public enum QuestionPrefix {
    HANH_CHINH("1.hanh-chinh_"),
    DAU_HIEU_SINH_TON("2.1.dau-hieu-sinh-ton"),
    HOAN_CANH_KHOI_PHAT("3.1.hoan-canh-khoi-phat"),
    BENH_AN_CAP("2.0.benh-an-cap_"),
    NHIEM_TRUNG("3.2.nhiem-trung_"),
    TRIEU_CHUNG_LAM_SANG("2.2.trieu-chung-lam-sang_"),
    TON_THUONG_CAP_TINH("2.2.1.ton-thuong-cap-tinh"),
    BENH_AN_MAN("2.0.benh-an-man_"),
    BENH_SU("3.1.benh-su_"),
    TIEN_SU_BAN_THAN("3.3.tien-su-ban-than_"),
    TON_THUONG_SAN_PHU("2.2.2.ton-thuong-san-phu");

    private final String prefix;


    QuestionPrefix(String prefix) {
        this.prefix = prefix;
    }

    @Override
    public String toString() {
        return prefix;
    }
}

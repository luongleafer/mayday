package mayday.utils;

import mayday.exceptions.InvalidRequestException;

/**
 * Chuan hoa chuoi dau vao dung chung de cac service khong phu thuoc cheo nhau.
 */
public final class TextUtils {
    private TextUtils() {
    }

    public static String requireNonBlank(String value, String field) {
        String result = trimToNull(value);
        if (result == null) {
            throw new InvalidRequestException(field + " must not be blank");
        }
        return result;
    }

    // Chuoi chi chua khoang trang duoc xem la khong co gia tri.
    public static String trimToNull(String value) {
        if (value == null) {
            return null;
        }
        String trimmed = value.trim();
        return trimmed.isEmpty() ? null : trimmed;
    }

    public static boolean validString(String value) {
        return value != null && !value.isBlank();
    }

    public static boolean validInsuranceNumber(String value) {
        return value.matches("^\\d{10}$");
    }

    public static boolean validPhoneNumber(String value) {
        return value.matches("((0)|(\\+84))[1345789]\\d{8}");
    }
}

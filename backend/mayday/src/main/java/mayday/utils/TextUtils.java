package mayday.utils;

import mayday.exceptions.InvalidRequestException;

/** Chuan hoa chuoi dau vao dung chung de cac service khong phu thuoc cheo nhau. */
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
}

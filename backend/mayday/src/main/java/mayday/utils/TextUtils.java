package mayday.utils;

import java.util.regex.Pattern;
import mayday.exceptions.ApiException;
import mayday.exceptions.InvalidRequestException;
import org.springframework.http.HttpStatus;

/**
 * Chuan hoa chuoi dau vao dung chung de cac service khong phu thuoc cheo nhau.
 */
public final class TextUtils {
    // So dien thoai chuan Viet Nam (dau so 03, 05, 07, 08, 09, gom 10 chu so) theo tab 3. response code (2004).
    private static final Pattern VN_PHONE_PATTERN = Pattern.compile("^0[35789][0-9]{8}$");

    private TextUtils() {
    }

    public static String requireNonBlank(String value, String field) {
        String result = normalizeWhitespace(value);
        if (result == null) {
            throw new InvalidRequestException(field + " must not be blank");
        }
        return result;
    }

    // Chuoi chi chua khoang trang duoc xem la khong co gia tri; gom khoang trang thua giua cac tu.
    public static String normalizeWhitespace(String value) {
        String trimmed = trimToNull(value);
        return trimmed == null ? null : trimmed.replaceAll("\\s+", " ");
    }

    // Chuoi chi chua khoang trang duoc xem la khong co gia tri.
    public static String trimToNull(String value) {
        if (value == null) {
            return null;
        }
        String trimmed = value.trim();
        return trimmed.isEmpty() ? null : trimmed;
    }

    public static String normalizePhone(String value) {
        String trimmed = trimToNull(value);
        if (trimmed == null) {
            return null;
        }
        // Loai bo khoang trang, dau gach ngang, dau cham, ngoac don de tranh trung SĐT khac dinh dang.
        String compact = trimmed.replaceAll("[\\s\\-.()]", "");
        if (compact.startsWith("+84") && compact.length() > 3) {
            compact = "0" + compact.substring(3);
        }
        if (!VN_PHONE_PATTERN.matcher(compact).matches()) {
            throw new ApiException("2004", HttpStatus.BAD_REQUEST, "Invalid phone number format");
        }
        return compact;
    }

    public static Long requirePositiveId(Long id, String field) {
        if (id == null || id <= 0) {
            throw new InvalidRequestException(field + " must be a positive number");
        }
        return id;
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

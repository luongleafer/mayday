package mayday.utils;

import java.sql.SQLException;
import org.hibernate.exception.ConstraintViolationException;

/** Nhan dien loi trung du lieu tu chuoi nguyen nhan cua exception JDBC/Hibernate. */
public final class PersistenceUtils {
    private PersistenceUtils() {
    }

    public static boolean isUniqueConstraintViolation(Throwable throwable) {
        // SQLState 23505 bieu thi vi pham unique; khong phu thuoc noi dung thong bao loi.
        Throwable current = throwable;
        while (current != null) {
            if (current instanceof ConstraintViolationException violation) {
                return "23505".equals(violation.getSQLState());
            }
            if (current instanceof SQLException sqlException && "23505".equals(sqlException.getSQLState())) {
                return true;
            }
            current = current.getCause();
        }
        return false;
    }
}

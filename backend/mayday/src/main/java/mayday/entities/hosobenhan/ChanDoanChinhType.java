package mayday.entities.hosobenhan;

import jakarta.persistence.EnumeratedValue;

public enum ChanDoanChinhType {
    CSU("CSU"),
    CINDU("CIndU"),
    CSU_CINDU("CSU + CIndU"),
    PHU_MACH_DON_THUAN("Phu mach don thuan");


    @EnumeratedValue
    final String value;


    ChanDoanChinhType(String value) {
        this.value = value;
    }
}

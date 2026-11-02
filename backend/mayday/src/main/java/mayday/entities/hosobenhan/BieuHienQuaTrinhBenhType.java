package mayday.entities.hosobenhan;

import jakarta.persistence.EnumeratedValue;

public enum BieuHienQuaTrinhBenhType {
    CHI_SAN_PHU("Chi san phu"),
    CHI_PHU_MACH("Chi phu mach"),
    SAN_PHU_VA_PHU_MACH("San phu + phu mach");

    @EnumeratedValue
    final String value;

    BieuHienQuaTrinhBenhType(String value) {
        this.value = value;
    }
}

package mayday.entities.hosobenhan;

import jakarta.persistence.Embeddable;

@Embeddable
public class PersonalMedicalHistory {
    private Boolean tienSuCoDia;

    private String tienSuCoDiaGhiRo;

    private Boolean tienSuTuyenGiap;

    private String tienSuTuyenGiapGhiRo;

    private Boolean tienSuTuMien;

    private String tienSuTuMienGhiRo;

    private Boolean tienSuDiUng;

    private String tienSuDiUngGhiRo;

    private Boolean tienSuPhanVe;

    private String tienSuPhanVeGhiRo;

    private Boolean tienSuViemNhiemMan;

    private String tienSuViemNhiemManGhiRo;

    private Boolean tienSuBenhKhac;

    private String tienSuBenhKhacGhiRo;
}

package mayday.services;

import lombok.RequiredArgsConstructor;
import mayday.dto.UAS7EligibilityResponseDto;
import mayday.entities.hosobenhan.HoSoBenhAn;
import mayday.entities.nguoi.BenhNhan;
import mayday.repositories.BenhNhanRepository;
import mayday.repositories.HoSoBenhAnRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UAS7Service {
    private final HoSoBenhAnRepository hoSoBenhAnRepository;

    @Transactional(readOnly = true)
    public UAS7EligibilityResponseDto checkUas7Eligibility(Long maBenhNhan) {
        Optional<HoSoBenhAn> hoSoActive = hoSoBenhAnRepository.findActiveByMaBenhNhan(maBenhNhan);

        if (hoSoActive.isPresent()) {
            return UAS7EligibilityResponseDto.builder()
                    .code("1000")
                    .message("OK")
                    .data(UAS7EligibilityResponseDto.EligibilityData.builder()
                            .eligible("true")
                            .medicalRecordId(String.valueOf(hoSoActive.get().getMaHoSo()))
                            .build())
                    .build();
        }

        return UAS7EligibilityResponseDto.builder()
                .code("1000")
                .message("OK")
                .data(UAS7EligibilityResponseDto.EligibilityData.builder()
                        .eligible("false")
                        .medicalRecordId(null)
                        .build())
                .build();
    }




}

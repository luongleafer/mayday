package mayday.services;

import mayday.dto.responses.UserDetailResponse;
import mayday.entities.nguoi.TaiKhoan;
import mayday.exceptions.NotFoundException;
import mayday.repositories.TaiKhoanRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final TaiKhoanRepository accountRepository;

    public UserService(TaiKhoanRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public List<UserDetailResponse> getListUsers() {
        return accountRepository.findAll().stream().map(
                UserDetailResponse::new
        ).toList();
    }

    public UserDetailResponse getUserDetails(Long userId) {
        TaiKhoan account = accountRepository.findById(userId).orElseThrow(
                () -> new NotFoundException("user", userId)
        );
        return new UserDetailResponse(account);
    }


}

package mayday.services;

import mayday.dto.requests.UserCreationRequest;
import mayday.dto.requests.UserEditRequest;
import mayday.dto.responses.UserDetailResponse;
import mayday.entities.nguoi.Nguoi;
import mayday.entities.nguoi.TaiKhoan;
import mayday.exceptions.NotFoundException;
import mayday.repositories.NguoiRepository;
import mayday.repositories.TaiKhoanRepository;
import mayday.utils.TextUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final TaiKhoanRepository accountsRepository;
    private final NguoiRepository peopleRepository;
    private final AccountService accountService;

    public UserService(TaiKhoanRepository accountsRepository, NguoiRepository peopleRepository, AccountService accountService) {
        this.accountsRepository = accountsRepository;
        this.peopleRepository = peopleRepository;
        this.accountService = accountService;
    }

    public List<UserDetailResponse> getListUsers() {
        return accountsRepository.findAll().stream().map(
                UserDetailResponse::new
        ).toList();
    }

    public UserDetailResponse getUserDetails(Long userId) {
        TaiKhoan account = accountsRepository.findById(userId).orElseThrow(
                () -> new NotFoundException("user", userId)
        );
        return new UserDetailResponse(account);
    }

    public UserDetailResponse createUser(UserCreationRequest request) {
        TaiKhoan newAccount = accountService.signUp(request, request.getRoleId());
        if (request.getIsActive() != null && request.getIsActive() == 0) newAccount.setTrangThaiHoatDong(false);
        accountsRepository.save(newAccount);
        return new UserDetailResponse(newAccount);
    }

    public UserDetailResponse editUser(Long userId, UserEditRequest request) {
        TaiKhoan account = accountsRepository.findById(userId).orElseThrow(
                () -> new NotFoundException("user", userId)
        );
        UserCreationRequest newData = request.getUserCreationRequest();
        if (TextUtils.validString(newData.getFullName())) {
            account.getNguoi().setHoTen(newData.getFullName().strip());
        }
        if (newData.getGender() != null) {
            account.getNguoi().setGioiTinh(newData.getGender());
        }
        if (newData.getRoleId() != null) {
            account.setMaVaiTro(newData.getRoleId());
        }
        if (newData.getIsActive() != null) {
            account.setTrangThaiHoatDong(newData.getIsActive() == 1);
        }
        peopleRepository.save(account.getNguoi());
        accountsRepository.save(account);
        return new UserDetailResponse(account);
    }

    public void deleteUser(Long userId) {
        accountsRepository.deleteById(userId);
//        TaiKhoan account = accountsRepository.findById(userId).orElseThrow(
//                () -> new NotFoundException("user", userId)
//        );
//        Nguoi nguoi = account.getNguoi();
//        nguoi.setTaiKhoan(null);
//        accountsRepository.delete(account);
    }


}

package mayday.services;

import mayday.dto.requests.SignUpRequest;
import mayday.dto.responses.SignUpResponseData;
import mayday.entities.nguoi.Nguoi;
import mayday.entities.nguoi.TaiKhoan;
import mayday.entities.nguoi.VaiTro;
import mayday.exceptions.PasswordMismatchException;
import mayday.exceptions.UserExistsException;
import mayday.repositories.NguoiRepository;
import mayday.repositories.TaiKhoanRepository;
import mayday.repositories.VaiTroRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AccountService {

    private final PasswordEncoder passwordEncoder;
    private final TaiKhoanRepository taiKhoanRepository;
    private final NguoiRepository nguoiRepository;
    private final VaiTroRepository vaiTroRepository;

    public AccountService(PasswordEncoder passwordEncoder, TaiKhoanRepository taiKhoanRepository, NguoiRepository nguoiRepository, VaiTroRepository vaiTroRepository) {
        this.passwordEncoder = passwordEncoder;
        this.taiKhoanRepository = taiKhoanRepository;
        this.nguoiRepository = nguoiRepository;
        this.vaiTroRepository = vaiTroRepository;
    }

    private VaiTro getDefaultRole() throws Exception {
        VaiTro benhNhan = null;
        benhNhan = vaiTroRepository.findByTenVaiTro("benh_nhan").orElse(null);
        if (benhNhan != null) return benhNhan;
        else throw new Exception("Default role not found");
    }


    @Transactional
    public SignUpResponseData signUp(SignUpRequest signUpRequest) throws Exception {
        if (taiKhoanRepository.existsByTenDangNhap(signUpRequest.getPhone()))
            throw new UserExistsException("User with username '" + signUpRequest.getPhone() + "' already exists.");
        VaiTro vaiTro = getDefaultRole();
        Nguoi person = new Nguoi();
        person.setHoTen(signUpRequest.getFullName());
        person.setGioiTinh(signUpRequest.getGender());
        person.setSoDienThoai(signUpRequest.getPhone());
        Nguoi newPerson = nguoiRepository.save(person);
        TaiKhoan account = new TaiKhoan();
        account.setTenDangNhap(signUpRequest.getPhone());
        account.setMatKhauMaHoa(passwordEncoder.encode(signUpRequest.getPassword()));
        account.setTrangThaiHoatDong(true);
        account.setVaiTro(vaiTro);
        account.setNguoi(newPerson);
        TaiKhoan newAccount = taiKhoanRepository.save(account);
        return new SignUpResponseData(newPerson, newAccount);
    }

    @Transactional
    public void changePassword(Long accountId, String oldPassword, String newPassword) {
        TaiKhoan account = taiKhoanRepository.findById(accountId).orElse(null);
        if (account == null) return;
        String oldPasswordHash = account.getMatKhauMaHoa();
        if (!passwordEncoder.matches(oldPassword, oldPasswordHash))
            throw new PasswordMismatchException("");
        account.setMatKhauMaHoa(passwordEncoder.encode(newPassword));
        taiKhoanRepository.save(account);
    }

}

package mayday.dto.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/** Du lieu tao chi nhanh theo dac ta API (name, address, phone). */
public class SetBranchRequest {
    @NotBlank
    @Size(max = 150)
    private String name;
    @NotBlank
    @Size(max = 500)
    private String address;
    @Size(max = 20)
    private String phone;

    public SetBranchRequest() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}

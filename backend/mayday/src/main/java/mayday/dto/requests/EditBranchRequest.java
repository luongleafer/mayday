package mayday.dto.requests;

import jakarta.validation.constraints.Size;

/** Du lieu sua chi nhanh; field null giu gia tri cu, phone rong duoc chuyen thanh null. */
public class EditBranchRequest {
    @Size(max = 150)
    private String name;
    private String address;
    @Size(max = 20)
    private String phone;

    public EditBranchRequest() {
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

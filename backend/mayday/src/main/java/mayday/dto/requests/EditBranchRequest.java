package mayday.dto.requests;

import jakarta.validation.constraints.Size;

/** Du lieu sua chi nhanh theo dac ta API (name, address, phone); field null giu gia tri cu. */
public class EditBranchRequest {
    @Size(max = 150)
    private String name;
    @Size(max = 500)
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

package mayday.dtos.responses;

public class BranchResponse {
    private Long id;
    private String name;
    private String address;
    private String phone;
    private Boolean is_active;

    public BranchResponse() {
    }

    public BranchResponse(Long id, String name, String address, String phone, Boolean is_active) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.is_active = is_active;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Boolean getIs_active() {
        return is_active;
    }

    public void setIs_active(Boolean is_active) {
        this.is_active = is_active;
    }
}
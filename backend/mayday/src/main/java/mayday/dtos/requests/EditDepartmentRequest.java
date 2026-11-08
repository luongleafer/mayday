package mayday.dtos.requests;

public class EditDepartmentRequest {
    private String name;
    private String description;
    private Long branch_id;

    public EditDepartmentRequest() {
    }
    public EditDepartmentRequest(String name, String description, Long branch_id) {
        this.name = name;
        this.description = description;
        this.branch_id = branch_id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public Long getBranch_id() {
        return branch_id;
    }

    public void setBranch_id(Long branch_id) {
        this.branch_id = branch_id;
    }
}

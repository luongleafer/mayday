package mayday.dtos.responses;

public class DepartmentResponse {
    private long id;
    private Long branch_id;
    private String name;
    private String description;

    // constructors
    public DepartmentResponse() {}
    public DepartmentResponse(long id, Long branch_id, String name, String description) {
        this.id = id;
        this.branch_id = branch_id;
        this.name = name;
        this.description = description;
    }
    // Getters and setters
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Long getBranch_id() {
        return branch_id;
    }

    public void setBranch_id(Long branch_id) {
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
}

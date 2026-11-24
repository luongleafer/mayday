package mayday.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;

@JsonIgnoreProperties({"id"})
public class RoleCreationRequest extends RoleDto {

}

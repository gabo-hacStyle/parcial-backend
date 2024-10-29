package demo.securityapp.dto.response;

import demo.securityapp.models.RoleEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserResponseDto {

    private String username;
    private Set<RoleEntity> roles;

}

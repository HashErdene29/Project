package cs489.project.dto.userauth;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserAuthRequest {
    @NotBlank(message = "Username cannot be null, empty or blankspance(s)")
    private String username;

    @NotBlank(message = "Password cannot be null, empty or blankspance(s)")
    private String password;
}
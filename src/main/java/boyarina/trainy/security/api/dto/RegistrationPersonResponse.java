package boyarina.trainy.security.api.dto;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class RegistrationPersonResponse {
    private String username;
    private String password;
    private String confirmPassword;
    private String email;
}
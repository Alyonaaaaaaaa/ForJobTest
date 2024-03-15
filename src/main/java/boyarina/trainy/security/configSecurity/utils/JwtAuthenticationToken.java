package boyarina.trainy.security.configSecurity.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

@Data
@AllArgsConstructor
public class JwtAuthenticationToken {
    private List<UserDetails> userDetails;
    private String token;
}
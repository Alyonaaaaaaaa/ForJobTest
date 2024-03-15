package boyarina.trainy.security.service;

import boyarina.trainy.security.api.dto.JwtResponse;
import boyarina.trainy.security.api.dto.RegistrationPersonResponse;
import boyarina.trainy.security.configSecurity.utils.JwtTokenUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenUtil jwtTokenUtil;
    private final AuthenticationManager authenticationManager;
    private final PersonService personService;

    public RegistrationPersonResponse createNewPerson(String username,
                                                      String email,
                                                      String password,
                                                      String confirmPassword) {
        return personService.createNewPerson(username, email, password, confirmPassword, passwordEncoder);
    }

    public JwtResponse createAuthToken(String username, String password) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username, password));
        } catch (BadCredentialsException e) {
            throw new BadCredentialsException("Incorrect login or password");
        }
        UserDetails userDetails = personService.loadUserByUsername(username);
        String token = jwtTokenUtil.generateToken(userDetails);
        return new JwtResponse(token);
    }
}
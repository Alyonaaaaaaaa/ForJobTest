package boyarina.trainy.security.api.controller;

import boyarina.trainy.security.api.dto.JwtRequest;
import boyarina.trainy.security.api.dto.RegistrationPersonRequest;
import boyarina.trainy.security.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthController {
    private final AuthenticationService authenticationService;

    @PostMapping("/auth")
    public ResponseEntity<?> createAuthToken(@RequestBody JwtRequest request) {
        return ResponseEntity.ok(authenticationService.createAuthToken(request.getUserName(), request.getPassword()));
    }

    @PostMapping("registration")
    public ResponseEntity<?> createNewPerson(@RequestBody RegistrationPersonRequest request) {
        return ResponseEntity.ok(authenticationService.createNewPerson(
                request.getUsername(),
                request.getEmail(),
                request.getPassword(),
                request.getConfirmPassword())
        );
    }
}
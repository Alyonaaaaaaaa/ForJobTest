package boyarina.trainy.mvc.first.service.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class UserRequest {
    private UUID id;
    private String email;
}
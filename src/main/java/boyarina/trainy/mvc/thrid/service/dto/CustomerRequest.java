package boyarina.trainy.mvc.thrid.service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
public class CustomerRequest {
    private UUID id;
    private String email;
    private String contactNumber;
}
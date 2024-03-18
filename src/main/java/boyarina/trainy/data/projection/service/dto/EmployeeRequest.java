package boyarina.trainy.data.projection.service.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.util.UUID;

@Data
public class EmployeeRequest {
    private UUID id;
    private String firstName;
    private String lastName;
    private String position;
    private BigDecimal salary;
    private UUID departmentId;
}
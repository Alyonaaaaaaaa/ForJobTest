package boyarina.trainy.data.projection.service.dto;

import boyarina.trainy.data.projection.entity.Department;
import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

@Data
@Accessors(chain = true)
public class CRUDEmployeeResponse {
    private String firstName;
    private String lastName;
    private String position;
    private BigDecimal salary;
    private Department department;
}
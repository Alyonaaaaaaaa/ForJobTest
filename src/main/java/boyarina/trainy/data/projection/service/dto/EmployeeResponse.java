package boyarina.trainy.data.projection.service.dto;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class EmployeeResponse {
    private String fullName;
    private String position;
    private String departmentName;
}
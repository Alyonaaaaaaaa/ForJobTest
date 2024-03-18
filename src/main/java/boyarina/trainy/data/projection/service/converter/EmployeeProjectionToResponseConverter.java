package boyarina.trainy.data.projection.service.converter;

import boyarina.trainy.data.projection.repository.projectionDTO.EmployeeProjection;
import boyarina.trainy.data.projection.service.dto.EmployeeResponse;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class EmployeeProjectionToResponseConverter implements Converter<EmployeeProjection, EmployeeResponse> {

    @Override
    public EmployeeResponse convert(EmployeeProjection employeeProjection) {
        return new EmployeeResponse()
                .setFullName(employeeProjection.getFullName())
                .setDepartmentName(employeeProjection.getDepartmentName())
                .setPosition(employeeProjection.getPosition());
    }
}
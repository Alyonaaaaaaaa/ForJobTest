package boyarina.trainy.data.projection.service.converter;

import boyarina.trainy.data.projection.entity.Employee;
import boyarina.trainy.data.projection.service.dto.CRUDEmployeeResponse;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class EmployeeToResponseConverter implements Converter<Employee, CRUDEmployeeResponse> {
    @Override
    public CRUDEmployeeResponse convert(Employee employee) {
        return new CRUDEmployeeResponse()
                .setFirstName(employee.getFirstName())
                .setLastName(employee.getLastName())
                .setPosition(employee.getPosition())
                .setSalary(employee.getSalary())
                .setDepartment(employee.getDepartment());
    }
}
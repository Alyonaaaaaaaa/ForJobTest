package boyarina.trainy.data.projection.repository.projectionDTO;


import org.springframework.beans.factory.annotation.Value;

public interface EmployeeProjection {

    @Value("#{target.employee.firstName + ' ' + target.employee.lastName}")
    String getFullName();
    String getPosition();

    @Value("#{target.employee.department.name}")
    String getDepartmentName();
}
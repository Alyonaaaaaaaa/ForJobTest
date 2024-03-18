package boyarina.trainy.data.projection.service;

import boyarina.exception.NotFoundException;
import boyarina.trainy.data.projection.entity.Department;
import boyarina.trainy.data.projection.entity.Employee;
import boyarina.trainy.data.projection.repository.EmployeeRepository;
import boyarina.trainy.data.projection.repository.projectionDTO.DepartmentRepository;
import boyarina.trainy.data.projection.service.converter.EmployeeProjectionToResponseConverter;
import boyarina.trainy.data.projection.service.converter.EmployeeToResponseConverter;
import boyarina.trainy.data.projection.service.dto.CRUDEmployeeResponse;
import boyarina.trainy.data.projection.service.dto.EmployeeResponse;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.UUID;

@Service
public class EmployeeService {
    private EmployeeRepository employeeRepository;
    private DepartmentRepository departmentRepository;
    private EmployeeProjectionToResponseConverter converter;
    private EmployeeToResponseConverter converterForCreate;


    public EmployeeResponse getEmployee(String firstname, String lastName) {
        return converter.convert(employeeRepository.findByFirstNameAndLastName(firstname, lastName));
    }

    public CRUDEmployeeResponse create(String firstName,
                                       String lastName,
                                       String position,
                                       BigDecimal salary,
                                       UUID departmentId) {
        return converterForCreate.convert(employeeRepository.save(new Employee()
                .setFirstName(firstName)
                .setLastName(lastName)
                .setPosition(position)
                .setSalary(salary)
                .setDepartment(getDepartmentById(departmentId))));
    }

    public CRUDEmployeeResponse updateEmployeeSalary(BigDecimal salary) {
        return converterForCreate.convert(employeeRepository.save(new Employee().setSalary(salary)));
    }

    public CRUDEmployeeResponse updateEmployeePositionAndDepartment(String position, UUID departmentId) {
        return converterForCreate.convert(employeeRepository.save(new Employee()
                .setPosition(position)
                .setDepartment(getDepartmentById(departmentId))));
    }

    public void deleteEmployee(UUID id) {
        employeeRepository.delete(getEmployee(id));
    }

    public Employee getEmployee(UUID id) {
        return employeeRepository.findById(id).orElseThrow(() -> new NotFoundException("Employee with this id not found"));
    }

    public Department getDepartmentById(UUID id) {
        return departmentRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException("Department with this id not found"));
    }

}
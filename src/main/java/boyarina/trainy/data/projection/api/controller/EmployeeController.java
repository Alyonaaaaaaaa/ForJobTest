package boyarina.trainy.data.projection.api.controller;

import boyarina.trainy.data.projection.service.EmployeeService;
import boyarina.trainy.data.projection.service.dto.CRUDEmployeeResponse;
import boyarina.trainy.data.projection.service.dto.EmployeeRequest;
import boyarina.trainy.data.projection.service.dto.EmployeeResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/employee")
public class EmployeeController {
    private final EmployeeService employeeService;

    @PostMapping("/getOne")
    public ResponseEntity<EmployeeResponse> getEmployee(@RequestBody @Validated EmployeeRequest request) {
        return ResponseEntity.ok(employeeService.getEmployee(request.getFirstName(), request.getLastName()));
    }

    @PostMapping("/create")
    public ResponseEntity<CRUDEmployeeResponse> create(@RequestBody @Validated EmployeeRequest request) {
        return ResponseEntity.ok(employeeService.create(
                request.getFirstName(),
                request.getLastName(),
                request.getPosition(),
                request.getSalary(),
                request.getDepartmentId()));
    }

    @PutMapping("/updateSalary")
    public ResponseEntity<CRUDEmployeeResponse> updateSalary(@RequestBody @Validated EmployeeRequest request) {
        return ResponseEntity.ok(employeeService.updateEmployeeSalary(request.getSalary()));
    }

    @PutMapping("/updatePositionAndDepartment")
    public ResponseEntity<CRUDEmployeeResponse> updatePositionAndDepartment(
            @RequestBody @Validated EmployeeRequest request) {
        return ResponseEntity.ok(employeeService.updateEmployeePositionAndDepartment(
                request.getPosition(),
                request.getDepartmentId()));
    }

    @DeleteMapping("/delete/{EMPLOYEE_UUID}")
    public void deleteEmployee(@PathVariable("EMPLOYEE_UUID") UUID id) {
        employeeService.deleteEmployee(id);
    }
}
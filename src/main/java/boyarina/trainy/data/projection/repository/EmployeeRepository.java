package boyarina.trainy.data.projection.repository;


import boyarina.trainy.data.projection.entity.Employee;
import boyarina.trainy.data.projection.repository.projectionDTO.EmployeeProjection;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface EmployeeRepository extends CrudRepository<Employee, UUID> {
    EmployeeProjection findByFirstNameAndLastName(String firstName, String lastName);
}
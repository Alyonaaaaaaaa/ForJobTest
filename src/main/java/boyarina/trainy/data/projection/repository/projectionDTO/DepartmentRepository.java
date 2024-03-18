package boyarina.trainy.data.projection.repository.projectionDTO;

import boyarina.trainy.data.projection.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, UUID> {
}
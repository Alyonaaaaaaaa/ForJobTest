package boyarina.trainy.mvc.thrid.repository;

import boyarina.trainy.mvc.thrid.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ProductRepository extends JpaRepository<Product, UUID> {
}
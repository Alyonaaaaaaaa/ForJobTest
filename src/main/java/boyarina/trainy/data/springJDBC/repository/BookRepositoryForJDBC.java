package boyarina.trainy.data.springJDBC.repository;

import boyarina.trainy.mvc.second.entity.Book;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface BookRepositoryForJDBC extends CrudRepository<Book, UUID> {
}
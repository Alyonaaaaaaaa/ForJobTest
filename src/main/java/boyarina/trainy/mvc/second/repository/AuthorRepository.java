package boyarina.trainy.mvc.second.repository;

import boyarina.trainy.mvc.first.entity.User;
import boyarina.trainy.mvc.second.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AuthorRepository extends JpaRepository<Author, UUID> {
    public Author findByAuthorName(String authorName);
}
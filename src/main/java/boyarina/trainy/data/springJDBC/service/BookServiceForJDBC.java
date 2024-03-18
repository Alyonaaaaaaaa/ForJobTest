package boyarina.trainy.data.springJDBC.service;

import boyarina.exception.NotFoundException;
import boyarina.trainy.data.springJDBC.repository.BookRepositoryForJDBC;
import boyarina.trainy.data.springJDBC.repository.PagingBookRepositoryForJDBC;
import boyarina.trainy.mvc.second.entity.Author;
import boyarina.trainy.mvc.second.entity.Book;
import boyarina.trainy.mvc.second.exception.DuplicateValueException;
import boyarina.trainy.mvc.second.repository.AuthorRepository;
import boyarina.trainy.mvc.second.service.converter.BookToResponseConverter;
import boyarina.trainy.mvc.second.service.dto.BookResponse;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class BookServiceForJDBC {

    private final BookToResponseConverter bookConverter;
    private final PagingBookRepositoryForJDBC pagingBookRepositoryForJDBC;
    private final BookRepositoryForJDBC bookRepositoryForJDBC;
    private final AuthorRepository authorRepository;

    public List<BookResponse> getAllBooks(Pageable pageable) {
        return pagingBookRepositoryForJDBC.findAll(pageable)
                .stream()
                .map(bookConverter::convert)
                .collect(Collectors.toList());
    }

    public BookResponse getBookResponse(UUID id) {
        return bookConverter.convert(getBook(id));
    }

    @Transactional(readOnly = false)
    public BookResponse createBook(String title, String authorName) {
        return bookConverter.convert(bookRepositoryForJDBC
                .save(new Book()
                        .setTitle(title)
                        .setAuthor(this.createAuthor(authorName))));
    }

    public void updateTitleForBook(UUID id, String newTitle) {
        bookRepositoryForJDBC.save(this.getBook(id).setTitle(newTitle));
    }

    @Transactional(readOnly = false)
    public void updateAuthorForBook(UUID id, String authorName) {
        Author author = this.createAuthor(authorName);
        bookRepositoryForJDBC.save(this.getBook(id).setAuthor(author));
    }

    public void deleteBook(UUID id) {
        bookRepositoryForJDBC.delete(this.getBook(id));
    }

    public Book getBook(UUID id) {
        return bookRepositoryForJDBC.findById(id).orElseThrow(() -> new NotFoundException("Book with this id not found"));
    }

    public Author createAuthor(String authorName) {
        if (authorRepository.findByAuthorName(authorName) != null) {
            throw new DuplicateValueException("This author already exists");
        }
        return authorRepository.save(new Author().setAuthorName(authorName));
    }
}
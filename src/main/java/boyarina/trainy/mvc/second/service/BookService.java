package boyarina.trainy.mvc.second.service;

import boyarina.exception.NotFoundException;
import boyarina.trainy.mvc.second.api.json.BookResponse;
import boyarina.trainy.mvc.second.entity.Author;
import boyarina.trainy.mvc.second.entity.Book;
import boyarina.trainy.mvc.second.exception.DuplicateValueException;
import boyarina.trainy.mvc.second.repository.AuthorRepository;
import boyarina.trainy.mvc.second.repository.BookRepository;
import boyarina.trainy.mvc.second.service.converter.AuthorToResponseConverter;
import boyarina.trainy.mvc.second.service.converter.BookToResponseConverter;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class BookService {
    private final BookToResponseConverter bookConverter;
    private final AuthorToResponseConverter authorConverter;
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    public List<BookResponse> getAllBooks(PageRequest pageRequest) {
        return bookRepository.findAll(pageRequest)
                .stream()
                .map(bookConverter::convert)
                .collect(Collectors.toList());
    }

    public BookResponse getBookResponse(UUID id) {
        return bookConverter.convert(getBook(id));
    }

    @Transactional(readOnly = false)
    public BookResponse createBook(String title, String authorName) {
        return bookConverter.convert(bookRepository
                .save(new Book()
                        .setTitle(title)
                        .setAuthor(this.createAuthor(authorName))));
    }

    public void updateTitleForBook(UUID id, String newTitle) {
        bookRepository.save(this.getBook(id).setTitle(newTitle));
    }

    @Transactional(readOnly = false)
    public void updateAuthorForBook(UUID id, String authorName) {
        Author author = this.createAuthor(authorName);
        bookRepository.save(this.getBook(id).setAuthor(author));
    }

    public void deleteBook(UUID id) {
        bookRepository.delete(this.getBook(id));
    }

    public Book getBook(UUID id) {
        return bookRepository.findById(id).orElseThrow(() -> new NotFoundException("Book with this id not found"));
    }

    public Author createAuthor(String authorName) {
        if (authorRepository.findByAuthorName(authorName) != null) {
            throw new DuplicateValueException("This author already exists");
        }
        return authorRepository.save(new Author().setAuthorName(authorName));
    }
}
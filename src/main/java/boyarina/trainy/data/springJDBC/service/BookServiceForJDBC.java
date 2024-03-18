package boyarina.trainy.data.springJDBC.service;

import boyarina.exception.NotFoundException;
import boyarina.trainy.data.springJDBC.dao.BookDaoImpl;
import boyarina.trainy.mvc.second.entity.Book;
import boyarina.trainy.mvc.second.service.converter.BookToResponseConverter;
import boyarina.trainy.mvc.second.service.dto.BookResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class BookServiceForJDBC {

    private final BookToResponseConverter bookConverter;
    private final BookDaoImpl bookDaoImpl;

    public List<BookResponse> getAllBooks() {
        return bookDaoImpl.getAllBooks().stream().map(bookConverter::convert).toList();
    }

    public BookResponse getBookResponse(UUID id) {
        return bookConverter.convert(getBook(id));
    }

    public void createBook(String title, String authorName) {
        bookDaoImpl.createBook(title, authorName);
    }

    public void updateTitleForBook(UUID id, String newTitle) {
        bookDaoImpl.updateTitleBook(newTitle, id);
    }

    public void deleteBook(UUID id) {
        bookDaoImpl.deleteBook(this.getBook(id));
    }

    public Book getBook(UUID id) {
        Book book = bookDaoImpl.getBookById(id);
        if (book == null) {
            throw new NotFoundException("Book with this id not found");
        }
        return book;
    }
}
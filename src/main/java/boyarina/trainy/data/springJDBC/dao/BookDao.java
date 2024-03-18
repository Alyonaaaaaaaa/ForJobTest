package boyarina.trainy.data.springJDBC.dao;


import boyarina.trainy.mvc.second.entity.Book;

import java.util.List;
import java.util.UUID;


public interface BookDao {
    Book getBookById(UUID id);

    List<Book> getAllBooks();

    void deleteBook(Book book);

    void updateTitleBook(String newTitle, UUID id);

    void createBook(String title, String authorName);
}
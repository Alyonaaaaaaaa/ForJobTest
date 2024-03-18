package boyarina.trainy.data.springJDBC.dao;

import boyarina.trainy.data.springJDBC.dao.model.BookModel;
import boyarina.trainy.mvc.second.entity.Book;
import lombok.Data;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.List;
import java.util.UUID;

@Component
@Data
public class BookDaoImpl implements BookDao {
    private final String SQL_FIND_BOOK = "select * from book where id = ?";
    private final String SQL_DELETE_BOOK = "delete from book where id = ?";
    private final String SQL_UPDATE_TITLE_BOOK = "update book set title = ? where id = ?";
    private final String SQL_GET_ALL = "select * from book";
    private final String SQL_INSERT_BOOK = "insert into book(id, title, author_id, publication_year) values(?,?,?,?)";
    private JdbcTemplate jdbcTemplate;


    public BookDaoImpl(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Book getBookById(UUID id) {
        return jdbcTemplate.queryForObject(SQL_FIND_BOOK, Book.class, id);
    }

    @Override
    public List<Book> getAllBooks() {
        return jdbcTemplate.query(SQL_GET_ALL, new BookModel());
    }

    @Override
    public void deleteBook(Book book) {
        jdbcTemplate.update(SQL_DELETE_BOOK, book.getId());
    }

    @Override
    public void updateTitleBook(String newTitle, UUID id) {
        jdbcTemplate.update(SQL_UPDATE_TITLE_BOOK, newTitle, id);
    }

    @Override
    public void createBook(String title, String authorName) {
        jdbcTemplate.update(SQL_INSERT_BOOK, title, authorName);
    }
}
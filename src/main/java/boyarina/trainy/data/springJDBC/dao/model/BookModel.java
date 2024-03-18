package boyarina.trainy.data.springJDBC.dao.model;

import boyarina.trainy.mvc.second.entity.Author;
import boyarina.trainy.mvc.second.entity.Book;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BookModel implements RowMapper<Book> {
    @Override
    public Book mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        Book book = new Book();
        book.setTitle(resultSet.getString("title"));
        book.setAuthor(resultSet.getObject("author_id", Author.class));
        return book;
    }
}
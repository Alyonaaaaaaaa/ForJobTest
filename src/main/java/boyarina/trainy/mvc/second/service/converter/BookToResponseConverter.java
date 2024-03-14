package boyarina.trainy.mvc.second.service.converter;

import boyarina.trainy.mvc.second.api.json.BookResponse;
import boyarina.trainy.mvc.second.entity.Book;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class BookToResponseConverter implements Converter<Book, BookResponse> {
    @Override
    public BookResponse convert(Book book) {
        return new BookResponse(book.getTitle(), book.getAuthor());
    }
}
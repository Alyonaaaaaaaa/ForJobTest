package boyarina.trainy.mvc.second.service.converter;

import boyarina.trainy.mvc.second.api.json.AuthorResponse;
import boyarina.trainy.mvc.second.entity.Author;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class AuthorToResponseConverter implements Converter<Author, AuthorResponse> {
    @Override
    public AuthorResponse convert(Author author) {
        return new AuthorResponse(author.getAuthorName());
    }
}
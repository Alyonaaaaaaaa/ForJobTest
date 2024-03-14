package boyarina.trainy.mvc.second.api.json;

import boyarina.trainy.mvc.second.entity.Author;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BookResponse {
    private String title;
    private Author author;
}
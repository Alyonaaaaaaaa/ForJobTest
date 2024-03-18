package boyarina.trainy.mvc.second.service.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class BookRequest {
    private UUID id;
    private String title;
    private String authorName;
}

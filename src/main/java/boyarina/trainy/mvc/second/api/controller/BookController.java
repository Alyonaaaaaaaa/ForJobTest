package boyarina.trainy.mvc.second.api.controller;

import boyarina.trainy.mvc.second.api.json.BookRequest;
import boyarina.trainy.mvc.second.api.json.BookResponse;
import boyarina.trainy.mvc.second.service.BookService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@AllArgsConstructor
@RequestMapping("/api/book")
public class BookController {
    private final BookService bookService;

    @GetMapping("/getAll")
    public List<BookResponse> getAllBooks(@RequestParam(required = false, defaultValue = "0") int page,
                                          @RequestParam(required = false, defaultValue = "3") int size
    ) {
        return bookService.getAllBooks(PageRequest.of(page, size));
    }

    @PostMapping("/getOne")
    public BookResponse getBook(@RequestBody @Validated BookRequest request) {
        return bookService.getBookResponse(request.getId());
    }

    @PostMapping("/create")
    public BookResponse createBook(@RequestBody @Validated BookRequest request) {
        return bookService.createBook(request.getTitle(), request.getAuthorName());
    }

    @PutMapping("/updateTitle/{BOOK_UUID}")
    public void updateTitleForBook(@PathVariable("BOOK_UUID") UUID id, @RequestBody @Validated BookRequest request) {
        bookService.updateTitleForBook(id, request.getTitle());
    }

    @DeleteMapping("/delete/{BOOK_UUID}")
    public void getBook(@PathVariable("BOOK_UUID") UUID id) {
        bookService.deleteBook(id);
    }
}
package boyarina.trainy.mvc.second.api.controller;

import boyarina.trainy.mvc.second.service.dto.BookRequest;
import boyarina.trainy.mvc.second.service.dto.BookResponse;
import boyarina.trainy.mvc.second.service.BookService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<List<BookResponse>> getAllBooks(Pageable pageable) {
        return ResponseEntity.ok(bookService.getAllBooks(pageable));
    }

    @PostMapping("/getOne")
    public ResponseEntity<BookResponse> getBook(@RequestBody @Validated BookRequest request) {
        return ResponseEntity.ok(bookService.getBookResponse(request.getId()));
    }

    @PostMapping("/create")
    public ResponseEntity<BookResponse> createBook(@RequestBody @Validated BookRequest request) {
        return ResponseEntity.ok(bookService.createBook(request.getTitle(), request.getAuthorName()));
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
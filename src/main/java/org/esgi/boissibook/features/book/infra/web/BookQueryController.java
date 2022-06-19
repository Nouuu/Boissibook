package org.esgi.boissibook.features.book.infra.web;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.esgi.boissibook.features.book.domain.BookQueryHandler;
import org.esgi.boissibook.features.book.infra.BookMapper;
import org.esgi.boissibook.features.book.infra.web.response.BookResponse;
import org.esgi.boissibook.features.book.infra.web.response.BooksResponse;
import org.esgi.boissibook.features.book.kernel.exception.BookNotFoundException;
import org.esgi.boissibook.kernel.repository.BookId;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Book controller", description = "Book features")
@RestController
@RequestMapping(value = "books", produces = MediaType.APPLICATION_JSON_VALUE)
public class BookQueryController {

    private final BookQueryHandler bookQueryHandler;

    public BookQueryController(BookQueryHandler bookQueryHandler) {
        this.bookQueryHandler = bookQueryHandler;
    }

    @Operation(summary = "Get all books", description = "Get all registered books")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successful operation", content = @Content(schema = @Schema(implementation = BooksResponse.class)))
    })
    @GetMapping
    public ResponseEntity<BooksResponse> getAllBooks() {
        var books = new BooksResponse(bookQueryHandler.getBooks().stream()
            .map(BookMapper::mapBookToBookResponse)
            .toList());
        return ResponseEntity.ok(books);
    }

    @Operation(summary = "Get books by id", description = "Get a book by its id")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successful operation", content = @Content(schema = @Schema(implementation = BookResponse.class))),
        @ApiResponse(responseCode = "404", description = "Not found", content = @Content(schema = @Schema(implementation = BookNotFoundException.class)))
    })
    @GetMapping(value = "{bookId}")
    public ResponseEntity<BookResponse> getBookById(@PathVariable("bookId") String bookId) {
        return ResponseEntity.ok(BookMapper.mapBookToBookResponse(bookQueryHandler.getBook(BookId.of(bookId))));
    }
}

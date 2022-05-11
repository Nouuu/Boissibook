package org.esgi.boissibook.features.book_search.infra.web;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.esgi.boissibook.features.book_search.domain.BookSearchQueryHandler;
import org.esgi.boissibook.features.book_search.infra.models.BookSearchResponse;
import org.esgi.boissibook.infra.web.HandledExceptionResponse;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Book search controller", description = "Search book through external service")
@RestController
@RequestMapping(value = "/book-search", produces = MediaType.APPLICATION_JSON_VALUE)
public class BookSearchRequestController {
    private final BookSearchQueryHandler bookSearchQueryHandler;

    public BookSearchRequestController(BookSearchQueryHandler bookSearchQueryHandler) {
        this.bookSearchQueryHandler = bookSearchQueryHandler;
    }

    @Operation(summary = "Search book", description = "Return a list of books matching the query search")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation", content = @Content(schema = @Schema(implementation = BookSearchResponse.class))),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(schema = @Schema(implementation = HandledExceptionResponse.class)))
    })
    @GetMapping("/")
    public ResponseEntity<BooksResponse> search(@RequestParam(name = "searchQuery") String query) {
        var books = bookSearchQueryHandler.searchBooks(query);
        return ResponseEntity.ok(BookSearchWebMapper.toBooksResponse(books));
    }

    @Operation(summary = "Search book", description = "Return a list of books matching the query search")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation", content = @Content(schema = @Schema(implementation = BookSearchResponse.class))),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(schema = @Schema(implementation = HandledExceptionResponse.class)))
    })
    @GetMapping("/{bookId}")
    public ResponseEntity<BookResponse> getBook(@PathVariable(name = "bookId") String bookId) {
        var book = bookSearchQueryHandler.getBook(bookId);
        return ResponseEntity.ok(BookSearchWebMapper.toBookResponse(book));
    }
}

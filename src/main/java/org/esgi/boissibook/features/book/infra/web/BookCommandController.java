package org.esgi.boissibook.features.book.infra.web;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import javax.validation.Valid;
import org.esgi.boissibook.features.book.domain.BookCommandHandler;
import org.esgi.boissibook.features.book.infra.BookMapper;
import org.esgi.boissibook.features.book.infra.web.request.AddBookRequest;
import org.esgi.boissibook.features.book.infra.web.response.BookIdResponse;
import org.esgi.boissibook.features.book.kernel.exception.BookNotFoundException;
import org.esgi.boissibook.features.book_search.domain.Book;
import org.esgi.boissibook.features.book_search.domain.BookSearchQueryHandler;
import org.esgi.boissibook.infra.web.HandledExceptionResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Book controller", description = "Book features")
@RestController
@RequestMapping(value = "books", produces = MediaType.APPLICATION_JSON_VALUE)
public class BookCommandController {
    private final BookCommandHandler bookCommandHandler;
    private final BookSearchQueryHandler bookSearchQueryHandler;

    public BookCommandController(BookCommandHandler bookCommandHandler, BookSearchQueryHandler bookSearchQueryHandler) {
        this.bookCommandHandler = bookCommandHandler;
        this.bookSearchQueryHandler = bookSearchQueryHandler;
    }

    @Operation(summary = "Add book", description = "Add a book with an id provided")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Created", content = @Content(schema = @Schema(implementation = String.class))),
        @ApiResponse(responseCode = "409", description = "Conflict", content = @Content(schema = @Schema(implementation = HandledExceptionResponse.class))),
        @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(schema = @Schema(implementation = HandledExceptionResponse.class)))
    })
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BookIdResponse> addBook(@Valid @RequestBody AddBookRequest addBookRequest) {
        Book searchedBook = bookSearchQueryHandler.getBook(addBookRequest.apiId());
        return ResponseEntity.status(HttpStatus.CREATED.value())
            .body(new BookIdResponse(bookCommandHandler.addBook(BookMapper.mapBookSearchToBook(searchedBook))));
    }

    @Operation(summary = "Delete book", description = "Delete a book by its id")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "No content", content = @Content(schema = @Schema(implementation = String.class))),
        @ApiResponse(responseCode = "404", description = "Not found", content = @Content(schema = @Schema(implementation = BookNotFoundException.class)))
    })
    @DeleteMapping(value = "{bookId}")
    public ResponseEntity<Void> deleteBook(@PathVariable("bookId") String bookId) {
        bookCommandHandler.deleteBook(bookId);
        return ResponseEntity.ok()
            .build();
    }
}

package org.esgi.boissibook.features.book_file.infra.web;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.esgi.boissibook.features.book_file.domain.BookFileCommandHandler;
import org.esgi.boissibook.features.book_file.infra.BookFileMapper;
import org.esgi.boissibook.features.book_file.infra.web.request.BookFileUploadRequest;
import org.esgi.boissibook.features.book_file.infra.web.response.BookFileIdResponse;
import org.esgi.boissibook.features.book_file.infra.web.response.BookFileSearchResponse;
import org.esgi.boissibook.features.book_file.kernel.exception.ZipCompressionException;
import org.esgi.boissibook.infra.web.HandledExceptionResponse;
import org.esgi.boissibook.kernel.repository.BookFileId;
import org.esgi.boissibook.kernel.repository.BookId;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.io.IOException;

@Tag(name = "Book file controller", description = "Book files features")
@RestController
@RequestMapping(value = "book-files", produces = MediaType.APPLICATION_JSON_VALUE)
public class BookFileCommandController {
    private final BookFileCommandHandler bookFileCommandHandler;

    public BookFileCommandController(BookFileCommandHandler bookFileCommandHandler) {
        this.bookFileCommandHandler = bookFileCommandHandler;
    }

    @Operation(summary = "Add book file", description = "Add a new file to a book")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Created", content = @Content(schema = @Schema(implementation = BookFileIdResponse.class))),
        @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(schema = @Schema(implementation = HandledExceptionResponse.class))),
        @ApiResponse(responseCode = "500", description = "Internal error", content = @Content(schema = @Schema(implementation = ZipCompressionException.class)))
    })
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<BookFileIdResponse> uploadBookFile(@Valid @ModelAttribute BookFileUploadRequest request) throws IOException {
        var newBookFile = BookFileMapper.mapWebBookFileToBookFile(request.bookId(), request.userId(), request.file());
        BookFileId bookFileId = bookFileCommandHandler.createBookFile(newBookFile);
        return ResponseEntity.status(HttpStatus.CREATED)
            .body(new BookFileIdResponse(bookFileId.value()));
    }

    @Operation(summary = "Search book file", description = "Delete a book file by id")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "No content", content = @Content(schema = @Schema(implementation = BookFileIdResponse.class))),
        @ApiResponse(responseCode = "404", description = "Bad request", content = @Content(schema = @Schema(implementation = HandledExceptionResponse.class))),
        @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(schema = @Schema(implementation = HandledExceptionResponse.class)))
    })
    @PostMapping(value = "search/{bookId}")
    public ResponseEntity<BookFileSearchResponse> searchBookFile(@PathVariable("bookId") String bookId) {
        var bookFileSearchStatus = bookFileCommandHandler.searchBookFile(BookId.of(bookId));
        var response = new BookFileSearchResponse(bookFileSearchStatus.status(), bookFileSearchStatus.data());
        return ResponseEntity.ok()
            .body(response);
    }

    @Operation(summary = "Delete book file", description = "Delete a book file by id")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "No content", content = @Content(schema = @Schema(implementation = BookFileIdResponse.class))),
        @ApiResponse(responseCode = "404", description = "Bad request", content = @Content(schema = @Schema(implementation = HandledExceptionResponse.class))),
        @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(schema = @Schema(implementation = HandledExceptionResponse.class)))
    })
    @DeleteMapping(value = "{bookFileId}")
    public ResponseEntity<Void> deleteBookFile(@PathVariable("bookFileId") String bookFileId) {
        bookFileCommandHandler.deleteBookFile(BookFileId.of(bookFileId));
        return ResponseEntity.noContent()
            .build();
    }
}

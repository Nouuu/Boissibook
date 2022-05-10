package org.esgi.boissibook.features.book_file.infra.web;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.io.IOException;
import javax.validation.Valid;
import org.esgi.boissibook.features.book_file.domain.BookFileCommandHandler;
import org.esgi.boissibook.features.book_file.infra.BookFileMapper;
import org.esgi.boissibook.features.book_file.infra.web.request.BookFileUploadRequest;
import org.esgi.boissibook.features.book_file.infra.web.response.BookFileIdResponse;
import org.esgi.boissibook.features.book_file.infra.web.response.BookFileResponse;
import org.esgi.boissibook.features.book_search.infra.models.BookSearchResponse;
import org.esgi.boissibook.infra.web.HandledExceptionResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
        @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(schema = @Schema(implementation = HandledExceptionResponse.class)))
    })
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<BookFileIdResponse> uploadBookFile(@Valid @ModelAttribute BookFileUploadRequest request) throws IOException {
        var newBookFile = BookFileMapper.mapWebBookFileToBookFile(request.bookId(), request.userId(), request.file());
        String bookFileId = bookFileCommandHandler.createBookFile(newBookFile);
        return ResponseEntity.status(HttpStatus.CREATED)
            .body(new BookFileIdResponse(bookFileId));
    }
}

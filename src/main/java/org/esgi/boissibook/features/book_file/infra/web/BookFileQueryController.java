package org.esgi.boissibook.features.book_file.infra.web;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.esgi.boissibook.features.book_file.domain.BookFileQueryHandler;
import org.esgi.boissibook.features.book_file.infra.BookFileMapper;
import org.esgi.boissibook.features.book_file.infra.web.response.BookFilesResponse;
import org.esgi.boissibook.infra.web.HandledExceptionResponse;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Book file controller", description = "Book files features")
@RestController
@RequestMapping(value = "book-files", produces = MediaType.APPLICATION_JSON_VALUE)
public class BookFileQueryController {

    private final BookFileQueryHandler bookFileQueryHandler;

    public BookFileQueryController(BookFileQueryHandler bookFileQueryHandler) {
        this.bookFileQueryHandler = bookFileQueryHandler;
    }

    @Operation(summary = "Get book files", description = "Return a list of book files for a given book id")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successful operation", content = @Content(schema = @Schema(implementation = BookFilesResponse.class))),
        @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(schema = @Schema(implementation = HandledExceptionResponse.class)))
    })
    @GetMapping(value = "/book/{bookId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BookFilesResponse> getBookFiles(@PathVariable("bookId") String bookId) {
        var bookFiles = bookFileQueryHandler.getBookFiles(bookId);
        return ResponseEntity.ok()
            .body(new BookFilesResponse(bookFiles.stream()
                .map(BookFileMapper::mapBookFileToBookFileResponse)
                .toList()));
    }

    @Operation(summary = "Download a book", description = "Return a list of books matching the query search")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successful operation", content = @Content(schema = @Schema(implementation = ByteArrayResource.class))),
        @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(schema = @Schema(implementation = HandledExceptionResponse.class)))
    })
    @GetMapping(value = "/{bookFileId}/download", produces = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ByteArrayResource> downloadBookFile(@PathVariable("bookFileId") String bookFileId) {
        var bookFile = bookFileQueryHandler.getBookFileById(bookFileId);
        ByteArrayResource resource = new ByteArrayResource(bookFile.content());

        return ResponseEntity.ok()
            .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + bookFile.name())
            .body(resource);
    }
}

package org.esgi.boissibook.features.bookfile.infra.web;

import io.swagger.v3.oas.annotations.tags.Tag;
import java.io.IOException;
import java.util.List;
import org.esgi.boissibook.features.bookfile.domain.BookFileQueryHandler;
import org.esgi.boissibook.features.bookfile.infra.BookFileMapper;
import org.esgi.boissibook.features.bookfile.infra.web.response.BookFileResponse;
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

    @GetMapping(value = "/book/{bookId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<BookFileResponse>> getBookFiles(@PathVariable("bookId") String bookId) {
        var bookFiles = bookFileQueryHandler.getBookFiles(bookId);
        return ResponseEntity.ok()
                .body(bookFiles.stream()
                        .map(BookFileMapper::mapBookFileToBookFileResponse)
                        .toList());
    }

    @GetMapping(value = "/{bookFileId}/download", produces = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ByteArrayResource> downloadBookFile(@PathVariable("bookFileId") String bookFileId) throws IOException {
        var bookFile = bookFileQueryHandler.getBookFileById(bookFileId);
        ByteArrayResource resource = new ByteArrayResource(bookFile.content());

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + bookFile.name())
                .body(resource);
    }
}

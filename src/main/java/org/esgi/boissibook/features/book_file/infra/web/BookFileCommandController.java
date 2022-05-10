package org.esgi.boissibook.features.book_file.infra.web;

import io.swagger.v3.oas.annotations.tags.Tag;
import java.io.IOException;
import javax.validation.Valid;
import org.esgi.boissibook.features.book_file.domain.BookFileCommandHandler;
import org.esgi.boissibook.features.book_file.infra.BookFileMapper;
import org.esgi.boissibook.features.book_file.infra.web.request.BookFileUploadRequest;
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

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> uploadBookFile(@Valid @ModelAttribute BookFileUploadRequest request) throws IOException {
        var newBookFile = BookFileMapper.mapWebBookFileToBookFile(request.bookId(), request.userId(), request.file());
        String bookFileId = bookFileCommandHandler.createBookFile(newBookFile);
        return ResponseEntity.ok()
                .body(bookFileId);
    }
}

package org.esgi.boissibook.features.readlist.infra.web;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.esgi.boissibook.features.readlist.domain.BookReviewQueryHandler;
import org.esgi.boissibook.features.readlist.infra.web.response.BookReviewResponse;
import org.esgi.boissibook.features.readlist.infra.web.response.BookReviewsResponse;
import org.esgi.boissibook.infra.web.HandledExceptionResponse;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Readlist controller", description = "Readlist features")
@RestController
@RequestMapping(value = "book-review", produces = MediaType.APPLICATION_JSON_VALUE)
public class ReadlistQueryController {
    private final BookReviewQueryHandler bookReviewQueryHandler;

    public ReadlistQueryController(BookReviewQueryHandler bookReviewQueryHandler) {
        this.bookReviewQueryHandler = bookReviewQueryHandler;
    }

    @Operation(summary = "Get progression by book review id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation",
                    content = @Content(schema = @Schema(implementation = BookReviewResponse.class))
            ),
            @ApiResponse(responseCode = "404", description = "book review not found",
                    content = @Content(schema = @Schema(implementation = HandledExceptionResponse.class))
            ),
    })
    @GetMapping(value = "/{id}")
    public ResponseEntity<BookReviewResponse> getBookReviewById(@PathVariable("id") String id) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Operation(summary = "Get review by book id and user id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation",
                    content = @Content(schema = @Schema(implementation = BookReviewResponse.class))
            ),
            @ApiResponse(responseCode = "404", description = "book review not found",
                    content = @Content(schema = @Schema(implementation = HandledExceptionResponse.class))
            )
    })
    @GetMapping(value = "/{bookId}/{userId}")
    public ResponseEntity<BookReviewResponse> getBookReviewProgressionByBookIdAndUserId(
            @PathVariable("bookId") String bookId,
            @PathVariable("userId") String userId
    ) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Operation(summary = "Get all review by user id")
    @ApiResponse(responseCode = "200", description = "Successful operation")
    @GetMapping(value = "/{userId}")
    public ResponseEntity<BookReviewsResponse> getAllReviewOfAUser(@PathVariable("userId") String userId) {
        throw new UnsupportedOperationException("Not implemented yet");
    }
}

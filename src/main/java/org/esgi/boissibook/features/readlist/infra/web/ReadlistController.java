package org.esgi.boissibook.features.readlist.infra.web;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.esgi.boissibook.features.readlist.infra.web.request.*;
import org.esgi.boissibook.features.readlist.infra.web.response.BookReviewIdResponse;
import org.esgi.boissibook.features.readlist.infra.web.response.BookReviewResponse;
import org.esgi.boissibook.infra.web.HandledExceptionResponse;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Tag(name = "Readlist controller", description = "Readlist features")
@RestController
@RequestMapping(value = "book-review", produces = MediaType.APPLICATION_JSON_VALUE)
public class ReadlistController {
    @Operation(summary = "Get progression by book review id")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successful operation",
            content = @Content(schema = @Schema(implementation = BookReviewResponse.class))
        ),
        @ApiResponse(responseCode = "404", description = "book review not found",
            content = @Content(schema = @Schema(implementation = HandledExceptionResponse.class))
        )
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
    public ResponseEntity<List<BookReviewResponse>> getAllReviewOfAUser(@PathVariable("userId") String userId) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Operation(summary = "Create a new book review")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Successful operation",
            content = @Content(schema = @Schema(implementation = BookReviewIdResponse.class))
        ),
        @ApiResponse(responseCode = "400", description = "invalid book review",
            content = @Content(schema = @Schema(implementation = HandledExceptionResponse.class))
        )
    })
    @PostMapping
    public ResponseEntity<BookReviewIdResponse> createBookReview(
            @Valid  @RequestBody CreateBookReviewRequest createBookProgressionRequest
    ) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Operation(summary = "Update a book review")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successful operation"),
        @ApiResponse(responseCode = "400", description = "invalid book review",
            content = @Content(schema = @Schema(implementation = HandledExceptionResponse.class))
        ),
        @ApiResponse(responseCode = "404", description = "book review not found",
            content = @Content(schema = @Schema(implementation = HandledExceptionResponse.class))
        )
    })
    @PutMapping(value = "/{id}")
    public ResponseEntity<Void> updateBookProgression(
        @PathVariable("id") String id,
        @RequestBody UpdateBookReviewRequest updateBookReviewRequest
    ) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Operation(summary = "Delete a book progression")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Successful operation"),
        @ApiResponse(responseCode = "404", description = "book review not found",
            content = @Content(schema = @Schema(implementation = HandledExceptionResponse.class))
        )
    })
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteBookReview(@PathVariable("id") String id) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Operation(summary = "Update comment for a book review")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successful operation"),
        @ApiResponse(responseCode = "400", description = "invalid comment",
            content = @Content(schema = @Schema(implementation = HandledExceptionResponse.class))
        ),
        @ApiResponse(responseCode = "404", description = "book review not found",
            content = @Content(schema = @Schema(implementation = HandledExceptionResponse.class))
        )
    })
    @PatchMapping(value = "/{id}/comment")
    public ResponseEntity<Void> updateComment(@PathVariable("id") String id, @Valid @RequestBody CommentRequest newComment) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Operation(summary = "Update status for a book progression")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successful operation"),
        @ApiResponse(responseCode = "400", description = "invalid status",
            content = @Content(schema = @Schema(implementation = HandledExceptionResponse.class))
        ),
        @ApiResponse(responseCode = "404", description = "book review not found",
            content = @Content(schema = @Schema(implementation = HandledExceptionResponse.class))
        )
    })
    @PatchMapping(value = "/{id}/status")
    public ResponseEntity<Void> updateStatus(@PathVariable("id") String id, @Valid @RequestBody StatusRequest newStatus) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Operation(summary = "Update progress for a book review")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successful operation"),
        @ApiResponse(responseCode = "400", description = "invalid progress",
            content = @Content(schema = @Schema(implementation = HandledExceptionResponse.class))
        ),
        @ApiResponse(responseCode = "404", description = "book review not found",
            content = @Content(schema = @Schema(implementation = HandledExceptionResponse.class))
        )
    })
    @PatchMapping(value = "/{id}/progress")
    public ResponseEntity<Void> updateProgress(@PathVariable("id") String id, @Valid @RequestBody ProgressRequest newProgress) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Operation(summary = "Update review for a book")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successful operation"),
        @ApiResponse(responseCode = "400", description = "invalid review",
            content = @Content(schema = @Schema(implementation = HandledExceptionResponse.class))
        ),
        @ApiResponse(responseCode = "404", description = "book review not found",
            content = @Content(schema = @Schema(implementation = HandledExceptionResponse.class))
        )
    })
    @PatchMapping(value = "/{id}/review")
    public ResponseEntity<Void> updateReview(@PathVariable("id") String id, @Valid @RequestBody ReviewRequest newReview) {
        throw new UnsupportedOperationException("Not implemented yet");
    }
}

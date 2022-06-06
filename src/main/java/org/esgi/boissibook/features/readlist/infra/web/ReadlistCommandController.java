package org.esgi.boissibook.features.readlist.infra.web;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.esgi.boissibook.features.readlist.domain.BookReview;
import org.esgi.boissibook.features.readlist.domain.BookReviewCommandHandler;
import org.esgi.boissibook.features.readlist.infra.mapper.ReviewMapper;
import org.esgi.boissibook.features.readlist.infra.web.request.*;
import org.esgi.boissibook.features.readlist.infra.web.response.BookReviewIdResponse;
import org.esgi.boissibook.infra.web.HandledExceptionResponse;
import org.esgi.boissibook.kernel.repository.BookReviewId;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;

@Tag(name = "Readlist controller", description = "Readlist features")
@RestController
@RequestMapping(value = "book-review", produces = MediaType.APPLICATION_JSON_VALUE)
public class ReadlistCommandController {
    private final BookReviewCommandHandler bookReviewCommandHandler;

    public ReadlistCommandController(BookReviewCommandHandler bookReviewCommandHandler) {
        this.bookReviewCommandHandler = bookReviewCommandHandler;
    }


    @Operation(summary = "Create a new book review")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Successful operation",
            content = @Content(schema = @Schema(implementation = BookReviewIdResponse.class))
        ),
        @ApiResponse(responseCode = "400", description = "invalid book review",
            content = @Content(schema = @Schema(implementation = HandledExceptionResponse.class))
        ),
        @ApiResponse(responseCode = "409", description = "a review of this user already exists",
            content = @Content(schema = @Schema(implementation = HandledExceptionResponse.class))
        ),
    })
    @PostMapping
    public ResponseEntity<BookReviewIdResponse> createBookReview(
            @Valid  @RequestBody CreateBookReviewRequest createBookProgressionRequest
    ) {
        var createReview = ReviewMapper.toReview(createBookProgressionRequest);
        var bookReviewId = bookReviewCommandHandler.createReview(createReview);
        return ResponseEntity.created(URI.create(bookReviewId.value())).body(new BookReviewIdResponse(bookReviewId.value()));
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
        var updateReview = ReviewMapper.toReview(updateBookReviewRequest);
        bookReviewCommandHandler.updateReview(BookReviewId.of(id), updateReview);
        return ResponseEntity.ok().build();
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
        bookReviewCommandHandler.deleteReview(BookReviewId.of(id));
        return ResponseEntity.noContent().build();
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
        bookReviewCommandHandler.updateComment(BookReviewId.of(id), newComment.comment());
        return ResponseEntity.ok().build();
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
        bookReviewCommandHandler.updateStatus(BookReviewId.of(id), newStatus.status());
        return ResponseEntity.ok().build();
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
        bookReviewCommandHandler.updateCurrentPage(BookReviewId.of(id), newProgress.currentPage());
        return ResponseEntity.ok().build();
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
        bookReviewCommandHandler.updateRating(BookReviewId.of(id), newReview.note());
        return ResponseEntity.ok().build();
    }
}

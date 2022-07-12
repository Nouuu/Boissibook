package org.esgi.boissibook.features.readlist.infra.web;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.esgi.boissibook.features.book.domain.BookRepository;
import org.esgi.boissibook.features.readlist.domain.BookReview;
import org.esgi.boissibook.features.readlist.domain.BookReviewCommandHandler;
import org.esgi.boissibook.features.readlist.domain.BookReviewQueryHandler;
import org.esgi.boissibook.features.readlist.infra.mapper.ReviewMapper;
import org.esgi.boissibook.features.readlist.infra.web.request.CommentRequest;
import org.esgi.boissibook.features.readlist.infra.web.request.CreateBookReviewRequest;
import org.esgi.boissibook.features.readlist.infra.web.request.ProgressRequest;
import org.esgi.boissibook.features.readlist.infra.web.request.ReviewRequest;
import org.esgi.boissibook.features.readlist.infra.web.request.StatusRequest;
import org.esgi.boissibook.features.readlist.infra.web.request.UpdateBookReviewRequest;
import org.esgi.boissibook.features.readlist.infra.web.response.BookReviewIdResponse;
import org.esgi.boissibook.features.readlist.kernel.exception.BookNotFoundException;
import org.esgi.boissibook.features.readlist.kernel.exception.ReviewAlreadyExistException;
import org.esgi.boissibook.features.readlist.kernel.exception.UserNotFoundException;
import org.esgi.boissibook.features.user.domain.UserRepository;
import org.esgi.boissibook.infra.web.HandledExceptionResponse;
import org.esgi.boissibook.kernel.exception.NotFoundException;
import org.esgi.boissibook.kernel.repository.BookReviewId;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Tag(name = "Readlist controller", description = "Readlist features")
@RestController
@RequestMapping(value = "book-review", produces = MediaType.APPLICATION_JSON_VALUE)
public class ReadlistCommandController {
    private final BookReviewCommandHandler bookReviewCommandHandler;
    private final BookReviewQueryHandler bookReviewQueryHandler;
    private final BookRepository bookRepository;
    private final UserRepository userRepository;

    public ReadlistCommandController(BookReviewCommandHandler bookReviewCommandHandler,
         BookRepository bookRepository,
         UserRepository userRepository,
         BookReviewQueryHandler bookReviewQueryHandler
    ) {
        this.bookReviewCommandHandler = bookReviewCommandHandler;
        this.bookReviewQueryHandler = bookReviewQueryHandler;
        this.bookRepository = bookRepository;
        this.userRepository = userRepository;
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
        @Valid @RequestBody CreateBookReviewRequest createBookProgressionRequest
    ) {
        var createReview = ReviewMapper.toReview(createBookProgressionRequest);
        verifyReview(createReview);
        var bookReviewId = bookReviewCommandHandler.createReview(createReview);
        return ResponseEntity.created(linkTo(methodOn(ReadlistQueryController.class)
            .getBookReviewById(bookReviewId.toString())).toUri())
            .body(new BookReviewIdResponse(bookReviewId.value()));
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
        var updateReview = ReviewMapper.toReview(BookReviewId.of(id), updateBookReviewRequest);
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

    private void verifyReview(BookReview createReview) {
        try {
            bookRepository.find(createReview.getBookId());
        } catch (NotFoundException exception) {
            throw new BookNotFoundException("Book with id: " + createReview.getBookId() + " not found");
        }
        try {
            userRepository.find(createReview.getUserId());
        } catch (NotFoundException exception) {
            throw new UserNotFoundException("User with id: " + createReview.getUserId() + " not found");
        }

        var isRegistered = bookReviewQueryHandler.isAlreadyReviewByUser(
            createReview.getBookId(),
            createReview.getUserId()
        );
        if (isRegistered) {
            throw new ReviewAlreadyExistException("You have already review this book");
        }
    }
}

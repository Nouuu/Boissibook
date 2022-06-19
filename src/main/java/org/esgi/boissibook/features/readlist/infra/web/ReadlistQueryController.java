package org.esgi.boissibook.features.readlist.infra.web;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.esgi.boissibook.features.readlist.domain.BookReviewQueryHandler;
import org.esgi.boissibook.features.readlist.infra.mapper.ReviewMapper;
import org.esgi.boissibook.features.readlist.infra.web.response.BookReviewResponse;
import org.esgi.boissibook.features.readlist.infra.web.response.BookReviewsResponse;
import org.esgi.boissibook.infra.web.HandledExceptionResponse;
import org.esgi.boissibook.kernel.repository.BookId;
import org.esgi.boissibook.kernel.repository.BookReviewId;
import org.esgi.boissibook.kernel.repository.UserId;
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
        var bookReview = bookReviewQueryHandler.getBookReviewById(BookReviewId.of(id));
        return ResponseEntity.ok(ReviewMapper.toResponse(bookReview));
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
    @GetMapping(value = "/book/{bookId}/user/{userId}")
    public ResponseEntity<BookReviewResponse> getBookReviewProgressionByBookIdAndUserId(
        @PathVariable("bookId") String bookId,
        @PathVariable("userId") String userId
    ) {
        var bookReview = bookReviewQueryHandler.getBookReviewByBookIdAndUserId(
            BookId.of(bookId),
            UserId.of(userId)
        );
        return ResponseEntity.ok(ReviewMapper.toResponse(bookReview));
    }

    @Operation(summary = "Get all review by user id")
    @ApiResponse(responseCode = "200", description = "Successful operation")
    @GetMapping(value = "/user/{userId}")
    public ResponseEntity<BookReviewsResponse> getAllReviewOfAUser(@PathVariable("userId") String userId) {
        var bookReviews = bookReviewQueryHandler.getAllReviewOfAUser(UserId.of(userId));
        var listOfReviews = bookReviews.stream().map(ReviewMapper::toResponse).toList();
        return ResponseEntity.ok(new BookReviewsResponse(listOfReviews));
    }
}

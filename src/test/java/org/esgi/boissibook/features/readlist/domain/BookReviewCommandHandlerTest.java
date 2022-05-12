package org.esgi.boissibook.features.readlist.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class BookReviewCommandHandlerTest {

   @Autowired
   public BookReviewCommandHandler bookReviewCommandHandler;
   @Autowired
   public BookReviewRepository bookReviewRepository;

   BookReview bookReview1;

   @BeforeEach
   void setUp() {
      bookReview1 = new BookReview(
        null,
        "book1",
        "user1",
        Visibility.PUBLIC,
        ReadingStatus.COMPLETED,
        320,
        3,
        "review1"
      );
   }
   @Test
   void createBookReview() {
      var bookReviewId = bookReviewCommandHandler.createReview(bookReview1);
      assert(bookReviewRepository.find(bookReviewId)).isPresent();
   }
}

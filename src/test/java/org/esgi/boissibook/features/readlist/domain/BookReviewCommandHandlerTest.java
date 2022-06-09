package org.esgi.boissibook.features.readlist.domain;

import org.esgi.boissibook.features.readlist.infra.config.SpringBookReviewBeans;
import org.esgi.boissibook.infra.SpringEventService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.annotation.DirtiesContext;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.annotation.DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD;

@Import({SpringBookReviewBeans.class, SpringEventService.class})
@DirtiesContext(classMode = BEFORE_EACH_TEST_METHOD)
@DataJpaTest
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
      assertThat(bookReviewId).isNotNull();
      assertThat(bookReviewRepository.find(bookReviewId)).isNotNull()
              .isEqualTo(bookReview1.setBookReviewId(bookReviewId));
   }
}

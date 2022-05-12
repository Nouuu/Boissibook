package org.esgi.boissibook.features.readlist.infra.repository;

import org.esgi.boissibook.features.readlist.domain.BookReview;
import org.esgi.boissibook.features.readlist.domain.BookReviewRepository;

import java.util.Optional;

public class SpringDataBookReviewRepository implements BookReviewRepository {

    @Override
    public String nextId() {
        return null;
    }

    @Override
    public void save(BookReview bookReview) {

    }

    @Override
    public Optional<BookReview> find(String bookReviewId) {
        return Optional.empty();
    }
}

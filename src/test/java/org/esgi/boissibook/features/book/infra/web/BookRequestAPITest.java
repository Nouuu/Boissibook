package org.esgi.boissibook.features.book.infra.web;

import org.esgi.boissibook.PostgresIntegrationTest;
import org.esgi.boissibook.features.book.infra.repository.JPABookRepository;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.springframework.test.annotation.DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD;

@DirtiesContext(classMode = BEFORE_EACH_TEST_METHOD)
@Testcontainers
@SpringBootTest
@ActiveProfiles("test")
public class BookRequestAPITest extends PostgresIntegrationTest {

    @Autowired
    JPABookRepository jpaBookRepository;

    @BeforeEach
    void setUp() {

    }
}

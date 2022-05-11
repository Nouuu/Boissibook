package org.esgi.boissibook.features.book_search.infra.search_engine;

import org.esgi.boissibook.features.book_search.infra.models.BookItem;
import org.esgi.boissibook.features.book_search.infra.models.BookSearchResponse;
import org.esgi.boissibook.features.book_search.kernel.exception.GoogleBookGetException;
import org.esgi.boissibook.features.book_search.kernel.exception.GoogleBookNotfoundException;
import org.esgi.boissibook.features.book_search.kernel.exception.GoogleBookSearchException;
import org.springframework.http.HttpStatus;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

public class GoogleBookRestSearchEngine implements RestSearchEngine {

    private static final String GOOGLE_BOOK_API_URL = "https://www.googleapis.com/books/v1/volumes";

    @Override
    public BookSearchResponse search(String query) {
        WebClient webClient = WebClient.builder().baseUrl(GOOGLE_BOOK_API_URL).build();
        try {
            return webClient.get()
                    .uri(uriBuilder -> uriBuilder
                            .queryParam("q", query)
                            .build()
                    )
                    .retrieve()
                    .bodyToMono(BookSearchResponse.class)
                    .block();
        } catch (WebClientResponseException e) {
            throw new GoogleBookSearchException(
                    String.format("%s : %s", e.getStatusCode(), e.getMessage())
            );
        }
    }

    @Override
    public BookItem getBookItem(String id) {
        WebClient webClient = WebClient.builder().baseUrl(GOOGLE_BOOK_API_URL).build();
        try {
            return webClient.get()
                    .uri(uriBuilder -> uriBuilder
                            .path("/{id}")
                            .build(id)
                    )
                    .retrieve()
                    .bodyToMono(BookItem.class)
                    .block();
        } catch (WebClientResponseException e) {
            if (e.getStatusCode() == HttpStatus.NOT_FOUND) {
                throw new GoogleBookNotFoundException(String.format("%s", e.getMessage()));
            }
            throw new GoogleBookGetException(
                    String.format("%s : %s", e.getStatusCode(), e.getMessage())
            );
        }
    }
}

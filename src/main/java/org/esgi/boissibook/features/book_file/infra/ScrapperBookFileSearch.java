package org.esgi.boissibook.features.book_file.infra;

import org.esgi.boissibook.features.book.domain.Book;
import org.esgi.boissibook.features.book_file.domain.BookFileSearch;
import org.esgi.boissibook.features.book_file.domain.BookFileSearchStatus;
import org.esgi.boissibook.features.book_file.infra.web.request.BookFileSearchRequest;
import org.esgi.boissibook.features.book_file.kernel.exception.ScrapperBookFileSearchException;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

public class ScrapperBookFileSearch implements BookFileSearch {
    private final String scrapperApiUrl;

    public ScrapperBookFileSearch(String scrapperApiUrl) {
        this.scrapperApiUrl = scrapperApiUrl;
    }

    @Override
    public BookFileSearchStatus searchBookFile(Book book) {
        BookFileSearchRequest request = new BookFileSearchRequest(book.id(), book.isbn13());
        WebClient webClient = WebClient.builder().baseUrl(scrapperApiUrl).build();
        try {
            return webClient.post()
                .uri(uriBuilder -> uriBuilder
                    .path("/book")
                    .build()
                )
                .accept(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(request))
                .retrieve()
                .bodyToMono(BookFileSearchStatus.class)
                .block();
        } catch (WebClientResponseException e) {
            throw new ScrapperBookFileSearchException(
                String.format("%s : %s", e.getStatusCode(), e.getMessage())
            );
        }
    }
}

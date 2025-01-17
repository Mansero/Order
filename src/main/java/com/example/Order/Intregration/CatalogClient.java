package com.example.Order.Intregration;

import com.example.Order.enums.GenreEnum;
import com.example.Order.model.Book;
import io.github.resilience4j.retry.annotation.Retry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
public class CatalogClient {

    private final String catalogServiceUrl;
    private final RestClient restClient;
    private static final Logger log = LoggerFactory.getLogger(CatalogClient.class);

    public CatalogClient(RestClient.Builder restClientBuilder, @Value("${catalog.service.url}") String catalogServiceUrl) {
        this.restClient = restClientBuilder.build();
        this.catalogServiceUrl = catalogServiceUrl;
    }

    @Retry(name = "findBookRetry", fallbackMethod = "testFallbackArray")
    public Book[] findBooks(String searchTerm) {
        return restClient
                .get()
                .uri(catalogServiceUrl + "/api/books/search?searchTerm=%s".formatted(searchTerm))
                .retrieve()
                .body(Book[].class);
    }

    @Retry(name = "findAllRetry", fallbackMethod = "testFallbackArray")
    public Book[] findAll() {
        return restClient
                .get()
                .uri(catalogServiceUrl + "/api/books/all")
                .retrieve()
                .body(Book[].class);
    }

    @Retry(name = "getBookRetry", fallbackMethod = "testFallback")
    public Book getBook(String isbn) {
        return restClient
                .get()
                .uri(catalogServiceUrl + "/api/books/%s".formatted(isbn))
                .retrieve()
                .body(Book.class);
    }

    public Book[] testFallbackArray(Exception e) throws Exception {
        log.error("All retries exhausted", e);
        throw e;
    }

    public Book testFallback(Exception e) throws Exception {
        log.error("All retries exhausted", e);
        throw e;
    }
}

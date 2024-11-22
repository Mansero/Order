package com.example.Order.Intregration;

import com.example.Order.model.Book;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
public class CatalogClient {

    private final RestClient restClient;

    public CatalogClient(RestClient.Builder restClientBuilder) {
        this.restClient = restClientBuilder.build();
    }

    public Book[] findBooks(String searchTerm) {
        return restClient
                .get()
                .uri("http://localhost:8080/api/search?searchTerm=%s".formatted(searchTerm))
                .retrieve()
                .body(Book[].class);
    }

    public Book[] findAll() {
        return restClient
                .get()
                .uri("http://localhost:8080/api/all")
                .retrieve()
                .body(Book[].class);
    }

    public Book getBook(String isbn) {
        return restClient
                .get()
                .uri("http://localhost:8080/api/books/%s".formatted(isbn))
                .retrieve()
                .body(Book.class);
    }

}

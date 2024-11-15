package com.example.Order.model;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    List<Book> findByTitleContainingIgnoreCase(String title);

    List<Book> findByDescriptionContainingIgnoreCase(String description);

    List<Book> findByAuthorContainingIgnoreCase(String author);

    default List<Book> searchBooks(String searchTerm) {
        if (searchTerm == null || searchTerm.isEmpty()) {
            return findAll();
        }

        List<Book> result = new ArrayList<>();

        result.addAll(findByTitleContainingIgnoreCase(searchTerm));
        result.addAll(findByDescriptionContainingIgnoreCase(searchTerm));
        result.addAll(findByAuthorContainingIgnoreCase(searchTerm));

        return result.stream()
                .distinct()
                .collect(Collectors.toList());
    }
}



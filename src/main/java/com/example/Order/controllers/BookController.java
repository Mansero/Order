package com.example.Order.controllers;

import com.example.Order.Intregration.CatalogClient;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("books")
public class BookController {

    private final CatalogClient catalogClient;

    public BookController(CatalogClient catalogClient) {
        this.catalogClient = catalogClient;
    }

    @GetMapping("/all")
    public String getAllBooks(Model model) {
        model.addAttribute("books", catalogClient.findAll());
        return "bookList";
    }


    @GetMapping("/search")
    public String searchBooks(Model model, String searchTerm) {
        model.addAttribute("books", catalogClient.findBooks(searchTerm));
        return "bookSearch";
    }

}



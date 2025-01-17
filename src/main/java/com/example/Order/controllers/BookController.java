package com.example.Order.controllers;

import com.example.Order.Intregration.CatalogClient;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestClientException;

@Controller
@RequestMapping("books")
public class BookController {

    private final CatalogClient catalogClient;

    public BookController(CatalogClient catalogClient) {
        this.catalogClient = catalogClient;
    }

    @GetMapping("/all")
    public String getAllBooks(Model model) {
        try {
            model.addAttribute("books", catalogClient.findAll());
            return "bookList";
        } catch (Exception e) {
            return "errorPage";
        }
    }

    @GetMapping("/search")
    public String searchBooks(Model model, String searchTerm) {
        try{
        model.addAttribute("books", catalogClient.findBooks(searchTerm));
        return "bookSearch";
        } catch (RestClientException e) {
            model.addAttribute("errorMessage", "Es ist ein Fehler aufgetreten. Bitte versuchen Sie es erneut.");
            return "errorPage";
        }
    }
}



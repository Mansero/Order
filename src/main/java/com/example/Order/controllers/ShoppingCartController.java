package com.example.Order.controllers;

import com.example.Order.Intregration.CatalogClient;
import com.example.Order.model.Book;
import com.example.Order.model.ShoppingCart;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.annotation.SessionScope;

@SessionScope
@Controller
@RequestMapping("shopping-cart")
public class ShoppingCartController {

    private final ShoppingCart shoppingCart = new ShoppingCart();

    private final CatalogClient catalogClient;

    public ShoppingCartController(CatalogClient catalogClient) {
        this.catalogClient = catalogClient;
    }

    @GetMapping("shopping-cart")
    public String catalog(Model model) {
        var items = shoppingCart.getItems();
        double total = shoppingCart.getItems().stream()
                .map(item -> item.getBook().getPrice())
                .reduce(0.0, (a, b) -> a + b);
        model.addAttribute("items", items);
        model.addAttribute("total", total);
        return "shoppingCart";
    }
    @GetMapping("/add/{isbn}")
    public String add(Model model, @PathVariable String isbn) {
        Book book = catalogClient.getBook(isbn);

        if (book != null) {
            shoppingCart.addBook(book);
        }
        return catalog(model);
    }

}

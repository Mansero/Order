package com.example.Order.model;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.ArrayList;
import java.util.List;


public class ShoppingCart {
    //Attribute
    List<Book> shoppingCard;

    //Constructor
    public ShoppingCart() {
        shoppingCard = new ArrayList<Book>();
    }

    //Getter&Setter
    public List<Book> getShoppingCard() {
        return shoppingCard;
    }

    public List<Book> setShoppingCard(List<Book> shoppingCard) {
        this.shoppingCard = shoppingCard;
        return shoppingCard;
    }

}

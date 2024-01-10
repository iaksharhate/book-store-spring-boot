package com.bookstore.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@RequiredArgsConstructor
public class Cart {

    @Id
    @Column(name = "cart_Id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int cart_Id;

    @ManyToOne
    @JoinColumn(name = "user_Id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "book_Id")
    private Book book;

    private int quantity;
//    private double totalPrice;
}

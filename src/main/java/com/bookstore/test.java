//package com.bookstore;
//
//import com.bookstore.model.Book;
//import com.google.gson.Gson;
//
//import java.util.Arrays;
//import java.util.List;
//
//public class test {
//    public static void main(String[] args) {
//        Gson gson = new Gson();
//        Cart cart = new Cart();
//        List<Book> bookList = Arrays.asList(new Book(1, "The 5 AM Club", "Robin Sharma", "Christopher Cantwell", 10, 250.0, "abc"),
//                new Book(2, "The 5 AM Club", "Robin Sharma", "Christopher Cantwell", 5, 200.0, "abc"),
//                new Book(3, "The 5 AM Club", "Robin Sharma", "Christopher Cantwell", 3, 100.0, "abc")
//        );
//
//        cart.setBookList(bookList);
//
//        System.out.println(gson.toJson(cart.getBookList()));
//
////        double totalPrice = 0.0;
////        cart.getBookList().forEach(book -> {
////
////            double price = book.getPrice();
////            double quantity = book.getQuantity();
////            totalPrice += price * quantity;
////
////            System.out.println("Total Price : "+ totalPrice);
////        });
//
//
//        double totalPrice = cart.getBookList().stream().mapToDouble(book -> book.getPrice() * book.getQuantity()).sum();
//        System.out.println("Total Price : "+ totalPrice);
//        cart.setTotalPrice(totalPrice);
//        System.out.println(gson.toJson(cart));
//
//
//    }
//}

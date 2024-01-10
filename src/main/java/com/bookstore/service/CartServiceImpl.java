package com.bookstore.service;

import com.bookstore.dto.CartDto;
import com.bookstore.dto.MasterResponse;
import com.bookstore.exception.CustomException;
import com.bookstore.model.Book;
import com.bookstore.model.Cart;
import com.bookstore.model.User;
import com.bookstore.repository.IBookRepository;
import com.bookstore.repository.ICartRepository;
import com.bookstore.repository.IUserRepository;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class CartServiceImpl implements ICartService {

    @Autowired
    private Gson gson;
    @Autowired
    private ICartRepository cartRepository;
    @Autowired
    private IUserRepository userRepository;
    @Autowired
    private IBookRepository bookRepository;

    @Override
    public MasterResponse addCart(CartDto cartDto) {

        log.info("ADD-CART API SERVICE REQUEST : {}", gson.toJson(cartDto));

        MasterResponse masterResponse = new MasterResponse();

        Cart cart = new Cart();

        Optional<User> user = userRepository.findById(cartDto.getUserId());
        Optional<Book> book = bookRepository.findById(cartDto.getBookId());

        if (user.isPresent() && book.isPresent()) {
            cart.setUser(user.get());
            cart.setBook(book.get());
            cart.setQuantity(cartDto.getQuantity());
//            cart.setTotalPrice(calculateTotalPrice(cartDto.quantity, book.get().getPrice()));
            Cart savedCart = cartRepository.save(cart);

            if (savedCart != null) {
                masterResponse.setStatus("S");
                masterResponse.setCode("200");
                masterResponse.setPayload(savedCart);
            } else {
                masterResponse.setStatus("F");
                masterResponse.setCode("500");
                masterResponse.setPayload("Something went wrong while adding cart!");
            }
        }

        log.info("ADD-CART API SERVICE RESPONSE : {}", gson.toJson(masterResponse));

        return masterResponse;
    }

    @Override
    public MasterResponse byUserId(int userId) {

        log.info("GET-CART-BY-USER-ID API SERVICE REQUEST : {}", gson.toJson(userId));

        MasterResponse masterResponse = new MasterResponse();

        try {

            List<Cart> cartList = cartRepository.getCartByUserId(userId);

            if (!cartList.isEmpty()) {
                masterResponse.setStatus("S");
                masterResponse.setCode("200");
                masterResponse.setPayload(cartList);
            } else {
                masterResponse.setStatus("F");
                masterResponse.setCode("500");
                masterResponse.setPayload("Something went wrong while fetching cart details!");
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new CustomException("Something went wrong while fetching cart details!");
        }

        log.info("GET-CART-BY-USER-ID API SERVICE RESPONSE : {}", gson.toJson(masterResponse));

        return masterResponse;
    }

//    private double calculateTotalPrice(int quantity, double price) {
//        return quantity * price;
//    }
}

package com.bookstore.service;

import com.bookstore.dto.CartDto;
import com.bookstore.dto.MasterResponse;

public interface ICartService {
    MasterResponse addCart(CartDto cartDto);
}

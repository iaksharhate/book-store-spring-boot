package com.bookstore.controller;

import com.bookstore.dto.CartDto;
import com.bookstore.dto.MasterResponse;
import com.bookstore.service.ICartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private ICartService cartService;

    @PostMapping("/addCart")
    public ResponseEntity<MasterResponse> addCart(@RequestBody CartDto cartDto){
        return new ResponseEntity<>(cartService.addCart(cartDto), HttpStatus.OK);
    }

    @GetMapping("getById/{userId}")
    public ResponseEntity<MasterResponse> getByUserId(@PathVariable (value = "userId") int userId){
        return new ResponseEntity<>(cartService.byUserId(userId), HttpStatus.OK);
    }
}

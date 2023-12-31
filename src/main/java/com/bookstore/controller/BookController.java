package com.bookstore.controller;

import com.bookstore.dto.MasterResponse;
import com.bookstore.model.Book;
import com.bookstore.service.IBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    private IBookService bookService;

    @PostMapping("/addBook")
    public ResponseEntity<MasterResponse> addBook(@RequestBody Book book){
        return new ResponseEntity<>(bookService.addBook(book), HttpStatus.OK);
    }

    @GetMapping("/getAll")
    public ResponseEntity<MasterResponse> getAllBooks(){
        return new ResponseEntity<>(bookService.getAllBooks(), HttpStatus.OK);
    }
}

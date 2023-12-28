package com.bookstore.service;

import com.bookstore.dto.MasterResponse;
import com.bookstore.exception.CustomException;
import com.bookstore.model.Book;
import com.bookstore.repository.IBookRepository;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class BookServiceImpl implements IBookService {

    @Autowired
    private Gson gson;
    @Autowired
    private IBookRepository bookRepository;

    @Override
    public MasterResponse addBook(Book book) {
        log.info("ADD-BOOK-API SERVICE REQUEST : {}", gson.toJson(book));
        MasterResponse masterResponse = new MasterResponse();
        try {
            Book savedBook = bookRepository.save(book);
            masterResponse.setStatus("S");
            masterResponse.setCode("200");
            masterResponse.setPayload(savedBook);
        } catch (Exception e) {
            throw new CustomException("Something went wrong while adding book");
        }
        log.info("ADD-BOOK-API SERVICE RESPONSE : {}", gson.toJson(masterResponse));
        return masterResponse;
    }
}

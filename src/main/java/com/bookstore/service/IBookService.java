package com.bookstore.service;

import com.bookstore.dto.MasterResponse;
import com.bookstore.model.Book;

public interface IBookService {
    MasterResponse addBook(Book book);
}

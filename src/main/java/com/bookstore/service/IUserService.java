package com.bookstore.service;

import com.bookstore.dto.MasterResponse;
import com.bookstore.model.User;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public interface IUserService {
    MasterResponse addUser(User user, MultipartFile profileImage);

    MasterResponse getById(int id);

    MasterResponse updateUser(String email, User user);


    MasterResponse updateImage(int id, MultipartFile file);
}

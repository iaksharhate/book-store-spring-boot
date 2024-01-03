package com.bookstore.service;

import com.bookstore.dto.MasterResponse;
import com.bookstore.exception.CustomException;
import com.bookstore.model.User;
import com.bookstore.repository.IUserRepository;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private IUserRepository userRepository;
    @Autowired
    private Gson gson;
    private final String FOLDER_PATH = "C:/Users/iaksh/Desktop/BookStore-Profile/";

    @Override
    public MasterResponse addUser(User user, MultipartFile profileImage) {

        log.info("ADD-USER API SERVICE REQUEST : {}", gson.toJson(user));

        MasterResponse masterResponse = new MasterResponse();

        try {
            String imagePath = FOLDER_PATH + UUID.randomUUID() + "-" + profileImage.getOriginalFilename();
            user.setProfile(imagePath);
            User savedUser = userRepository.save(user);
            profileImage.transferTo(new File(imagePath));
            masterResponse.setStatus("S");
            masterResponse.setCode("200");
            masterResponse.setPayload(savedUser);

        } catch (Exception exception) {
            exception.printStackTrace();
            masterResponse.setStatus("F");
            masterResponse.setCode("500");
            masterResponse.setPayload("Something went wrong while adding user");
        }

        log.info("ADD-USER API SERVICE RESPONSE : {}", gson.toJson(masterResponse));
        return masterResponse;
    }

    @Override
    public MasterResponse getById(int id) {

        log.info("GET-USER-BY-ID API SERVICE REQUEST : {}", gson.toJson(id));

        MasterResponse masterResponse = new MasterResponse();

        try {

            Optional<User> user = userRepository.findById(id);

            if (user.isEmpty()) {
                masterResponse.setStatus("F");
                masterResponse.setCode("404");
                masterResponse.setPayload("User with ID " + id + " not found!");
            } else {
                masterResponse.setStatus("S");
                masterResponse.setCode("200");
                masterResponse.setPayload(user.get());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        log.info("GET-USER-BY-ID API SERVICE RESPONSE : {}", gson.toJson(masterResponse));

        return masterResponse;
    }

    @Override
    public MasterResponse updateUser(String email, User user) {

        log.info("UPDATE-USER-BY-ID API SERVICE REQUEST : {} {}", email, gson.toJson(user));

        MasterResponse masterResponse = new MasterResponse();

        try {

            User existingUser = userRepository.getUserByEmail(email);

            if (existingUser == null){
                masterResponse.setStatus("F");
                masterResponse.setCode("404");
                masterResponse.setPayload("User with email " + email + " not found!");
            } else {
                existingUser.setName(user.getName());
                existingUser.setEmail(user.getEmail());
                existingUser.setPassword(user.getPassword());
                existingUser.setCity(user.getCity());
                existingUser.setState(user.getState());
                existingUser.setPinCode(user.getPinCode());

                User updatedUser = userRepository.save(existingUser);
                masterResponse.setStatus("S");
                masterResponse.setCode("200");
                masterResponse.setPayload(updatedUser);
            }
        } catch (Exception exception){
            exception.printStackTrace();
            throw new CustomException("Something went wrong while updating user!");
        }

        log.info("UPDATE-USER-BY-ID API SERVICE RESPONSE : {}", gson.toJson(masterResponse));

        return masterResponse;
    }



}

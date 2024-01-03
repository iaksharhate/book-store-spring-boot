package com.bookstore.service;

import com.bookstore.dto.MasterResponse;
import com.bookstore.model.User;
import com.bookstore.repository.IUserRepository;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
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

        log.info("ADD-USER-API SERVICE REQUEST : {}", gson.toJson(user));

        MasterResponse masterResponse = new MasterResponse();

        try {
            user.setProfile(saveProfileImage(profileImage));
            User savedUser = userRepository.save(user);
            masterResponse.setStatus("S");
            masterResponse.setCode("200");
            masterResponse.setPayload(savedUser);

        } catch (Exception exception) {
            exception.printStackTrace();
            masterResponse.setStatus("F");
            masterResponse.setCode("500");
            masterResponse.setPayload("Something went wrong while adding user");
        }

        log.info("ADD-USER-API SERVICE RESPONSE : {}", gson.toJson(masterResponse));
        return masterResponse;
    }

    private String saveProfileImage(MultipartFile file) throws IOException {
        String imagePath = FOLDER_PATH + UUID.randomUUID() + "-" + file.getOriginalFilename();
        file.transferTo(new File(imagePath));
        return imagePath;
    }
}

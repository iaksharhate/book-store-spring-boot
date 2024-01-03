package com.bookstore.controller;

import com.bookstore.dto.MasterResponse;
import com.bookstore.model.User;
import com.bookstore.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserService userService;

    @PostMapping("/addUser")
    public ResponseEntity<MasterResponse> addUser(@RequestParam("profileImage")MultipartFile profileImage,
                                                  @ModelAttribute User user){
        return new ResponseEntity<>(userService.addUser(user, profileImage), HttpStatus.OK);
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<MasterResponse> getById(@PathVariable (value = "id") int id ){
        return new ResponseEntity<>(userService.getById(id), HttpStatus.OK);
    }
}

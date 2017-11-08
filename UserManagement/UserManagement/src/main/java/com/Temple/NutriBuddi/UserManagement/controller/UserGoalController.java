package com.Temple.NutriBuddi.UserManagement.controller;

import com.Temple.NutriBuddi.UserManagement.model.User;
import com.Temple.NutriBuddi.UserManagement.repository.UserGoalRepository;
import com.Temple.NutriBuddi.UserManagement.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping(path="/userGoal")
public class UserGoalController {
    @Autowired
    private UserGoalRepository userGoalRepository;
    @Autowired
    private UserRepository userRepository;

    private Logger logger = LoggerFactory.getLogger(UserGoalController.class);


    @GetMapping(path="/deleteUserGoal")
    @ResponseBody
    public ResponseEntity<Object> deleteUserGoal(@RequestParam String email){
        ResponseEntity response;
        User user;

        if(email.equals("") || email == null){
            response = new ResponseEntity("email must not be empty", HttpStatus.NOT_ACCEPTABLE);
        }else {
             user = userRepository.findByEmail(email);
            if(user == null){
                response = new ResponseEntity("user not found with given email", HttpStatus.NOT_ACCEPTABLE);
            } else {
                userGoalRepository.deleteByUserId(user.getId());
                response = new ResponseEntity("delete successful", HttpStatus.OK);
            }
        }
        return response;
    }


}

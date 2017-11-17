package com.Temple.NutriBuddi.UserManagement.controller;

import com.Temple.NutriBuddi.UserManagement.model.Food;
import com.Temple.NutriBuddi.UserManagement.model.User;
import com.Temple.NutriBuddi.UserManagement.repository.EatsRepository;
import com.Temple.NutriBuddi.UserManagement.repository.FoodRepository;
import com.Temple.NutriBuddi.UserManagement.repository.ImageRepository;
import com.Temple.NutriBuddi.UserManagement.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.sql.rowset.serial.SerialBlob;
import javax.xml.ws.Response;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.logging.Logger;

@Controller
@RequestMapping("imageClassifier")
public class ImageController {

    @Autowired
    ImageRepository imageRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    EatsRepository eatsRepository;
    @Autowired
    FoodRepository foodRepository;

    private static Logger LOG = Logger.getLogger(ImageController.class.getName());


    @GetMapping(path="/addImage") // Map ONLY GET Requests
    @ResponseBody
    public ResponseEntity<Object> getImageAsResource (@RequestParam String email,
                                                      @RequestParam String imageString,
                                                      @RequestParam String foodName,
                                                      @RequestParam String fileName) {
        ResponseEntity response;
        Food food;
        User user;
        if(imageString.equals("") || imageString.equals(null)){
            response = new ResponseEntity("Image must not be empty", HttpStatus.NOT_ACCEPTABLE);
        }
        if(email.equals("") || email.equals(null)){
            response = new ResponseEntity("Email must not be empty", HttpStatus.NOT_ACCEPTABLE);

        }
        if(foodName.equals("") || foodName.equals(null)){
            response = new ResponseEntity("Food name must not be empty", HttpStatus.NOT_ACCEPTABLE);

        }
        if(fileName.equals("") || fileName.equals(null)){
            response = new ResponseEntity("File name must not be empty", HttpStatus.NOT_ACCEPTABLE);

        }

        food = foodRepository.findByFoodName(foodName);
        if(food == null){
            //set number
        } else {

        }

        user = userRepository.findByEmail(email);
        if(user == null){
            response = new ResponseEntity("User not found with provided email", HttpStatus.CONFLICT);
        }


        byte[] bytes = imageString.getBytes();
        try{
            Blob blob = new SerialBlob(bytes);

        }catch(SQLException e){
            LOG.info(e.getMessage());
            response = new ResponseEntity("Error converting uri image", HttpStatus.CONFLICT);
        }

        return null;
    }

    @GetMapping(path="/deleteImage")
    @ResponseBody
    public ResponseEntity<Object> deleteImageResource (@RequestParam String email,
                                                       @RequestParam String imageString,
                                                       @RequestParam String fileName) {
        ResponseEntity response = null;
        User user;

        if(imageString.equals("") || imageString.equals(null)){
            response = new ResponseEntity("Image must not be empty", HttpStatus.NOT_ACCEPTABLE);
        }
        if(email.equals("") || email.equals(null)){
            response = new ResponseEntity("Email must not be empty", HttpStatus.NOT_ACCEPTABLE);

        }
        if(fileName.equals("") || fileName.equals(null)){
            response = new ResponseEntity("File name must not be empty", HttpStatus.NOT_ACCEPTABLE);

        }

        user = userRepository.findByEmail(email);
        if(user == null){
            response = new ResponseEntity("User not found with provided email", HttpStatus.CONFLICT);
        }



        return response;
    }
}

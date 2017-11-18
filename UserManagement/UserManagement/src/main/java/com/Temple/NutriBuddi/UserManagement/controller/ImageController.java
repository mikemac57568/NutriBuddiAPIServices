package com.Temple.NutriBuddi.UserManagement.controller;

import com.Temple.NutriBuddi.UserManagement.model.Eats;
import com.Temple.NutriBuddi.UserManagement.model.Food;
import com.Temple.NutriBuddi.UserManagement.model.Image;
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
    private ImageRepository imageRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private EatsRepository eatsRepository;
    @Autowired
    private FoodRepository foodRepository;

    private static Logger LOG = Logger.getLogger(ImageController.class.getName());


    @GetMapping(path="/addNewImage") // Map ONLY GET Requests
    @ResponseBody
    public ResponseEntity<Object> addNewImage(@RequestParam String email,
                                                      @RequestParam String imageString,
                                                      @RequestParam String foodName,
                                                      @RequestParam String fileName,
                                                      @RequestParam String numServing) {
        ResponseEntity response;
        Food food;
        User user;
        Eats eats;
        Image image;

        int classificationNumber;
        if(imageString.equals("") || imageString == null){
            response = new ResponseEntity("Image must not be empty", HttpStatus.NOT_ACCEPTABLE);
        } else if(email.equals("") || email == null){
            response = new ResponseEntity("Email must not be empty", HttpStatus.NOT_ACCEPTABLE);

        } else if(foodName.equals("") || foodName == null){
            response = new ResponseEntity("Food name must not be empty", HttpStatus.NOT_ACCEPTABLE);

        } else if(fileName.equals("") || fileName == null){
            response = new ResponseEntity("File name must not be empty", HttpStatus.NOT_ACCEPTABLE);

        } else {

            if (foodName.toLowerCase().equals("unknown")) {
                classificationNumber = 0;
            } else if (foodName.length() > 0) {
                classificationNumber = 1;
            } else {
                classificationNumber = 2;
            }
            food = foodRepository.findByFoodName(foodName);
            if (food == null) {
                //Check if it is a valid food by checking online using foodName

                //if it is a real type of food, then add it to the database

                //else if it is unknown add food group

                //else if it's fake tell user that this isn't a known/real food item
                food = new Food("Unknown", "mg", 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
            }
            response = null;        //TODO: Delete this for refactor and fill out food
            user = userRepository.findByEmail(email);
            if (user == null) {
                response = new ResponseEntity("User not found with provided email", HttpStatus.CONFLICT);
            }

            try {
                byte[] bytes = imageString.getBytes();
                Blob imageBlob = new SerialBlob(bytes);
                image = new Image(foodName, fileName, imageBlob);
                eats = new Eats(user, Integer.parseInt(numServing), food, image, classificationNumber);
                imageRepository.save(image);
                eatsRepository.save(eats);
                if (imageString != null)
                    response = new ResponseEntity("Image added", HttpStatus.OK);
            } catch (SQLException e) {
                LOG.info(e.getMessage());
                response = new ResponseEntity("Error converting uri image", HttpStatus.CONFLICT);
            } catch (NumberFormatException e) {
                LOG.info(e.getMessage());
                response = new ResponseEntity("Error casting number of servings", HttpStatus.CONFLICT);
            }
        }
        return response;
    }

    @GetMapping(path="/deleteImage")
    @ResponseBody
    public ResponseEntity<Object> deleteImageResource (@RequestParam String email,
                                                       @RequestParam String imageString,
                                                       @RequestParam String fileName) {
        ResponseEntity response = null;
        User user;

        if(imageString.equals("") || imageString  == null){
            response = new ResponseEntity("Image must not be empty", HttpStatus.NOT_ACCEPTABLE);
        }
        if(email.equals("") || email == null){
            response = new ResponseEntity("Email must not be empty", HttpStatus.NOT_ACCEPTABLE);

        }
        if(fileName.equals("") || fileName == null){
            response = new ResponseEntity("File name must not be empty", HttpStatus.NOT_ACCEPTABLE);

        }

        user = userRepository.findByEmail(email);
        if(user == null){
            response = new ResponseEntity("User not found with provided email", HttpStatus.CONFLICT);
        }



        return response;
    }
}

package com.Temple.NutriBuddi.UserManagement.controller;

import com.Temple.NutriBuddi.UserManagement.validation.Rules.*;
import com.Temple.NutriBuddi.UserManagement.validation.ValidationResponse;
import com.Temple.NutriBuddi.UserManagement.validation.ValidationRule;
import com.Temple.NutriBuddi.UserManagement.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.Temple.NutriBuddi.UserManagement.model.User;
import com.Temple.NutriBuddi.UserManagement.repository.UserRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Logger;

@Controller    // This means that this class is a Controller
@RequestMapping(path="/user") // This means URL's start with /v1 (after Application path)
public class UserController {
	@Autowired // This means to get the bean called userRepository
	           // Which is auto-generated by Spring, we will use it to handle the data
	private UserRepository userRepository;
	static Logger LOG = Logger.getLogger(UserController.class.getName());

	@GetMapping(path="/addNewUser") // Map ONLY GET Requests
	@ResponseBody
	public ResponseEntity<Object> addNewUser (@RequestParam String email
			, @RequestParam String password
			, @RequestParam String password2
			, @RequestParam String userName
			, @RequestParam String first
			, @RequestParam String last
			, @RequestParam String height
			, @RequestParam String weight
			, @RequestParam String age
			, @RequestParam String gender) {

		ValidationResponse validationResponse;

		UserVO userVO = new UserVO(email, password, password2, userName, first, last,
						height, weight, age, gender, userRepository);

		ArrayList<ValidationRule> rules = new ArrayList<>();
		rules.add(new EmailValidationRule());
		rules.add(new PasswordValidationRule());
		rules.add(new FirstNameValidationRule());
		rules.add(new LastNameValidationRule());
		rules.add(new HeightValidationRule());
		rules.add(new WeightValidationRule());
		rules.add(new AgeValidationRule());
		rules.add(new GenderValidationRule());

		for(ValidationRule rule: rules){
			validationResponse = rule.validate(userVO);
			LOG.info(validationResponse.toString());
			if(!validationResponse.getStatus().is2xxSuccessful()){
				LOG.info("Status failure: " + validationResponse.getStatus());
				return new ResponseEntity<>(validationResponse.getResponseBody(), validationResponse.getStatus());
			}
		}
		int h = Integer.parseInt(height);
		int w = Integer.parseInt(weight);
		int a = Integer.parseInt(age);
		int g = Integer.parseInt(gender);
        User user = new User(email, password, userName, first, last, h, w, a, g);

		userRepository.save(user);
		return new ResponseEntity<>("User added", HttpStatus.OK);
	}
	
	@GetMapping(path="/updateUser") // Map ONLY GET Requests
	@ResponseBody
	public ResponseEntity<Object> updateUser (@RequestParam String email
			, @RequestParam String password
			, @RequestParam String password2
			, @RequestParam String height
			, @RequestParam String weight
			, @RequestParam String age
			, @RequestParam String gender) {

        User user;

        if (!email.equals("")) {
			if (userRepository.findByEmail(email) != null) {
                user = userRepository.findByEmail(email);
			} else {
				return new ResponseEntity<>("Email does not exist", HttpStatus.NOT_ACCEPTABLE);
			}
		} else {
			return new ResponseEntity<>("Valid email required", HttpStatus.NOT_ACCEPTABLE);
		}

		if (!password.equals("")) {
			if (password.equals(password2)) {
                user.setPassword(password);
			} else {
				return new ResponseEntity<>("Passwords must match", HttpStatus.NOT_ACCEPTABLE);
			}
		} else {
			return new ResponseEntity<>("Password required", HttpStatus.NOT_ACCEPTABLE);
		}

		if (!height.equals("")) {
            user.setHeight(Integer.parseInt(height));
		} else {
			return new ResponseEntity<>("Height required", HttpStatus.NOT_ACCEPTABLE);
		}

		if (!weight.equals("")) {
            user.setWeight(Integer.parseInt(weight));
		} else {
			return new ResponseEntity<>("Weight required", HttpStatus.NOT_ACCEPTABLE);
		}

		if (!age.equals("")) {
            user.setAge(Integer.parseInt(age));
		} else {
			return new ResponseEntity<>("Age required", HttpStatus.NOT_ACCEPTABLE);
		}

		if (!gender.equals("")) {
            user.setGender(Integer.parseInt(gender));
		} else {
			return new ResponseEntity<>("Gender required", HttpStatus.NOT_ACCEPTABLE);
		}
		
		
		userRepository.save(user);
		return new ResponseEntity<>("User Updated", HttpStatus.OK);
	}
	
	@GetMapping(path="/getUser") // Map ONLY GET Requests
	public @ResponseBody User getUser (@RequestParam String email) {
		
		return userRepository.findByEmail(email); 
	
	}

	@GetMapping(path="/all")
	public @ResponseBody Iterable<User> getAllUsers() {

		return userRepository.findAll();
	}
	
	@GetMapping(path="/login")
	@ResponseBody
	public ResponseEntity<Object> userLogin(@RequestParam String email, @RequestParam String password) {
		
		User response = userRepository.findByEmailAndPassword(email, password);
		
		if (response == null)
			return new ResponseEntity<>("User email and password combination does not exist", HttpStatus.UNAUTHORIZED);
		
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
}


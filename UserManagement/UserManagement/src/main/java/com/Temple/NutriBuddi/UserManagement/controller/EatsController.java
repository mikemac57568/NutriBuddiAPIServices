package com.Temple.NutriBuddi.UserManagement.controller;

import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.Temple.NutriBuddi.UserManagement.model.Eats;
import com.Temple.NutriBuddi.UserManagement.model.Food;
import com.Temple.NutriBuddi.UserManagement.repository.EatsRepository;
import com.Temple.NutriBuddi.UserManagement.repository.FoodRepository;
import com.Temple.NutriBuddi.UserManagement.repository.UserRepository;

@Controller    
@RequestMapping(path="/eats")
public class EatsController {
	@Autowired
	private EatsRepository eatsRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private FoodRepository foodRepository;
	
	static Logger LOG = Logger.getLogger(EatsController.class.getName());

	@GetMapping(path="/addNewEats") // Map ONLY GET Requests
	@ResponseBody
	public ResponseEntity<Object> addNewEats (@RequestParam String email
		, @RequestParam String numServings
		, @RequestParam String foodName) {
	
		int s;
		try {
			s = Integer.parseInt(numServings);
		} catch (NumberFormatException e ) {
			return new ResponseEntity<>("Number of servings is invalid", HttpStatus.NOT_ACCEPTABLE);
		}
				
		if (userRepository.findByEmail(email) == null) {
			return new ResponseEntity<>("Email does not exist", HttpStatus.NOT_ACCEPTABLE);
		}
		if (foodRepository.findByFoodName(foodName) == null) {
			return new ResponseEntity<>(foodName + " does not exist", HttpStatus.NOT_ACCEPTABLE);
		}
		if (email.equals("tug25055@temple.edu")) {
			return new ResponseEntity<>("tug25055@temple.edu TERMINATION SCHEDULED. NUTRITIONAL MAINTENANCE IRRELLEVANT", HttpStatus.I_AM_A_TEAPOT);
		}
		
		Eats eats = new Eats(userRepository.findByEmail(email), s, foodRepository.findByFoodName(foodName));
		
		eatsRepository.save(eats);
		return new ResponseEntity<>("Eat transaction added", HttpStatus.OK);
	}
	

}

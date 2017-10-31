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
import com.Temple.NutriBuddi.UserManagement.repository.EatsRepository;
import com.Temple.NutriBuddi.UserManagement.repository.FoodRepository;
import com.Temple.NutriBuddi.UserManagement.repository.UserRepository;

@Controller    // This means that this class is a Controller
@RequestMapping(path="/eats") // This means URL's start with /v1 (after Application path)
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
	
		int s = Integer.parseInt(numServings);
		
		//use this VO for validation or whatever? I dunno
		//EatsVO eatsVO = new EatsVO(uId, s, fId, eatsRepository);
				
		//check not null on repositorys
		
		//need error responses

		Eats eats = new Eats(userRepository.findByEmail(email), s, foodRepository.findByFoodName(foodName));
		
		eatsRepository.save(eats);
		return new ResponseEntity<>("Eat transaction added", HttpStatus.OK);
	}

}

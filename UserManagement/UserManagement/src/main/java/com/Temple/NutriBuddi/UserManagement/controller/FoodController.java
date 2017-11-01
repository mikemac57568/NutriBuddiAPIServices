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
import com.Temple.NutriBuddi.UserManagement.repository.FoodRepository;

@Controller    // This means that this class is a Controller
@RequestMapping(path="/food")
public class FoodController {

	@Autowired
	private FoodRepository foodRepository;
	
	static Logger LOG = Logger.getLogger(EatsController.class.getName());
	
	@GetMapping(path="/getFoodNutrition") // Map ONLY GET Requests
	@ResponseBody
	public ResponseEntity<Object> getFoodNutrition (@RequestParam String foodName) {
		
		if (foodRepository.findByFoodName(foodName) == null) {
			return new ResponseEntity<>(foodName + " does not exist", HttpStatus.NOT_ACCEPTABLE);
		}
	
		return new ResponseEntity<>(foodRepository.findByFoodName(foodName), HttpStatus.OK);
	}

	@GetMapping(path="/addTestFood") // Map ONLY GET Requests
	@ResponseBody
	public ResponseEntity<Object> addTestFood () {
	
		Food quantumKumquat = new Food();
		quantumKumquat.setFoodName("quantumKumquat");
		quantumKumquat.setCalories(0);
		quantumKumquat.setCarbohydrates(0);
		quantumKumquat.setFiber(0);
		quantumKumquat.setIron(0);
		quantumKumquat.setMagnesium(0);
		quantumKumquat.setPhospherous(0);
		quantumKumquat.setPotassium(0);
		quantumKumquat.setProtein(0);
		quantumKumquat.setSatFat(0);
		quantumKumquat.setServingUnit("Quarks");
		quantumKumquat.setSodium(19);
		quantumKumquat.setSugar(11);
		quantumKumquat.setTotalFat(99);
		quantumKumquat.setTransFat(4);
		quantumKumquat.setVitaminC(1);
		quantumKumquat.setVitaminD(69);
		quantumKumquat.setZinc(12);
		
		foodRepository.save(quantumKumquat);
		
		return new ResponseEntity<>("Food added", HttpStatus.OK);
	}
}

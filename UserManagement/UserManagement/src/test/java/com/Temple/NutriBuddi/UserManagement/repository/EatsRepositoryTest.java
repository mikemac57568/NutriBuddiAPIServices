package com.Temple.NutriBuddi.UserManagement.repository;


import java.util.List;

import com.Temple.NutriBuddi.UserManagement.model.Food;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.Temple.NutriBuddi.UserManagement.model.Eats;
import com.Temple.NutriBuddi.UserManagement.model.User;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;


@RunWith(SpringRunner.class)
@Transactional
@SpringBootTest
public class EatsRepositoryTest {
	
    private static Logger log = LoggerFactory.getLogger(EatsRepositoryTest.class);

    @Autowired
	private UserRepository userRepository;
    @Autowired
    private FoodRepository foodRespository;
	@Autowired
    private EatsRepository eatsRepository;

	private String testEmail = "test@testing.com";
	private String testFood = "wapple";


	@Before
	public void setUp() throws Exception {
//		//Insert test data into user repository
		User user = new User(testEmail, "thisisatest;", "test1", "boo",
				"blah", 5, 180, 30, 1);
		userRepository.save(user);
		Integer num1 = new Integer(23);
		Integer num2 = new Integer(1);
//
//		//Insert test data for food repository
		Food food = new Food(testFood, "2", 100, 100, num2, num2, num2, num2, num2, num2, num2, num2, num2, num2, num2, num2, num2, num2);
		foodRespository.save(food);

		//Insert test data for eats repository
		Eats eats = new Eats(user, 17, food);
		eatsRepository.save(eats);
	}

	@Test
	public void testFindByUserId() {
		//Find user by unique email in user repository
		User user = userRepository.findByEmail(testEmail);
		//Use the user's id to find the list of eaten records
		List<Eats> response = eatsRepository.findByUserId(user.getId());
        log.info("findByUserId Response: " + response);
        log.info("response id: " + response.get(0).getId() + "user id: " + user.getId());
        assert(response.get(0).getUser().getId() == user.getId());
        assert(response.get(0).getNumServings() == 17);

	}
	
	@Test
	public void testFindByFoodId() {
		//Find food by unique food name in food repository
		Food food = foodRespository.findByFoodName(testFood);
		//Use food id to find food item in list of eaten records
		List<Eats> response = eatsRepository.findByFoodId(food.getId());
        log.info("findByFoodId Response: " + response);
        assert(response.get(0).getFood().getProtein() == 100);
        assert(response.get(0).getFood().getFoodName().equals(testFood));

	}

	@Test
	public void testFindByFoodName(){
		Food food = foodRespository.findByFoodName(testFood);
		List<Eats> response = eatsRepository.findByFoodName(testFood);
		log.info("findByFoodName Response id: " + response.get(0).getId());
		assert(food.getId() == response.get(0).getFood().getId());
		assertNotNull(response);
		assertTrue("List size is greater than 0",response.size() > 0);

	}

}

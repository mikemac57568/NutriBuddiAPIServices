package com.Temple.NutriBuddi.UserManagement.repository;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.Temple.NutriBuddi.UserManagement.model.Eats;
import com.Temple.NutriBuddi.UserManagement.model.User;



@RunWith(SpringRunner.class)
@Transactional
@SpringBootTest
public class EatsRepositoryTest {
	
    private static Logger log = LoggerFactory.getLogger(EatsRepositoryTest.class);

	@Autowired
    private EatsRepository eatsRepository;
	 
	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testFindByUserId() {
		List<Eats> response = eatsRepository.findByUserId(1);
        log.info("findByUserId Response: " + response);
        assert(response.get(0).getId() == 2);
        assert(response.get(0).getNumServings() == 17);

	}
	
	@Test
	public void testFindByFoodId() {
		List<Eats> response = eatsRepository.findByFoodId(1);
        log.info("findByFoodId Response: " + response);
        assert(response.get(0).getFood().getProtein() == 100);
        assert(response.get(0).getFood().getFoodName().equals("tOMATO"));

	}

}

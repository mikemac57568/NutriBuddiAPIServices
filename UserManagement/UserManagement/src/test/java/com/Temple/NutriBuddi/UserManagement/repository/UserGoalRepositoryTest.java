package com.Temple.NutriBuddi.UserManagement.repository;

import com.Temple.NutriBuddi.UserManagement.model.User;
import com.Temple.NutriBuddi.UserManagement.model.UserGoal;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@Transactional
@SpringBootTest
public class UserGoalRepositoryTest {
    @Autowired
    private UserGoalRepository userGoalRepository;
    @Autowired
    private UserRepository userRepository;

    private Logger log = LoggerFactory.getLogger(UserGoalRepositoryTest.class);

    private ArrayList<User> users;
    private ArrayList<UserGoal> userGoals;

    private String email1 = "thecakeisalie@truths.com";
    private String email2 = "alice@RSA.com";
    private String goal1 = "I want to eat more cake";
    private String goal2 = "I want to hack NutriBuddi";


    @Before
    public void setUp() throws Exception {
        users = new ArrayList<>();
        users.add(new User(email1, "it'sallaconspiracy;", "sugarcrazedstan", "stan",
                "theman", 6, 180, 30, 1));
        users.add(new User(email2, "bob-didn't-encrypt-his-password", "IBrokeRSA", "Alice",
                "NotBob", 5, 110, 25, 0));
        userRepository.save(users);

        userGoals = new ArrayList<>();
        userGoals.add(new UserGoal(users.get(0), 0, 0, 0, 0, 0, 0));
        userGoals.add(new UserGoal(users.get(1), 0, 0, 1, 10, 20, 30));
        userGoalRepository.save(userGoals);

    }

    @Test
    public void deleteByGoalAndId() throws Exception {
        User user = userRepository.findByEmail(email1);
        log.info("email1: " + user.getEmail());
        long status = userGoalRepository.deleteByUserId(user.getId());
        log.info("status: " + status);
        assertTrue(status == 1);
    }

}
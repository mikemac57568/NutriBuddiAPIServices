package com.Temple.NutriBuddi.UserManagement.repository;

import com.Temple.NutriBuddi.UserManagement.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@Transactional
@SpringBootTest
public class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;

    private static final String email = "test1@test.com";


    @Test
    public void testInsertEmail() throws Exception {
        User user = new User();
        user.setEmail(email);
        userRepository.save(user);
        User userRepo = userRepository.findByEmail(email);
        assertEquals(userRepo.getEmail(), user.getEmail());
    }

    @Test
    public void testFindById() throws Exception {

    }

    @Test
    public void testFindByEmailAndPassword() throws Exception {

    }

}
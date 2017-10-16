package com.Temple.NutriBuddi.UserManagement.repository;

import org.springframework.data.repository.CrudRepository;

import com.Temple.NutriBuddi.UserManagement.model.User;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface UserRepository extends CrudRepository<User, Long> {
	
	User findByEmail(String email);
	User findById(int id);
	User findByEmailAndPassword(String email, String password);	
}

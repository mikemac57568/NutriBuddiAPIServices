package com.Temple.NutriBuddi.UserManagement.repository;

import com.Temple.NutriBuddi.UserManagement.model.Eats;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface EatsRepository extends CrudRepository<Eats, Long> {
	List<Eats> findByUserId(int userId);
	List<Eats> findByFoodId(int foodId);
}

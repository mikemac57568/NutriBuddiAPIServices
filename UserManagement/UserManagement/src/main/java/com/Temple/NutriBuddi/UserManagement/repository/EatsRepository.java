package com.Temple.NutriBuddi.UserManagement.repository;

import com.Temple.NutriBuddi.UserManagement.model.Eats;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface EatsRepository extends CrudRepository<Eats, Long> {

	List<Eats> findByUserId(int userId);
	List<Eats> findByFoodId(int foodId);

	@Query("SELECT e FROM Eats e, Food f, User u WHERE f.id = e.food AND u.id = e.user AND f.foodName = :foodName")
	List<Eats> findByFoodName(@Param("foodName") String foodName);
}

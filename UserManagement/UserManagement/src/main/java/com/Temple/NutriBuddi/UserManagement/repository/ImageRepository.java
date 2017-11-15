package com.Temple.NutriBuddi.UserManagement.repository;

import com.Temple.NutriBuddi.UserManagement.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface ImageRepository extends JpaRepository<Image, Long> {

}

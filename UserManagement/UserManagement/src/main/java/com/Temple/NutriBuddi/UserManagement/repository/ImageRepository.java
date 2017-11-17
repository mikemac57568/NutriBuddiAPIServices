package com.Temple.NutriBuddi.UserManagement.repository;

import com.Temple.NutriBuddi.UserManagement.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

public interface ImageRepository extends JpaRepository<Image, Long> {

    Image findByFileName(String fileName);
    Image findById(int id);

    @Transactional
    long deleteByFileName(String fileName);
}

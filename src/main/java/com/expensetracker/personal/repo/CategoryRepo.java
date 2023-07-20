package com.expensetracker.personal.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.expensetracker.personal.entity.CategoryEntity;

public interface CategoryRepo extends JpaRepository<CategoryEntity, String> {
 
}

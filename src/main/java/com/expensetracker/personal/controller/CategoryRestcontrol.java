package com.expensetracker.personal.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.expensetracker.personal.entity.CategoryEntity;
import com.expensetracker.personal.repo.CategoryRepo;

@RestController
public class CategoryRestcontrol {
	@Autowired
	private CategoryRepo crepo;

	@GetMapping("/allcategory")
	public List<CategoryEntity> getAllCategory() {
		return crepo.findAll();
	}
	
}


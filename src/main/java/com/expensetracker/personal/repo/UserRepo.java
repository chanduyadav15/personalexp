package com.expensetracker.personal.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.expensetracker.personal.entity.UserEntity;

public interface UserRepo  extends JpaRepository<UserEntity, String> {

}

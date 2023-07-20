package com.expensetracker.personal.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.expensetracker.personal.entity.PaymentmodeEntity;
import com.expensetracker.personal.entity.PaymentmodeId;

import jakarta.websocket.server.PathParam;

public interface PaymentmodeRepo extends JpaRepository<PaymentmodeEntity, PaymentmodeId> {


	//List<PaymentmodeEntity> findByUser_uname(String username);
    @Query(value="select name from paymentmodes where uname=:uname",nativeQuery = true)
	List<String> getPaymentModes(@PathParam("uname") String uname);
}

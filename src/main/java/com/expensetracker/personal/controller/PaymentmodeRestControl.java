package com.expensetracker.personal.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.expensetracker.personal.entity.PaymentmodeEntity;
import com.expensetracker.personal.entity.PaymentmodeId;
import com.expensetracker.personal.entity.UserEntity;
import com.expensetracker.personal.repo.PaymentmodeRepo;

@RestController

public class PaymentmodeRestControl {

	@Autowired
	private PaymentmodeRepo pr;

	//1
	@PostMapping("/payment/add")
	public PaymentmodeEntity addPay(@RequestBody PaymentmodeEntity newPay) {
	String user =SecurityContextHolder.getContext().getAuthentication().getName();
	if (pr.findById(new PaymentmodeId(newPay.getPaycode(), user)).isPresent()) {
	throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Payment Mode already exists..!");
	}
	UserEntity u = new UserEntity();
	u.setUname(user);
	newPay.setUser(u);
	try {
	return pr.save(newPay);
	} catch (Exception ex) {
	throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Invalid data!");
	}
	}

	//9
	@DeleteMapping("/delete/paymode/{code}")
	public ResponseStatusException dltPayMode(@PathVariable String code) {
	String user = SecurityContextHolder.getContext().getAuthentication().getName();
	var payId = new PaymentmodeId(code, user);
	if (pr.findById(payId).isPresent()) {
	pr.deleteById(payId);
	return new ResponseStatusException(HttpStatus.NO_CONTENT);
	}
	throw new ResponseStatusException(HttpStatus.NOT_FOUND, "payment mode not found...!");
	}
	
	
	@PutMapping("/update/paymode/{code}")
	public PaymentmodeEntity updateMode(@RequestBody PaymentmodeEntity newPay, @PathVariable String code) {
	String user = SecurityContextHolder.getContext().getAuthentication().getName();
	var paymode = pr.findById(new PaymentmodeId(code, user));
	if (paymode.isPresent()) {
	PaymentmodeEntity mode = paymode.get();
	try {
	mode.setPayname(newPay.getPayname());
	mode.setPayremarks(newPay.getPayremarks());
	return pr.save(mode);
	} catch (Exception e) {
	throw new ResponseStatusException(HttpStatus.NO_CONTENT, "Invalid data...");
	}
	} else
	throw new ResponseStatusException(HttpStatus.NOT_FOUND, "payment mode not found for given code");
	}

	@GetMapping("get/all/paymodes")
	public List<String> getUserPaymentModes() {
	String user = SecurityContextHolder.getContext().getAuthentication().getName();
	return pr.getPaymentModes(user);
	}



	
	
}
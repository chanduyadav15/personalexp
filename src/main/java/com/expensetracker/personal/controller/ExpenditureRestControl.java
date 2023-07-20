package com.expensetracker.personal.controller;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
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

import com.expensetracker.personal.entity.ExpenditureEntity;
import com.expensetracker.personal.entity.PaymentmodeId;
import com.expensetracker.personal.repo.CategoryRepo;
import com.expensetracker.personal.repo.ExpenditureRepo;
import com.expensetracker.personal.repo.PaymentmodeRepo;

@RestController

public class ExpenditureRestControl {

	@Autowired
	private ExpenditureRepo er;

	@Autowired
	private PaymentmodeRepo pr;
	@Autowired
	private CategoryRepo cr;


	// 2
	@GetMapping("/get/exp/catcode/{catcode}")
	public List<ExpenditureEntity> getExpByCat(@PathVariable String catcode) {
		String username=SecurityContextHolder.getContext().getAuthentication().getName();
		if (cr.findById(catcode).isEmpty()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invaild cat code");
		}
		return er.findByCategory_CatcodeAndPayment_User_uname(catcode, username, 
				                                               PageRequest.of(0, 2, Sort.by("id")));
	}
 
	// 3
	@GetMapping("/get/exp/paymode/{code}")
	public List<ExpenditureEntity> getExpByUserPayModes(@PathVariable String code) {
	String user = SecurityContextHolder.getContext().getAuthentication().getName();
	var paymode = pr.findById(new PaymentmodeId(code, user));
	if (paymode.isEmpty()) {
	throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid payment mode....!");
	}
	return er.findByPayment_User_unameAndPayment_Paycode(user, code, PageRequest.of(0, 3, Sort.by("id").descending()));
	}
	
	// 4
	@GetMapping("/get/exp/dates/{date1}/{date2}")
	public List<ExpenditureEntity> getExpBydate(@PathVariable Date date1, @PathVariable Date date2) {
		String user=SecurityContextHolder.getContext().getAuthentication().getName();
		return er.findByPayment_User_unameAndSpentonBetween(user, date1, date2,
				                                            PageRequest.of(0, 3, Sort.by("spenton").descending()));
	}

	// 5
	@GetMapping("/get/exp/tag/{tag}")
	public List<ExpenditureEntity> getExpsByTag(@PathVariable String tag) {
		String user=SecurityContextHolder.getContext().getAuthentication().getName();
		return er.findByTagsContainingAndPayment_User_uname(tag,user);
	}
	//6
	@GetMapping("/get/exp/month/{month}")
	public List<String> getTotalAmount(@PathVariable("month")int month){
		String user=SecurityContextHolder.getContext().getAuthentication().getName();
		return er.totalAmount(month,user);
	}

	// 7
	@GetMapping("/exp/top3")
	public List<ExpenditureEntity> getExpsTop3() {
		String user =  SecurityContextHolder.getContext().getAuthentication().getName();
		return er.findTop3ByPayment_User_unameOrderByAmountDesc(user);
	}

	// 8
	@DeleteMapping("/delete/exp/{id}")
	public ResponseStatusException deltexp(@PathVariable int id) {
		String user =  SecurityContextHolder.getContext().getAuthentication().getName();
		if (er.findByIdAndPayment_User_Uname(id, user) == null) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "expenditure not found for user");
		}
		er.deleteById(id);
		return new ResponseStatusException(HttpStatus.NO_CONTENT);
	}
	
	@PutMapping("/exp/update/{id}")
	public ExpenditureEntity updateExp(@RequestBody ExpenditureEntity updatedExp, @PathVariable int id) {
	String user = SecurityContextHolder.getContext().getAuthentication().getName();
	if (er.findByIdAndPayment_User_Uname(id, user)==null) {
	throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Expenditure not found for user");
	}
	// validating payment mode
	var paymentMode = pr.findById(new PaymentmodeId(updatedExp.getPayment().getPaycode(), user));
	if (paymentMode.isEmpty()) {
	throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid payment mode!");
	}
	updatedExp.setPayment(paymentMode.get());
	// validating given catcode
	var category = cr.findById(updatedExp.getCategory().getCatcode());
	if (category.isEmpty()) { // no catcode
	throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid cat code data!");
	}
	      updatedExp.setId(id);
	return er.save(updatedExp);
	}

	// 12
	@PostMapping("/exp/add")
	public ExpenditureEntity getExpByCat(@RequestBody ExpenditureEntity newExp) {
		String user =  SecurityContextHolder.getContext().getAuthentication().getName();
		// validat ing payment mode
		var paymentMode = pr.findById(new PaymentmodeId(newExp.getPayment().getPaycode(), user));
		if (paymentMode.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Invalid payment mode!");
		}
		newExp.setPayment(paymentMode.get());
		// validating catcode
		var category = cr.findById(newExp.getCategory().getCatcode());
		if (category.isEmpty())
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid cat code data!");
		try {
			return er.save(newExp);
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid data....!");
		}
	}

}

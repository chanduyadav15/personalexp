package com.expensetracker.personal.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
public class UserEntity {
	@Id
	@Column(name = "uname")
	private String uname;
	@OneToMany(mappedBy = "user")
	@JsonIgnore
	private List<PaymentmodeEntity> userPay;
	public String getUname() {
		return uname;
	}

	public void setUname(String uname) {
		this.uname = uname;
	}

	public List<PaymentmodeEntity> getUserPay() {
		return userPay;
	}

	public void setUserPay(List<PaymentmodeEntity> userPay) {
		this.userPay = userPay;
	}


	

}

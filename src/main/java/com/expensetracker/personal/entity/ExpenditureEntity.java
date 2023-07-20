package com.expensetracker.personal.entity;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "expenditures")
public class ExpenditureEntity{
	
	
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
@Column(name = "id")
private int id;


@Column(name = "amount")
private Double amount;

@Column(name = "spent_on")
private Date spenton;

@Column(name = "description")
private String description;


@Column(name = "remarks")
private String remarks;

@Column(name = "tags")
private String tags;


@ManyToOne
@JoinColumn(name="catcode")
private CategoryEntity category;
	
	@ManyToOne
	@JoinColumn(name="payment_mode")
	@JoinColumn(name="uname")
	private PaymentmodeEntity payment;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public Date getSpenton() {
		return spenton;
	}

	public void setSpenton(Date spenton) {
		this.spenton = spenton;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getTags() {
		return tags;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}

	public CategoryEntity getCategory() {
		return category;
	}

	public void setCategory(CategoryEntity category) {
		this.category = category;
	}

	public PaymentmodeEntity getPayment() {
		return payment;
	}

	public void setPayment(PaymentmodeEntity payment) {
		this.payment = payment;
	}

	
	
}

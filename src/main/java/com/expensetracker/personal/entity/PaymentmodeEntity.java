package com.expensetracker.personal.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "paymentmodes")
@IdClass(PaymentmodeId.class)
public class PaymentmodeEntity {
	@Id
	@Column(name = "code")
	private String paycode;

	@Column(name = "name")
	private String payname;
	
	@Column(name = "remarks")
	private String payremarks;
    @Id
	@ManyToOne
	@JsonIgnore
	@JoinColumn(name = "uname")
	private UserEntity user;
   @OneToMany(mappedBy = "payment")
   @JsonIgnore
   private List<ExpenditureEntity> payExps;
	public PaymentmodeEntity() {
		super();
	}
	

	public PaymentmodeEntity(String paycode) {
		super();
		this.paycode = paycode;
	}


	public String getPaycode() {
		return paycode;
	}

	public void setPaycode(String paycode) {
		this.paycode = paycode;
	}

	public String getPayname() {
		return payname;
	}

	public void setPayname(String payname) {
		this.payname = payname;
	}

	public String getPayremarks() {
		return payremarks;
	}

	public void setPayremarks(String payremarks) {
		this.payremarks = payremarks;
	}

	public UserEntity getUser() {
		return user;
	}

	public void setUser(UserEntity user) {
		this.user = user;
	}

	public List<ExpenditureEntity> getPayExps() {
		return payExps;
	}

	public void setPayExps(List<ExpenditureEntity> payExps) {
		this.payExps = payExps;
	}

	
}

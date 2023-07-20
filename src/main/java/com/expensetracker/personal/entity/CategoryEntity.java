package com.expensetracker.personal.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "categories")
public class CategoryEntity {
	@Id
	@Column(name = "catcode")
	private String catcode;

	@Column(name = "catname")
	private String catname;
	@OneToMany(mappedBy = "category")
	@JsonIgnore
	private List<ExpenditureEntity> catExps;

	public CategoryEntity() {
		super();
	}

	public CategoryEntity(String catcode) {
		super();
		this.catcode = catcode;
	}

	public String getCatcode() {
		return catcode;
	}

	public void setCatcode(String catcode) {
		this.catcode = catcode;
	}

	public String getCatname() {
		return catname;
	}

	public void setCatname(String catname) {
		this.catname = catname;
	}

	public List<ExpenditureEntity> getCatExps() {
		return catExps;
	}

	public void setCatExps(List<ExpenditureEntity> catExps) {
		this.catExps = catExps;
	}

}

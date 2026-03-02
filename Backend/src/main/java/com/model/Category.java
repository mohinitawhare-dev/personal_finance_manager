package com.model;

import java.util.List;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "category")
public class Category {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long categoryId;
	
	private String name;
	private String type;
	
	
	@OneToMany(mappedBy="category", cascade = CascadeType.ALL,fetch=FetchType.EAGER)
	@JsonIgnore
	private List<Expenses> expenses;
	
	@OneToMany(mappedBy="category",cascade = CascadeType.ALL,fetch=FetchType.EAGER)
	@JsonIgnore
	private List<Budget> budget;

	public Category() {
		super();
	}

	public Category(Long categoryId, String name, String type, List<Expenses> expenses, List<Budget> budget) {
		super();
		this.categoryId = categoryId;
		this.name = name;
		this.type = type;
		this.expenses = expenses;
		this.budget = budget;
	}

	public Long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public List<Expenses> getExpenses() {
		return expenses;
	}

	public void setExpenses(List<Expenses> expenses) {
		this.expenses = expenses;
	}

	public List<Budget> getBudget() {
		return budget;
	}

	public void setBudget(List<Budget> budget) {
		this.budget = budget;
	}

	
}

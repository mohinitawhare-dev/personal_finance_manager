package com.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long userId;
	
	private String username;
	@Column(unique = true)
	private String email;
	private String password;
	
	
	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
	@JsonIgnore
    private List<Incomes> incomes;
	
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	@JsonIgnore
	private List<Expenses> expenses;

	@OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
	@JsonIgnore
	private List<Budget> budget;
	
	
	public User() {
		super();
	}


	public User(Long userId, String username, String email, String password, List<Incomes> incomes,
			List<Expenses> expenses, List<Budget> budget) {
		super();
		this.userId = userId;
		this.username = username;
		this.email = email;
		this.password = password;
		this.incomes = incomes;
		this.expenses = expenses;
		this.budget = budget;
	}


	public Long getUserId() {
		return userId;
	}


	public void setUserId(Long userId) {
		this.userId = userId;
	}


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public List<Incomes> getIncomes() {
		return incomes;
	}


	public void setIncomes(List<Incomes> incomes) {
		this.incomes = incomes;
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
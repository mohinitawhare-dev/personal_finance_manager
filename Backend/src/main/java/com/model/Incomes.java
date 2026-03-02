package com.model;

import java.time.LocalDate;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "incomes")
public class Incomes {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long incomeId;
	
	private Double amount;
	private LocalDate date;
	private String source;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="user_id")
	private User user;
	
	
	public Incomes() {
		super();
	}


	public Incomes(Long incomeId, Double amount, LocalDate date, String source, User user) {
		super();
		this.incomeId = incomeId;
		this.amount = amount;
		this.date = date;
		this.source = source;
		this.user = user;
	}


	public Long getIncomeId() {
		return incomeId;
	}


	public void setIncomeId(Long incomeId) {
		this.incomeId = incomeId;
	}


	public Double getAmount() {
		return amount;
	}


	public void setAmount(Double amount) {
		this.amount = amount;
	}


	public LocalDate getDate() {
		return date;
	}


	public void setDate(LocalDate date) {
		this.date = date;
	}


	public String getSource() {
		return source;
	}


	public void setSource(String source) {
		this.source = source;
	}


	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}
	
}
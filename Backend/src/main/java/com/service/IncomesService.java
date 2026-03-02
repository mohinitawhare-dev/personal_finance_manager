package com.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.http.ResponseEntity;

import com.model.Incomes;

public interface IncomesService {
	
	public Incomes createIncome(Incomes income);

	public ResponseEntity<?> getIncomeById(Long id);
	

	public ResponseEntity<?> deleteIncome(Long id);

	public List<Incomes> getAllIncomes();
	 
	public List<Incomes> getAllIncomesByUser(Long userId);
	
	public Incomes getMaxIncome();
	
	public Incomes getIncomeByAmount(double amount);
	
	public Boolean checkIncomeExists(Long id);
	
	public List<Incomes> getIncomesByDateRange(LocalDate start, LocalDate end);
	
	public List<Incomes> getIncomesAbove50000();
	
	  public Incomes updateIncome(Long id, Incomes incomeDetails);
}

package com.service;

import java.time.LocalDate;
import java.util.List;

import com.model.Expenses;

public interface ExpenseService {
	
	
		public Expenses createExpense(Expenses expense);

	  	public List<Expenses> getAllExpenses();

	  	public Expenses getExpenseById(Long id);

	 	public Expenses updateExpense(Long id, Expenses expenseDetails);

	 	public void deleteExpenses(Long id);
	 	
	 	public List<Expenses> getAllExpensesByUser(Long userId);
	    
	 	public Expenses getMaxExpense();
	    
	    public List<Expenses> getExpensesByCategory(Long categoryId);
	    
	    public Boolean checkExpenseExists(Long id);
	    
	    public List<Expenses> getExpensesByDateRange(Long userId, LocalDate start, LocalDate end);
}




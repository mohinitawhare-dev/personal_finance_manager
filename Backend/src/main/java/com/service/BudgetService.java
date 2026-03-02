package com.service;

import java.util.List;
import com.model.Budget;

public interface BudgetService {
	
	 	Budget createBudget(Budget budget);

	    Budget getBudgetById(Long id);

	    List<Budget> getAllBudgets();

	    Budget updateBudget(Long id, Budget budgetDetails);

	    void deleteBudget(Long id);
	    
	    List<Budget>getBudgetsByMonthAndYear(int month, int year);

	    List<Budget> getMaxBudget();

	    Boolean checkBudgetExists(Long id);
	   
}

package com.dao;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.model.Expenses;

public interface ExpensesRepository  extends JpaRepository<Expenses,Long>{
	
	 
	 	List<Expenses> findByUserUserId(Long userId);
	 	
	    @Query("select e from Expenses e where e.amount = (select max(e2.amount) from Expenses e2)")
	    Expenses findMaxExpense();
	    
	    List<Expenses> findByCategoryCategoryId(Long categoryId);

	    @Query("select case when count(e)>0 then true else false end from Expenses e where e.expenseId=?1")
	    Boolean isExpenseExistsById(Long id);

	    List<Expenses> findByUserUserIdAndDateBetween(Long userId, LocalDate start, LocalDate end);
	    
	    Expenses findTopByOrderByAmountDesc();
}

	

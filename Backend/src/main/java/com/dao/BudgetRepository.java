package com.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.model.Budget;

public interface BudgetRepository  extends JpaRepository<Budget,Long>{
	
	 @Query("select b from Budget b where b.month=:month and b.year=:year")
	    List<Budget> findByMonthAndYear(@Param("month") int month, @Param("year") int year);

	    @Query("select b from Budget b where b.limitAmount = (select max(b2.limitAmount) from Budget b2)")
	    List<Budget> findMaxBudget();

	    @Query("select case when count(b)>0 then true else false end from Budget b where b.budgetId=?1")
	    Boolean isBudgetExistsById(Long id);

}

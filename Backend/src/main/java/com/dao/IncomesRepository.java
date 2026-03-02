package com.dao;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.model.Incomes;

public interface IncomesRepository extends JpaRepository<Incomes,Long> {
		
		
		@Query("SELECT i FROM Incomes i WHERE i.user.userId = :userId")
		List<Incomes> findByUserUserId(Long userId);

	 	@Query("select i from Incomes i where i.amount = (select max(i2.amount) from Incomes i2)")
	    Incomes findMaxIncome();

	    @Query("select i from Incomes i where i.amount = :amount")
	    Incomes findIncomeByAmount(@Param("amount") double amount);

	    @Query("select case when count(i) > 0 then true else false end from Incomes i where i.incomeId = ?1")
	    Boolean isIncomeExistsById(Long id);
	    List<Incomes> findByDateBetween(LocalDate start, LocalDate end);
	    
	    @Query("SELECT i FROM Incomes i WHERE i.amount > 50000")
	    List<Incomes> findIncomesAbove50000();
	
}

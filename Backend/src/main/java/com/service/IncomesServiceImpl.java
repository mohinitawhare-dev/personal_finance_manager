package com.service;

import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.dao.IncomesRepository;
import com.dao.UserRepository;
import com.exception.ResourceNotFoundException;
import com.model.Incomes;
import com.model.User;

@Service
public class IncomesServiceImpl implements IncomesService {

	    @Autowired
	    private IncomesRepository incomesRepository;

	    @Autowired
	    private UserRepository userRepository;

	    
	    @Override
	    public Incomes createIncome(Incomes income) {
	        Long userId = income.getUser().getUserId();
	        User user = userRepository.findById(userId)
	                .orElseThrow(() ->new RuntimeException("User not found with id: " + userId));
	        income.setUser(user);
	        return incomesRepository.save(income);
	    }
	    
	    @Override
	    public List<Incomes> getAllIncomes() {
	        List<Incomes> list = incomesRepository.findAll();
	        if (list.isEmpty()) {
	            throw new RuntimeException("No incomes found");
	        }
	        return list;
	    }

	   
	    @Override
	    public ResponseEntity<?> getIncomeById(Long id) {
	        Incomes income = incomesRepository.findById(id)
	                .orElseThrow(() ->new RuntimeException("Income not found with id: " + id));
	        return ResponseEntity.ok(income);
	    }

	    
	    public Incomes updateIncome(Long id, Incomes incomeDetails) {
	        Incomes income = incomesRepository.findById(id)
	            .orElseThrow(() -> new ResourceNotFoundException("Income not found for id " + id));
	        
	        income.setAmount(incomeDetails.getAmount());
	        income.setDate(incomeDetails.getDate());
	        income.setSource(incomeDetails.getSource());
	        return incomesRepository.save(income);
	    }

	    
	    @Override
	    public ResponseEntity<?> deleteIncome(Long id) {
	        if (!incomesRepository.existsById(id)) {
	            throw new RuntimeException("Income not found with id: " + id);
	        }
	        incomesRepository.deleteById(id);
	        return ResponseEntity.ok("Income deleted successfully");
	    }
	    
	    @Override
	    public List<Incomes> getAllIncomesByUser(Long userId) {
	        return incomesRepository.findByUserUserId(userId);
	    }
	    
	    @Override
	    public Incomes getMaxIncome() {
	        return incomesRepository.findMaxIncome();
	    }

	    @Override
	    public Incomes getIncomeByAmount(double amount) {
	        return incomesRepository.findIncomeByAmount(amount);
	    }

	    @Override
	    public Boolean checkIncomeExists(Long id) {
	        return incomesRepository.isIncomeExistsById(id);
	    }

	    @Override
	    public List<Incomes> getIncomesByDateRange(LocalDate start, LocalDate end) {
	        return incomesRepository.findByDateBetween(start, end);
	    }
	    
	    @Override
	    public List<Incomes> getIncomesAbove50000() {
	        return incomesRepository.findIncomesAbove50000();
	    }
	    
}
	    

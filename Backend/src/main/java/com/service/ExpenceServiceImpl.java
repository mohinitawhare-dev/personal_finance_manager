package com.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.CategoryRepository;
import com.dao.ExpensesRepository;
import com.dao.UserRepository;
import com.exception.ResourceNotFoundException;
import com.model.Category;
import com.model.Expenses;
import com.model.User;

@Service
public class ExpenceServiceImpl implements ExpenseService {
	
	@Autowired
	private CategoryRepository categoryRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ExpensesRepository expenseRepository;

	@Override
	public Expenses createExpense(Expenses expense) {

	    // Fetch category from DB
	    Category category = categoryRepository.findById(expense.getCategory().getCategoryId())
	            .orElseThrow(() -> new RuntimeException("Category not found with id: " + expense.getCategory().getCategoryId()));

	    // Fetch user from DB
	    User user = userRepository.findById(expense.getUser().getUserId())
	            .orElseThrow(() -> new RuntimeException("User not found with id: " + expense.getUser().getUserId()));

	    // Set fetched entities
	    expense.setCategory(category);
	    expense.setUser(user);

	    // Save expense
	    return expenseRepository.save(expense);
	}


	    
	    @Override
	    public List<Expenses> getAllExpenses() {
	        return expenseRepository.findAll();
	    }

	    @Override
	    public Expenses getExpenseById(Long id) {
	        return expenseRepository.findById(id)
	                .orElseThrow(() ->new ResourceNotFoundException("Expense not found with id " + id));
	    }
	   

	    @Override
	    public Expenses updateExpense(Long id, Expenses expenseDetails) {
	        Expenses expense = getExpenseById(id);
	        expense.setAmount(expenseDetails.getAmount());
	        expense.setDate(expenseDetails.getDate());
	        expense.setDescription(expenseDetails.getDescription());
	        return expenseRepository.save(expense);
	    }
	    
	    @Override
	    public void deleteExpenses(Long id) {
	        if (!expenseRepository.existsById(id)) {
	            throw new ResourceNotFoundException("Expense not found with id " + id);
	        }
	        expenseRepository.deleteById(id);
	    }
	    
	    @Override
	    public List<Expenses> getAllExpensesByUser(Long userId) {
	        return expenseRepository.findByUserUserId(userId);
	    }

	    @Override
	    public Expenses getMaxExpense() {
	        return expenseRepository.findTopByOrderByAmountDesc();
	    }

	    @Override
	    public List<Expenses> getExpensesByCategory(Long categoryId) {
	        return expenseRepository.findByCategoryCategoryId(categoryId);
	    }

	    @Override
	    public Boolean checkExpenseExists(Long id) {
	        return expenseRepository.isExpenseExistsById(id);
	    }

	    @Override
	    public List<Expenses> getExpensesByDateRange(Long userId, LocalDate start, LocalDate end) {
	        return expenseRepository.findByUserUserIdAndDateBetween(userId, start, end);
	    }
}


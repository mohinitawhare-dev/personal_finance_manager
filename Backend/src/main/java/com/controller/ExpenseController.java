package com.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.exception.ResourceNotFoundException;
import com.model.Expenses;
import com.service.ExpenseService;

	@RestController
	@RequestMapping("/api/expenses")
	@CrossOrigin(origins = "http://localhost:3000")
	public class ExpenseController {

	    @Autowired
	    private ExpenseService expenseService;

	    @PostMapping("/create")
	    public ResponseEntity<Expenses> createExpense(@RequestBody Expenses expense) {

	        Expenses created = expenseService.createExpense(expense);
	        return ResponseEntity.status(HttpStatus.CREATED).body(created);
	    }

	    @GetMapping("/all")
	    public ResponseEntity<List<Expenses>> getAllExpenses() {
	    	
	        List<Expenses> expenses = expenseService.getAllExpenses();
	        return ResponseEntity.ok(expenses);
	    }


	    @GetMapping("/get/{id}")
	    public ResponseEntity<?> getExpenseById(@PathVariable Long id) {

	        try {
	            return ResponseEntity.ok(expenseService.getExpenseById(id));

	        } catch (ResourceNotFoundException ex) {

	            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
	        }
	    }
	    
	    @PutMapping("/update/{id}")
	    public ResponseEntity<Expenses> updateExpense(@PathVariable Long id,@RequestBody Expenses expense) {

	        try {
	            return ResponseEntity.ok(expenseService.updateExpense(id, expense));

	        } catch (ResourceNotFoundException ex) {

	            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(expense);
	        }
	    }

	    @DeleteMapping("/delete/{id}")
	    public ResponseEntity<String> deleteExpense(
	            @PathVariable Long id) {

	        try {
	            expenseService.deleteExpenses(id);

	            return ResponseEntity.ok("Expense deleted successfully");

	        } catch (ResourceNotFoundException ex) {
	        	
	            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Expense not found");
	        }
	    }
	    
	    @GetMapping("/user/{userId}")
	    public ResponseEntity<List<Expenses>> getAllExpenses(@PathVariable Long userId) {
	        return ResponseEntity.ok().header("get","all expenses")
	                .body(expenseService.getAllExpensesByUser(userId));
	    }

	    @GetMapping("/max")
	    public ResponseEntity<Expenses> getMaxExpense() {
	        return ResponseEntity.ok().header("get","max expense")
	                .body(expenseService.getMaxExpense());
	    }

	    @GetMapping("/category/{categoryId}")
	    public ResponseEntity<List<Expenses>> getExpensesByCategory(@PathVariable Long categoryId) {
	        return ResponseEntity.ok().header("get","expenses by category")
	                .body(expenseService.getExpensesByCategory(categoryId));
	    }

	    @GetMapping("/exists/{id}")
	    public ResponseEntity<Boolean> checkExpenseExists(@PathVariable Long id) {
	        return ResponseEntity.ok().header("get","check exists")
	                .body(expenseService.checkExpenseExists(id));
	    }
}
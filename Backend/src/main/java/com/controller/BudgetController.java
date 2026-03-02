package com.controller;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.exception.ResourceNotFoundException;
import com.model.Budget;
import com.service.BudgetService;

@RestController
@RequestMapping("/api/budgets")
@CrossOrigin(origins = "http://localhost:3000")

public class BudgetController {

    private BudgetService budgetService;

    public BudgetController(BudgetService budgetService) {
        this.budgetService = budgetService;
    }

    @PostMapping("/create")
    public ResponseEntity<Budget> createBudget(@RequestBody Budget budget) {
        return ResponseEntity.status(HttpStatus.CREATED).body(budgetService.createBudget(budget));
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<?> getBudgetById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(budgetService.getBudgetById(id));
        } catch (ResourceNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<Budget>> getAllBudgets() {
        return ResponseEntity.ok(budgetService.getAllBudgets());
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateBudget(@PathVariable Long id, @RequestBody Budget budgetDetails) {
        try {
            return ResponseEntity.ok(budgetService.updateBudget(id, budgetDetails));
        } catch (ResourceNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Budget not found");
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteBudget(@PathVariable Long id) {
        try {
            budgetService.deleteBudget(id);
            return ResponseEntity.ok("Budget deleted successfully");
        } catch (ResourceNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Budget not found");
        }
    }
    
    @GetMapping("/monthYear")
    public ResponseEntity<List<Budget>> getBudgetsByMonthYear(@RequestParam int month,@RequestParam int year) {
    	
    	return ResponseEntity.ok().header("get", "budgets by month and year")
    			.body(budgetService.getBudgetsByMonthAndYear(month, year));
    }

    @GetMapping("/max")
    public ResponseEntity<List<Budget>> getMaxBudget() {
    	
        return ResponseEntity.ok().header("get", "max budget").body(budgetService.getMaxBudget());   
    }


    // 🔹 Check budget exists
    @GetMapping("/exists/{id}")
    public ResponseEntity<Boolean> checkBudgetExists(@PathVariable Long id) {

        return ResponseEntity.ok().header("get", "check budget exists").body(budgetService.checkBudgetExists(id));
        
    }
}


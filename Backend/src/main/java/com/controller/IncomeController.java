package com.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.exception.ResourceNotFoundException;
import com.model.Incomes;
import com.service.IncomesService;

	@RestController
	@RequestMapping("/api/incomes")
	@CrossOrigin(origins = "http://localhost:3000")
	public class IncomeController {

	    @Autowired
	    private IncomesService incomesService;

	    @PostMapping("/create")
	    public ResponseEntity<Incomes> createIncome(@RequestBody Incomes income) {
	        Incomes created = incomesService.createIncome(income);
	        return ResponseEntity.status(HttpStatus.CREATED).body(created);
	    }

	    @GetMapping("/get/all")
	    public ResponseEntity<List<Incomes>> getAllIncomes() {
	        List<Incomes> incomes = incomesService.getAllIncomes();
	        return ResponseEntity.ok(incomes);
	    }

	    @GetMapping("/get/{id}")
	    public ResponseEntity<?> getIncomeById(@PathVariable Long id) {
	        try {
	            return ResponseEntity.ok(incomesService.getIncomeById(id));
	        } catch (ResourceNotFoundException ex) {

	            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
	            
	        	}
	    }
	    
	    @PutMapping("/update/{id}")
	    public ResponseEntity<?> updateIncome(@PathVariable Long id, @RequestBody Incomes income) {
	        try {
	            return ResponseEntity.ok(incomesService.updateIncome(id, income));
	        } catch (ResourceNotFoundException ex) {
	            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
	        }
	    }

	    
	    @DeleteMapping("/delete/{id}")
	    public ResponseEntity<String> deleteIncome(@PathVariable Long id) {
	        try {
	            incomesService.deleteIncome(id);
	            return ResponseEntity.ok("Income deleted successfully");
	        } catch (ResourceNotFoundException ex) {
	            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Income not found");
	        }
	    }
	    
	    @GetMapping("/user/{userId}")
	    public ResponseEntity<List<Incomes>> getAllIncomes(@PathVariable Long userId) {
	        return ResponseEntity.ok().header("get","all incomes").body(incomesService.getAllIncomesByUser(userId));
	    }

	    @GetMapping("/max")
	    public ResponseEntity<Incomes> getMaxIncome() {
	        return ResponseEntity.ok().header("get","max income").body(incomesService.getMaxIncome());
	    }

	    @GetMapping("/amount/{amount}")
	    public ResponseEntity<Incomes> getIncomeByAmount(@PathVariable double amount) {
	        return ResponseEntity.ok().header("get","income by amount").body(incomesService.getIncomeByAmount(amount));
	    }

	    @GetMapping("/exists/{id}")
	    public ResponseEntity<Boolean> checkIncomeExists(@PathVariable Long id) {
	        return ResponseEntity.ok().header("get","check exists").body(incomesService.checkIncomeExists(id));
	    }
	    
	    @GetMapping("/above50k")
	    public ResponseEntity<List<Incomes>> getIncomesAbove50k() {
	        return ResponseEntity.ok().header("get", "incomes above 50000").body(incomesService.getIncomesAbove50000());
	    }
	    
	        // Get incomes between two dates
	        @GetMapping("/range")
	        public List<Incomes> getIncomesByDateRange(
	                @RequestParam("start") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate start,
	                @RequestParam("end") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate end) {
	            return incomesService.getIncomesByDateRange(start, end);
	        }
}




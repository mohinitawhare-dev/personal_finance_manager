package com.service;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.dao.BudgetRepository;
import com.dao.CategoryRepository;
import com.dao.UserRepository;
import com.exception.ResourceNotFoundException;
import com.model.Budget;
import com.model.Category;
import com.model.User;
@Service
public class BudgetServiceImpl implements BudgetService {

    @Autowired
    private BudgetRepository budgetRepository;

    @Autowired
    private UserRepository userRepository;  

    @Autowired
    private CategoryRepository categoryRepository; 

    @Override
    public Budget createBudget(Budget budget) {

        // Fetch existing User from DB
        Long userId = budget.getUser().getUserId();
        User user = userRepository.findById(userId)   
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id " + userId));
        budget.setUser(user);

        // Fetch existing Category from DB
        Long categoryId = budget.getCategory().getCategoryId();
        Category category = categoryRepository.findById(categoryId)   // ✅ use instance
                .orElseThrow(() -> new ResourceNotFoundException("Category not found with id " + categoryId));
        budget.setCategory(category);

        // Save Budget
        return budgetRepository.save(budget);
    }



    @Override
    public Budget getBudgetById(Long id) {
        return budgetRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Budget not found with id " + id));
    }

    @Override
    public List<Budget> getAllBudgets() {
        return budgetRepository.findAll();
    }

    @Override
    public Budget updateBudget(Long id, Budget budgetDetails) {
        Budget budget = getBudgetById(id);
        budget.setMonth(budgetDetails.getMonth());
        budget.setYear(budgetDetails.getYear());
        budget.setLimitAmount(budgetDetails.getLimitAmount());
        budget.setCategory(budgetDetails.getCategory());
        return budgetRepository.save(budget);
    }

    @Override
    public void deleteBudget(Long id) {
        if (!budgetRepository.existsById(id)) {
            throw new ResourceNotFoundException("Budget not found with id " + id);
        }
        budgetRepository.deleteById(id);
    }
    
    @Override
    public List<Budget> getBudgetsByMonthAndYear(int month, int year) {
        return budgetRepository.findByMonthAndYear(month, year);
    }

    @Override
    public List<Budget> getMaxBudget() {
        return budgetRepository.findMaxBudget();
    }

    @Override
    public Boolean checkBudgetExists(Long id) {
        return budgetRepository.isBudgetExistsById(id);
    }
}



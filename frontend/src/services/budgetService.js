import axios from "axios";

const API_URL = "http://localhost:8088/api/budgets";

const budgetService = {

  // Get all budgets
  getAllBudgets: () => axios.get(`${API_URL}/all`),

  // Get budget by ID
  getBudgetById: (id) => axios.get(`${API_URL}/get/${id}`),

  // Get all budgets for a specific user (frontend filtering)
  getAllBudgetsByUser: (userId) => axios.get(`${API_URL}/all`), // Filter in frontend if needed

  // Create new budget
  createBudget: (budget) => axios.post(`${API_URL}/create`, budget),

  // Update budget by ID
  updateBudget: (id, budget) => axios.put(`${API_URL}/update/${id}`, budget),

  // Delete budget by ID
  deleteBudget: (id) => axios.delete(`${API_URL}/delete/${id}`),

  // Extra endpoints if needed
  getBudgetsByMonthYear: (month, year) => axios.get(`${API_URL}/monthYear?month=${month}&year=${year}`),
  getMaxBudget: () => axios.get(`${API_URL}/max`),
  checkBudgetExists: (id) => axios.get(`${API_URL}/exists/${id}`)
};

export default budgetService;

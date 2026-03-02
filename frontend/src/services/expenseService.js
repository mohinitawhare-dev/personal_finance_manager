import axios from "axios";

const API_URL = "http://localhost:8088/api/expenses";

const expenseService = {
  getExpensesByUser: (userId) => axios.get(`${API_URL}/user/${userId}`),
  createExpense: (expense) => axios.post(`${API_URL}/create`, expense),
  updateExpense: (id, expense) => axios.put(`${API_URL}/update/${id}`, expense),
  deleteExpense: (id) => axios.delete(`${API_URL}/delete/${id}`),
};

export default expenseService;

import axios from "axios";
const API_URL = "http://localhost:8088/api/incomes";

const incomeService = {
  getAllIncomesByUser: (userId) => axios.get(`${API_URL}/user/${userId}`),
  createIncome: (income) => axios.post(`${API_URL}/create`, income),
  updateIncome: (id, income) => axios.put(`${API_URL}/update/${id}`, income),
  deleteIncome: (id) => axios.delete(`${API_URL}/delete/${id}`),
};

export default incomeService;

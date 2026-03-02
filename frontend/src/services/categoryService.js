import axios from "axios";

const API_URL = "http://localhost:8088/api/categories";

const categoryService = {
  getAllCategories: () => axios.get(`${API_URL}/all`),
  getCategoryById: (id) => axios.get(`${API_URL}/get/${id}`),
  getCategoriesByType: (type) => axios.get(`${API_URL}/type/${type}`),
  createCategory: (category) => axios.post(`${API_URL}/create`, category),
  updateCategory: (id, category) => axios.put(`${API_URL}/update/${id}`, category),
  deleteCategory: (id) => axios.delete(`${API_URL}/delete/${id}`)
};

export default categoryService;

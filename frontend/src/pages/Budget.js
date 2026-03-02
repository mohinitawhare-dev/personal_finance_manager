import React, { useEffect, useState, useCallback } from "react";
import { Table, Button, Form, Card } from "react-bootstrap";
import budgetService from "../services/budgetService";

function Budget() {
  const [budgets, setBudgets] = useState([]);
  const [month, setMonth] = useState("");
  const [year, setYear] = useState("");
  const [limitAmount, setLimitAmount] = useState("");
  const [categoryId, setCategoryId] = useState("");
  const [editId, setEditId] = useState(null);

  const user = JSON.parse(localStorage.getItem("user")) || { userId: 1, username: "Priti Patil" };
  const userId = parseInt(user.userId);

  // Fetch budgets for logged-in user
  const fetchBudgets = useCallback(async () => {
    try {
      const res = await budgetService.getAllBudgetsByUser(userId);
      // Filter budgets for current user
      const userBudgets = res.data.filter(b => b.user && b.user.userId === userId);
      setBudgets(userBudgets);
    } catch (err) {
      console.error("Fetch budgets failed:", err);
    }
  }, [userId]);

  useEffect(() => {
    fetchBudgets();
  }, [fetchBudgets]);

  // Add new budget
  const handleAdd = async () => {
    try {
      const payload = {
        month: parseInt(month),
        year: parseInt(year),
        limitAmount: parseFloat(limitAmount),
        category: { categoryId: parseInt(categoryId) },
        user: { userId }
      };
      const res = await budgetService.createBudget(payload);
      if (res.status === 201) {
        setMonth(""); setYear(""); setLimitAmount(""); setCategoryId("");
        fetchBudgets();
      }
    } catch (err) {
      console.error("Add budget failed:", err);
    }
  };

  // Edit budget
  const handleEdit = (b) => {
    setEditId(b.budgetId);
    setMonth(b.month);
    setYear(b.year);
    setLimitAmount(b.limitAmount);
    setCategoryId(b.category.categoryId);
  };

  const handleSave = async () => {
    try {
      const payload = {
        month: parseInt(month),
        year: parseInt(year),
        limitAmount: parseFloat(limitAmount),
        category: { categoryId: parseInt(categoryId) },
        user: { userId }
      };
      const res = await budgetService.updateBudget(editId, payload);
      if (res.status === 200) {
        setEditId(null); setMonth(""); setYear(""); setLimitAmount(""); setCategoryId("");
        fetchBudgets();
      }
    } catch (err) {
      console.error("Save budget failed:", err);
    }
  };

  const handleDelete = async (id) => {
    try {
      const res = await budgetService.deleteBudget(id);
      if (res.status === 200) fetchBudgets();
    } catch (err) {
      console.error("Delete budget failed:", err);
    }
  };

  return (
    <div className="container mt-4">
      <Card className="p-4" style={{ backgroundColor: "#f3e5f5" }}>
        <h3>{editId ? "Edit Budget" : "Add Budget"}</h3>
        <Form className="d-flex gap-2 mb-3">
          <Form.Control type="number" placeholder="Month" value={month} onChange={(e) => setMonth(e.target.value)} />
          <Form.Control type="number" placeholder="Year" value={year} onChange={(e) => setYear(e.target.value)} />
          <Form.Control type="number" placeholder="Limit Amount" value={limitAmount} onChange={(e) => setLimitAmount(e.target.value)} />
          <Form.Control type="number" placeholder="Category ID" value={categoryId} onChange={(e) => setCategoryId(e.target.value)} />
          {editId ? (
            <Button style={{ backgroundColor: "#6a1b9a" }} onClick={handleSave}>Save</Button>
          ) : (
            <Button style={{ backgroundColor: "#4a148c" }} onClick={handleAdd}>Add</Button>
          )}
        </Form>

        <Table striped bordered hover style={{ backgroundColor: "#ede7f6" }}>
          <thead>
            <tr>
              <th>ID</th>
              <th>Month</th>
              <th>Year</th>
              <th>Limit Amount</th>
              <th>Category</th>
              <th>User</th>
              <th>Actions</th>
            </tr>
          </thead>
          <tbody>
            {budgets.map(b => (
              <tr key={b.budgetId}>
                <td>{b.budgetId}</td>
                <td>{b.month}</td>
                <td>{b.year}</td>
                <td>{b.limitAmount}</td>
                <td>{b.category ? b.category.name : "N/A"}</td>
                <td>{b.user ? b.user.username : "N/A"}</td>
                <td>
                  <Button style={{ backgroundColor: "#ab47bc" }} size="sm" onClick={() => handleEdit(b)}>Edit</Button>{" "}
                  <Button style={{ backgroundColor: "#d32f2f" }} size="sm" onClick={() => handleDelete(b.budgetId)}>Delete</Button>
                </td>
              </tr>
            ))}
          </tbody>
        </Table>
      </Card>
    </div>
  );
}

export default Budget;

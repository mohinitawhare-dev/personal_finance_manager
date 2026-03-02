import React, { useEffect, useState, useCallback } from "react";
import { Table, Spinner, Alert, Button, Modal, Form } from "react-bootstrap";
import expenseService from "../services/expenseService";

function Expense({ userId }) {
  const [expenses, setExpenses] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState("");

  // Modal state
  const [showModal, setShowModal] = useState(false);
  const [editExpense, setEditExpense] = useState(null);
  const [formData, setFormData] = useState({
    description: "",
    amount: "",
    date: "",
    categoryId: "",
  });

  // fetchExpenses wrapped in useCallback for ESLint safe useEffect
  const fetchExpenses = useCallback(() => {
    if (!userId) return;

    setLoading(true);
    expenseService.getExpensesByUser(userId)
      .then(res => {
        setExpenses(res.data);
        setLoading(false);
      })
      .catch(err => {
        console.error(err);
        setError("Failed to fetch expenses.");
        setLoading(false);
      });
  }, [userId]);

  useEffect(() => {
    fetchExpenses();
  }, [fetchExpenses]);

  const handleClose = () => {
    setShowModal(false);
    setEditExpense(null);
    setFormData({ description: "", amount: "", date: "", categoryId: "" });
  };

  const handleShowAdd = () => setShowModal(true);

  const handleShowEdit = (expense) => {
    setEditExpense(expense);
    setFormData({
      description: expense.description,
      amount: expense.amount,
      date: expense.date,
      categoryId: expense.category.categoryId,
    });
    setShowModal(true);
  };

  const handleDelete = (id) => {
    if (window.confirm("Are you sure you want to delete this expense?")) {
      expenseService.deleteExpense(id)
        .then(() => fetchExpenses())
        .catch(err => console.error(err));
    }
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    const payload = {
      ...formData,
      amount: parseFloat(formData.amount),
      category: { categoryId: parseInt(formData.categoryId) },
      user: { userId: userId },
    };

    if (editExpense) {
      expenseService.updateExpense(editExpense.expenseId, payload)
        .then(() => {
          fetchExpenses();
          handleClose();
        })
        .catch(err => console.error(err));
    } else {
      expenseService.createExpense(payload)
        .then(() => {
          fetchExpenses();
          handleClose();
        })
        .catch(err => console.error(err));
    }
  };

  if (loading) return <div className="text-center mt-5"><Spinner animation="border" /> Loading...</div>;
  if (error) return <Alert variant="danger">{error}</Alert>;

  return (
    <div className="container mt-4">
      <h2>My Expenses</h2>
      <Button variant="primary" className="mb-3" onClick={handleShowAdd}>Add Expense</Button>

      {expenses.length === 0 ? (
        <Alert variant="info">No expenses found.</Alert>
      ) : (
        <Table striped bordered hover responsive>
          <thead className="table-dark">
            <tr>
              <th>ID</th>
              <th>Description</th>
              <th>Amount</th>
              <th>Date</th>
              <th>User</th>
              <th>Category</th>
              <th>Actions</th>
            </tr>
          </thead>
          <tbody>
            {expenses.map(exp => (
              <tr key={exp.expenseId}>
                <td>{exp.expenseId}</td>
                <td>{exp.description}</td>
                <td>{exp.amount}</td>
                <td>{exp.date}</td>
                <td>{exp.user?.username || "N/A"}</td>
                <td>{exp.category?.name || "N/A"}</td>
                <td>
                  <Button variant="warning" size="sm" className="me-2" onClick={() => handleShowEdit(exp)}>Edit</Button>
                  <Button variant="danger" size="sm" onClick={() => handleDelete(exp.expenseId)}>Delete</Button>
                </td>
              </tr>
            ))}
          </tbody>
        </Table>
      )}

      {/* Add / Edit Modal */}
      <Modal show={showModal} onHide={handleClose}>
        <Modal.Header closeButton>
          <Modal.Title>{editExpense ? "Edit Expense" : "Add Expense"}</Modal.Title>
        </Modal.Header>
        <Modal.Body>
          <Form onSubmit={handleSubmit}>
            <Form.Group className="mb-2">
              <Form.Label>Description</Form.Label>
              <Form.Control
                type="text"
                value={formData.description}
                onChange={e => setFormData({ ...formData, description: e.target.value })}
                required
              />
            </Form.Group>
            <Form.Group className="mb-2">
              <Form.Label>Amount</Form.Label>
              <Form.Control
                type="number"
                value={formData.amount}
                onChange={e => setFormData({ ...formData, amount: e.target.value })}
                required
              />
            </Form.Group>
            <Form.Group className="mb-2">
              <Form.Label>Date</Form.Label>
              <Form.Control
                type="date"
                value={formData.date}
                onChange={e => setFormData({ ...formData, date: e.target.value })}
                required
              />
            </Form.Group>
            <Form.Group className="mb-2">
              <Form.Label>Category ID</Form.Label>
              <Form.Control
                type="number"
                value={formData.categoryId}
                onChange={e => setFormData({ ...formData, categoryId: e.target.value })}
                required
              />
            </Form.Group>
            <Button variant="success" type="submit" className="mt-2">
              {editExpense ? "Update" : "Add"}
            </Button>
          </Form>
        </Modal.Body>
      </Modal>
    </div>
  );
}

export default Expense;

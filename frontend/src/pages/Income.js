import React, { useEffect, useState, useCallback } from "react";
import { Table, Button, Form, Card } from "react-bootstrap";
import incomeService from "../services/incomeService";

function Income() {
  const [incomes, setIncomes] = useState([]);
  const [amount, setAmount] = useState("");
  const [date, setDate] = useState("");
  const [source, setSource] = useState("");
  const [editId, setEditId] = useState(null);

  // Logged-in user from localStorage
  const user = JSON.parse(localStorage.getItem("user")) || { userId: 1 };
  const userId = parseInt(user.userId);


const fetchIncomes = useCallback(async () => {
  try {
    const res = await incomeService.getAllIncomesByUser(userId);
    const userIncomes = res.data.filter((inc) => inc.user.userId === userId);
    setIncomes(userIncomes);
  } catch (err) {
    console.error(err);
  }
}, [userId]);

useEffect(() => {
  fetchIncomes();
}, [fetchIncomes]);

  const handleAdd = async () => {
    try {
      const payload = { amount: parseFloat(amount), date, source, user: { userId } };
      const res = await incomeService.createIncome(payload);
      if (res.status === 201) {
        setAmount(""); setDate(""); setSource("");
        fetchIncomes();
      }
    } catch (err) {
      console.error("Add failed:", err);
    }
  };

  const handleEdit = (inc) => {
    setEditId(inc.incomeId);
    setAmount(inc.amount);
    setDate(inc.date);
    setSource(inc.source);
  };

  const handleSave = async () => {
  try {
    const payload = { amount: parseFloat(amount), date, source, user: { userId } };
    const res = await incomeService.updateIncome(editId, payload); // editId = incomeId
    if (res.status === 200) {
      setEditId(null);
      setAmount("");
      setDate("");
      setSource("");
      fetchIncomes(); // Reload
    }
  } catch (err) {
    console.error("Update failed:", err);
  }
};


  const handleDelete = async (id) => {
    try {
      const res = await incomeService.deleteIncome(id);
      if (res.status === 200) fetchIncomes();
    } catch (err) {
      console.error("Delete failed:", err);
    }
  };

  return (
    <div className="container mt-4">
      <Card className="p-4" style={{ backgroundColor: "#f3e5f5" }}>
        <h3>{editId ? "Edit Income" : "Add Income"}</h3>
        <Form className="d-flex gap-2 mb-3">
          <Form.Control type="number" placeholder="Amount" value={amount} onChange={(e) => setAmount(e.target.value)} />
          <Form.Control type="date" value={date} onChange={(e) => setDate(e.target.value)} />
          <Form.Control type="text" placeholder="Source" value={source} onChange={(e) => setSource(e.target.value)} />
          {editId ? (
            <Button style={{ backgroundColor: "#6a1b9a" }} onClick={handleSave}>Save</Button>
          ) : (
            <Button style={{ backgroundColor: "#4a148c" }} onClick={handleAdd}>Add</Button>
          )}
        </Form>

        <Table striped bordered hover style={{ backgroundColor: "#ede7f6" }}>
          <thead>
            <tr>
              <th>ID</th><th>Amount</th><th>Date</th><th>Source</th><th>Actions</th>
            </tr>
          </thead>
          <tbody>
            {incomes.map((inc) => (
              <tr key={inc.incomeId}>
                <td>{inc.incomeId}</td>
                <td>{inc.amount}</td>
                <td>{inc.date}</td>
                <td>{inc.source}</td>
                <td>
                  <Button style={{ backgroundColor: "#ab47bc" }} size="sm" onClick={() => handleEdit(inc)}>Edit</Button>{" "}
                  <Button style={{ backgroundColor: "#d32f2f" }} size="sm" onClick={() => handleDelete(inc.incomeId)}>Delete</Button>
                </td>
              </tr>
            ))}
          </tbody>
        </Table>
      </Card>
    </div>
  );
}

export default Income;

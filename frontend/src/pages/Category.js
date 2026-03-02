import React, { useState, useEffect } from "react";
import { Table, Button, Form, Card } from "react-bootstrap";
import categoryService from "../services/categoryService";

function Category() {
  const [categories, setCategories] = useState([]);
  const [name, setName] = useState("");
  const [type, setType] = useState("");
  const [editId, setEditId] = useState(null);

  // Load categories
  const fetchCategories = async () => {
    try {
      const res = await categoryService.getAllCategories();
      setCategories(res.data);
    } catch (err) {
      console.error("Fetch categories failed:", err);
    }
  };

  useEffect(() => {
    fetchCategories();
  }, []);

  // Add category
  const handleAdd = async () => {
    try {
      await categoryService.createCategory({ name, type });
      setName(""); setType("");
      fetchCategories();
    } catch (err) {
      console.error("Add category failed:", err);
    }
  };

  // Edit category
  const handleEdit = (c) => {
    setEditId(c.categoryId);
    setName(c.name);
    setType(c.type);
  };

  // Save edited category
  const handleSave = async () => {
    try {
      await categoryService.updateCategory(editId, { name, type });
      setEditId(null); setName(""); setType("");
      fetchCategories();
    } catch (err) {
      console.error("Save category failed:", err);
    }
  };

  // Delete category
  const handleDelete = async (id) => {
    try {
      await categoryService.deleteCategory(id);
      fetchCategories();
    } catch (err) {
      console.error("Delete category failed:", err);
    }
  };

  return (
    <div className="container mt-4">
      <Card className="p-4" style={{ backgroundColor: "#f3e5f5" }}>
        <h3>{editId ? "Edit Category" : "Add Category"}</h3>
        <Form className="d-flex gap-2 mb-3">
          <Form.Control placeholder="Name" value={name} onChange={(e) => setName(e.target.value)} />
          <Form.Control placeholder="Type (EXPENSE/INCOME)" value={type} onChange={(e) => setType(e.target.value)} />
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
              <th>Name</th>
              <th>Type</th>
              <th>Actions</th>
            </tr>
          </thead>
          <tbody>
            {categories.map(c => (
              <tr key={c.categoryId}>
                <td>{c.categoryId}</td>
                <td>{c.name}</td>
                <td>{c.type}</td>
                <td>
                  <Button style={{ backgroundColor: "#ab47bc" }} size="sm" onClick={() => handleEdit(c)}>Edit</Button>{" "}
                  <Button style={{ backgroundColor: "#d32f2f" }} size="sm" onClick={() => handleDelete(c.categoryId)}>Delete</Button>
                </td>
              </tr>
            ))}
          </tbody>
        </Table>
      </Card>
    </div>
  );
}

export default Category;

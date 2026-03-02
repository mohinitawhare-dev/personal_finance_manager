import React, { useState } from "react";
import { Form, Button, Container, Card } from "react-bootstrap";
import axios from "axios";

function Register() {

  const [user, setUser] = useState({
    name: "",
    email: "",
    password: ""
  });

  // Input change handler
  const handleChange = (e) => {
    setUser({ ...user, [e.target.name]: e.target.value });
  };

  // Form submit
  const handleSubmit = async (e) => {
    e.preventDefault();

    try {
      const response = await axios.post(
         "http://localhost:8088/api/users/register",
        user
      );

      alert("Registration Successful ✅");
      console.log(response.data);

    } catch (error) {
      alert("Registration Failed ❌");
      console.error(error);
    }
  };

  return (
    <Container className="mt-5">
      <Card className="p-4 shadow" style={{ maxWidth: "400px", margin: "auto" }}>
        <h3 className="text-center mb-3">Register</h3>

        <Form onSubmit={handleSubmit}>

          <Form.Group className="mb-3">
            <Form.Label>UserName</Form.Label>
            <Form.Control
              type="text"
              name="name"
              placeholder="Enter name"
              onChange={handleChange}
              required
            />
          </Form.Group>

          <Form.Group className="mb-3">
            <Form.Label>Email</Form.Label>
            <Form.Control
              type="email"
              name="email"
              placeholder="Enter email"
              onChange={handleChange}
              required
            />
          </Form.Group>

          <Form.Group className="mb-3">
            <Form.Label>Password</Form.Label>
            <Form.Control
              type="password"
              name="password"
              placeholder="Enter password"
              onChange={handleChange}
              required
            />
          </Form.Group>

          <Button type="submit" variant="success" className="w-100">
            Register
          </Button>

        </Form>
      </Card>
    </Container>
  );
}

export default Register;
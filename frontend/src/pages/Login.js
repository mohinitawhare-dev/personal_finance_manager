import React, { useState } from "react";
import { Form, Button, Card } from "react-bootstrap";
import axios from "axios";

function Login() {

  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [error, setError] = useState("");

  const handleLogin = async (e) => {
    e.preventDefault();

    try {

      const response = await axios.post(
        "http://localhost:8088/api/users/login",
        { email, password }
      );

      const user = response.data;

      // ⭐ MOST IMPORTANT
      localStorage.setItem("userId", user.userId);
      localStorage.setItem("username", user.username);
      localStorage.setItem("user", JSON.stringify(user));

      alert("Login successful ✅");

      window.location.href = "/income";

    } catch (err) {

      const msg =
        err.response?.data?.message ||
        err.response?.data ||
        "Invalid email or password";

      setError(msg);
    }
  };

  return (
    <div
      style={{
        background: "linear-gradient(to right, #4e54c8, #8f94fb)",
        height: "100vh",
        display: "flex",
        alignItems: "center",
        justifyContent: "center"
      }}
    >
      <Card style={{ width: "400px" }} className="p-4 shadow-lg">
        <h2 className="text-center mb-4">Login</h2>

        <Form onSubmit={handleLogin}>

          <Form.Group className="mb-3">
            <Form.Label>Email</Form.Label>
            <Form.Control
              type="email"
              placeholder="Enter email"
              value={email}
              onChange={(e) => setEmail(e.target.value)}
              required
            />
          </Form.Group>

          <Form.Group className="mb-4">
            <Form.Label>Password</Form.Label>
            <Form.Control
              type="password"
              placeholder="Enter password"
              value={password}
              onChange={(e) => setPassword(e.target.value)}
              required
            />
          </Form.Group>

          {error && <p style={{ color: "red" }}>{error}</p>}

          <Button variant="dark" className="w-100" type="submit">
            Login
          </Button>

        </Form>

        <p className="text-center mt-3">
          Don't have account?{" "}
          <span
            style={{ color: "blue", cursor: "pointer" }}
            onClick={() => (window.location.href = "/register")}
          >
            Register
          </span>
        </p>

      </Card>
    </div>
  );
}

export default Login;

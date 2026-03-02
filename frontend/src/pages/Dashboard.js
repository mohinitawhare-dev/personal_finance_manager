import React, { useEffect, useState } from "react";
import {
  Card,
  Row,
  Col,
  Spinner,
  Alert,
  Container,
  Nav
} from "react-bootstrap";
import incomeService from "../services/incomeService";
import expenseService from "../services/expenseService";

function Dashboard() {
  const [totalIncome, setTotalIncome] = useState(0);
  const [totalExpense, setTotalExpense] = useState(0);
  const [remainingBudget, setRemainingBudget] = useState(0);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState("");

  const user = JSON.parse(localStorage.getItem("user")) || { userId: 1 };
  const userId = parseInt(user.userId);

  useEffect(() => {
    const fetchTotals = async () => {
      setLoading(true);
      setError("");

      try {
        const incomeRes = await incomeService.getAllIncomesByUser(userId);
        const incomeSum = incomeRes.data
          .filter((inc) => inc.user && inc.user.userId === userId)
          .reduce((acc, cur) => acc + cur.amount, 0);
        setTotalIncome(incomeSum);

        const expenseRes = await expenseService.getExpensesByUser(userId);
        const expenseSum = expenseRes.data
          .filter((exp) => exp.user && exp.user.userId === userId)
          .reduce((acc, cur) => acc + cur.amount, 0);
        setTotalExpense(expenseSum);

        setRemainingBudget(incomeSum - expenseSum);
        setLoading(false);
      } catch (err) {
        setError("Failed to load dashboard data.");
        setLoading(false);
      }
    };

    fetchTotals();
  }, [userId]);

  if (loading)
    return (
      <div className="text-center mt-5">
        <Spinner animation="border" /> Loading...
      </div>
    );

  if (error)
    return (
      <Alert variant="danger" className="mt-3">
        {error}
      </Alert>
    );

  return (
    <div style={{ display: "flex", minHeight: "100vh" }}>
      
      {/* 🔹 Sidebar */}
      <div
        style={{
          width: "230px",
          background: "#1e293b",
          color: "white",
          padding: "20px"
        }}
      >
        <h4 className="fw-bold mb-4">Finance App</h4>

        <Nav className="flex-column">
          <Nav.Link href="/dashboard" style={{ color: "white" }}>
            📊 Dashboard
          </Nav.Link>
          <Nav.Link href="/income" style={{ color: "white" }}>
            💰 Income
          </Nav.Link>
          <Nav.Link href="/expense" style={{ color: "white" }}>
            💸 Expenses
          </Nav.Link>
          <Nav.Link href="/budget" style={{ color: "white" }}>
            📅 Budget
          </Nav.Link>
          <Nav.Link href="/category" style={{ color: "white" }}>
            🏷 Category
          </Nav.Link>
        </Nav>
      </div>

      {/* 🔹 Main Content */}
      <Container fluid className="p-4" style={{ background: "#f1f5f9" }}>
        
        <h2 className="fw-bold mb-4">Dashboard Overview</h2>

        {/* 🔹 Top Cards */}
        <Row className="g-4">

          <Col md={4}>
            <Card className="shadow border-0 p-4 text-center">
              <div style={{ fontSize: 42 }}>💰</div>
              <h6 className="text-muted mt-2">Total Income</h6>
              <h2 className="fw-bold text-success">₹ {totalIncome}</h2>
            </Card>
          </Col>

          <Col md={4}>
            <Card className="shadow border-0 p-4 text-center">
              <div style={{ fontSize: 42 }}>💸</div>
              <h6 className="text-muted mt-2">Total Expenses</h6>
              <h2 className="fw-bold text-danger">₹ {totalExpense}</h2>
            </Card>
          </Col>

          <Col md={4}>
            <Card className="shadow border-0 p-4 text-center">
              <div style={{ fontSize: 42 }}>📊</div>
              <h6 className="text-muted mt-2">Remaining Budget</h6>
              <h2 className="fw-bold text-primary">
                ₹ {remainingBudget}
              </h2>
            </Card>
          </Col>

        </Row>

        {/* 🔹 Summary Section */}
        <Row className="mt-5">
          <Col md={12}>
            <Card className="shadow border-0 p-4">
              <h5 className="fw-bold mb-3">Financial Summary</h5>
              <p className="text-muted mb-1">
                ✔️ Track your income and expenses easily
              </p>
              <p className="text-muted mb-1">
                ✔️ Stay within your monthly budget
              </p>
              <p className="text-muted">
                ✔️ Make better financial decisions
              </p>
            </Card>
          </Col>
        </Row>

      </Container>
    </div>
  );
}

export default Dashboard;
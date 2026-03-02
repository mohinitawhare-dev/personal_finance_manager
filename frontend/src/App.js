import React from "react";
import { BrowserRouter, Routes, Route } from "react-router-dom";
import Layout from "./components/Layout";
import Login from "./pages/Login";
import Register from "./pages/Register";
import Dashboard from "./pages/Dashboard";
import Expense from "./pages/Expense";
import Income from "./pages/Income";
import Budget from "./pages/Budget";
import Category from "./pages/Category";

function App() {
  // Logged-in user from localStorage (used for Expense component)
  const loggedInUser = JSON.parse(localStorage.getItem("user")) || { userId: 1 };

  return (
    <BrowserRouter>
      <Routes>
        {/* Public routes */}
        <Route path="/" element={<Login />} />
        <Route path="/register" element={<Register />} />

        {/* Protected routes wrapped with Layout (Navbar on top) */}
        <Route path="/dashboard" element={<Layout><Dashboard /></Layout>} />
        <Route path="/income" element={<Layout><Income /></Layout>} />
        <Route path="/expenses" element={<Layout><Expense userId={loggedInUser.userId} /></Layout>} />
        <Route path="/budget" element={<Layout><Budget /></Layout>} />
        <Route path="/category" element={<Layout><Category /></Layout>} />
      </Routes>
    </BrowserRouter>
  );
}

export default App;
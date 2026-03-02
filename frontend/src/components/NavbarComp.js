import React from "react";
import { Navbar, Nav, Container, Button } from "react-bootstrap";

function NavbarComp() {

  const handleLogout = () => {
    localStorage.removeItem("user");
    window.location.href = "/login";
  };

  return (
    <Navbar bg="primary" variant="dark" expand="lg" className="shadow-sm mb-4">
      <Container>

        {/* Project Name */}
        <Navbar.Brand href="/" className="fw-bold">
          Personal Finance Manager
        </Navbar.Brand>

        <Navbar.Toggle aria-controls="basic-navbar-nav" />

        <Navbar.Collapse id="basic-navbar-nav">

          {/* Left Menu */}
          <Nav className="me-auto">
            <Nav.Link href="/income">Income</Nav.Link>
            <Nav.Link href="/expense">Expenses</Nav.Link>
            <Nav.Link href="/budget">Budget</Nav.Link>
            <Nav.Link href="/category">Category</Nav.Link>
          </Nav>

          {/* Right Side — Only Logout */}
          <Nav className="ms-auto">
            <Button variant="light" size="sm" onClick={handleLogout}>
              Logout
            </Button>
          </Nav>

        </Navbar.Collapse>
      </Container>
    </Navbar>
  );
}

export default NavbarComp;
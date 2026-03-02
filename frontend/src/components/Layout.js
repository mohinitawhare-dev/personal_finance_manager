import React from "react";
import NavbarComp from "./NavbarComp"; // Import your Navbar component

function Layout({ children }) {
  return (
    <>
      {/* Navbar always on top */}
      <NavbarComp />

      {/* Page content below navbar */}
      <div className="container mt-4">
        {children}
      </div>
    </>
  );
}

export default Layout;
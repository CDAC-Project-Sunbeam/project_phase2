// SellerDashboard.js
import React from "react";
import { useNavigate } from "react-router-dom";
import Navbar from "../components/Navbar";

function SellerDashboard() {
  const navigate = useNavigate();
  const sellerId = sessionStorage.getItem("sellerid"); // Example sellerId, replace with dynamic value if needed

  const handleAddProductClick = () => {
    navigate(`/add-product/${sellerId}`);
  };

  const viewProducts = () => {
    navigate(`/view-products`);
  };

  const manageOrders = () => {
    navigate(`/manage-orders/${sellerId}`);
  };

  return (
    <div style={{ textAlign: "center", padding: "50px 0" }}>
      <Navbar />
      <h1 style={{ marginBottom: "50px", marginTop: 100 }}>SELLER DASHBOARD</h1>

      <div style={{ display: "flex", justifyContent: "center", gap: "20px" }}>
        <button
          onClick={handleAddProductClick}
          className="button"
          style={{
            padding: "15px 30px",
            fontSize: "16px",
            cursor: "pointer",
            border: "none",
            borderRadius: "5px",
            backgroundColor: "#007bff",
            color: "#fff",
          }}
        >
          <span className="label">ADD PRODUCT</span>
        </button>

        <button
          onClick={viewProducts}
          className="button"
          style={{
            padding: "15px 30px",
            fontSize: "16px",
            cursor: "pointer",
            border: "none",
            borderRadius: "5px",
            backgroundColor: "#007bff",
            color: "#fff",
          }}
        >
          <span className="label">VIEW PRODUCTS</span>
        </button>

        <button
          onClick={manageOrders}
          className="button"
          style={{
            padding: "15px 30px",
            fontSize: "16px",
            cursor: "pointer",
            border: "none",
            borderRadius: "5px",
            backgroundColor: "#007bff",
            color: "#fff",
          }}
        >
          <span className="label">MANAGE ORDERS</span>
        </button>
      </div>
    </div>
  );
}

export default SellerDashboard;

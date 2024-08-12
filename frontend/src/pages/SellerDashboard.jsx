import React from 'react';
import { Link } from 'react-router-dom';
import { useNavigate } from "react-router-dom";


function SellerDashboard() {
const navigate = useNavigate();
const sellerId = 1; // Example sellerId, replace with dynamic value if needed

const handleAddProductClick = () => {
  navigate(`/add-product/${sellerId}`);
};

return (
  <div style={{ textAlign: "center", padding: "50px 0" }}>
    <h1 style={{ marginBottom: "50px" }}>SELLER DASHBOARD</h1>

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
        onClick={() => {}}
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
        <span className="label">MANAGE PRODUCT</span>
      </button>

      <button
        onClick={() => {}}
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
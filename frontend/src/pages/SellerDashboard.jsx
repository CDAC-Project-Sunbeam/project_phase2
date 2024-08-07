import React from 'react';
import { Link } from 'react-router-dom';



function Dashboard() {
  return (
    <div>
      <h1>Seller Dashboard</h1>
      <ul>
        <li><Link to="/add-product">Add Product</Link></li>
        <li><Link to="/manage-products">Manage Products</Link></li>
        <li><Link to="/manage-orders">Manage Orders</Link></li>
      </ul>
    </div>
  );
}

export default Dashboard;
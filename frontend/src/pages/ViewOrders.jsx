// ViewOrders.js
import React, { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import Navbar from "../components/Navbar";

const ViewOrders = () => {
  const { sellerId } = useParams();
  const [orders, setOrders] = useState([]);

  useEffect(() => {
    const fetchOrders = async () => {
      try {
        const response = await fetch(
          `http://localhost:8080/orders/seller/${sellerId}`
        );
        if (!response.ok) {
          throw new Error("Network response was not ok");
        }
        const ordersData = await response.json();
        setOrders(ordersData);
      } catch (error) {
        console.error("Error fetching orders:", error);
      }
    };

    fetchOrders();
  }, [sellerId]);

  return (
    <div style={{ textAlign: "center", padding: "50px 0" }}>
      <Navbar />
      <h1 style={{ marginBottom: "50px", marginTop: 100 }}>VIEW ORDERS</h1>

      <div style={{ padding: "0 20px" }}>
        <h2>Orders</h2>
        {orders.length > 0 ? (
          <table style={{ margin: "0 auto", borderCollapse: "collapse" }}>
            <thead>
              <tr>
                <th style={{ border: "1px solid #ddd", padding: "8px" }}>
                  Product Name
                </th>
                <th style={{ border: "1px solid #ddd", padding: "8px" }}>
                  Quantity
                </th>
                <th style={{ border: "1px solid #ddd", padding: "8px" }}>
                  Amount
                </th>
                <th style={{ border: "1px solid #ddd", padding: "8px" }}>
                  Shipping Address
                </th>
                <th style={{ border: "1px solid #ddd", padding: "8px" }}>
                  Status
                </th>
              </tr>
            </thead>
            <tbody>
              {orders.map((order) => (
                <tr key={order.id}>
                  <td style={{ border: "1px solid #ddd", padding: "8px" }}>
                    {order.productName}
                  </td>
                  <td style={{ border: "1px solid #ddd", padding: "8px" }}>
                    {order.quantity}
                  </td>
                  <td style={{ border: "1px solid #ddd", padding: "8px" }}>
                    ${order.amount}
                  </td>
                  <td style={{ border: "1px solid #ddd", padding: "8px" }}>
                    {order.shippingAddress.adrLine1},{" "}
                    {order.shippingAddress.city}, {order.shippingAddress.state},{" "}
                    {order.shippingAddress.zipCode}
                  </td>
                  <td style={{ border: "1px solid #ddd", padding: "8px" }}>
                    {order.status}
                  </td>
                </tr>
              ))}
            </tbody>
          </table>
        ) : (
          <p>No orders available.</p>
        )}
      </div>
    </div>
  );
};

export default ViewOrders;

import React, { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import Navbar from "../components/Navbar";

const ViewProducts = () => {
  const sellerId = sessionStorage.getItem("sellerid");
  const navigate = useNavigate();
  const [products, setProducts] = useState([]);

  useEffect(() => {
    const fetchProducts = async () => {
      try {
        const response = await fetch(
          `http://localhost:8080/product/seller/${sellerId}`
        );
        if (!response.ok) {
          throw new Error("Network response was not ok");
        }
        const productsData = await response.json();
        setProducts(productsData);
      } catch (error) {
        console.error("Error fetching products:", error);
      }
    };

    fetchProducts();
  }, [sellerId]);

  const handleUpdateClick = (product) => {
    navigate(`/update-product/${product.id}`, { state: { product } });
  };

  return (
    <div style={{ textAlign: "center", padding: "50px 0" }}>
      <Navbar />
      <h1 style={{ marginBottom: "50px", marginTop: 100 }}>VIEW PRODUCTS</h1>

      <div style={{ padding: "0 20px" }}>
        <h2>Products</h2>
        {products.length > 0 ? (
          <table style={{ margin: "0 auto", borderCollapse: "collapse" }}>
            <thead>
              <tr>
                <th style={{ border: "1px solid #ddd", padding: "8px" }}>
                  Product ID
                </th>
                <th style={{ border: "1px solid #ddd", padding: "8px" }}>
                  Name
                </th>
                <th style={{ border: "1px solid #ddd", padding: "8px" }}>
                  Price
                </th>
                <th style={{ border: "1px solid #ddd", padding: "8px" }}>
                  Description
                </th>
                <th style={{ border: "1px solid #ddd", padding: "8px" }}>
                  Actions
                </th>
              </tr>
            </thead>
            <tbody>
              {products.map((product) => (
                <tr key={product.id}>
                  <td style={{ border: "1px solid #ddd", padding: "8px" }}>
                    {product.id}
                  </td>
                  <td style={{ border: "1px solid #ddd", padding: "8px" }}>
                    {product.name}
                  </td>
                  <td style={{ border: "1px solid #ddd", padding: "8px" }}>
                    ${product.price}
                  </td>
                  <td style={{ border: "1px solid #ddd", padding: "8px" }}>
                    {product.description}
                  </td>
                  <td style={{ border: "1px solid #ddd", padding: "8px" }}>
                    <button
                      onClick={() => handleUpdateClick(product)}
                      style={{
                        padding: "10px 20px",
                        fontSize: "14px",
                        cursor: "pointer",
                        border: "none",
                        borderRadius: "5px",
                        backgroundColor: "#28a745",
                        color: "#fff",
                      }}
                    >
                      Update
                    </button>
                  </td>
                </tr>
              ))}
            </tbody>
          </table>
        ) : (
          <p>No products available.</p>
        )}
      </div>
    </div>
  );
};

export default ViewProducts;

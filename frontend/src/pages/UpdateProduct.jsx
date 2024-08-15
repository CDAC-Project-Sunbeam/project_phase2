import React, { useState, useEffect } from "react";
import { useNavigate, useLocation } from "react-router-dom";
import Navbar from "../components/Navbar";

const UpdateProduct = () => {
  const navigate = useNavigate();
  const location = useLocation();
  const product = location.state?.product;

  const [formData, setFormData] = useState({
    name: "",
    description: "",
    price: 0,
    stockQuantity: 0,
    discount: 0,
    brandName: "",
    mainImgUrl: "",
  });

  useEffect(() => {
    if (product) {
      setFormData({
        name: product.name,
        description: product.description,
        price: product.price,
        stockQuantity: product.stockQuantity,
        discount: product.discount,
        brandName: product.brandName,
        mainImgUrl: product.mainImgUrl,
      });
    }
  }, [product]);

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData((prev) => ({
      ...prev,
      [name]: value,
    }));
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      const response = await fetch(
        `http://localhost:8080/product/${product.id}`,
        {
          method: "PUT",
          headers: {
            "Content-Type": "application/json",
          },
          body: JSON.stringify(formData),
        }
      );
      if (response.ok) {
        alert("Product updated successfully!");
        navigate("/view-products"); // Navigate back to the view products page
      } else {
        throw new Error("Failed to update product");
      }
    } catch (error) {
      console.error("Error updating product:", error);
    }
  };

  return (
    <div style={{ textAlign: "center", padding: "50px 0" }}>
      <Navbar />
      <h1 style={{ marginBottom: "50px", marginTop: 100 }}>UPDATE PRODUCT</h1>

      <div style={{ padding: "0 20px" }}>
        <form onSubmit={handleSubmit}>
          <label>
            Name:
            <input
              type="text"
              name="name"
              value={formData.name}
              onChange={handleChange}
              required
            />
          </label>
          <br />
          <label>
            Description:
            <textarea
              name="description"
              value={formData.description}
              onChange={handleChange}
              required
            />
          </label>
          <br />
          <label>
            Price:
            <input
              type="number"
              name="price"
              value={formData.price}
              onChange={handleChange}
              required
            />
          </label>
          <br />
          <label>
            Stock Quantity:
            <input
              type="number"
              name="stockQuantity"
              value={formData.stockQuantity}
              onChange={handleChange}
              required
            />
          </label>
          <br />
          <label>
            Discount:
            <input
              type="number"
              name="discount"
              value={formData.discount}
              onChange={handleChange}
            />
          </label>
          <br />
          <label>
            Brand Name:
            <input
              type="text"
              name="brandName"
              value={formData.brandName}
              onChange={handleChange}
            />
          </label>
          <br />
          <label>
            Main Image URL:
            <input
              type="text"
              name="mainImgUrl"
              value={formData.mainImgUrl}
              onChange={handleChange}
            />
          </label>
          <br />
          <button
            type="submit"
            style={{
              padding: "10px 20px",
              fontSize: "16px",
              cursor: "pointer",
              border: "none",
              borderRadius: "5px",
              backgroundColor: "#007bff",
              color: "#fff",
            }}
          >
            Update Product
          </button>
        </form>
      </div>
    </div>
  );
};

export default UpdateProduct;

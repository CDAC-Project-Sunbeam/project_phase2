import React, { useState } from "react";
import { useParams } from "react-router-dom";
import { createProduct } from "../services/products";
import { toast } from "react-toastify";
const AddProductPage = () => {
  
  const { sellerId } = useParams();
  const [name, setName] = useState("");
  const [description, setDescription] = useState("");
  const [price, setPrice] = useState("");
  const [brandName, setBrandName] = useState("");
  const [stockQuantity, setStockQuantity] = useState("");
  const [discount, setDiscount] = useState("");
  const [file, setFile] = useState(null);
  const categoryId = 2; // Example categoryId, replace with dynamic value if needed

  const handleFileChange = (e) => {
    setFile(e.target.files[0]);
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    const formData = new FormData();
    formData.append("name", name);
    formData.append("description", description);
    formData.append("price", price);
    formData.append("brandName", brandName);
    formData.append("stockQuantity", stockQuantity);
    formData.append("discount", discount);
    if (file) {
      formData.append("file", file);
    }

    try {
      const response = await createProduct(formData, sellerId, categoryId);
      console.log("Product added successfully:", response);
       toast.success("Product added successfully:");
    } catch (error) {
      console.error("Error adding product:", error);
    }
  };

  return (
    
    <div >
      <h1 className='mb-4'>Add New Product</h1>
      <form onSubmit={handleSubmit}>
        <div className='form-group mb-3'>
          <label>Name:</label>
          <input
            type="text"
            value={name}
            onChange={(e) => setName(e.target.value)}
            required
          />
        </div>
        <div className='form-group mb-3'>
          <label>Description:</label>
          <textarea
            value={description}
            onChange={(e) => setDescription(e.target.value)}
            required
          />
        </div>
        <div className='form-group mb-3'>
          <label>Price:</label>
          <input
            type="number"
            step="0.01"
            value={price}
            onChange={(e) => setPrice(e.target.value)}
            required
          />
        </div>
        <div className='form-group mb-3'>
          <label>Brand Name:</label>
          <input
            type="text"
            value={brandName}
            onChange={(e) => setBrandName(e.target.value)}
            required
          />
        </div>
        <div className='form-group mb-3'>
          <label>Stock Quantity:</label>
          <input
            type="number"
            value={stockQuantity}
            onChange={(e) => setStockQuantity(e.target.value)}
            required
          />
        </div>
        <div className='form-group mb-3'>
          <label>Discount:</label>
          <input
            type="number"
            step="0.01"
            value={discount}
            onChange={(e) => setDiscount(e.target.value)}
            required
          />
        </div>
        <div className='form-group mb-3'>
          <label>Main Image:</label>
          <input type="file" onChange={handleFileChange} />
        </div>
        <button type="submit">Add Product</button>
      </form>
    </div>
  );
};

export default AddProductPage;

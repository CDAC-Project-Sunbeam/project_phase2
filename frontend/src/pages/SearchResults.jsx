import React from "react";
import { useLocation, useNavigate } from "react-router-dom";
import "../css/SearchResults.css"; // Ensure you have a CSS file for styling

function SearchResults() {
  const location = useLocation();
  const navigate = useNavigate();
  const { products } = location.state || { products: [] };

  const handleProductClick = (id) => {
    navigate(`/product-details/${id}`);
  };

  return (
    <div className="container">
      <h1>Search Results</h1>
      {products.length > 0 ? (
        <div className="product-grid">
          {products.map((product) => (
            <div
              key={product.id}
              className="product-card"
              onClick={() => handleProductClick(product.id)}
            >
              <img
                src={`http://localhost:8080/${product.mainImgUrl}`}
                alt={product.name}
                className="product-image"
              />
              <div className="product-info">
                <h2>{product.name}</h2>
                <p>Brand: {product.brandName}</p>
                <p>Price: ${product.price}</p>
              </div>
            </div>
          ))}
        </div>
      ) : (
        <p>No products found.</p>
      )}
    </div>
  );
}

export default SearchResults;

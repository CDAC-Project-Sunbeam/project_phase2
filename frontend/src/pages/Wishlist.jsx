import React, { useEffect, useState,useParams } from "react";
import { getWishlistProducts,addProductToCart } from "../services/products";
import axios from "axios";
import { toast } from "react-toastify";
import { useNavigate } from "react-router-dom";
import "../css/Wishlist.css";

function Wishlist() {
  
  const [wishlistItems, setWishlistItems] = useState([]);
  const customerId = sessionStorage.getItem("customerid");
  const navigate = useNavigate(); // Initialize navigate

  // Function to load wishlist products
  const loadWishlistProducts = async (customerId) => {
    try {
      const products = await getWishlistProducts(customerId);
      setWishlistItems(products);
    } catch (error) {
      console.error("Failed to fetch wishlist products:", error);
    }
  };

  // Function to handle removing a product from the wishlist
  const handleRemoveProduct = async (customerId, productId) => {
    try {
      await axios.delete(
        `http://localhost:8080/wishlist/${customerId}/${productId}`
      );
      toast.success("Product removed from wishlist");
      setWishlistItems((prevItems) =>
        prevItems.filter((item) => item.id !== productId)
      );
    } catch (error) {
      toast.error("Failed to remove product from wishlist");
      console.error("Error during product removal:", error);
    }
  };

  // Function to handle adding a product to the cart
  // const handleAddToCart = async (product) => {
  //   try {
  //     await axios.post(`http://localhost:8080/cart/${customerId}`, {
  //       productId: product.id,
  //       quantity: 1, // Default quantity to 1
  //     });
  //     toast.success("Product added to cart");
  //     // Optionally remove the product from the wishlist after adding it to the cart
  //     handleRemoveProduct(customerId, product.id);
  //     // Navigate to the Cart page
  //     navigate("/cart");
  //   } catch (error) {
  //     toast.error("Failed to add product to cart");
  //     console.error("Error during adding product to cart:", error);
  //   }
  // };
   const handleAddToCart = async () => {
     const customerId = sessionStorage.getItem("customerid");
     if (!customerId) {
       console.error("Customer ID is not available");
       return;
     }
     const quantity = 1;

     try {
       await addProductToCart(customerId, quantity);
       navigate("/cart"); // Navigate to cart page
     } catch (error) {
       console.error("Failed to add product to cart:", error);
     }
   };

  // Function to handle navigation to product detail page
  const handleProductClick = (productId) => {
    navigate(`/product/${productId}`);
  };

  useEffect(() => {
    if (customerId) {
      loadWishlistProducts(customerId);
    } else {
      console.error("No customer ID found in session storage");
    }
  }, [customerId]);

  return (
    <div className="wishlist-page">
      <h2 className="wishlist-title">Your Wishlist</h2>
      {wishlistItems.length > 0 ? (
        <div className="wishlist-items-container">
          {wishlistItems.map((item) => (
            <div key={item.id} className="wishlist-item">
              <img
                src={`http://localhost:8080/images/${item.mainImgUrl}`}
                alt={item.name}
                className="product-image"
              />
              <div className="product-details">
                <h3
                  className="product-name"
                  onClick={() => handleProductClick(item.id)}
                >
                  {item.name}
                </h3>
                <p className="product-brand">{item.brandName}</p>
                <p className="product-price">Price: ${item.price}</p>
                <p className="product-discount">Discount: {item.discount}%</p>
                <div className="button-group">
                  <button
                    onClick={() => handleAddToCart(item)}
                    className="add-to-cart-btn"
                  >
                    Add to Cart
                  </button>
                  
                  <button
                    onClick={() => handleRemoveProduct(customerId, item.id)}
                    className="remove-btn"
                  >
                    Remove from Wishlist
                  </button>
                </div>
              </div>
            </div>
          ))}
        </div>
      ) : (
        <p className="empty-message">Your wishlist is empty.</p>
      )}
    </div>
  );
}

export default Wishlist;

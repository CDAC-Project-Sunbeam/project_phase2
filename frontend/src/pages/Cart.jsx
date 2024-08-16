import React, { useEffect, useState } from "react";
import { getCartProducts } from "../services/products";
import { useNavigate } from "react-router-dom";
import axios from "axios";
import { toast } from "react-toastify";
import BarNav from "../components/BarNav";
import "../css/Cart.css"; // Import the new CSS

function Cart() {
  const [cartItems, setCartItems] = useState([]);
  const customerId = sessionStorage.getItem("customerid");
  const navigate = useNavigate(); // Initialize navigate

  // Function to load cart products
  const loadCartProducts = async (customerId) => {
    try {
      const products = await getCartProducts(customerId);
      // Initialize quantity to 1 for all products
      const productsWithQuantityAndDiscount = products.map((product) => ({
        ...product,
        quantity: 1,
        discount: product.discount || 0, // Ensure discount is included, default to 0 if not present
      }));
      setCartItems(productsWithQuantityAndDiscount);
    } catch (error) {
      console.error("Failed to fetch cart products:", error);
    }
  };

  // Function to handle quantity change
  const handleQuantityChange = (productId, newQuantity) => {
    setCartItems((prevItems) =>
      prevItems.map((item) =>
        item.id === productId
          ? { ...item, quantity: parseInt(newQuantity, 10) }
          : item
      )
    );
  };

  // Function to handle removing a product from the cart
  const handleRemoveProduct = async (customerId, productId) => {
    try {
      await axios.delete(
        `http://localhost:8080/cart/${customerId}/${productId}`
      );
      toast.success("Product removed from cart");
      setCartItems((prevItems) =>
        prevItems.filter((item) => item.id !== productId)
      );
    } catch (error) {
      toast.error("Failed to remove product from cart");
      console.error("Error during product removal:", error);
    }
  };

  // Function to handle placing the order
  const handlePlaceOrder = () => {
    const totalAmount = cartItems.reduce((total, item) => {
      const discountedPrice = item.price * (1 - item.discount / 100);
      return total + discountedPrice * item.quantity;
    }, 0);
    navigate("/place-order", { state: { cartItems, totalAmount } }); // Pass cartItems and totalAmount to the place order page
  };

  useEffect(() => {
    if (customerId) {
      loadCartProducts(customerId);
    } else {
      console.error("No customer ID found in session storage");
    }
  }, [customerId]);

  return (
    <div className="cart-page">
      <div>
        <BarNav />
      </div>
      <div className="cart-content">
        <h2 className="cart-title">Your Cart</h2>
        {cartItems.length > 0 ? (
          <div className="cart-items-container">
            {cartItems.map((item) => (
              <div key={item.id} className="cart-item">
                <img
                  src={`http://localhost:8080/images/${item.mainImgUrl}`}
                  alt={item.name}
                  className="product-image"
                />
                <div className="product-details">
                  <h3 className="product-name">{item.name}</h3>
                  <p className="product-brand">{item.brandName}</p>
                  <p>Quantity:</p>
                  <input
                    type="number"
                    value={item.quantity}
                    min="1"
                    onChange={(e) =>
                      handleQuantityChange(item.id, e.target.value)
                    }
                  />
                  <p className="product-price">Price: ${item.price}</p>
                  <p className="product-discount">Discount: {item.discount}%</p>
                  <button
                    onClick={() => handleRemoveProduct(customerId, item.id)}
                    className="remove-btn"
                  >
                    Remove
                  </button>
                </div>
              </div>
            ))}
            <button onClick={handlePlaceOrder} className="place-order-btn">
              Place Order
            </button>
          </div>
        ) : (
          <p className="empty-message">Your cart is empty.</p>
        )}
      </div>
    </div>
  );
}

export default Cart;

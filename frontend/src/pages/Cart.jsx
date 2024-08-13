import React, { useEffect, useState } from 'react';
import { getCartProducts } from '../services/products'; // Ensure correct path

function Cart() {
  const [cartItems, setCartItems] = useState([]);
  const customerId = sessionStorage.getItem('customerId');

  // Function to load cart products
  const loadCartProducts = async (customerId) => {
    try {
      const products = await getCartProducts(customerId);
      setCartItems(products);
    } catch (error) {
      console.error('Failed to fetch cart products:', error);
    }
  };

  useEffect(() => {
    if (customerId) {
      loadCartProducts(customerId);
    } else {
      console.error('No customer ID found in session storage');
    }
  }, [customerId]);

  return (
    <div>
      <h2>Your Cart</h2>
      {cartItems.length > 0 ? (
        <div>
          {cartItems.map((item) => (
            <div key={item.productId} className="cart-item">
              <h3>{item.productName}</h3>
              <p>Quantity: {item.quantity}</p>
              <p>Price: {item.price}</p>
              <hr />
            </div>
          ))}
        </div>
      ) : (
        <p>Your cart is empty.</p>
      )}
    </div>
  );
}

export default Cart;



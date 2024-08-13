import React, { useEffect, useState } from 'react';
import { getCartProducts } from '../services/products'; // Ensure correct path
import { useNavigate } from 'react-router-dom'; // For navigation

function Cart() {
  const [cartItems, setCartItems] = useState([]);
  const customerId = sessionStorage.getItem('customerid');
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
      console.error('Failed to fetch cart products:', error);
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

  // Function to handle placing the order
  const handlePlaceOrder = () => {
    const totalAmount = cartItems.reduce((total, item) => {
      const discountedPrice = item.price * (1 - item.discount / 100);
      return total + discountedPrice * item.quantity;
    }, 0);
    navigate('/place-order', { state: { cartItems, totalAmount } }); // Pass cartItems and totalAmount to the place order page
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
              <h3>{item.brandName}</h3>
              <h3>{item.name}</h3>
              <img
                style={{ height: 100,width:100 }}
                src={`http://localhost:8080/${item.mainImgUrl}`}
                alt={item.name}
                className="product-image"
              />
              <p>Quantity:</p>
              <input
                type="number"
                value={item.quantity}
                min="1"
                onChange={(e) =>
                  handleQuantityChange(item.id, e.target.value)
                }
              />
              <p>Price: {item.price}</p>
              <p>Discount: {item.discount}%</p>
              <hr />
            </div>
          ))}
          <button onClick={handlePlaceOrder}>Place Order</button>
        </div>
      ) : (
        <p>Your cart is empty.</p>
      )}
    </div>
  );
}

export default Cart;

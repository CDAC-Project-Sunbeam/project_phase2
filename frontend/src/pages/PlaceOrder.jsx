import React from 'react';
import { useLocation } from 'react-router-dom';

function PlaceOrder() {
  const location = useLocation();
  const { cartItems, totalAmount } = location.state; // Access cart items and total amount passed from the Cart component

  return (
    <div>
      <h2>Place Your Order</h2>
      {cartItems.map((item) => (
        <div key={item.productId} className="order-item">
          <h3>{item.brandName} - {item.name}</h3>
          <p>Quantity: {item.quantity}</p>
          <p>Price: {item.price}</p>
          <p>Discount: {item.discount}%</p>
          <p>
            Total for this item after discount: {(item.price * (1 - item.discount / 100) * item.quantity).toFixed(2)}
          </p>
          <hr />
        </div>
      ))}
      <h3>Total Amount after Discount: {totalAmount.toFixed(2)}</h3>
      {/* Add place order form or button here */}
    </div>
  );
}

export default PlaceOrder;

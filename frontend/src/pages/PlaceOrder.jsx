import React, { useState } from "react";
import { useLocation, useNavigate } from "react-router-dom";
import axios from "axios";
import { toast } from "react-toastify";

function PlaceOrder() {
  const location = useLocation();
  const { cartItems, totalAmount } = location.state;
  const navigate = useNavigate();

  const [address, setAddress] = useState({
    adrLine1: "",
    adrLine2: "",
    city: "",
    state: "",
    country: "",
    zipCode: "",
  });

  const handleChange = (e) => {
    const { name, value } = e.target;
    setAddress((prevAddress) => ({
      ...prevAddress,
      [name]: value,
    }));
  };

  const handlePlaceOrder = async () => {
    const customerId = sessionStorage.getItem("customerid");

    // Prepare order data
    const orderData = {
      shippingAddress: {
        ...address,
      },
      orderItemRequestDTO: cartItems.map((item) => ({
        productId: item.id,
        quantity: item.quantity,
        price: item.price,
      })),
    };

    try {
      const response = await axios.post(
        `http://localhost:8080/orders/createOrder/${customerId}`,
        orderData
      );
      toast.success("Order placed successfully");
      navigate("/order-confirmation", {
        state: { orderDetails: response.data },
      });
    } catch (error) {
      console.error("Error placing order:", error);
      toast.error("Failed to place order");
    }
  };

  return (
    <div>
      <h2>Place Your Order</h2>
      {cartItems.length > 0 ? (
        <div>
          <div>
            {cartItems.map((item) => (
              <div key={item.id} className="order-item">
                <h3>
                  {item.brandName} - {item.name}
                </h3>
                <p>Quantity: {item.quantity}</p>
                <p>Price: {item.price}</p>
                <p>Discount: {item.discount}%</p>
                <p>
                  Total for this item after discount:{" "}
                  {(
                    item.price *
                    (1 - item.discount / 100) *
                    item.quantity
                  ).toFixed(2)}
                </p>
                <hr />
              </div>
            ))}
          </div>
          <h3>Total Amount after Discount: {totalAmount.toFixed(2)}</h3>
          <form onSubmit={(e) => e.preventDefault()}>
            <div>
              <label>Address Line 1:</label>
              <input
                type="text"
                name="adrLine1"
                value={address.adrLine1}
                onChange={handleChange}
                required
              />
            </div>
            <div>
              <label>Address Line 2:</label>
              <input
                type="text"
                name="adrLine2"
                value={address.adrLine2}
                onChange={handleChange}
              />
            </div>
            <div>
              <label>City:</label>
              <input
                type="text"
                name="city"
                value={address.city}
                onChange={handleChange}
                required
              />
            </div>
            <div>
              <label>State:</label>
              <input
                type="text"
                name="state"
                value={address.state}
                onChange={handleChange}
                required
              />
            </div>
            <div>
              <label>Country:</label>
              <input
                type="text"
                name="country"
                value={address.country}
                onChange={handleChange}
                required
              />
            </div>
            <div>
              <label>Zip Code:</label>
              <input
                type="text"
                name="zipCode"
                value={address.zipCode}
                onChange={handleChange}
                required
              />
            </div>
            <button onClick={handlePlaceOrder}>Place Order</button>
          </form>
        </div>
      ) : (
        <p>Your cart is empty.</p>
      )}
    </div>
  );
}

export default PlaceOrder;

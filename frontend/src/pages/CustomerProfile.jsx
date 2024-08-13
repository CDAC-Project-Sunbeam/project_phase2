import React, { useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";
import axios from "axios";

function CustomerProfile() {
  const [user, setUser] = useState(null);
  const [showAddressForm, setShowAddressForm] = useState(false);
  const [address, setAddress] = useState({
    adrLine1: "",
    adrLine2: "",
    city: "",
    state: "",
    country: "",
    zipCode: "",
  });
  const navigate = useNavigate();
  const customerId = sessionStorage.getItem("customerid");

  useEffect(() => {
    const fetchUserDetails = async () => {
      try {
        const response = await axios.get(
          `http://localhost:8080/customer/${customerId}`
        );
        console.log(response);
        setUser(response.data);
        // Check if address exists
        if (response.data.customerAddress) {
          setAddress(response.data.customerAddress);
        } else {
          setShowAddressForm(true);
        }
      } catch (error) {
        console.error("Error fetching user details:", error);
      }
    };

    if (customerId) {
      fetchUserDetails();
    } else {
      navigate("/login");
    }
  }, [customerId, navigate]);

  const handleAddressChange = (e) => {
    const { name, value } = e.target;
    setAddress((prevAddress) => ({
      ...prevAddress,
      [name]: value,
    }));
  };

  const handleAddressSubmit = async (e) => {
    e.preventDefault();
    try {
      await axios.post(
        `http://localhost:8080/customer/Address/${customerId}`,
        address
      );
      setShowAddressForm(false); // Hide form after submission
      // Optionally, re-fetch user details to update the address display
    } catch (error) {
      console.error("Error adding address:", error);
    }
  };

  if (!user) {
    return <div>Loading...</div>;
  }

  return (
    <div>
      <h2>Customer Profile</h2>
      <p>
        <strong>First Name:</strong> {user.firstName}
      </p>
      <p>
        <strong>Last Name:</strong> {user.lastName}
      </p>
      <p>
        <strong>Email:</strong> {user.email}
      </p>
      <p>
        <strong>Phone Number:</strong> {user.phoneNumber}
      </p>
      <p>
        <strong>Date of Birth:</strong>{" "}
        {user.dob ? new Date(user.dob).toLocaleDateString() : "N/A"}
      </p>

      {user.customerAddress ? (
        <div>
          <h3>Address</h3>
          <p>
            <strong>Address Line 1:</strong> {user.customerAddress.adrLine1}
          </p>
          <p>
            <strong>Address Line 2:</strong> {user.customerAddress.adrLine2}
          </p>
          <p>
            <strong>City:</strong> {user.customerAddress.city}
          </p>
          <p>
            <strong>State:</strong> {user.customerAddress.state}
          </p>
          <p>
            <strong>Country:</strong> {user.customerAddress.country}
          </p>
          <p>
            <strong>Zip Code:</strong> {user.customerAddress.zipCode}
          </p>
          <button onClick={() => navigate("/Home")}>Go to Home</button>
        </div>
      ) : (
        showAddressForm && (
          <div>
            <h3>Add Address</h3>
            <form onSubmit={handleAddressSubmit}>
              <div>
                <label>Address Line 1:</label>
                <input
                  type="text"
                  name="adrLine1"
                  value={address.adrLine1}
                  onChange={handleAddressChange}
                  required
                />
              </div>
              <div>
                <label>Address Line 2:</label>
                <input
                  type="text"
                  name="adrLine2"
                  value={address.adrLine2}
                  onChange={handleAddressChange}
                />
              </div>
              <div>
                <label>City:</label>
                <input
                  type="text"
                  name="city"
                  value={address.city}
                  onChange={handleAddressChange}
                  required
                />
              </div>
              <div>
                <label>State:</label>
                <input
                  type="text"
                  name="state"
                  value={address.state}
                  onChange={handleAddressChange}
                  required
                />
              </div>
              <div>
                <label>Country:</label>
                <input
                  type="text"
                  name="country"
                  value={address.country}
                  onChange={handleAddressChange}
                  required
                />
              </div>
              <div>
                <label>Zip Code:</label>
                <input
                  type="text"
                  name="zipCode"
                  value={address.zipCode}
                  onChange={handleAddressChange}
                  required
                />
              </div>
              <button type="submit">Submit Address</button>
            </form>
          </div>
        )
      )}
    </div>
  );
}

export default CustomerProfile;

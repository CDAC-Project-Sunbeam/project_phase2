// src/components/DashboardButtons.js
import React from 'react';
import { useNavigate } from 'react-router-dom';
import '../components/Button/button.css'
import '../css/Styles.css';
const DashboardButtons = () => {
const navigate = useNavigate();
const ManageCustomersClick = () => {
  navigate("/AdminDashboardPage/viewCustomers"); // Navigates to the ViewCustomers page
};
const ManageSellersClick = () => {
  navigate("/AdminDashboardPage/viewSellers"); // Navigates to the ViewCustomers page
};
    
    return (
      // <div class="centered-div">
      <div class="colored-div">
        <br />
        <br />
        <br />
        <br />
        <div className="row mt-5">
          <div
            className="col-6"
            style={{
              display: "flex",
              alignItems: "center",
              justifyContent: "center",
            }}
          >
            <button onClick={() => {}} className=" button">
              <span class="transition"></span>
              <span class="gradient"></span>
              <span class="label">INSERT PRODUCT</span>
            </button>
          </div>

          <div
            className="col-6"
            style={{
              display: "flex",
              alignItems: "center",
              justifyContent: "center",
            }}
          >
            <button onClick={() => {}} className=" button">
              <span class="transition"></span>
              <span class="gradient"></span>
              <span class="label">DELETE PRODUCT</span>
            </button>
          </div>
        </div>
        <br />
        <br />

        <div className="row mt-5">
          <div
            className="col-6"
            style={{
              display: "flex",
              alignItems: "center",
              justifyContent: "center",
            }}
          >
            <button onClick={() => {}} className=" button">
              <span class="transition"></span>
              <span class="gradient"></span>
              <span class="label">UPDATE PRODUCT</span>
            </button>
          </div>
          <div
            className="col-6"
            style={{
              display: "flex",
              alignItems: "center",
              justifyContent: "center",
            }}
          >
            <button onClick={() => {}} className=" button">
              <span class="transition"></span>
              <span class="gradient"></span>
              <span class="label">DISPLAY PRODUCT</span>
            </button>
          </div>
        </div>
        <br />
        <br />

        <div className="row mt-5">
          <div
            className="col-6"
            style={{
              display: "flex",
              alignItems: "center",
              justifyContent: "center",
            }}
          >
            <button onClick={ManageSellersClick} className=" button">
              <span class="transition"></span>
              <span class="gradient"></span>
              <span class="label">MANAGE SELLERS</span>
            </button>
          </div>
          <div
            className="col-6"
            style={{
              display: "flex",
              alignItems: "center",
              justifyContent: "center",
            }}
          >
            <button onClick={ManageCustomersClick} className=" button">
              <span class="transition"></span>
              <span class="gradient"></span>
              <span class="label">MANAGE CUSTOMERS</span>
            </button>
          </div>

          <div
            className="col-6"
            style={{
              display: "flex",
              alignItems: "center",
              justifyContent: "center",
            }}
          >
            <button onClick={() => {}} className=" button">
              <span class="transition"></span>
              <span class="gradient"></span>
              <span class="label">MANAGE ORDER</span>
            </button>
          </div>
        </div>
      </div>
    );
};

export default DashboardButtons;

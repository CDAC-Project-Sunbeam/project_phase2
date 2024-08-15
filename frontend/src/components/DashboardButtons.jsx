import React from "react";
import { useNavigate } from "react-router-dom";
import "../components/Button/button.css";
import "../css/Styles.css";

const DashboardButtons = () => {
  const navigate = useNavigate();

  const ManageCustomersClick = () => {
    navigate("/AdminDashboardPage/viewCustomers"); // Navigates to the ViewCustomers page
  };

  const ManageSellersClick = () => {
    navigate("/AdminDashboardPage/viewSellers"); // Navigates to the ViewSellers page
  };

  const ManageOrdersClick = () => {
    navigate("/AdminDashboardPage/manageOrders"); // Navigates to the ManageOrders page
  };

  const ManageProductsClick = () => {
    navigate("/AdminDashboardPage/manageProducts"); // Navigates to the ManageProducts page
  };
  const ViewProductsClick = () => {
    navigate("/AdminDashboardPage/viewAllProducts");
  };

  return (
    <div className="colored-div" style={{ marginTop: 50 }}>
      <br />
      <br />
      <div className="container-fluid">
        <div className="row d-flex justify-content-center">
          <div className="col-auto d-flex justify-content-center">
            <button onClick={ManageSellersClick} className="button btn-lg">
              <span className="gradient"></span>
              <span className="label">MANAGE SELLERS</span>
            </button>
          </div>
          <div className="col-auto d-flex justify-content-center">
            <button onClick={ManageCustomersClick} className="button btn-lg">
              <span className="gradient"></span>
              <span className="label">MANAGE CUSTOMERS</span>
            </button>
          </div>
          <div className="col-auto d-flex justify-content-center">
            <button onClick={ManageOrdersClick} className="button btn-lg">
              <span className="gradient"></span>
              <span className="label">VIEW ORDERS</span>
            </button>
          </div>
          <div className="col-auto d-flex justify-content-center">
            <button onClick={ViewProductsClick} className="button btn-lg w-100">
              <span className="gradient"></span>
              <span className="label">VIEW PRODUCTS</span>
            </button>
          </div>
        </div>
      </div>
    </div>
  );
};

export default DashboardButtons;

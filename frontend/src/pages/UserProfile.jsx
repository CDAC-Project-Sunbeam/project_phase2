import React from "react";
import { useNavigate } from "react-router-dom";
import "../css/Profile.css";

const UserProfilePage = () => {
  const navigate = useNavigate();

  const onEdit = () => {
    navigate("/userprofileform");
  };

  return (
    <div className="user-profile-page">
      <div className="profile-container">
        <div className="profile-card">
          <div className="profile-header">
            <img
              src="https://via.placeholder.com/150"
              alt="Profile"
              className="profile-img"
            />
          </div>
          <div className="profile-body">
            <div className="info">
              <div className="info-item">
                <h4>Name:</h4>
                <p>SIDDHANT</p>
              </div>
              <div className="info-item">
                <h4>Mobile:</h4>
                <p>9988556644</p>
              </div>
              <div className="info-item">
                <h4>Email:</h4>
                <p>test@example.com</p>
              </div>
              <div className="info-item">
                <h4>Date of Birth:</h4>
                <p>January 1, 2000</p>
              </div>
            </div>
            <div className="address-info">
              <h4>Address:</h4>
              <p>Hinjewadi, Pune</p>
            </div>
          </div>
          <div className="profile-footer">
            <button onClick={onEdit} className="edit-btn">
              Edit Profile
            </button>
          </div>
        </div>
      </div>
    </div>
  );
};

export default UserProfilePage;

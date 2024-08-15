import React from 'react';
import { Link ,useNavigate} from 'react-router-dom';
import { toast } from "react-toastify";
import '../css/Navbar.css'; // Ensure this CSS file is correctly linked

function Navbar() {
 const  navigate=useNavigate();
  const handleLogout = () => {
    sessionStorage.clear();
    toast.success("Logged out successfully");
    navigate("/login");
  };
  return (
    <div className="container-fluid p-0 fixed-top">
      <nav className="navbar navbar-expand-lg">
        <div className="container-fluid">
          {/* Brand section (you can customize or remove this if needed) */}
          <a className="navbar-brand" href="/">
            {/* Logo or Brand Name */}
            <span>ElectroBuzz</span>
          </a>

          {/* Sign Out button */}
          <div className="navbar-nav ms-auto">
            <Link
              to="/login"
              className="btn btn-outline-success"
              onClick={handleLogout}
            >
              <svg
                xmlns="http://www.w3.org/2000/svg"
                width="16"
                height="16"
                fill="currentColor"
                className="bi bi-box-arrow-right"
                viewBox="0 0 16 16"
              >
                <path
                  fillRule="evenodd"
                  d="M10.854 10.354a.5.5 0 0 1-.708-.708L12.293 8H5.5a.5.5 0 0 1 0-1h6.793l-2.147-2.146a.5.5 0 1 1 .708-.708l3 3a.5.5 0 0 1 0 .708l-3 3z"
                />
                <path
                  fillRule="evenodd"
                  d="M13.5 15A1.5 1.5 0 0 1 12 13.5v-11A1.5 1.5 0 0 1 13.5 1h-10A1.5 1.5 0 0 1 2 2.5v11A1.5 1.5 0 0 1 3.5 15h10zm0-1h-10A.5.5 0 0 1 3 13.5v-11a.5.5 0 0 1 .5-.5h10a.5.5 0 0 1 .5.5v11a.5.5 0 0 1-.5.5z"
                />
              </svg>
              <span className="ms-2">Sign Out</span>
            </Link>
          </div>
        </div>
      </nav>
    </div>
  );
}

export default Navbar;

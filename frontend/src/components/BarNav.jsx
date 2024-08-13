// import "../css/BarNav.css";
// import { Link, useNavigate } from 'react-router-dom'
// function BarNav() {
//   return (
//     <div className="container-fluid p-0 fixed-top" >
//       <nav className="navbar navbar-expand-lg">
//         <div className="container-fluid">
//         <Link className="nav-link " aria-current="page" to="/Home">ELECTRON</Link>
//           <button className="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
//             <span className="navbar-toggler-icon"></span>
//           </button>
//           <div className="collapse navbar-collapse" id="navbarSupportedContent">
//             <div className="navbar-nav mx-auto">
//               <form className="d-flex" role="search">
//                 <input className="form-control me-2" type="search" placeholder="Search" aria-label="Search" />
//                 <button className="btn btn-outline-success" type="submit">Search</button>
//               </form>
//             </div>
//             <ul className="navbar-nav ms-auto mb-2 mb-lg-0">
//               <li className="nav-item">
//               <Link className="nav-link " aria-current="page" to="/login">Login</Link>
//               </li>
//               <li className="nav-item">
//               <Link className="nav-link " aria-current="page" to="/About">About</Link>
//               </li>
//               <li className="nav-item">
//               <Link className="nav-link " aria-current="page" to="/Contact">Contact</Link>
//               </li>
//               <li className="nav-item">
//                 <a className="nav-link" href="#">Wishlist</a>
//               </li>
//               <li className="nav-item">
//                 <a className="nav-link" href="#">Cart</a>
//               </li>
//             </ul>
//           </div>
//         </div>
//       </nav>
//     </div>
//   );
// }

// export default BarNav;

import React, { useState } from "react";
import { Link, useNavigate } from "react-router-dom";
import axios from "axios";

function BarNav() {
  const [searchQuery, setSearchQuery] = useState("");
  const navigate = useNavigate();

  const handleSearch = async (event) => {
    event.preventDefault();
    try {
      const response = await axios.get(`http://localhost:8080/product/search`, {
        params: {
          query: searchQuery,
        },
      });
      navigate("/search-results", { state: { products: response.data } });
    } catch (error) {
      console.error("Error during search:", error);
    }
  };

  return (
    <div className="container-fluid p-0 fixed-top">
      <nav className="navbar navbar-expand-lg">
        <div className="container-fluid">
          <Link className="nav-link " aria-current="page" to="/Home">
            ELECTRON
          </Link>
          <button
            className="navbar-toggler"
            type="button"
            data-bs-toggle="collapse"
            data-bs-target="#navbarSupportedContent"
            aria-controls="navbarSupportedContent"
            aria-expanded="false"
            aria-label="Toggle navigation"
          >
            <span className="navbar-toggler-icon"></span>
          </button>
          <div className="collapse navbar-collapse" id="navbarSupportedContent">
            <div className="navbar-nav mx-auto">
              <form className="d-flex" role="search" onSubmit={handleSearch}>
                <input
                  className="form-control me-2"
                  type="search"
                  placeholder="Search"
                  aria-label="Search"
                  value={searchQuery}
                  onChange={(e) => setSearchQuery(e.target.value)}
                />
                <button className="btn btn-outline-success" type="submit">
                  Search
                </button>
              </form>
            </div>
            <ul className="navbar-nav ms-auto mb-2 mb-lg-0">
              <li className="nav-item">
                <Link className="nav-link " aria-current="page" to="/login">
                  Login
                </Link>
              </li>
              <li className="nav-item">
                <Link className="nav-link " aria-current="page" to="/About">
                  About
                </Link>
              </li>
              <li className="nav-item">
                <Link className="nav-link " aria-current="page" to="/Contact">
                  Contact
                </Link>
              </li>
              <li className="nav-item">
              <Link className="nav-link " aria-current="page" to="/Wishlist">
                  Wishlist
                </Link>
              </li>
              <li className="nav-item">
                <Link className="nav-link " aria-current="page" to="/Cart">
                  Cart
                </Link>
              </li>
              
            </ul>
          </div>
        </div>
      </nav>
    </div>
  );
}

export default BarNav;

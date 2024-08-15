import { useState } from "react";
import { useNavigate } from "react-router-dom";
import { addProductToWishlist, addProductToCart } from "../services/products"; // Ensure to import addProductToWishlist and addProductToCart
import "../css/Product.css"; // Ensure this CSS file is imported
import { toast } from "react-toastify";
function Product({ product }) {
  const [isInWishlist, setIsInWishlist] = useState(false); // Track wishlist state
  const navigate = useNavigate();

  const getShortDetails = () => {
    return product.description.length > 50
      ? product.description.substr(0, 50) + "..."
      : product.description;
  };

  const onProductClick = () => {
    navigate(`/product-details/${product.id}`);
  };

  const handleAddToWishlist = async () => {
    const customerId = sessionStorage.getItem("customerid");
    if (!customerId) {
      console.error("Customer ID is not available");
      return;
    }
    try {
      await addProductToWishlist(customerId, product.id);
      setIsInWishlist(true); // Update local state to reflect that the product is in the wishlist
      toast.success("Product Added To Wishlist");
    } catch (error) {
      console.error("Failed to add product to wishlist:", error);
    }
  };

  // const handleAddToCart = async () => {
  //   const customerId = sessionStorage.getItem('customerid');
  //   if (!customerId) {
  //     console.error('Customer ID is not available');
  //     return;
  //   }
  //   const quantity = 1;
  //   try {
  //     await addProductToCart(customerId, product.id, quantity);
  //     alert('Product added to cart!');
  //   } catch (error) {
  //     console.error('Failed to add product to cart:', error);
  //   }
  // };

  return (
    <div onClick={onProductClick} className="product-card">
      <div className="card">
        <img
          className="card-img-top"
          src={`http://localhost:8080/images/${product.mainImgUrl}`}
          alt={product.title}
        />
        <div className="card-body">
          <h5 className="card-title">
            {product.brandName} {product.name}
          </h5>
          <p className="card-text">{getShortDetails()}</p>
          <div className="product-price">₹ {product.price}</div>
          <p className="card-text">{product.discount}% off</p>
          <div className="product-actions">
            <button
              className={`btn wishlist-btn ${
                isInWishlist ? "in-wishlist" : ""
              }`}
              onClick={(e) => {
                e.stopPropagation(); // Prevent triggering onProductClick
                handleAddToWishlist();
              }}
            >
              <i className={`fa${isInWishlist ? "s" : "r"} fa-heart`}></i>
            </button>
            {/* <button
              className="btn cart-btn"
              onClick={(e) => {
                e.stopPropagation(); // Prevent triggering onProductClick
                handleAddToCart();
              }}
            >
              <i className="fas fa-cart-plus"></i>
            </button> */}
          </div>
        </div>
      </div>
    </div>
  );
}

export default Product;

import React, { useEffect, useState } from 'react';
import { getWishlistProducts } from '../services/products'; // Ensure correct path
import { useNavigate } from 'react-router-dom'; // For navigation

function Wishlist() {
  const [wishlistItems, setWishlistItems] = useState([]);
  const customerId = sessionStorage.getItem('customerid');
  const navigate = useNavigate(); // Initialize navigate

  // Function to load wishlist products
  const loadWishlistProducts = async (customerId) => {
    try {
      const products = await getWishlistProducts(customerId);
      setWishlistItems(products);
      
    } catch (error) {
      console.error('Failed to fetch wishlist products:', error);
    }
  };

  useEffect(() => {
    if (customerId) {
      loadWishlistProducts(customerId);
    } else {
      console.error('No customer ID found in session storage');
    }
  }, [customerId]);

  return (
    <div>
      <h2>Your Wishlist</h2>
      {wishlistItems.length > 0 ? (
        <div>
          {wishlistItems.map((item) => (
            <div key={item.productId} className="wishlist-item">
              <h3>{item.brandName}</h3>
              <h3>{item.name}</h3>
              <img
                style={{ height: 100, width: 100 }}
                src={`http://localhost:8080/${item.mainImgUrl}`}
                alt={item.name}
                className="product-image"
              />
              <p>Price: {item.price}</p>
              <p>Discount: {item.discount}%</p>
              <hr />
            </div>
          ))}
        </div>
      ) : (
        <p>Your wishlist is empty.</p>
      )}
    </div>
  );
}

export default Wishlist;

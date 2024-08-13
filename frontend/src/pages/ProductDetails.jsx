import { Link, useParams,useNavigate } from 'react-router-dom'
import { getProductDetails,addProductToCart } from '../services/products'
//import { toast } from 'react-toastify'
import { useEffect, useState } from 'react'
import BarNav from "../components/BarNav";
//import config from '../config'

function ProductDetails() {
  const { productId } = useParams();
  const [details, setDetails] = useState(null);
  const navigate = useNavigate();

  // Load product details on component mount
  useEffect(() => {
    const loadProductDetails = async () => {
      try {
        const result = await getProductDetails(productId);
        setDetails(result);
      } catch (error) {
        console.error('Failed to load product details:', error);
      }
    };

    loadProductDetails();
  }, [productId]);

  // Handle adding product to cart
  const handleAddToCart = async () => {
    const customerId = sessionStorage.getItem('customerId');
    if (!customerId) {
      console.error('Customer ID is not available');
      return;
    }
    const quantity = 1;

    try {
      await addProductToCart(customerId, productId, quantity);
      navigate('/cart'); // Navigate to cart page
    } catch (error) {
      console.error('Failed to add product to cart:', error);
    }
  };

  return (
    <div>
      <div>
        <BarNav />
      </div>
      <br></br>
      {details && (
        <div className="mt-5">
          <h3 style={{ fontSize: 26 }}>
            {details.brandName} <br /> {details.name}
          </h3>
          <hr />
          <img
            className="mt-2"
            style={{ height: 300 }}
            src={`http://localhost:8080/${details.mainImgUrl}`}
            alt={details.name}
          />
          <div className="mt-2">
            <h4 className="mt-2"> {details.description}</h4>
          </div>
          <hr />
          <div className="mt-3">
            <div>
              Category: <span style={{ fontWeight: 'bold' }}>{details.categoryName}</span>
            </div>
            <div>Rating: {details.discount}% Off</div>
            <hr />
            <div>Stock available: {details.stockQuantity}</div>
            <hr />
          </div>
          <div className="mt-3">
            <h4>Price</h4>
            <div>{details.price}</div>
            <hr />
          </div>

          <div className="mt-5 d-flex">
            <Link to="/home" className="btn btn-danger mr-2" style={{ flex: 1 }}>Back</Link>
            <button onClick={handleAddToCart} className="btn btn-success" style={{ flex: 1 }}>Add to Cart</button>
          </div>
        </div>
      )}
    </div>
  );
}

export default ProductDetails;

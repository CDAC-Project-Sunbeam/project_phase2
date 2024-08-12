import { useNavigate } from 'react-router-dom';
import '../css/Product.css'; // Ensure this CSS file is imported

function Product({ product }) {
  const getShortDetails = () => {
    return product.description.length > 50
      ? product.description.substr(0, 50) + '...'
      : product.description;
  };

  const navigate = useNavigate();
  const onProductClick = () => {
    navigate(`/product-details/${product.id}`);
  };

  return (
    <div onClick={onProductClick} className="product-card">
      <div className="card">
        <img
          className="card-img-top"
          src={`http://localhost:8080/${product.mainImgUrl}`}
          alt={product.title}
        />
        <div className="card-body">
          <h5 className="card-title">
            {product.brandName}
            {product.name}
          </h5>
          <p className="card-text">{getShortDetails()}</p>
          <div className="product-price">₹ {product.price}</div>
          <p className="card-text">{product.discount}% off</p>
          <button className="btn btn-success buy-btn">Buy</button>
        </div>
      </div>
    </div>
  );
}

export default Product;

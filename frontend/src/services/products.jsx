import axios from "axios";

export async function getProducts() {
  const response = await axios.get("http://localhost:8080/product/all");
  return response.data;
}

export async function getProductDetails(id) {
  const response = await axios.get(
    `http://localhost:8080/product/getProductById/${id}`
  );
  return response.data;
}

// Function to handle file upload
export const uploadFile = async (file) => {
  const formData = new FormData();
  formData.append("file", file);

  const response = await axios.post(
    "http://localhost:8080/product/upload",
    formData,
    {
      headers: {
        "Content-Type": "multipart/form-data",
      },
    }
  );

  return response.data.fileName; // Adjust according to your backend response
};

// Function to create a product
export const createProduct = async (productData, sellerId, categoryId) => {
  try {
    const response = await axios.post(
      `http://localhost:8080/product/${sellerId}/${categoryId}`,
      productData,
      {
        headers: {
          "Content-Type": "multipart/form-data",
        },
      }
    );
    return response.data;
  } catch (error) {
    throw new Error("Error creating product: " + error.message);
  }
};

// Function to add product to cart
export async function addProductToCart(customerId, productId, quantity) {
  const response = await axios.post('http://localhost:8080/cart', {
    customerId,
    productId,
    quantity,
  });
  return response.data;
}

// Function to get cart products
export async function getCartProducts(customerId) {
  const response = await axios.get(`http://localhost:8080/cart/${customerId}`);
  return response.data;
}

// Function to add product to wishlist
export const addProductToWishlist = async (customerId, productId) => {
  const token = sessionStorage.getItem("token");

  try {
    const response = await axios.post(
      `http://localhost:8080/wishlist/${customerId}/${productId}`,
      {},
      {
        headers: {
          Authorization: `Bearer ${token}`,
        },
      }
    );
    console.log("Added to wishlist", response.data);
  } catch (error) {
    console.error("Add to wishlist failed", error);
  }
};
// Function to get wishlist products
export const getWishlistProducts = async (customerId) => {
  const token = sessionStorage.getItem("token"); // Retrieve the token from session storage

  try {
    const response = await axios.get(
      `http://localhost:8080/wishlist/${customerId}`,
      {
        headers: {
          Authorization: `Bearer ${token}`, // Include the token in the Authorization header
        },
      }
    );
    return response.data;
  } catch (error) {
    console.error("Error fetching wishlist products:", error);
    throw error;
  }
};





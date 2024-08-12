import axios from "axios";

export async function getProducts() {
  const response = await axios.get("http://localhost:8080/product/all");
  return response.data;
}

export async function getProductDetails(id) {
  const response = await axios.get(`https://fakestoreapi.com/products/${id}`);
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

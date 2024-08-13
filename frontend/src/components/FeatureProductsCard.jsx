import * as React from "react";
import Card from "@mui/material/Card";
import CardContent from "@mui/material/CardContent";
import CardMedia from "@mui/material/CardMedia";
import Typography from "@mui/material/Typography";
import { CardActionArea } from "@mui/material";
import axios from "axios";
import { useNavigate } from "react-router-dom";

export default function FeatureProductsCard() {
  const navigate = useNavigate();

  const categories = [
    {
      title: "Smartphones",
      description:
        "A high-end smartphone with a powerful processor and high-resolution display.",
      image: "http://localhost:8080/apple-12.jpg/",
      id: 2,
    },
    {
      title: "Laptops",
      description:
        "A lightweight laptop with a long-lasting battery and fast performance.",
      image: "http://localhost:8080/1605478088_6c-acer.jpg",
      id: 1,
    },
    {
      title: "Smartwatches",
      description:
        "Noise-canceling headphones with high-fidelity sound quality.",
      image: "http://localhost:8080/rhh_1695470152346_1695470161360.webp",
      id: 5,
    },
    {
      title: "TV",
      description:
        "Noise-canceling headphones with high-fidelity sound quality.",
      image:
        "http://localhost:8080/How-to-Use-Multi-View-on-LG-TV-for-Split-Screen-768x488.webp",
      id: 4,
    },
    {
      title: "Speakers",
      description:
        "Noise-canceling headphones with high-fidelity sound quality.",
      image: "http://localhost:8080/71Pc9HBR7JL._SL1500_.jpg",
      id: 3,
    },
    // Add more categories as needed
  ];

  const handleClick = async (categoryId) => {
    try {
      const response = await axios.get(
        `http://localhost:8080/product/getProductsByCategory`,
        {
          params: { categoryId },
        }
      );
      navigate("/search-results", { state: { products: response.data } });
    } catch (error) {
      console.error("Error fetching products:", error);
    }
  };

  return (
    <div
      style={{
        display: "flex",
        flexWrap: "nowrap",
        overflowX: "auto",
        gap: "16px",
        padding: "16px",
      }}
    >
      {categories.map((category, index) => (
        <Card
          key={index}
          sx={{ maxWidth: 345 }}
          onClick={() => handleClick(category.id)}
        >
          <CardActionArea>
            <CardMedia
              component="img"
              height="140"
              image={category.image}
              alt={category.title}
            />
            <CardContent>
              <Typography gutterBottom variant="h5" component="div">
                {category.title}
              </Typography>
              <Typography variant="body2" color="text.secondary">
                {category.description}
              </Typography>
            </CardContent>
          </CardActionArea>
        </Card>
      ))}
    </div>
  );
}

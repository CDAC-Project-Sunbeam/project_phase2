import * as React from 'react';
import Card from '@mui/material/Card';
import CardContent from '@mui/material/CardContent';
import CardMedia from '@mui/material/CardMedia';
import Typography from '@mui/material/Typography';
import { CardActionArea } from '@mui/material';

export default function FeatureProductsCard() {
    const products = [
        {
          title: 'Smartphone',
          description: 'A high-end smartphone with a powerful processor and high-resolution display.',
          image: '/static/images/electronics/smartphone.jpg',
          alt: 'smartphone'
        },
        {
          title: 'Laptop',
          description: 'A lightweight laptop with a long-lasting battery and fast performance.',
          image: '/static/images/electronics/laptop.jpg',
          alt: 'laptop'
        },
        {
          title: 'Headphones',
          description: 'Noise-canceling headphones with high-fidelity sound quality.',
          image: '/static/images/electronics/headphones.jpg',
          alt: 'headphones'
        },
        // Add more products as needed
    ];

  return (
    <div style={{ display: 'flex', flexWrap: 'nowrap', overflowX: 'auto', gap: '16px', padding: '16px' }}>
      {products.map((product, index) => (
        <Card key={index} sx={{ maxWidth: 345 }}>
          <CardActionArea>
            <CardMedia
              component="img"
              height="140"
              image={product.image}
              alt={product.alt}
            />
            <CardContent>
              <Typography gutterBottom variant="h5" component="div">
                {product.title}
              </Typography>
              <Typography variant="body2" color="text.secondary">
                {product.description}
              </Typography>
            </CardContent>
          </CardActionArea>
        </Card>
      ))}
    </div>
  );
}



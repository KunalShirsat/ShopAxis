// ProductGrid.js
import React, { useEffect, useState } from 'react';
import { Link } from 'react-router-dom';
import './ProductGrid.css';

const ProductGrid = () => {
  const [products, setProducts] = useState([]);

  useEffect(() => {
    fetch('https://13.126.117.137:8686/api/products/v1/getAll')
      .then(response => response.json())
      .then(data => setProducts(data))
      .catch(error => console.error('Error fetching products:', error));
  }, []);

  return (
    <div className="product-grid">
      <h2>Great Indian Festival</h2>
      <p>Diwali Special</p>
      <div className="product-list">
        {products.map(product => (
          <Link to={`/product/${product.id}`} key={product.id} className="product-card">
            <img src={product.image} alt={product.name} className="product-image" />
            <div className="product-info">
              <h3 className="product-name">{product.name}</h3>
              <p className="product-description">{product.description}</p>
              <p className="product-price">${product.price}</p>
              <p className="product-category">{product.category}</p>
            </div>
          </Link>
        ))}
      </div>
    </div>
  );
};

export default ProductGrid;

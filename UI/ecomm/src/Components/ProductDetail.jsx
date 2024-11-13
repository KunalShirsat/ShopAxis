import React, { useEffect, useState } from 'react';
import { useParams } from 'react-router-dom';
import './ProductDetail.css';
import { useCart } from './CartContext'; // Import useCart to access cart functionality

const ProductDetail = () => {
  const { id } = useParams();
  const { addToCart } = useCart(); // Destructure addToCart from useCart
  const [product, setProduct] = useState(null);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  useEffect(() => {
    const fetchProduct = async () => {
      try {
        const response = await fetch(`http://localhost:8080/api/products/v1/${id}`);
        if (!response.ok) {
          throw new Error(`Error fetching product: ${response.statusText}`);
        }
        const data = await response.json();
        setProduct(data[0]); 
      } catch (error) {
        setError(error.message);
      } finally {
        setLoading(false);
      }
    };

    fetchProduct();
  }, [id]);

  const handleAddToCart = async () => {
    try {
      const response = await fetch(`http://localhost:8082/api/cart/addtocart?id=${id}`, {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
      });
      
      if (!response.ok) {
        throw new Error(`Failed to add product to cart: ${response.statusText}`);
      }
      
      // Add the product to the cart context to update the count
      addToCart(product);

      alert(`Product ${product.id} added to cart!`);
    } catch (error) {
      alert(`Error: ${error.message}`);
    }
  };

  if (loading) return <p>Loading...</p>;
  if (error) return <p>Error: {error}</p>;
  if (!product) return null;

  return (
    <div className="product-detail-container">
      <div className="product-image">
        <img src={product.image} alt={product.name} />
      </div>
      <div className="product-info">
        <h1>{product.name}</h1>
        <p>{product.description}</p>
        <p className="price">₹{product.price}</p>
        <p>Category: {product.category}</p>
        <div className="product-options">
          <p>Quantity:</p>
          <select>
            {[1, 2, 3, 4, 5].map((qty) => (
              <option key={qty} value={qty}>{qty}</option>
            ))}
          </select>
        </div>
        <div className="button-group">
          <button className="add-to-cart" onClick={handleAddToCart}>Add to Cart</button>
          <button className="buy-now">Buy Now</button>
        </div>
      </div>
    </div>
  );
};

export default ProductDetail;

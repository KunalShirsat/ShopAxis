import React, { useEffect, useState } from 'react';
import './SearchResult.css';

const SearchResult = ({ searchQuery }) => {
  const [products, setProducts] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  useEffect(() => {
    const fetchProducts = async () => {
      if (!searchQuery) return; // Don't fetch if searchQuery is empty
      setLoading(true);
      setError(null);
      try {
        const response = await fetch(`http://localhost:8080/api/products/v1/search?keyword=${encodeURIComponent(searchQuery)}`);
        if (!response.ok) {
          throw new Error(`Error fetching products: ${response.statusText}`);
        }
        const data = await response.json();
        setProducts(data);
      } catch (error) {
        setError(error.message);
      } finally {
        setLoading(false);
      }
    };

    fetchProducts();
  }, [searchQuery]);

  if (loading) return <p>Loading...</p>;
  if (error) return <p>Error: {error}</p>;
  if (products.length === 0) return <p>No products found.</p>;

  return (
    <div className="search-results">
      <h2>Search Results:</h2>
      <div className="results-container">
        {products.map(product => (
          <div key={product.id} className="product-item">
            <img src={product.image} alt={product.name} className="product-image" />
            <div className="product-info">
              <h3>{product.name}</h3>
              <p>{product.description}</p>
              <p>Price: ₹{product.price}</p>
              <p>Category: {product.category}</p>
            </div>
          </div>
        ))}
      </div>
    </div>
  );
};

export default SearchResult;

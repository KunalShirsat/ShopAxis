import React, { useEffect, useState } from 'react';
import './Cart.css';

const Cart = () => {
  const [cartItems, setCartItems] = useState([]);
  const [loading, setLoading] = useState(true);

  // Fetch cart items when component mounts
  useEffect(() => {
    const fetchCartItems = async () => {
      try {
        const response = await fetch('http://localhost:8082/api/cart/getcart');
        const data = await response.json();
        
        setCartItems(data);  // Update the state with the fetched data
      } catch (error) {
        console.error("Error fetching cart items:", error);
      } finally {
        setLoading(false);
      }
    };

    fetchCartItems();
  }, []);

  // Function to handle delete operation
  const handleDelete = async (id) => {
    try {
      const response = await fetch(`http://localhost:8082/api/cart/remove?id=${id}`, {
        method: 'DELETE'
      });

      if (response.ok) {
        // Remove the item from state after successful deletion
        setCartItems((prevItems) => prevItems.filter(item => item.id !== id));
      } else {
        console.error("Failed to delete item");
        alert("Failed to delete item");
      }
    } catch (error) {
      console.error("Error deleting item:", error);
      alert("An error occurred while deleting the item.");
    }
  };

  if (loading) return <p>Loading...</p>;

  return (
    <div className="cart-container">
      <h1>Shopping Cart</h1>
      {cartItems.length > 0 ? (
        cartItems.map((item) => (
          <div className="cart-item" key={item.id}>
            <img src={item.image} alt={item.name} className="cart-item-image" />
            <div className="cart-item-details">
              <h2>{item.name}</h2>
              <p>{item.description}</p>
              <p className="cart-item-price">₹{item.price}</p>
              <p className="cart-item-category">Category: {item.category}</p>
              <p className="cart-item-tags">Tags: {item.tags}</p>
              <div className="quantity-control">
                <button>-</button>
                <span>{item.quantity}</span>
                <button>+</button>
              </div>
              {/* Add onClick handler for Delete button */}
              <button className="delete-button" onClick={() => handleDelete(item.id)}>
                Delete
              </button>
            </div>
          </div>
        ))
      ) : (
        <p>Your cart is empty!</p>
      )}
    </div>
  );
};

export default Cart;

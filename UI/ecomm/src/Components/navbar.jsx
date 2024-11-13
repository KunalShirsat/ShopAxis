import React, { useState } from 'react';
import './Navbar.css';
import { useCart } from './CartContext';
import { useNavigate } from 'react-router-dom';

const Navbar = () => {
  const { cartCount } = useCart();
  const [searchQuery, setSearchQuery] = useState('');
  const navigate = useNavigate();

  const handleSearch = async () => {
    try {
      const response = await fetch(`http://localhost:8080/api/products/v1/search?keyword=${encodeURIComponent(searchQuery)}`);
      const data = await response.json();
      
      if (response.ok) {
        console.log("Search Results:", data);
        // Render or redirect to search results as needed
      } else {
        console.error("Search failed:", data);
        alert("Search failed: " + data);
      }
    } catch (error) {
      console.error("Error:", error);
      alert("An error occurred while searching.");
    }
  };
  
  const handleAccountClick = () => {
    navigate('/your-addresses');
  };
  
  const handleLogolick = () => {
    navigate('/');
  };

  const handleKeyPress = (e) => {
    if (e.key === 'Enter') {
      handleSearch();
    }
  };

  const handleCartClick = () => {
    navigate('/cart');
  };

  return (
    <div className="navbar">
      <div className="navbar__belt">
        <div className="navbar__main">
          {/* Logo */}
          <div className="navbar__logo" onClick={handleLogolick}>
            <img src="https://upload.wikimedia.org/wikipedia/commons/a/a9/Amazon_logo.svg" alt="Amazon" className="navbar__logoImage" />
          </div>

          {/* Delivery Location */}
          <div className="navbar__deliver">
            <div className="navbar__deliverIcon">
              <i className="location-icon"></i>
            </div>
            <div className="navbar__deliverContent">
              <span className="navbar__deliverLine1">Deliver to KUNAL</span>
              <span className="navbar__deliverLine2">Nashik 422009</span>
            </div>
          </div>

          {/* Search */}
          <div className="navbar__search">
            <select className="navbar__searchDropdown">
              <option>All</option>
            </select>
            <input 
              type="text" 
              className="navbar__searchInput" 
              placeholder="Search Amazon.in"
              value={searchQuery}
              onChange={(e) => setSearchQuery(e.target.value)} 
              onKeyPress={handleKeyPress}
            />
            <button className="navbar__searchButton" onClick={handleSearch}>
              <i className="search-icon"></i>
            </button>
          </div>

          {/* Right Side */}
          <div className="navbar__rightContainer">
            {/* Language */}
            <div className="navbar__language">
              <img src="/in-flag.png" alt="IN" className="navbar__flag" />
              <span>EN</span>
            </div>

            {/* Account */}
            <div className="navbar__option" onClick={handleAccountClick}>
              <span className="navbar__optionLine1">Hello, Kunal</span>
              <span className="navbar__optionLine2">Account & Lists</span>
            </div>

            {/* Returns & Orders */}
            <div className="navbar__option">
              <span className="navbar__optionLine1">Returns</span>
              <span className="navbar__optionLine2">& Orders</span>
            </div>

            {/* Cart */}
            <div className="navbar__cart" onClick={handleCartClick}>
              <span className="navbar__cartCount">{cartCount}</span>
              <span>Cart</span>
            </div>
          </div>
        </div>
      </div>

      {/* Bottom nav */}
      <div className="navbar__bottom">
        <div className="navbar__bottomLinks">
          <a href="#">All</a>
          <a href="#">Fresh Meat</a>
          <a href="#">Buy Again</a>
          <a href="#">Electronics</a>
          <a href="#">Today's Deals</a>
          <a href="#">MX Player</a>
          <a href="#">Sell</a>
          <a href="#">Gift Cards</a>
          <a href="#">Gift Ideas</a>
          <a href="#">Amazon Pay</a>
          <a href="#">AmazonBasics</a>
          <a href="#">Customer Service</a>
          <a href="#">Browsing History</a>
          <a href="#">Contact Developer</a>
        </div>
      </div>
    </div>
  );
};

export default Navbar;

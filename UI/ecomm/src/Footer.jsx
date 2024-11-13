// Footer.js
import React from 'react';
import './Footer.css';

const Footer = () => {
  return (
    <footer className="footer">
      <div className="footer-section">
        <h4>Get to Know Us</h4>
        <ul>
          <li>About Amazon</li>
          <li>Careers</li>
          <li>Press Releases</li>
          <li>Amazon Science</li>
        </ul>
      </div>
      <div className="footer-section">
        <h4>Connect with Us</h4>
        <ul>
          <li>Facebook</li>
          <li>Twitter</li>
          <li>Instagram</li>
        </ul>
      </div>
      <div className="footer-section">
        <h4>Make Money with Us</h4>
        <ul>
          <li>Sell on Amazon</li>
          <li>Sell under Amazon Accelerator</li>
          <li>Protect and Build Your Brand</li>
          <li>Amazon Global Selling</li>
          <li>Supply to Amazon</li>
          <li>Become an Affiliate</li>
          <li>Fulfillment by Amazon</li>
          <li>Advertise Your Products</li>
          <li>Amazon Pay on Merchants</li>
        </ul>
      </div>
      <div className="footer-section">
        <h4>Let Us Help You</h4>
        <ul>
          <li>Your Account</li>
          <li>Returns Centre</li>
          <li>Recalls and Product Safety Alerts</li>
          <li>100% Purchase Protection</li>
          <li>Amazon App Download</li>
          <li>Help</li>
        </ul>
      </div>
      <div className="footer-bottom">
        <img src="https://upload.wikimedia.org/wikipedia/commons/a/a9/Amazon_logo.svg" alt="Amazon Logo" className="footer-logo" />
        <div className="footer-country">
          <button className="footer-lang-btn">English</button>
          <button className="footer-country-btn">India</button>
        </div>
      </div>
    </footer>
  );
};

export default Footer;

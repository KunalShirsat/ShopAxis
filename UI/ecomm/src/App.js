import React from 'react';
import './App.css';
import { BrowserRouter as Router, Routes, Route, useLocation } from 'react-router-dom';
import Login from './Components/login';
import Navbar from './Components/navbar';
import AmazonSignupForm from './Components/signup';
import ProductGrid from './Components/ProductGrid';
import Footer from './Footer';
import ProductDetail from './Components/ProductDetail';
import CarouselComponent from './CarouselComponent';
import { CartProvider } from './Components/CartContext';
import Cart from './Components/Cart';
import YourAddresses from './Components/YourAddresses';


// Main App Component
function App() {
  return (
    <CartProvider>
      <Router>
        {/* Conditional Navbar rendering based on the current route */}
        <ConditionalNavbar />
        <Routes>
          <Route path="/login" element={<Login />} />
          <Route path="/signup" element={<AmazonSignupForm />} />
          <Route path="/" element={<Home />} />
          <Route path="/product/:id" element={<ProductDetail />} />
          <Route path="/cart" element={<Cart/>} />
          <Route path="/your-addresses" element={<YourAddresses />} />
          
        </Routes>
        {/* Conditional Footer rendering */}
        <ConditionalFooter />
      </Router>
    </CartProvider>
  );
}

// Component to conditionally render Navbar
const ConditionalNavbar = () => {
  const location = useLocation();
  // Only show Navbar if not on the login or signup page
  return location.pathname === '/login' || location.pathname === '/signup' ? null : <Navbar />;
};

// Component to conditionally render Footer
const ConditionalFooter = () => {
  const location = useLocation();
  // Only show Footer if not on the login or signup page
  return location.pathname === '/login' || location.pathname === '/signup' ? null : <Footer />;
};

// Home component with Carousel and Product Grid
const Home = () => {
  return (
    <>
      <CarouselComponent />
      <ProductGrid />
    </>
  );
};

export default App;

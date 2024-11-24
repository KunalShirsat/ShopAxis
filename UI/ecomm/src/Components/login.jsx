import React, { useState } from 'react';
import './login.css'; // For styling, you'll add CSS later
import { Link, useNavigate } from 'react-router-dom';

function Login() {
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState(''); // Add password state
  const navigate = useNavigate(); // Initialize useNavigate

  const handleSubmit = async (e) => {
    e.preventDefault();
    console.log("Submitted Email/Mobile:", email);
    
    try {
      const response = await fetch("http://localhost:8084/api/auth/login", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({ email, password }), // Ensure password is included
      });
      const data = await response.json();
      if (response.ok) {
        localStorage.setItem("token", data.token); // Save token in localStorage
        // alert("Login successful");
        navigate('/'); // Redirect to the home page
      } else {
        alert("Login failed: " + data);
      }
    } catch (error) {
      console.error("Error:", error);
    }
  };

  return (
    <div className="login-container">
      <div className="login-box">
        <img 
          className="login-logo" 
          src="https://upload.wikimedia.org/wikipedia/commons/a/a9/Amazon_logo.svg" 
          alt="Amazon Logo" 
        />
        <form onSubmit={handleSubmit}>
          <h2>Sign in</h2>
          <label>Email or mobile phone number</label>
          <input 
            type="text" 
            value={email} 
            onChange={(e) => setEmail(e.target.value)} 
            required 
          />
          <label>Password</label>
          <input 
            type="password" 
            value={password} 
            onChange={(e) => setPassword(e.target.value)} 
            required 
          />
          <button type="submit" className="continue-button">Continue</button>
        </form>

        <p>
          By continuing, you agree to Amazon's <a href="#">Conditions of Use</a> and <a href="#">Privacy Notice</a>.
        </p>
        
        <a href="http://localhost:3000/signup" className="help-link">Need help?</a>

        <hr />

        <a href="#" className="business-link">Buying for work? <span>Shop on Amazon Business</span></a>

        <hr />

        <div className="container">
          <p>New to Amazon?</p>
          <Link to="/signup">
            <button className="create-account-button">Create your Amazon account</button>
          </Link>
        </div>
      </div>
    </div>
  );
}

export default Login;

import React, { useState } from 'react';
import axios from 'axios';
import { AlertCircle } from 'lucide-react';
import './AmazonSignup.css';

const AmazonSignupForm = () => {
  const [formData, setFormData] = useState({
    firstName: '',
    lastName: '',
    email: '',
    password: ''
  });

  const [errors, setErrors] = useState({
    firstName: '',
    lastName: '',
    email: '',
    password: ''
  });

  const [responseMessage, setResponseMessage] = useState(''); // For backend response

  const validateForm = () => {
    const newErrors = {
      firstName: '',
      lastName: '',
      email: '',
      password: ''
    };

    if (formData.firstName.length < 1) {
      newErrors.firstName = 'Enter at least 1 character';
    }

    if (formData.lastName.length < 1) {
      newErrors.lastName = 'Enter at least 1 character';
    }

    if (!formData.email.match(/^[^\s@]+@[^\s@]+\.[^\s@]+$/)) {
      newErrors.email = 'Enter a valid email address';
    }

    if (formData.password.length < 6) {
      newErrors.password = 'Password must be at least 6 characters';
    }

    setErrors(newErrors);
    return !Object.values(newErrors).some(error => error !== '');
  };

  const handleSubmit = async (e) => {
    e.preventDefault();

    if (validateForm()) {
      try {
        const response = await axios.post('http://localhost:8081/api/v1/register', formData, {
          headers: {
            'Content-Type': 'application/json'
          }
        });

        // Set the response message to a specific property
        if (response.data.message) { // Adjust this if your backend uses a different structure
          setResponseMessage(response.data.message);
        } else {
          setResponseMessage("Account created successfully!");
        }
      } catch (error) {
        console.error("There was an error!", error);
        setResponseMessage('Error occurred while sending data');
      }
    }
  };

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData(prev => ({
      ...prev,
      [name]: value
    }));
  };

  return (
    <div className="max-w-md mx-auto p-6 bg-white rounded-lg shadow">
      <h1 className="text-3xl mb-6">Create Account</h1>
      
      <form onSubmit={handleSubmit} className="space-y-4">
        {/* First Name */}
        <div>
          <label className="block text-sm font-medium mb-1">First Name</label>
          <input
            type="text"
            name="firstName"
            placeholder="First name"
            value={formData.firstName}
            onChange={handleChange}
            className="w-full px-3 py-2 border rounded-md focus:outline-none focus:ring-1 focus:ring-blue-500"
          />
          {errors.firstName && (
            <div className="flex items-center mt-1 text-sm text-red-600">
              <AlertCircle className="w-4 h-4 mr-1" />
              {errors.firstName}
            </div>
          )}
        </div>

        {/* Last Name */}
        <div>
          <label className="block text-sm font-medium mb-1">Last Name</label>
          <input
            type="text"
            name="lastName"
            placeholder="Last name"
            value={formData.lastName}
            onChange={handleChange}
            className="w-full px-3 py-2 border rounded-md focus:outline-none focus:ring-1 focus:ring-blue-500"
          />
          {errors.lastName && (
            <div className="flex items-center mt-1 text-sm text-red-600">
              <AlertCircle className="w-4 h-4 mr-1" />
              {errors.lastName}
            </div>
          )}
        </div>

        {/* Email */}
        <div>
          <label className="block text-sm font-medium mb-1">Email</label>
          <input
            type="email"
            name="email"
            placeholder="Email address"
            value={formData.email}
            onChange={handleChange}
            className="w-full px-3 py-2 border rounded-md focus:outline-none focus:ring-1 focus:ring-blue-500"
          />
          {errors.email && (
            <div className="flex items-center mt-1 text-sm text-red-600">
              <AlertCircle className="w-4 h-4 mr-1" />
              {errors.email}
            </div>
          )}
        </div>

        {/* Password */}
        <div>
          <label className="block text-sm font-medium mb-1">Password</label>
          <input
            type="password"
            name="password"
            placeholder="At least 6 characters"
            value={formData.password}
            onChange={handleChange}
            className="w-full px-3 py-2 border rounded-md focus:outline-none focus:ring-1 focus:ring-blue-500"
          />
          {errors.password && (
            <div className="flex items-center mt-1 text-sm text-red-600">
              <AlertCircle className="w-4 h-4 mr-1" />
              {errors.password}
            </div>
          )}
        </div>

        <button
          type="submit"
          className="w-full py-2 px-4 bg-yellow-400 hover:bg-yellow-500 rounded-md"
        >
          Submit
        </button>
      </form>

      {/* Response Message */}
      {responseMessage && (
        <div className="mt-4 text-center text-sm text-green-600">
          {responseMessage}
        </div>
      )}
    </div>
  );
};

export default AmazonSignupForm;

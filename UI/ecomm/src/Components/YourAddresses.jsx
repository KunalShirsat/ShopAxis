import React, { useState, useEffect } from 'react';
import './YourAddresses.css';

const YourAddresses = () => {
  const [addresses, setAddresses] = useState([]);
  const [newAddress, setNewAddress] = useState({
    fullName: '',
    mobileNumber: '',
    pincode: '',
    flat: '',
    houseNo: '',
    building: '',
    company: '',
    apartment: '',
    area: '',
    street: '',
    sector: '',
    village: '',
    landmark: '',
    city: '',
    state: '',
  });

  useEffect(() => {
    // Fetch existing addresses from API (assuming an endpoint)
    fetch('http://localhost:8081/api/user/getaddress')
      .then((response) => response.json())
      .then((data) => setAddresses(data))
      .catch((error) => console.error('Error fetching addresses:', error));
  }, []);

  const handleAddAddress = () => {
    fetch('http://localhost:8081/api/user/address', {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify(newAddress),
    })
      .then((response) => response.json())
      .then((data) => {
        setAddresses([...addresses, data]);
        setNewAddress({
          fullName: '',
          mobileNumber: '',
          pincode: '',
          flat: '',
          houseNo: '',
          building: '',
          company: '',
          apartment: '',
          area: '',
          street: '',
          sector: '',
          village: '',
          landmark: '',
          city: '',
          state: '',
        });
      })
      .catch((error) => console.error('Error adding address:', error));
  };

  return (
    <div className="your-addresses">
      <h2>Your Addresses</h2>
      <div className="address-list">
        {addresses.map((address, index) => (
          <div key={index} className="address-card">
            <h4>{address.fullName}</h4>
            <p>{address.flat}, {address.building}, {address.street}</p>
            <p>{address.area}, {address.city}, {address.state} {address.pincode}</p>
            <p>Phone number: {address.mobileNumber}</p>
            <a href="/">Add delivery instructions</a>
            <div className="address-actions">
              <button>Edit</button>
              <button>Remove</button>
              <button>Set as Default</button>
            </div>
          </div>
        ))}
        <div className="add-address">
          <h4>Add Address</h4>
          <input type="text" placeholder="Full Name" value={newAddress.fullName} onChange={(e) => setNewAddress({ ...newAddress, fullName: e.target.value })} />
          <input type="text" placeholder="Mobile Number" value={newAddress.mobileNumber} onChange={(e) => setNewAddress({ ...newAddress, mobileNumber: e.target.value })} />
          {/* Add additional fields as needed */}
          <button onClick={handleAddAddress}>Add Address</button>
        </div>
      </div>
    </div>
  );
};

export default YourAddresses;

import React, { useState, useEffect } from 'react';
import './YourAddresses.css';

const YourAddresses = () => {
  const [addresses, setAddresses] = useState([]);
  const [isAddingNew, setIsAddingNew] = useState(false);
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
    fetch('http://localhost:8084/api/user/getaddress')
      .then((response) => response.json())
      .then((data) => setAddresses(data))
      .catch((error) => console.error('Error fetching addresses:', error));
  }, []);

  const handleAddAddress = () => {
    fetch('http://localhost:8084/api/user/address', {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify(newAddress),
    })
      .then((response) => response.json())
      .then((data) => {
        setAddresses([...addresses, data]);
        setIsAddingNew(false); // Close the form after adding
      })
      .catch((error) => console.error('Error adding address:', error));
  };

  const openNewAddressForm = () => {
    setIsAddingNew(true);
  };

  return (
    <div className="your-addresses">
      <h2>Your Addresses</h2>
      {!isAddingNew ? (
        <div className="address-list">
          {addresses.map((address, index) => (
            <div key={index} className="address-card">
              <h4>{address.fullName}</h4>
              <p>{address.flat}, {address.building}, {address.street}</p>
              <p>{address.area}, {address.city}, {address.state} {address.pincode}</p>
              <p>Phone number: {address.mobileNumber}</p>
              <div className="address-actions">
                <button>Edit</button>
                <button>Remove</button>
                <button>Set as Default</button>
              </div>
            </div>
          ))}
          <div className="add-address-card" onClick={openNewAddressForm}>
            <div className="add-icon">+</div>
            <p>Add Address</p>
          </div>
        </div>
      ) : (
        <div className="new-address-form">
          <h3>Add a New Address</h3>
          <input type="text" placeholder="Full Name" value={newAddress.fullName} onChange={(e) => setNewAddress({ ...newAddress, fullName: e.target.value })} />
          <input type="text" placeholder="Mobile Number" value={newAddress.mobileNumber} onChange={(e) => setNewAddress({ ...newAddress, mobileNumber: e.target.value })} />
          <input type="text" placeholder="Pincode" value={newAddress.pincode} onChange={(e) => setNewAddress({ ...newAddress, pincode: e.target.value })} />
          <input type="text" placeholder="Flat, House No., Building, Company, Apartment" value={newAddress.flat} onChange={(e) => setNewAddress({ ...newAddress, flat: e.target.value })} />
          <input type="text" placeholder="Area, Street, Sector, Village" value={newAddress.area} onChange={(e) => setNewAddress({ ...newAddress, area: e.target.value })} />
          <input type="text" placeholder="Landmark" value={newAddress.landmark} onChange={(e) => setNewAddress({ ...newAddress, landmark: e.target.value })} />
          <input type="text" placeholder="City" value={newAddress.city} onChange={(e) => setNewAddress({ ...newAddress, city: e.target.value })} />
          <input type="text" placeholder="State" value={newAddress.state} onChange={(e) => setNewAddress({ ...newAddress, state: e.target.value })} />
          <button onClick={handleAddAddress}>Save Address</button>
        </div>
      )}
    </div>
  );
};

export default YourAddresses;

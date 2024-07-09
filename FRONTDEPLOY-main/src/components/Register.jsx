import React, { useState } from 'react';
import { useAuth } from '../components/AuthContext';
import { register } from '../components/Api';
import { useNavigate } from 'react-router-dom';
import LogoutButton from './LogoutButton';

const Register = () => {
    const [firstName, setFirstName] = useState('');
    const [lastName, setLastName] = useState('');
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');
    const [isAdmin, setIsAdmin] = useState(false);
    const { login: setAuthToken } = useAuth();
    const navigate = useNavigate();
  
    const handleSubmit = async (e) => {
      e.preventDefault();
      try {
        const response = await register(firstName, lastName, email, password, isAdmin);
        setAuthToken(response.token);
        navigate('/publicaciones');
      } catch (error) {
        console.error('Registration failed:', error);
      }
    };
  
    return (
        
      <form onSubmit={handleSubmit}>
        <input
          type="text"
          placeholder="First Name"
          value={firstName}
          onChange={(e) => setFirstName(e.target.value)}
          required
        />
        <br/>
        <input
          type="text"
          placeholder="Last Name"
          value={lastName}
          onChange={(e) => setLastName(e.target.value)}
          required
        />
        <br/>
        <input
          type="email"
          placeholder="Email"
          value={email}
          onChange={(e) => setEmail(e.target.value)}
          required
        />
        <br/>
        <input
          type="password"
          placeholder="Password"
          value={password}
          onChange={(e) => setPassword(e.target.value)}
          required
        />
        <br/>
        <label>
          Admin:
          <input
            type="checkbox"
            checked={isAdmin}
            onChange={() => setIsAdmin(!isAdmin)}
          />
        </label>
        <br/>
        <button type="submit">Register</button>
        <LogoutButton/>
      </form>
      
    );
  };
  
  export default Register;
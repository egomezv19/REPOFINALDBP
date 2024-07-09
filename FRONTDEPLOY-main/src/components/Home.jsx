import React from 'react';
import { useNavigate } from 'react-router-dom';
import LogoutButton from './LogoutButton';

const Home = () => {
  const navigate = useNavigate();

  return (
    <div className="home-container">
      <h1>Welcome to Our Application</h1>
      <button onClick={() => navigate('/login')}>Login</button>
      <button onClick={() => navigate('/register')}>Register</button>
      <LogoutButton/>
    </div>
  );
};

export default Home;

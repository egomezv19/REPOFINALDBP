import React, { useState } from 'react';
import { useAuth } from '../components/AuthContext';
import { login } from '../components/Api';
import { useNavigate } from 'react-router-dom';
import LogoutButton from './LogoutButton';

const Login = () => {
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const { login: setAuthToken } = useAuth();
  const navigate = useNavigate();

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      const response = await login(email, password);
      setAuthToken(response.token);
      navigate('/publicaciones');
    } catch (error) {
      console.error('Login failed:', error);
    }
  };

  return (
    <form onSubmit={handleSubmit}>
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
      <button type="submit">Login</button>
      <LogoutButton/>
    </form>
  );
};

export default Login;

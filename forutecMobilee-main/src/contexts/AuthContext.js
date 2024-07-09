
import React, { createContext, useState, useEffect } from 'react';
import { setToken, getToken, removeToken } from '../utils/storage';
import { login, register } from '../services/authService';

const AuthContext = createContext();

export const AuthProvider = ({ children }) => {
  const [user, setUser] = useState(null);

  useEffect(() => {
    const loadUser = async () => {
      const token = await getToken();
      if (token) {
        
        setUser({ token });
      }
    };
    loadUser();
  }, []);

  const handleLogin = async (email, password) => {
    const data = await login(email, password);
    setUser(data);
    await setToken(data.token);
  };

  const handleRegister = async (email, password) => {
    const data = await register(email, password);
    setUser(data);
    await setToken(data.token);
  };

  const handleLogout = async () => {
    setUser(null);
    await removeToken();
  };

  return (
    <AuthContext.Provider value={{ user, handleLogin, handleRegister, handleLogout }}>
      {children}
    </AuthContext.Provider>
  );
};

export default AuthContext;

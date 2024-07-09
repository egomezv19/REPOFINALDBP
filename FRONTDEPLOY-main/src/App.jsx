import React from 'react';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import { AuthProvider } from './components/AuthContext';
import Home from './components/Home';
import Login from './components/Login';
import Register from './components/Register';
import Publicaciones from './components/Publicaciones';
import Comments from './components/Comments';
import Suscripciones from './components/Suscripciones';
import Perfil from './components/Perfil';
const App = () => {
  return (
    <AuthProvider>
      <Router>
        <Routes>
          <Route path="/" element={<Home />} />
          <Route path="/login" element={<Login />} />
          <Route path="/register" element={<Register />} />
          <Route path="/publicaciones" element={<Publicaciones />} />
          <Route path="/comments" element={<Comments />} />
          <Route path="/suscripciones" element={<Suscripciones />} />
          <Route path="/perfil" element={<Perfil />} />
        </Routes>
      </Router>
    </AuthProvider>
  );
};

export default App;

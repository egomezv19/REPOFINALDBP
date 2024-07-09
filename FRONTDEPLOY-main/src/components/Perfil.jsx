import React, { useState, useEffect } from 'react';
import { useAuth } from '../components/AuthContext';
import { fetchPerfil } from '../components/Api';

const Perfil = () => {
  const [perfil, setPerfil] = useState(null);
  const { token } = useAuth();

  useEffect(() => {
    const getPerfil = async () => {
      try {
        const data = await fetchPerfil(token);
        setPerfil(data);
      } catch (error) {
        console.error('Error fetching perfil:', error);
      }
    };

    getPerfil();
  }, [token]);

  if (!perfil) {
    return <p>Loading...</p>;
  }

  return (
    <div>
      <h1>Perfil</h1>
      <p>ID: {perfil.id}</p>
      <p>Usuario: {perfil.usuario.nombre}</p>
      <p>Información Adicional: {perfil.informacionAdicional}</p>
    </div>
  );
};

export default Perfil;

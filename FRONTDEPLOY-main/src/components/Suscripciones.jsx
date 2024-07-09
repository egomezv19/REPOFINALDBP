import React, { useState, useEffect } from 'react';
import { useAuth } from '../components/AuthContext';
import { fetchSuscripciones } from '../components/Api';

const Suscripciones = () => {
  const [suscripciones, setSuscripciones] = useState([]);
  const { token } = useAuth();

  useEffect(() => {
    const getSuscripciones = async () => {
      try {
        const data = await fetchSuscripciones(token);
        setSuscripciones(data);
      } catch (error) {
        console.error('Error fetching suscripciones:', error);
      }
    };

    getSuscripciones();
  }, [token]);

  return (
    <div>
      <h1>Suscripciones</h1>
      <ul>
        {suscripciones.map((suscripcion) => (
          <li key={suscripcion.id}>
            <p>Usuario: {suscripcion.usuario.nombre}</p>
            <p>Categoria: {suscripcion.categoria.nombre}</p>
            <p>Publicación: {suscripcion.publicacion.titulo}</p>
          </li>
        ))}
      </ul>
    </div>
  );
};

export default Suscripciones;

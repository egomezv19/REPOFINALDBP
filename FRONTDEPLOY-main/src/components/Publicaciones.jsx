import React, { useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import { useAuth } from '../components/AuthContext';
import { fetchPublicaciones } from '../components/Api';
import Comments from '../components/Comments';

const Publicaciones = () => {
  const [publicaciones, setPublicaciones] = useState([]);
  const { token } = useAuth();
  const navigate = useNavigate();

  useEffect(() => {
    if (!token) {
      navigate('/login');
      return;
    }

    const fetchAllPublicaciones = async () => {
      try {
        const data = await fetchPublicaciones(token);
        setPublicaciones(data);
      } catch (error) {
        console.error('Error fetching publicaciones:', error);
      }
    };

    fetchAllPublicaciones();
  }, [token, navigate]);

  return (
    <div>
      <h1>Publicaciones</h1>
      <ul>
        {publicaciones.map((publicacion) => (
          <li key={publicacion.id}>
            <h2>{publicacion.contenido}</h2>
            <p>{new Date(publicacion.fechaHoraPublicacion).toLocaleString()}</p>
            <Comments comentarios={publicacion.comentarios || []} />
          </li>
        ))}
      </ul>
    </div>
  );
};

export default Publicaciones;

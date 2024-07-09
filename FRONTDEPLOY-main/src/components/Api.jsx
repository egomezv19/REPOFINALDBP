import axios from 'axios';

const API_URL = 'http://3.235.222.9/api';

export const login = async (email, password) => {
  const response = await axios.post(`${API_URL}/auth/login`, { email, password });
  return response.data;
};

export const register = async (name, email, password, isTeacher) => {
  const response = await axios.post(`${API_URL}/auth/register`, { name, email, password, isTeacher });
  return response.data;
};

export const fetchPublicaciones = async (token) => {
    const response = await axios.get(`${API_URL}/publicaciones`, {
      headers: {
        Authorization: `Bearer ${token}`,
      },
    });
    return response.data;
  };
  
  export const fetchComentariosByPublicacion = async (publicacionId, token) => {
    const response = await axios.get(`${API_URL}/publicaciones/${publicacionId}/comentarios`, {
      headers: {
        Authorization: `Bearer ${token}`,
      },
    });
    return response.data;
  };
  
  export const fetchSuscripciones = async (token) => {
    const response = await axios.get(`${API_URL}/suscripciones`, {
      headers: {
        Authorization: `Bearer ${token}`,
      },
    });
    return response.data;
  };
  
  export const fetchPerfil = async (token) => {
    const response = await axios.get(`${API_URL}/perfil`, {
      headers: {
        Authorization: `Bearer ${token}`,
      },
    });
    return response.data;
  };
  
  export default {
    login,
    register,
    fetchPublicaciones,
    fetchComentariosByPublicacion,
    fetchSuscripciones,
    fetchPerfil,
  };
import api from '../utils/api';

export const getAllPosts = async () => {
  const response = await api.get('/publicaciones');
  return response.data;
};

export const getPostById = async (id) => {
  const response = await api.get(`/publicaciones/${id}`);
  return response.data;
};

export const createPost = async (post) => {
  const response = await api.post('/publicaciones', post);
  return response.data;
};

export const updatePost = async (id, post) => {
  const response = await api.put(`/publicaciones/${id}`, post);
  return response.data;
};

export const deletePost = async (id) => {
  await api.delete(`/publicaciones/${id}`);
};

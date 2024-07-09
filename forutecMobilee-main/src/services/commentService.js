import api from '../utils/api';

export const getAllComments = async () => {
  const response = await api.get('/comentarios');
  return response.data;
};

export const getCommentById = async (id) => {
  const response = await api.get(`/comentarios/${id}`);
  return response.data;
};

export const createComment = async (comment) => {
  const response = await api.post('/comentarios', comment);
  return response.data;
};

export const updateComment = async (id, comment) => {
  const response = await api.put(`/comentarios/${id}`, comment);
  return response.data;
};

export const deleteComment = async (id) => {
  await api.delete(`/comentarios/${id}`);
};


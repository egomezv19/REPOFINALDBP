import api from '../utils/api';

export const getAllCategories = async () => {
  const response = await api.get('/categorias');
  return response.data;
};

export const getCategoryById = async (id) => {
  const response = await api.get(`/categorias/${id}`);
  return response.data;
};

export const createCategory = async (category) => {
  const response = await api.post('/categorias', category);
  return response.data;
};

export const updateCategory = async (id, category) => {
  const response = await api.put(`/categorias/${id}`, category);
  return response.data;
};

export const deleteCategory = async (id) => {
  await api.delete(`/categorias/${id}`);
};

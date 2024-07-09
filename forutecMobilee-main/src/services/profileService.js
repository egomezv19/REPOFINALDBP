import api from '../utils/api';

export const getAllProfiles = async () => {
  const response = await api.get('/perfiles');
  return response.data;
};

export const getProfileById = async (id) => {
  const response = await api.get(`/perfiles/${id}`);
  return response.data;
};

export const createProfile = async (profile) => {
  const response = await api.post('/perfiles', profile);
  return response.data;
};

export const updateProfile = async (id, profile) => {
  const response = await api.put(`/perfiles/${id}`, profile);
  return response.data;
};

export const deleteProfile = async (id) => {
  await api.delete(`/perfiles/${id}`);
};

import api from '../utils/api';

export const getAllSubscriptions = async () => {
  const response = await api.get('/suscripciones');
  return response.data;
};

export const getSubscriptionById = async (id) => {
  const response = await api.get(`/suscripciones/${id}`);
  return response.data;
};

export const createSubscription = async (subscription) => {
  const response = await api.post('/suscripciones', subscription);
  return response.data;
};

export const deleteSubscription = async (id) => {
  await api.delete(`/suscripciones/${id}`);
};

import axios from 'axios';
import AsyncStorage from '@react-native-async-storage/async-storage';
import 'react-native-gesture-handler';


const API_URL = 'http://3.235.222.9/api'; 

const api = axios.create({
  baseURL: API_URL,
});

api.interceptors.request.use(async config => {
  const token = await AsyncStorage.getItem('token');
  if (token) {
    config.headers.Authorization = `Bearer ${token}`;
  }
  return config;
});

export default api;

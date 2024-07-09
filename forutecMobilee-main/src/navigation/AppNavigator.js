// src/navigation/AppNavigator.js
import React from 'react';
import { NavigationContainer } from '@react-navigation/native';
import { createStackNavigator } from '@react-navigation/stack';
import HomeScreen from '../screens/HomeScreen';
import LoginScreen from '../screens/LoginScreen';
import RegisterScreen from '../screens/RegisterScreen';
import ProfileScreen from '../screens/ProfileScreen';
import PostDetailsScreen from '../screens/PostDetailsScreen';
import PublicationScreen from '../screens/PublicationScreen';

const Stack = createStackNavigator();

const AppNavigator = () => {
  return (
    <NavigationContainer>
      <Stack.Navigator initialRouteName="HomeScreen">
        <Stack.Screen name="HomeScreen" component={HomeScreen} options={{ title: 'Home' }} />
        <Stack.Screen name="LoginScreen" component={LoginScreen} options={{ title: 'Login' }} />
        <Stack.Screen name="RegisterScreen" component={RegisterScreen} options={{ title: 'Register' }} />
        <Stack.Screen name="ProfileScreen" component={ProfileScreen} options={{ title: 'Profile' }} />
        <Stack.Screen name="PostDetailsScreen" component={PostDetailsScreen} options={{ title: 'Post Details' }} />
        <Stack.Screen name="PublicationScreen" component={PublicationScreen} options={{ title: 'Publications' }} />
      </Stack.Navigator>
    </NavigationContainer>
  );
};

export default AppNavigator;

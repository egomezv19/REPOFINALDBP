// src/screens/HomeScreen.js
import React from 'react';
import { View, Text, Button, StyleSheet } from 'react-native';

const HomeScreen = ({ navigation }) => {
  return (
    <View style={styles.container}>
      <Text style={styles.title}>Welcome to Forutec</Text>
      <Button
        title="Login"
        onPress={() => navigation.navigate('LoginScreen')}
      />
      <Button
        title="Register"
        onPress={() => navigation.navigate('RegisterScreen')}
      />
      <Button
        title="Go to Profile"
        onPress={() => navigation.navigate('ProfileScreen')}
      />
      <Button
        title="Go to Posts"
        onPress={() => navigation.navigate('PostDetailsScreen')}
      />
      <Button
        title="View Publications"
        onPress={() => navigation.navigate('PublicationScreen')}
      />
    </View>
  );
};

const styles = StyleSheet.create({
  container: {
    flex: 1,
    justifyContent: 'center',
    alignItems: 'center',
  },
  title: {
    fontSize: 24,
    fontWeight: 'bold',
    marginBottom: 20,
  },
});

export default HomeScreen;

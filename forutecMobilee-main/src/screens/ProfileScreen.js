// src/screens/ProfileScreen.js
import React, { useState, useEffect } from 'react';
import { View, Text, Button } from 'react-native';
import { getProfileById } from '../services/profileService';

const ProfileScreen = ({ route, navigation }) => {
  const { userId } = route.params;
  const [profile, setProfile] = useState(null);

  useEffect(() => {
    const fetchProfile = async () => {
      const data = await getProfileById(userId);
      setProfile(data);
    };
    fetchProfile();
  }, [userId]);

  if (!profile) {
    return <Text>Loading...</Text>;
  }

  return (
    <View>
      <Text>{profile.name}</Text>
      <Text>{profile.email}</Text>
      <Button title="Go Back" onPress={() => navigation.goBack()} />
    </View>
  );
};

export default ProfileScreen;

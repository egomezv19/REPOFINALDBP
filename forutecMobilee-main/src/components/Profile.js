// src/components/Profile.js
import React from 'react';
import { View, Text } from 'react-native';

const Profile = ({ profile }) => {
  return (
    <View>
      <Text>{profile.name}</Text>
      <Text>{profile.email}</Text>
    </View>
  );
};

export default Profile;

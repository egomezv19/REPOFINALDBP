// src/components/SubscriptionItem.js
import React from 'react';
import { View, Text } from 'react-native';

const SubscriptionItem = ({ subscription }) => {
  return (
    <View>
      <Text>{subscription.categoryName}</Text>
    </View>
  );
};

export default SubscriptionItem;

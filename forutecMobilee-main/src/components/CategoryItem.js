// src/components/CategoryItem.js
import React from 'react';
import { View, Text } from 'react-native';

const CategoryItem = ({ category }) => {
  return (
    <View>
      <Text>{category.name}</Text>
    </View>
  );
};

export default CategoryItem;

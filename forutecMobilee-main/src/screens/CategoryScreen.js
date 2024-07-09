// src/screens/CategoryScreen.js
import React, { useState, useEffect } from 'react';
import { View, FlatList, Button } from 'react-native';
import { getAllCategories } from '../services/categoryService';
import CategoryItem from '../components/CategoryItem';

const CategoryScreen = ({ navigation }) => {
  const [categories, setCategories] = useState([]);

  useEffect(() => {
    const fetchCategories = async () => {
      const data = await getAllCategories();
      setCategories(data);
    };
    fetchCategories();
  }, []);

  return (
    <View>
      <FlatList
        data={categories}
        keyExtractor={(item) => item.id.toString()}
        renderItem={({ item }) => <CategoryItem category={item} />}
      />
      <Button title="Go Back" onPress={() => navigation.goBack()} />
    </View>
  );
};

export default CategoryScreen;

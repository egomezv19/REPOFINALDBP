// src/components/PostItem.js
import React from 'react';
import { View, Text, TouchableOpacity } from 'react-native';

const PostItem = ({ post, navigation }) => {
  return (
    <TouchableOpacity onPress={() => navigation.navigate('PostDetails', { postId: post.id })}>
      <View>
        <Text>{post.title}</Text>
        <Text>{post.content}</Text>
      </View>
    </TouchableOpacity>
  );
};

export default PostItem;

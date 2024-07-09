// src/components/CommentItem.js
import React from 'react';
import { View, Text } from 'react-native';

const CommentItem = ({ comment }) => {
  return (
    <View>
      <Text>{comment.content}</Text>
    </View>
  );
};

export default CommentItem;

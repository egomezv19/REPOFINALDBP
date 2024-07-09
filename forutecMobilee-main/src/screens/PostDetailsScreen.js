// src/screens/PostDetailsScreen.js
import React, { useState, useEffect } from 'react';
import { View, Text, FlatList, Button } from 'react-native';
import { getPostById } from '../services/postService';
import { getAllComments } from '../services/commentService';
import CommentItem from '../components/CommentItem';

const PostDetailsScreen = ({ route, navigation }) => {
  const { postId } = route.params;
  const [post, setPost] = useState(null);
  const [comments, setComments] = useState([]);

  useEffect(() => {
    const fetchPost = async () => {
      const data = await getPostById(postId);
      setPost(data);
    };

    const fetchComments = async () => {
      const data = await getAllComments();
      setComments(data);
    };

    fetchPost();
    fetchComments();
  }, [postId]);

  if (!post) {
    return <Text>Loading...</Text>;
  }

  return (
    <View>
      <Text>{post.title}</Text>
      <Text>{post.content}</Text>
      <FlatList
        data={comments}
        keyExtractor={(item) => item.id.toString()}
        renderItem={({ item }) => <CommentItem comment={item} />}
      />
      <Button title="Go Back" onPress={() => navigation.goBack()} />
    </View>
  );
};

export default PostDetailsScreen;

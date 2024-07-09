// src/screens/SubscriptionScreen.js
import React, { useState, useEffect } from 'react';
import { View, FlatList, Button } from 'react-native';
import { getAllSubscriptions } from '../services/subscriptionService';
import SubscriptionItem from '../components/SubscriptionItem';

const SubscriptionScreen = ({ navigation }) => {
  const [subscriptions, setSubscriptions] = useState([]);

  useEffect(() => {
    const fetchSubscriptions = async () => {
      const data = await getAllSubscriptions();
      setSubscriptions(data);
    };
    fetchSubscriptions();
  }, []);

  return (
    <View>
      <FlatList
        data={subscriptions}
        keyExtractor={(item) => item.id.toString()}
        renderItem={({ item }) => <SubscriptionItem subscription={item} />}
      />
      <Button title="Go Back" onPress={() => navigation.goBack()} />
    </View>
  );
};

export default SubscriptionScreen;

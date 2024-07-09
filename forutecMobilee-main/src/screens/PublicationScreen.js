// src/screens/PublicationScreen.js
import React from 'react';
import { View, Text, FlatList, StyleSheet } from 'react-native';

const publications = [
  { id: '1', title: 'New Research Facility at UTEC', content: 'UTEC has inaugurated a new research facility dedicated to advanced technological studies.' },
  { id: '2', title: 'UTEC Wins Engineering Award', content: 'Congratulations to the UTEC engineering team for winning the national engineering competition.' },
  { id: '3', title: 'Upcoming Tech Symposium', content: 'Join us for the annual tech symposium where industry leaders will discuss the future of technology.' },
  // Añadir más publicaciones según sea necesario
];

const PublicationScreen = () => {
  return (
    <View style={styles.container}>
      <Text style={styles.title}>Publications</Text>
      <FlatList
        data={publications}
        keyExtractor={(item) => item.id}
        renderItem={({ item }) => (
          <View style={styles.publicationContainer}>
            <Text style={styles.publicationTitle}>{item.title}</Text>
            <Text style={styles.publicationContent}>{item.content}</Text>
          </View>
        )}
      />
    </View>
  );
};

const styles = StyleSheet.create({
  container: {
    flex: 1,
    padding: 16,
    backgroundColor: '#fff',
  },
  title: {
    fontSize: 24,
    fontWeight: 'bold',
    marginBottom: 20,
    textAlign: 'center',
  },
  publicationContainer: {
    marginBottom: 20,
    padding: 16,
    borderColor: '#ccc',
    borderWidth: 1,
    borderRadius: 8,
  },
  publicationTitle: {
    fontSize: 18,
    fontWeight: 'bold',
  },
  publicationContent: {
    fontSize: 14,
  },
});

export default PublicationScreen;

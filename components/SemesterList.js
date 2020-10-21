import React, { useState, useEffect } from 'react';
import { StyleSheet,
         View,
         Text,
         Button,
         FlatList,
         SafeAreaView,
         TextInput,
         Keyboard,
         Platform,
         StatusBar,
         KeyboardAvoidingView } from 'react-native';
import { nanoid } from 'nanoid/non-secure';


export default function SemesterList() {
  const [ subjects, setSubjects ] = useState([]);
  const [ subjectText, setSubjectText ] = useState("");
  const [ viewPadding, setViewPadding ] = useState(10);

  const deleteSubject = (id) => {
    const filtered = subjects.filter(subject =>
                                     id !== subject.id);
    setSubjects(filtered);
  };

  const addSubject = () => {
    const nid = nanoid();
    const newSubject = { id: nid, text: subjectText };

    setSubjectText("");
    setSubjects([...subjects, newSubject]);
  };

  const Subject = ({ item }) => {
    return (
      <View>
        <Text style={styles.subject}>{item.text}</Text>
        <Button title="X" style={styles.button}
                onPress={() => deleteSubject(item.id)} />
      </View>
    );
  }

  return (
    <KeyboardAvoidingView style={[styles.container]}>
      <StatusBar />
      <FlatList
        data={subjects}
        renderItem={Subject}
        keyExtractor={item => item.id}
      />
      <TextInput
        styles={styles.TextInput}
        onChangeText={setSubjectText}
        onSubmitEditing={addSubject}
        value={subjectText}
        placeholder="Add a subject..."
        returnKeyType="done"
        returnKeyLabel="done"
      />
    </KeyboardAvoidingView>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    justifyContent: "flex-start",
    alignItems: "center",
    backgroundColor: "#FFFFFF",
    padding: 10,
    paddingTop: 20
  },
  semesterList: {
    width: "100%"
  },
  subject: {
    fontSize: 14
  },
  subjectview: {
    flex: 1,
    alignItems: 'flex-start',
    justifyContent: 'flex-start',
    paddingBottom: "10",
  },
  button: {
  },
  h1: {
  },
  textInput: {
    height: 40,
    width: "100%",
    borderWidth: 0
  }
});
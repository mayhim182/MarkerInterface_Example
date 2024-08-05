package org.example;


import java.io.*;

class Person implements Serializable {

  private static final long serialVersionUID = 1L;
  private String name;
  private int age;

  public Person(String name, int age) {
    this.name = name;
    this.age = age;
  }

  @Override
  public String toString() {
    return "Person{name = "+ name +" age = "+age+"}";
  }

}

public class SerializeEx {
  public static void serializePerson(Person person, String filename) {
    try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("test.txt"))){
      out.writeObject(person);
      System.out.println("Person object has been serialized to " + filename);
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public static Person deserializePerson(String filename) {
    try (ObjectInputStream in = new ObjectInputStream(new FileInputStream("test.txt"))) {
      return (Person) in.readObject();
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    }
    return null;
  }

  public static void main(String args[]) {
    Person person = new Person("Alice", 26);
    String filename = "test.txt";
    serializePerson(person, filename);

    Person deserializedPerson = deserializePerson(filename);
    if (deserializedPerson != null) {
      System.out.println("Deserialized Person object: " + deserializedPerson);
    }
  }
}

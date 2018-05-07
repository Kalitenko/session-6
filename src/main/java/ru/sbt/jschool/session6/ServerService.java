package ru.sbt.jschool.session6;

import java.io.IOException;
import java.util.List;

public interface ServerService {

    void createUser(String name, int age, double salary) throws IOException;

    boolean deleteUser(int id);

    List<User> list() throws IOException, ClassNotFoundException;

    User getUser(int id) throws IOException, ClassNotFoundException;

}

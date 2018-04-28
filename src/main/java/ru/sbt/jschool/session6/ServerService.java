package ru.sbt.jschool.session6;

import java.util.List;

/**
 * Created by 1 on 22.04.2018.
 */
public interface ServerService {

    void createUser(String name, int age, double salary);

    boolean deleteUser(int id);

    List<User> list();

    User getUser(int id);

}

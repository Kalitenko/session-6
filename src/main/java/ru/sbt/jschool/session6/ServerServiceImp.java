package ru.sbt.jschool.session6;

import java.util.List;

public class ServerServiceImp implements ServerService {

    private String databasePath = "src/main/database";
    private Database database = new Database(databasePath);


    @Override
    public void createUser(String name, int age, double salary) {
        User user = new User(name, age, salary);
        database.writeFile(user);
    }

    @Override
    public boolean deleteUser(int id) {
        return database.deleteFile(id);
    }

    @Override
    public List<User> list() {
        return database.readAllFiles();
    }

    @Override
    public User getUser(int id) {
        return database.readFile(id);
    }
}

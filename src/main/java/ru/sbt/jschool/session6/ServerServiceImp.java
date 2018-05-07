package ru.sbt.jschool.session6;

import java.io.IOException;
import java.util.List;

public class ServerServiceImp implements ServerService {

    private String databasePath = "src/main/database";
    private Database database = new Database(databasePath);


    @Override
    public void createUser(String name, int age, double salary) throws IOException {
        User user = new User(name, age, salary);
        database.writeFile(user);
    }

    @Override
    public boolean deleteUser(int id) {
        return database.deleteFile(id);
    }

    @Override
    public List<User> list() throws IOException, ClassNotFoundException {
        return database.readAllFiles();
    }

    @Override
    public User getUser(int id) throws IOException, ClassNotFoundException {
        return database.readFile(id);
    }
}

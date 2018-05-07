package ru.sbt.jschool.session6;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


class Database {

    private String databasePath;

    Database(String databasePath) {
        this.databasePath = databasePath;
    }

    boolean deleteFile(int id) {
        String fileName = "/" + id + ".bin";
        File file = new File(databasePath + fileName);

        return file.delete();
    }

    private int findFreeId() {

        int id = 0;
        String fileName = id + ".bin";

        File file = new File(databasePath);
        List<String> listOfFiles = new ArrayList<>(Arrays.asList(file.list()));

        boolean flag = true;

        while (flag) {
            if (listOfFiles.contains(fileName)) {
                id++;
                fileName = id + ".bin";
            } else
                flag = false;
        }
        return id;
    }

    void writeFile(User user) throws IOException {

        FileOutputStream fos = new FileOutputStream(databasePath + "/" + findFreeId() + ".bin");
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(user);
        oos.close();
        fos.close();
    }

    User readFile(int id) throws IOException, ClassNotFoundException {
        User user;

        FileInputStream fis = new FileInputStream(databasePath + "/" + id + ".bin");
        ObjectInputStream ois = new ObjectInputStream(fis);
        user = (User) ois.readObject();
        ois.close();
        fis.close();

        return user;
    }

    private User readFile(String fileName) throws IOException, ClassNotFoundException {
        User user;

        FileInputStream fis = new FileInputStream(fileName);
        ObjectInputStream ois = new ObjectInputStream(fis);
        user = (User) ois.readObject();
        ois.close();
        fis.close();
        return user;
    }

    List<User> readAllFiles() throws IOException, ClassNotFoundException {

        List<User> list = new ArrayList<>();
        File file = new File(databasePath);
        List<File> listOfFiles = new ArrayList<>(Arrays.asList(file.listFiles()));

        for (File f : listOfFiles)
            list.add((User)readFile(f.toString()));

        return list;
    }
}


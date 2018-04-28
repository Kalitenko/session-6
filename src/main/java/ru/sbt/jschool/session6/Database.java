package ru.sbt.jschool.session6;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by 1 on 27.04.2018.
 */
public class Database {

    private String databasePath;

    public Database(String databasePath) {
        this.databasePath = databasePath;
    }

    public boolean deleteFile(int id) {
        String fileName = "/" + id + ".bin";
        File file = new File(databasePath + fileName);

        return file.delete();
    }

    public int findFreeId() throws NullPointerException{

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

    public void writeFile(User user) {
        try {
            FileOutputStream fos = new FileOutputStream(databasePath + "/" + findFreeId() + ".bin");
            try {
                ObjectOutputStream oos = new ObjectOutputStream(fos);
                oos.writeObject(user);
                oos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public User readFile(int id) {
        User user = null;
        try {
            FileInputStream fis = new FileInputStream(databasePath + "/" + id + ".bin");
            try {
                ObjectInputStream ois = new ObjectInputStream(fis);
                try {
                    user = (User) ois.readObject();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
                ois.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            fis.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return user;
    }

    public User readFile(String fileName) {
        User user = null;
        try {
            FileInputStream fis = new FileInputStream(fileName);
            try {
                ObjectInputStream ois = new ObjectInputStream(fis);
                try {
                    user = (User) ois.readObject();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
                ois.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            fis.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return user;
    }

    public List<User> readAllFiles() {

        List<User> list = new ArrayList<>();
        File file = new File(databasePath);
        List<File> listOfFiles = new ArrayList<>(Arrays.asList(file.listFiles()));

        for (File f : listOfFiles)
            list.add((User)readFile(f.toString()));

        return list;
    }
}


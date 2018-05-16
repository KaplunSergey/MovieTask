package com.example.testtask.data.database.user;

public interface UserDAO {
    void addUser(UserDb userDb);

    UserDb getUser();

    void updateUser(UserDb userDb);

    void deleteUser(UserDb userDb);

    void close();
}

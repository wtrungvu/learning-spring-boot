package com.wtrungvu.learningspringboot.dao;

import com.wtrungvu.learningspringboot.model.User;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class FakeDataDao implements UserDao {

    private static Map<UUID, User> database;

    static {
        database = new HashMap<>();
        UUID johnUserIdOne = UUID.randomUUID();
        database.put(johnUserIdOne, new User(johnUserIdOne, "John", "Cena", User.Gender.MALE, 25, "john.cena123456@gmail.com"));
    }

    @Override
    public List<User> getAllUsers() {
        return null;
    }

    @Override
    public User getUser(UUID id) {
        return null;
    }

    @Override
    public int updateUser(User user) {
        return 0;
    }

    @Override
    public int removeUser(UUID id) {
        return 0;
    }

    @Override
    public int insertUser(User user) {
        return 0;
    }
}

package com.wtrungvu.learningspringboot.dao;

import com.wtrungvu.learningspringboot.model.User;

import java.util.List;
import java.util.UUID;

public interface UserDao {

    List<User> getAllUsers();

    User getUser(UUID id);

    int updateUser(User user);

    int removeUser(UUID id);

    int insertUser(User user);

}

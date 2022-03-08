package com.wtrungvu.learningspringboot.dao;

import com.wtrungvu.learningspringboot.model.User;

import java.util.List;
import java.util.UUID;

public interface UserDao {

    List<User> selectAllUsers();

    User selectUserByUserUid(UUID userUid);

    int updateUser(User user);

    int deleteUserByUserUid(UUID userUid);

    int insertUser(UUID userUid, User user);

}

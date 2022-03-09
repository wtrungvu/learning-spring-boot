package com.wtrungvu.learningspringboot.service;

import com.wtrungvu.learningspringboot.dao.UserDao;
import com.wtrungvu.learningspringboot.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

    private UserDao userDao;

    @Autowired
    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    public List<User> getAllUsers() {
        return userDao.selectAllUsers();
    }

    public Optional<User> getUser(UUID userUid) {
        return userDao.selectUserByUserUid(userUid);
    }

    public int updateUser(User user) {
        Optional<User> optionalUser = getUser(user.getUserUid());
        if (optionalUser.isPresent()) {
            System.out.println("User updated");
            return userDao.updateUser(user);
        }
        System.out.println("User not found");
        return -1;
    }

    public int removeUser(UUID userUid) {
        Optional<User> optionalUser = getUser(userUid);
        if (optionalUser.isPresent()) {
            System.out.println("User deleted");
            return userDao.deleteUserByUserUid(userUid);
        }
        System.out.println("User not found");
        return -1;
    }

    public int insertUser(User user) {
        UUID userUid = UUID.randomUUID();
        user.setUserUid(userUid);
        System.out.println("User inserted");
        return userDao.insertUser(userUid, user);
    }
}

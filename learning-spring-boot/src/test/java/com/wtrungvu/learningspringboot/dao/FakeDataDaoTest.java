package com.wtrungvu.learningspringboot.dao;

import com.wtrungvu.learningspringboot.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class FakeDataDaoTest {

    private FakeDataDao fakeDataDao;

    @BeforeEach
    void setUp() {
        fakeDataDao = new FakeDataDao();
    }

    @Test
    void shouldSelectAllUsers() {
        List<User> users = fakeDataDao.selectAllUsers();
        assertThat(users).hasSize(1);

        User user = users.get(0);

        assertThat(user.getUserUid()).isNotNull();
        assertThat(user.getFirstName()).isEqualTo("John");
        assertThat(user.getLastName()).isEqualTo("Cena");
        assertThat(user.getGender()).isEqualTo(User.Gender.MALE);
        assertThat(user.getAge()).isEqualTo(25);
        assertThat(user.getEmail()).isEqualTo("john.cena123456@gmail.com");
    }

    @Test
    void selectUserByUserUid() {
    }

    @Test
    void updateUser() {
    }

    @Test
    void deleteUserByUserUid() {
    }

    @Test
    void insertUser() {
    }
}
package com.wtrungvu.learningspringboot.dao;

import com.wtrungvu.learningspringboot.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

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
    void shouldSelectUserByUserUid() {
        UUID annaUserUid = UUID.randomUUID();
        User annaUser = new User(
                annaUserUid,
                "Anna",
                "Montana",
                User.Gender.MALE,
                25,
                "Anna.Montana@gmail.com"
        );
        fakeDataDao.insertUser(annaUserUid, annaUser);
        assertThat(fakeDataDao.selectAllUsers()).hasSize(2);

        Optional<User> annaUserOptional = fakeDataDao.selectUserByUserUid(annaUserUid);
        assertThat(annaUserOptional.isPresent()).isTrue();

        /* isEqualToComparingFieldByField() is deprecated */
//        assertThat(annaUserOptional.get()).isEqualToComparingFieldByField(annaUser);

        /* Use the recursive comparison by calling usingRecursiveComparison() */
        assertThat(annaUserOptional.get()).usingRecursiveComparison().isEqualTo(annaUser);
    }

    @Test
    void shouldNotSelectUserByRandomUserUid() {
        Optional<User> user = fakeDataDao.selectUserByUserUid(UUID.randomUUID());
        assertThat(user.isPresent()).isFalse();
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
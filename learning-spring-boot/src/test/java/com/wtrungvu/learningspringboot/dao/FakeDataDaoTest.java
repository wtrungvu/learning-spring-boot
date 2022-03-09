package com.wtrungvu.learningspringboot.dao;

import com.wtrungvu.learningspringboot.model.User;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

public class FakeDataDaoTest {

    private FakeDataDao fakeDataDao;

    @Before
    public void setUp() {
        fakeDataDao = new FakeDataDao();
    }

    @Test
    public void shouldSelectAllUsers() {
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
    public void shouldSelectUserByUserUid() {
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
        assertThat(annaUserOptional.get()).isEqualToComparingFieldByField(annaUser);

        /* Use the recursive comparison by calling usingRecursiveComparison() */
//        assertThat(annaUserOptional.get()).usingRecursiveComparison().isEqualTo(annaUser);
    }

    @Test
    public void shouldNotSelectUserByRandomUserUid() {
        Optional<User> user = fakeDataDao.selectUserByUserUid(UUID.randomUUID());
        assertThat(user.isPresent()).isFalse();
    }

    @Test
    public void shouldUpdateUser() {
        UUID joeUserUid = fakeDataDao.selectAllUsers().get(0).getUserUid();
        User newJoeUser = new User(
                joeUserUid,
                "Anna",
                "Montana",
                User.Gender.MALE,
                25,
                "Anna.Montana@gmail.com"
        );
        fakeDataDao.updateUser(newJoeUser);
        Optional<User> user = fakeDataDao.selectUserByUserUid(joeUserUid);
        assertThat(user.isPresent()).isTrue();

        assertThat(fakeDataDao.selectAllUsers()).hasSize(1);
        assertThat(user.get()).isEqualToComparingFieldByField(newJoeUser);
    }

    @Test
    public void shouldDeleteUserByUserUid() {
        UUID joeUserUid = fakeDataDao.selectAllUsers().get(0).getUserUid();

        fakeDataDao.deleteUserByUserUid(joeUserUid);

        assertThat(fakeDataDao.selectUserByUserUid(joeUserUid).isPresent()).isFalse();
        assertThat(fakeDataDao.selectAllUsers()).hasSize(0);
    }

    @Test
    public void shouldInsertUser() {
        UUID userUid = UUID.randomUUID();
        User user = new User(
                userUid,
                "Anna",
                "Montana",
                User.Gender.MALE,
                25,
                "Anna.Montana@gmail.com"
        );

        fakeDataDao.insertUser(userUid, user);

        List<User> users = fakeDataDao.selectAllUsers();
        assertThat(users).hasSize(2);
        assertThat(fakeDataDao.selectUserByUserUid(userUid).get()).isEqualToComparingFieldByField(user);
    }
}
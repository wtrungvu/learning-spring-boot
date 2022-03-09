package com.wtrungvu.learningspringboot.service;

import com.google.common.collect.ImmutableList;
import com.wtrungvu.learningspringboot.dao.FakeDataDao;
import com.wtrungvu.learningspringboot.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

class UserServiceTest {

    @Mock
    private FakeDataDao fakeDataDao;

    private UserService userService;

    @BeforeEach
    void setUp() {
        /* 'initMocks(java.lang.Object)' is deprecated  */
//        MockitoAnnotations.initMocks(this);
        /* Replace method call with openMocks */
        MockitoAnnotations.openMocks(this);
        userService = new UserService(fakeDataDao);
    }

    @Test
    void shouldGetAllUsers() {
        UUID annaId = UUID.randomUUID();

        User annaUser = new User(
                annaId,
                "Anna",
                "Montana",
                User.Gender.FERMALE,
                30,
                "Anna.Montana123456@gmail.com"
        );

        ImmutableList<User> users = new ImmutableList.Builder<User>()
                .add(annaUser)
                .build();

        given(fakeDataDao.selectAllUsers()).willReturn(users);

        List<User> allUsers = userService.getAllUsers();

        assertThat(allUsers).hasSize(1);

        User user = allUsers.get(0);

        assertThat(user.getUserUid()).isNotNull();
        assertThat(user.getFirstName()).isEqualTo("Anna");
        assertThat(user.getLastName()).isEqualTo("Montana");
        assertThat(user.getGender()).isEqualTo(User.Gender.FERMALE);
        assertThat(user.getAge()).isEqualTo(30);
        assertThat(user.getEmail()).isEqualTo("Anna.Montana123456@gmail.com");
    }

    @Test
    void shouldGetUser() {
    }

    @Test
    void shouldUpdateUser() {
    }

    @Test
    void shouldRemoveUser() {
    }

    @Test
    void shouldInsertUser() {
    }
}
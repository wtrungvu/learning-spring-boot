package com.wtrungvu.learningspringboot.service;

import com.google.common.collect.ImmutableList;
import com.wtrungvu.learningspringboot.dao.FakeDataDao;
import com.wtrungvu.learningspringboot.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

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

        assertUserFields(user);
    }

    @Test
    void shouldGetUser() {
        UUID annaUid = UUID.randomUUID();

        User annaUser = new User(
                annaUid,
                "Anna",
                "Montana",
                User.Gender.FERMALE,
                30,
                "Anna.Montana123456@gmail.com"
        );

        given(fakeDataDao.selectUserByUserUid(annaUid)).willReturn(Optional.of(annaUser));

        Optional<User> userOptional = userService.getUser(annaUid);

        assertThat(userOptional.isPresent()).isTrue();

        User user = userOptional.get();

        assertUserFields(user);
    }

    @Test
    void shouldUpdateUser() {
        UUID annaUid = UUID.randomUUID();

        User annaUser = new User(
                annaUid,
                "Anna",
                "Montana",
                User.Gender.FERMALE,
                30,
                "Anna.Montana123456@gmail.com"
        );

        given(fakeDataDao.selectUserByUserUid(annaUid)).willReturn(Optional.of(annaUser));
        given(fakeDataDao.updateUser(annaUser)).willReturn(1);

        ArgumentCaptor<User> captor = ArgumentCaptor.forClass(User.class);

        int updateResult = userService.updateUser(annaUser);

        verify(fakeDataDao).selectUserByUserUid(annaUid);
        verify(fakeDataDao).updateUser(captor.capture());

        User user = captor.getValue();
        assertUserFields(user);

        assertThat(updateResult).isEqualTo(1);
    }

    @Test
    void shouldRemoveUser() {
    }

    @Test
    void shouldInsertUser() {
    }

    private void assertUserFields(User user) {
        assertThat(user.getUserUid()).isNotNull();
        assertThat(user.getFirstName()).isEqualTo("Anna");
        assertThat(user.getLastName()).isEqualTo("Montana");
        assertThat(user.getGender()).isEqualTo(User.Gender.FERMALE);
        assertThat(user.getAge()).isEqualTo(30);
        assertThat(user.getEmail()).isEqualTo("Anna.Montana123456@gmail.com");
    }
}
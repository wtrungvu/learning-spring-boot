package com.wtrungvu.learningspringboot.it;

import com.wtrungvu.learningspringboot.clientproxy.UserResourceV1;
import com.wtrungvu.learningspringboot.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.ws.rs.NotFoundException;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class UserIT {

	@Autowired
	private UserResourceV1 userResourceV1;

	@Test
	public void shouldInsertUser() {
		//Given
		UUID johnUserUid = UUID.randomUUID();
		User johnUser = new User(
				johnUserUid,
				"John",
				"Cena",
				User.Gender.MALE,
				25,
				"john.cena123456@gmail.com"
		);

		//When
		userResourceV1.insertNewUser(johnUser);

		//Then
		User user = userResourceV1.fetchUser(johnUserUid);
		assertThat(user).isEqualToComparingFieldByField(johnUser);
	}

	@Test
	public void shouldDeleteUser() {
		//Given
		UUID johnUserUid = UUID.randomUUID();
		User johnUser = new User(
				johnUserUid,
				"John",
				"Cena",
				User.Gender.MALE,
				25,
				"john.cena123456@gmail.com"
		);

		//When
		userResourceV1.insertNewUser(johnUser);

		//Then
		User user = userResourceV1.fetchUser(johnUserUid);
		assertThat(user).isEqualToComparingFieldByField(johnUser);

		//When
		userResourceV1.deleteUser(johnUserUid);

		//Then
		assertThatThrownBy(() -> userResourceV1.fetchUser(johnUserUid))
				.isInstanceOf(NotFoundException.class);

	}

	@Test
	public void shouldUpdateUser() {
		//Given
		UUID userUid = UUID.randomUUID();
		User user = new User(
				userUid,
				"John",
				"Cena",
				User.Gender.MALE,
				25,
				"john.cena123456@gmail.com"
		);

		//When
		userResourceV1.insertNewUser(user);

		User updatedUser = new User(
				userUid,
				"Alexandra",
				"Pochentino",
				User.Gender.FEMALE,
				36,
				"Alexandra.Pochentino@gmail.com"
		);
		userResourceV1.updateUser(updatedUser);

		//Then
		user = userResourceV1.fetchUser(userUid);
		assertThat(user).isEqualToComparingFieldByField(updatedUser);
	}

	@Test
	public void shouldFetchUsersByGender() {
		//Given
		UUID userUid = UUID.randomUUID();
		User user = new User(
				userUid,
				"John",
				"Cena",
				User.Gender.MALE,
				25,
				"john.cena123456@gmail.com"
		);

		//When
		userResourceV1.insertNewUser(user);

		List<User> fermales = userResourceV1.fetchUsers(User.Gender.FEMALE.name());

		assertThat(fermales).extracting("userUid").doesNotContain(user.getUserUid());
		assertThat(fermales).extracting("firstName").doesNotContain(user.getFirstName());
		assertThat(fermales).extracting("lastName").doesNotContain(user.getLastName());
		assertThat(fermales).extracting("gender").doesNotContain(user.getGender());
		assertThat(fermales).extracting("age").doesNotContain(user.getAge());
		assertThat(fermales).extracting("email").doesNotContain(user.getEmail());

		List<User> males = userResourceV1.fetchUsers(User.Gender.MALE.name());

		assertThat(males).extracting("userUid").contains(user.getUserUid());
		assertThat(males).extracting("firstName").contains(user.getFirstName());
		assertThat(males).extracting("lastName").contains(user.getLastName());
		assertThat(males).extracting("gender").contains(user.getGender());
		assertThat(males).extracting("age").contains(user.getAge());
		assertThat(males).extracting("email").contains(user.getEmail());

	}
}

package com.wtrungvu.learningspringboot;

import com.wtrungvu.learningspringboot.clientproxy.UserResourceV1;
import com.wtrungvu.learningspringboot.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class LearningSpringBootApplicationTests {

	@Autowired
	private UserResourceV1 userResourceV1;

	@Test
	public void itShouldFetchAllUsers() {
		List<User> users = userResourceV1.fetchUsers(null);

		assertThat(users).hasSize(1);

		User johnUser = new User(
				null,
				"John",
				"Cena",
				User.Gender.MALE,
				25,
				"john.cena123456@gmail.com"
		);

		assertThat(users.get(0)).isEqualToIgnoringGivenFields(johnUser, "userUid");

		assertThat(users.get(0).getUserUid()).isInstanceOf(UUID.class);

		assertThat(users.get(0).getUserUid()).isNotNull();
	}

}

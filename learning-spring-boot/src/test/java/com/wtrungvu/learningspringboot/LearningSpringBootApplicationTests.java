package com.wtrungvu.learningspringboot;

import com.wtrungvu.learningspringboot.clientproxy.UserResourceV1;
import com.wtrungvu.learningspringboot.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class LearningSpringBootApplicationTests {

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
}

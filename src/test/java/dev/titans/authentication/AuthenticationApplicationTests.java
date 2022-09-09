package dev.titans.authentication;

import dev.titans.dtos.LoginCredentials;
import dev.titans.entities.User;
import dev.titans.repos.UserRepo;
import dev.titans.services.LoginService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jms.core.JmsTemplate;

import java.util.List;

@SpringBootTest
class AuthenticationApplicationTests {
	@Autowired
	LoginService loginService;

		@Autowired
	JmsTemplate jmsTemplate;

	@Test
	void contextLoads() {
		String message = "The Login was performed at 10:00am, Batman";
		jmsTemplate.convertAndSend("titan-important-events", message);

	}

}

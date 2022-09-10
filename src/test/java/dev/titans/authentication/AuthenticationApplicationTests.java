package dev.titans.authentication;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jms.core.JmsTemplate;

@SpringBootTest
class AuthenticationApplicationTests {

	@Autowired
	JmsTemplate jmsTemplate;

	@Test
	void contextLoads() {
		String message = "The Login was performed at 10:00am, Batman";
		jmsTemplate.convertAndSend("titan-important-events", message);

	}

}

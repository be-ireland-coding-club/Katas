package com.gavinfitzgerald.socialNetworkTDD;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class SocialNetworkTddApplicationTests {

	private final String DATE_PATTERN = "yyyy-MM-dd HH-mm";
	private SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_PATTERN);

	@Test
	void contextLoads() {
	}

	@Test
	void testCanPublishMessageToPersonalTimeline(){
		//Arrange
		UserController unitUnderTest = new UserController();
		String user = "Bob";
		String message = "Hello World";

		//Act
		unitUnderTest.post(user, message);

		//Assert
		List<Message> actual = unitUnderTest.getTimeline(user);
		//Method will execute in under a minute so the below date comparison is deterministic
		String expected = user+", "+dateFormat.format(new Date()).toString()+": "+message+"\n";
		assertEquals(expected, actual.get(0).toString(), "Timeline should return '"+expected+"' as only post");
	}

	@Test
	void testCanPublishMultilpleMessageToPersonalTimeline(){
		//Arrange
		UserController unitUnderTest = new UserController();
		String user = "Bob";
		String firstMessage = "First Message";
		String secondMessage = "Second Message";
		String thirdMessage = "Third Message";

		//Act
		unitUnderTest.post(user, firstMessage);
		unitUnderTest.post(user, secondMessage);
		unitUnderTest.post(user, thirdMessage);

		//Assert
		List<Message> actual = unitUnderTest.getTimeline(user);
		String expectedFirstMessage = user+", "+dateFormat.format(new Date()).toString()+": "+firstMessage+"\n";
		String expectedSecondMessage = user+", "+dateFormat.format(new Date()).toString()+": "+secondMessage+"\n";
		String expectedThirdMessage = user+", "+dateFormat.format(new Date()).toString()+": "+thirdMessage+"\n";
		assertEquals(expectedFirstMessage, actual.get(0).toString(), "Timeline should return '"+expectedFirstMessage+"' as first post");
		assertEquals(expectedSecondMessage, actual.get(1).toString(), "Timeline should return '"+expectedSecondMessage+"' as second post");
		assertEquals(expectedThirdMessage, actual.get(2).toString(), "Timeline should return '"+expectedThirdMessage+"' as third post");
	}


}

package com.gavinfitzgerald.socialNetworkTDD;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class SocialNetworkTddApplicationTests {

	@Test
	void contextLoads() {
	}

	@Test
	void testCanPublishMessageToPersonalTimeline(){
		UserController unitUnderTest = new UserController();
		String message = "Hello World";
		unitUnderTest.post(message);
		assertEquals(message, unitUnderTest.readTimeline(), "Timeline should return 'Hello World' as only post");
	}

	@Test
	void testCanPublishMultipleMessageToPersonalTimeline(){
		UserController unitUnderTest = new UserController();
		String firstMessage = "First Message";
		String secondMessage = "Second Message";
		String thirdMessage = "Third Message";
		unitUnderTest.post(firstMessage);
		unitUnderTest.post(secondMessage);
		unitUnderTest.post(thirdMessage);
		String expected = firstMessage+"\n"+secondMessage+"\n"+thirdMessage+"\n";
		assertEquals(expected, unitUnderTest.readTimeline(), "Timeline should return each message in timeline on a new line");
	}

}

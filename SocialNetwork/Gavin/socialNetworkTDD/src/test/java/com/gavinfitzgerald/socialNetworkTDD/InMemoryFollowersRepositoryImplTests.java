package com.gavinfitzgerald.socialNetworkTDD;

import com.gavinfitzgerald.socialNetworkTDD.Repositories.IFollowersRepository;
import com.gavinfitzgerald.socialNetworkTDD.Repositories.InMemoryFollowersRepositoryImpl;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.ArrayList;
import java.util.List;

public class InMemoryFollowersRepositoryImplTests extends BaseTestClass {

    @Test
    public void testReturnSubscriptionListFromUser() {
        //Arrange
        IFollowersRepository followersRepository = new InMemoryFollowersRepositoryImpl();
        List<String> subscriptions = new ArrayList<String>() {{
            add(USER_TWO);
            add(USER_THREE);
        }};
        followersRepository.subscribe(subscriptions, USER_ONE);

        //Act
        List<String> actual = followersRepository.getSubscriptions(USER_ONE);

        //Assert
        assertEquals(USER_TWO, actual.get(0));
        assertEquals(USER_THREE, actual.get(1));
    }

    @Test
    public void testReturnSubscriptionListFromUserIsNull() {
        //Arrange
        IFollowersRepository followersRepository = new InMemoryFollowersRepositoryImpl();

        //Act
        List<String> actual = followersRepository.getSubscriptions(USER_TWO);

        //Assert
        assertNull(actual);
    }

    @Test
    public void testAddSubscriptionForUserThatAlreadyExists() {
        //Arrange
        IFollowersRepository followersRepository = new InMemoryFollowersRepositoryImpl();
        List<String> subscriptions = new ArrayList<String>() {{
            add(USER_TWO);
        }};
        followersRepository.subscribe(subscriptions, USER_ONE);
        subscriptions = new ArrayList<String>() {{
            add(USER_THREE);
        }};

        //Act
        followersRepository.subscribe(subscriptions, USER_ONE);

        //Assert
        List<String> actual = followersRepository.getSubscriptions(USER_ONE);
        assertEquals(USER_TWO, actual.get(0));
        assertEquals(USER_THREE, actual.get(1));
    }
}

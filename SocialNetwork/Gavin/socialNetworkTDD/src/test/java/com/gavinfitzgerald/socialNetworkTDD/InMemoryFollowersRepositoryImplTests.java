package com.gavinfitzgerald.socialNetworkTDD;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

public class InMemoryFollowersRepositoryImplTests extends BaseTestClass {

    @Test
    public void testReturnFollowerListFromUser(){
        //Arrange
        IFollowersRepository followersRepository = new InMemoryFollowersRepositoryImpl();
        List<String> subscriptions = new ArrayList<String>(){{ add(USER_TWO); add(USER_THREE);}};
        followersRepository.subscribe(subscriptions, USER_ONE);

        //Act
        List<String> actual = followersRepository.getSubscriptions(USER_ONE);

        //Assert
        assertEquals(USER_TWO, actual.get(0));
        assertEquals(USER_THREE, actual.get(1));
    }

}

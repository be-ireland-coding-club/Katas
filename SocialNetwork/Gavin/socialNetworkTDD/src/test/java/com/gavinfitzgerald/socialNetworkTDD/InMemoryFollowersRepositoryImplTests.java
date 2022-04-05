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
        List<String> followers = new ArrayList<String>(){{ add(USER_TWO); add(USER_THREE);}};
        followersRepository.follow(followers, USER_ONE);

        //Act
        List<String> actual = followersRepository.getFollowers(USER_ONE);

        //Assert
        assertEquals(actual.get(0), USER_TWO);
        assertEquals(actual.get(1), USER_THREE);
    }

}

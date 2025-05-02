package aiss.bitbucketminer.service;

import aiss.bitbucketminer.BitbucketminerApplication;
import aiss.bitbucketminer.model.gitMiner.Project;
import aiss.bitbucketminer.model.gitMiner.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = BitbucketminerApplication.class)
class UserServiceTest {

    @Autowired
    UserService userService;

    @Test
    @DisplayName("Get Current User")
    void getCurrentUser() {
        User user = userService.getCurrentUser();
        assertNotNull(user);
        System.out.println(user);
    }

    @Test
    @DisplayName("Get user by Id")
    void getUserById() {
        User user = userService.getUserById("Miguel Boix");
        assertNotNull(user);
        System.out.println(user);
    }
}
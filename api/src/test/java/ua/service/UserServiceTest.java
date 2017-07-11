package ua.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import ua.model.entity.User;
import ua.model.service.UserService;

/**
 * Created by andrii on 09.07.17.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @Test
    public void findUserByUsername() throws Exception {
//        registerUser();
        User user = userService.loadUserByUsername("aseverin@i.ua");
        assert (user != null);
        assert (user.getUsername().equals("aseverin@i.ua"));

    }

    @Test(expected = UsernameNotFoundException.class)
    public void findUnknownUser() throws Exception {
        userService.loadUserByUsername("Ivan");
    }


}

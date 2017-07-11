package ua.model.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import ua.model.entity.User;

/**
 * @version 1.0 09 Jul 2017
 * @author Andrii Severin
 */
public interface UserService extends UserDetailsService {
    User loadUserByUsername(String username);
}

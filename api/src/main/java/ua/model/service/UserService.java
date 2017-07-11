package ua.model.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import ua.model.entity.User;

/**
 * Created by andrii on 09.07.17.
 */
public interface UserService extends UserDetailsService {
    User loadUserByUsername(String username);
}

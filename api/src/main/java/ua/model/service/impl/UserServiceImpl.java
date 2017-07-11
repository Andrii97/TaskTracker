package ua.model.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ua.model.entity.User;
import ua.model.repository.UserRepository;
import ua.model.service.UserService;

import java.util.Optional;

/**
 * @author Andrii Severin
 */
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository repository;

    @Autowired
    public UserServiceImpl(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public User loadUserByUsername(String email) {
        Optional<User> user = this.repository.findByEmail(email);
        return user.orElseThrow(() -> new UsernameNotFoundException("No user"));
    }

}

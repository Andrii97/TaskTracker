package ua.controller.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import ua.model.repository.UserRepository;

@Component
public class SpringDataJpaUserDetailsService  implements UserDetailsService {

    private final UserRepository repository;

    @Autowired
    public SpringDataJpaUserDetailsService(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        ua.model.entity.User user = this.repository.findByEmail(email);
        String[] roles = new String[0];
        return new User(user.getEmail(), user.getPassword(),
                AuthorityUtils.createAuthorityList(roles));
    }

}

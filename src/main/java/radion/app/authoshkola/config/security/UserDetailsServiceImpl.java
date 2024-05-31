package radion.app.authoshkola.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import radion.app.authoshkola.model.users.User;
import radion.app.authoshkola.repositories.UserService;

import java.util.Optional;


@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> user = userService.findByEmail(email);
        return user.map(MyUserDetails::new).orElseThrow(
                () -> new UsernameNotFoundException(email + "not found!")
        );
    }

}

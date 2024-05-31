package radion.app.authoshkola.config.security;

import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import radion.app.authoshkola.model.users.User;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

@AllArgsConstructor
public class MyUserDetails implements UserDetails {

    private final User student;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Arrays.stream(new String[]{student.getRole()})
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return student.getPassword();
    }

    @Override
    public String getUsername() {
        return student.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}

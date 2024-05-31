package radion.app.authoshkola.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import radion.app.authoshkola.config.security.UserDetailsServiceImpl;
import radion.app.authoshkola.repositories.WeekService;

import javax.management.relation.RoleNotFoundException;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final WeekService weekService;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/student/profile/**").hasRole("STUDENT")
                        .requestMatchers("/instructor/profile/**", "/group/schedule/**").hasRole("INSTRUCTOR")
                        .requestMatchers("/instructor/list/**", "/student/group/**").hasAnyRole("STUDENT", "INSTRUCTOR", "ADMIN")
                        .requestMatchers("/admin/profile/**", "/student/create/**", "/student/all/**").hasRole("ADMIN")
                        .anyRequest().authenticated()
                )
                .formLogin(log -> {
                    log.permitAll();
                    log.successHandler(((request, response, authentication) -> {
                        String redirectUrl = authentication.getAuthorities().stream()
                                .map(GrantedAuthority::getAuthority)
                                .filter(authority -> authority.equals("ROLE_ADMIN") || authority.equals("ROLE_STUDENT") || authority.equals("ROLE_INSTRUCTOR"))
                                .findFirst()
                                .map(authority -> {
                                    if (authority.equals("ROLE_ADMIN")) {
                                        return "/admin/profile/" + weekService.findFirstWeek();
                                    } else if (authority.equals("ROLE_STUDENT")) {
                                        return "/student/profile/" + weekService.findFirstWeek();
                                    } else if(authority.equals("ROLE_INSTRUCTOR")){
                                        return "/instructor/profile/" + weekService.findFirstWeek();
                                    }else {
                                        try {
                                            throw new RoleNotFoundException("Роль не найдена: " + authority.toString());
                                        } catch (RoleNotFoundException e) {
                                            throw new RuntimeException(e);
                                        }
                                    }
                                })
                                .orElseThrow(() -> new IllegalStateException("Неизвестная роль"));

                        response.sendRedirect(redirectUrl);
                    }));
                })

                .build();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService());
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(12);
    }

    @Bean
    public UserDetailsService userDetailsService(){
        return new UserDetailsServiceImpl();
    }
}

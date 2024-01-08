package org.example.crud_system.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class UserConfig {

    @Bean
    protected SecurityFilterChain se(HttpSecurity http) throws Exception {

        http.
//                csrf(AbstractHttpConfigurer::disable).
        authorizeHttpRequests(authorizeRequests ->
        authorizeRequests
                .requestMatchers("/api/v1/user/**").hasRole("ADMIN")
//                        hasAnyRole("USER", "ADMIN")
                .requestMatchers("/api/v1/test/**").permitAll()
                .anyRequest().authenticated()).formLogin(s ->s.loginProcessingUrl("/api/v1/user/login")
                .successForwardUrl("/api/v1/user/**")
                .failureUrl("/api/v1/user/login-error").permitAll());
//                .sessionManagement(m ->m.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
        ;
        return http.build();
    }

    @Bean
    protected BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService(){
        var user = new InMemoryUserDetailsManager();

        UserDetails u1 = User.withUsername("Usopp")
                .password(passwordEncoder().encode("1234"))
                .roles("ADMIN").build();

        UserDetails u2 = User.withUsername("Zoro")
                .password(passwordEncoder().encode("1234"))
                .roles("USER").build();

        user.createUser(u1);
        user.createUser(u2);
        return user;
    }

}

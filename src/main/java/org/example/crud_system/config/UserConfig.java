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
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationProvider;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class UserConfig {

//    @Bean
//    protected SecurityFilterChain endpoints(HttpSecurity http) throws Exception {
//
//        http.
//                authorizeHttpRequests(authorizeRequests ->
//                authorizeRequests
//                .requestMatchers("/api/v1/user/**").hasRole("ADMIN")
////                        hasAnyRole("USER", "ADMIN")
//                .requestMatchers("/api/v1/test/**").permitAll()
//                .anyRequest().authenticated()).formLogin(s ->s.loginProcessingUrl("/api/v1/user/login")
//                .successForwardUrl("/api/v1/user/**")
//                .failureUrl("/api/v1/user/login-error").permitAll())
//                .logout(logout -> logout.logoutUrl("/api/v1/user/logout").logoutSuccessUrl("/api/v1/user/logout-success"))
////                .addFilterBefore(new JwtTokenFilter(), JwtTokenFilter.class)
//                .exceptionHandling(exceptionHandling -> exceptionHandling
//                        .accessDeniedPage("/api/v1/user/access-denied"))
//        ;
//        return http.build();
//    }


    @Bean
    protected SecurityFilterChain endpoints(HttpSecurity http) throws Exception {

        http.
                authorizeHttpRequests(authorizeRequests ->
                        authorizeRequests
                                .requestMatchers("/api/v1/user/**").hasRole("ADMIN")
                                .requestMatchers("/api/v1/test/**").permitAll()
                                .anyRequest().authenticated()).
                formLogin(s ->s.loginProcessingUrl("/api/v1/user/login").loginProcessingUrl("/api/v1/user/login")
                        .successForwardUrl("/api/v1/user/**")
                        .failureUrl("/api/v1/user/login-error").permitAll())
                .logout(logout -> logout.logoutUrl("/api/v1/user/logout").logoutSuccessUrl("/api/v1/user/logout-success"));
//                .addFilterBefore(new JwtTokenFilter(), JwtTokenFilter.class);




//                .authenticationProvider(new JwtAuthenticationProvider(jwtDecoder())).
//                oauth2ResourceServer(oauth2ResourceServer ->
//                        oauth2ResourceServer.jwt(jwt ->
//                                jwt.jwtAuthenticationConverter()))
                return http.build();
    }

    @Bean
    public JwtDecoder jwtDecoder() {

        String jwkSetUri = "https://www.googleapis.com/oauth2/v3/certs";
        return NimbusJwtDecoder.withJwkSetUri(jwkSetUri).build();
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

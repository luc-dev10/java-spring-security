package com.security.util;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        //        UserDetails user = User.builder()
        //                               .username("user")
        //                               .password("1234")
        //                               .roles("USER")
        //                               .build();
        //        UserDetails admin = User.builder()
        //                                .username("admin")
        //                                .password("1234")
        //                                .roles("USER", "ADMIN")
        //                                .build();

        //         add users in memory
        User.UserBuilder users = User.withDefaultPasswordEncoder();

        auth.inMemoryAuthentication()
            .withUser(users.username("user")
                           .password("1234")
                           .roles("USER"))
            .withUser(users.username("admin")
                           .password("1234")
                           .roles("ADMIN"));

    }
}


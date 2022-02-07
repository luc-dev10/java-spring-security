package com.security.util;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

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

    // Override default configuration in Spring
    @Override
    protected void configure(HttpSecurity http) throws Exception {

        //        http.authorizeHttpRequests()
        //            .anyRequest()
        //            .authenticated()
        //            .and()
        //            .formLogin()
        //            .loginPage("/login-")
        //            .loginProcessingUrl("/authenticateTheUser");

        http.authorizeRequests()
            .anyRequest()
            .authenticated()
            .and()
            .formLogin()
            .loginPage("/login")
            .loginProcessingUrl("/authenticateTheUser")
            .permitAll()
            .and()
            .logout()
            .permitAll();

    }

}


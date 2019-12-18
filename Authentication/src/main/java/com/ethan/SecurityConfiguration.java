package com.ethan;

import java.net.PasswordAuthentication;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private MyUserDetailsService myUserDetailsService;

    // @Autowired
    // DataSource dataSource;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(myUserDetailsService);

        // auth.jdbcAuthentication()
        //     .dataSource(dataSource);
        //     .usersByUsernameQuery("Select username, password, enables From users Where username = ?")
        //     .authoritiesByUsernameQuery("Select username, authorit From authorities Where username = ?")

        // auth.inMemoryAuthentication()
        //     .withUser("admin")
        //     .password("admin")
        //     .roles("ADMIN") 
        //     .and()
        //     .withUser("user")
        //     .password("pass")
        //     .roles("USER");

    }

    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return NoOpPasswordEncoder.getInstance();
        //return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
            .antMatchers("/authenticate").permitAll();
            // .and().formLogin()

            // .antMatchers("/**").hasAnyRole("ADMIN", "USER")
            // .antMatchers("/**").hasRole("ADMIN")
    }
}
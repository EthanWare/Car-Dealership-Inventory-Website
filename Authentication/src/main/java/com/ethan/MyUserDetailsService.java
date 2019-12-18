package com.ethan;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {
    @Autowired
    AuthenticationRepository repo;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        Optional<User> u = repo.findByUserName(userName);

        u.orElseThrow(() -> new UsernameNotFoundException("Not found:  + userName"));

        // return u.map(MyUserDetails::new).get();
        return new MyUserDetails(u.get());
    }
}
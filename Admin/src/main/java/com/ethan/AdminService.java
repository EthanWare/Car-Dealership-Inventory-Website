package com.ethan;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminService {
    @Autowired
    AdminRepository repo;


    /*
    public int authenticate(User u) {
        ArrayList<User> users = new ArrayList<>();
        repo.findAll().forEach(users::add);
        for(User user: users){
            if(user.getUserName().equals(u.getUserName())) {
                if(user.getPassword().equals(u.getPassword())) {
                    return user.getIsAdmin();
                }
            }
        }
        return -1;
    }
    */
}
package com.ethan;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
public class AuthenticationController {
    @Autowired
    AuthenticationService service;

    //returns 0 for user, 1 for admin, and -1 if neither of those
    @RequestMapping(value="/authenticate", method=RequestMethod.POST)
    public int authenticate(@RequestParam User user) {
        try{
            return service.authenticate(user);
        }
        catch(Exception e){
            System.out.print(e.getMessage());
            return -1;
        }
    }
}
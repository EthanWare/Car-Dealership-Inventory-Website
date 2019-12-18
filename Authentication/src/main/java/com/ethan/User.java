package com.ethan;

import javax.persistence.*;

@Entity
@Table(name = "user_table")
public class User{

    @Id
    @GeneratedValue
    private int id;
    private String userName;
    private String password;
    private boolean active;
    private String roles;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    // @Id
    // private int id;
    // private String userName;
    // private String password;
    // private int isAdmin;
    

    // public User(int id, String userName, String password, int isAdmin) {
    //     this.id = id;
    //     this.userName = userName;
    //     this.password = password;
    //     this.isAdmin = isAdmin;

    // }
    
    // public User() {
    // }


    // public String getUserName() {
    //     return userName;
    // }

    // public void setUserName(String userName) {
    //     this.userName = userName;
    // }

    // public String getPassword() {
    //     return password;
    // }

    // public void setPassword(String password) {
    //     this.password = password;
    // }

    // public int getIsAdmin() {
    //     return isAdmin;
    // }

    // public void setIsAdmin(int isAdmin) {
    //     this.isAdmin = isAdmin;
    // }
}
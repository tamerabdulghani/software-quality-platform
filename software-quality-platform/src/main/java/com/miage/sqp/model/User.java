/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.miage.sqp.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author kimphuong
 */
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int userID;

    private String username;
    private String password;
    private String firstname;
    private String lastname;
    private String email;
    private String phone;
  
    public User() 
    {
        this.username = "";
        this.password = "";
        this.firstname = "";
        this.lastname = "";
        this.email = "";
        this.phone = "";
    }
    
    public User(String username, String password) 
    {
        this.username = username;
        this.password = password;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.phone = phone;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }
      
    public String getUsername() {
    return username;
    }
    public void setUsername(String username) {
    this.username = username;
    }
    public String getPassword() {
    return password;
    }
    public void setPassword(String password) {
    this.password = password;
    }
    public String getFirstname() {
    return firstname;
    }
    public void setFirstname(String firstname) {
    this.firstname = firstname;
    }
    public String getLastname() {
    return lastname;
    }
    public void setLastname(String lastname) {
    this.lastname = lastname;
    }
    public String getEmail() {
    return email;
    }
    public void setEmail(String email) {
    this.email = email;
    }

    public String getPhone() {
    return phone;
    }
    public void setPhone(String phone) {
    this.phone = phone;
    } 
}

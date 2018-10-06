/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.miage.sqp.model;

import com.miage.sqp.model.User;

/**
 *
 * @author kimphuong
 */
public class Login {
    String UserName;
    String Password;

    public Login(String UserName, String Password) {
        this.UserName = UserName;
        this.Password = Password;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String UserName) {
        this.UserName = UserName;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String Password) {
        this.Password = Password;
    }
    
    // To be implement - Connect to SQL 
    public User Validate(Login log)
    {
        return new User();
    }
    
}

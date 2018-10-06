/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.miage.sqp;

import com.miage.sqp.model.User;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author kimphuong
 */
public class DAO {
    private static String url ="jdbc:mysql://localhost:3306/software_quality_platform";
    private static String login="root";
    private static String password="";
    private Connection con;
    
    public DAO(){
        
        try {
            con = (Connection) DriverManager.getConnection(url,login,password);
            System.out.println("Connecton successful");
        } catch (SQLException ex) {
            System.out.println("Connection not possible!");
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        }   
    }
    
    public Connection GetConnection()
    {
        return this.con;
    }
    
    public void setConnection(Connection connection) {
        this.con = connection;
    }
    
    // To be implement 
    public User isUserExist()
    {
        return new User();
    }
}

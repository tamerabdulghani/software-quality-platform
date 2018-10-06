/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.miage.sqp.Controller;

import com.miage.sqp.model.Login;
import com.miage.sqp.model.User;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author kimphuong
 */
@Controller
public class LoginController {
    
    @RequestMapping("/authenticate")
    public String authenticate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
    {
        boolean authenticate = request.authenticate(response);
        return authenticate ? "index" : null;
    }
    
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView showLogin(HttpServletRequest request, HttpServletResponse response) 
    {
        ModelAndView mav = new ModelAndView("login");
        mav.addObject("login", new User());
        return mav;
    }
  
    @RequestMapping(value = "/loginProcess", method = RequestMethod.POST)
    public ModelAndView loginProcess(HttpServletRequest request, HttpServletResponse response,
    @ModelAttribute("login") Login login) 
    {
      ModelAndView mav = null;
      User user;
      user = login.Validate(login);

      if (null != user)
      {
          mav = new ModelAndView("welcome");
          mav.addObject("firstname", user.getFirstname());
      } else 
      {
          mav = new ModelAndView("login");
          mav.addObject("message", "Username or Password is wrong!!");
      }
      return mav;
    }

    @RequestMapping("/logout")
    public String logout(HttpServletRequest request) throws ServletException 
    {
    	request.logout();
        return "redirect:/";
    }
}

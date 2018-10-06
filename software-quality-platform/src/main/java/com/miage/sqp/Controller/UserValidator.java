/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.miage.sqp.Controller;

import com.miage.sqp.model.User;
import java.util.regex.Pattern;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 *
 * @author kimphuong
 */
public class UserValidator implements Validator {
   @Override
   public boolean supports(Class<?> clazz) {
      return User.class.equals(clazz);
   }

   @Override
   public void validate(Object obj, Errors err) {

      ValidationUtils.rejectIfEmpty(err, "name", "user.name.empty");
      ValidationUtils.rejectIfEmpty(err, "email", "user.email.empty");

      User user = (User) obj;

      // Validate email format
      Pattern pattern = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$",
            Pattern.CASE_INSENSITIVE);
      if (!(pattern.matcher(user.getEmail()).matches())) {
         err.rejectValue("email", "user.email.invalid");
      }
   }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.miage.controllers.rest;

import com.miage.models.User;
import com.miage.repositories.PointRepository;
import com.miage.repositories.UserRepository;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Mikhail
 */
@RestController
@RequestMapping("/leaderBoard")
public class PointsRestController {

    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private PointRepository pointRepository;
    
    @GetMapping("/getUserPoints")
    public List<UserPoints> getInfoUserPoints() {
        List<UserPoints> userPoints = new ArrayList<>();
        pointRepository.findAll()
                .forEach((p)->
                {
                    int userId=p.getUserId();
                    User user = userRepository.findById(userId).get();
                    userPoints.add( new UserPoints(userId, user.getUsername(),p.getValue()));
                    Collections.sort(userPoints, (u1, u2) -> {                        
                        if (u1.point < u2.point) {
                            return 1;
                        } else {
                            return -1;
                        }
                    }
                    );
                });
        return userPoints;
    }

    class UserPoints{
    private int id;
    private String Username;
    private int point;

        public UserPoints(int id, String Username, int point) {
            this.id = id;
            this.Username = Username;
            this.point = point;
        }

        public int getId() {
            return id;
        }

        public String getUsername() {
            return Username;
        }

        public int getPoint() {
            return point;
        }

        public void setId(int id) {
            this.id = id;
        }

        public void setUsername(String Username) {
            this.Username = Username;
        }

        public void setPoint(int point) {
            this.point = point;
        }
    
    
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.miage.controllers.rest;

import com.miage.models.Annotation;
import com.miage.models.File;
import com.miage.models.Point;
import com.miage.models.User;
import com.miage.repositories.AnnotationRepository;
import com.miage.repositories.FileRepository;
import com.miage.repositories.PointRepository;
import com.miage.repositories.UserRepository;
import com.miage.services.LeaderBoardService;
import java.util.AbstractMap;
import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
    
    @Autowired
    private FileRepository fileRepository;
    
    @Autowired
    private AnnotationRepository annotationRepository;
    
    @Autowired
    private LeaderBoardService leaderBoardService;
    
    @GetMapping("/getUserInfo/{id}")
    public List<UserInfo> getUserInfo(@PathVariable Integer id) {
        List<UserInfo> userInfo = new ArrayList<>();

        User selectedUser = leaderBoardService.getAllUsers()
                .stream()
                .filter(user -> user.getId() == id)
                .findFirst()
                .get();
        Point point = leaderBoardService.getUserPoints(selectedUser);
        
        System.out.println(point);
        int pointVal= point != null ? point.getValue() : 0;
        boolean isHappy = leaderBoardService.isHappy(selectedUser);
        List<String> badges = leaderBoardService.getAllBadges(selectedUser);

        if (badges.size() != 0) {
            badges.forEach(b -> userInfo.add(new UserInfo(b, selectedUser, pointVal, isHappy)));
        } else {
            userInfo.add(new UserInfo(null, selectedUser, pointVal, isHappy));
        }

        return userInfo;
    }
    
    @GetMapping("/getLeaderBoard")
    public List<UserPoints> getLeaderBoard() {
        List<UserPoints> userPoints = new ArrayList<>();        
        
        leaderBoardService.getAllUsers()
                .forEach(user->{
                    System.out.println(user.getId());
                    Point point = leaderBoardService.getUserPoints(user);
                    int pointVal= point != null ? point.getValue() : 0;
                    boolean isHappy = leaderBoardService.isHappy(user);
                    userPoints.add(new UserPoints(user, pointVal, isHappy));
                });                                    
        return userPoints;
    }
    
    @GetMapping("/getAllUsersWithBadges")
    public List<UserBadge> getAllUsersWithBadges() {
        List<UserBadge> usersWithBages = new ArrayList<>();
        
        leaderBoardService.getAllUsers()
                .forEach(user -> 
                {
                    leaderBoardService.getAllBadges(user)
                            .forEach( badge -> usersWithBages.add(new UserBadge(user, badge)));
                });
        
        return usersWithBages;
    }
        
    public class UserBadge{
        private User user;
        private String badge;

        public UserBadge(User user, String badge) {
            this.user = user;
            this.badge = badge;
        }

        public User getUser() {
            return user;
        }

        public String getBadge() {
            return badge;
        }

        public void setUser(User user) {
            this.user = user;
        }

        public void setBadge(String badge) {
            this.badge = badge;
        }                
    }
    
    public class UserPoints {
        private User user;
        private Integer points;
        private boolean isHappy;

        public UserPoints(User user, Integer points, boolean isHappy) {
            this.user = user;
            this.points = points;
            this.isHappy = isHappy;
        }

        public User getUser() {
            return user;
        }

        public Integer getPoints() {
            return points;
        }

        public boolean isIsHappy() {
            return isHappy;
        }

        public void setUser(User user) {
            this.user = user;
        }

        public void setPoints(Integer points) {
            this.points = points;
        }

        public void setIsHappy(boolean isHappy) {
            this.isHappy = isHappy;
        }                
    }
    
    public class UserInfo extends UserPoints{
        private String badge;

        public UserInfo(String badge, User user, Integer points, boolean isHappy) {
            super(user, points, isHappy);
            this.badge = badge;
        }

        public String getBadge() {
            return badge;
        }

        public void setBadge(String badge) {
            this.badge = badge;
        }
        
        
        
        
        
    }
}

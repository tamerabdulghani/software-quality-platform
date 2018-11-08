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
    
    
    @GetMapping("/getUserPoints")
    public List<UserPoints> getInfoUserPoints() {
        List<UserPoints> userPoints = new ArrayList<>();        
        int allUserPoints = getAllUserPoints();
        int numOfUsers = userRepository.findAll().size();
             
        
        User userWithHighestPoints= getUserWithHighestPoint();
        User userWithHighestNumberOfUploads=getUserWithHighestNumberOfUploads();
        User userWithHighestNumberOfReviews=getUserWithHighestNumberOfReviews();
        User userWithHighestNumberOfFilesWithoutAnnotations=getUserWithHighestNumberOfFilesWithoutAnnotations();                
        
        Hashtable<User, String> badges = new Hashtable<>();
        badges.put(userWithHighestPoints, "Super Star");
        badges.put(userWithHighestNumberOfUploads, "Best Coder");
        badges.put(userWithHighestNumberOfReviews, "Master Reviewer");
        badges.put(userWithHighestNumberOfFilesWithoutAnnotations, "Master Coder");
                
        pointRepository.findAll()
                .forEach((Point p)->
                {
                    int userId=p.getUserId();
                    User user = userRepository.findById(userId).get();
                    boolean happy = (p.getValue() > allUserPoints/numOfUsers);                                        
                    String badge = badges.get(user);                                                            
                    userPoints.add( new UserPoints(userId, user.getUsername(),
                            p.getValue(), badge, happy));
                    Collections.sort(userPoints, (u1, u2) -> u1.point<u2.point ? 1 : -1);
                });
        return userPoints;
    }
    
    private Integer getAllUserPoints(){
        Iterable<Point> points =  pointRepository.findAll();
        Stream<Point> pointsStream = StreamSupport.stream(points.spliterator(), false);  
        return pointsStream.mapToInt(p->p.getValue()).sum();
    }
    
    private User getUserWithHighestPoint(){
        Iterable<Point> points =  pointRepository.findAll();
        Stream<Point> pointsStream = StreamSupport.stream(points.spliterator(), false);  
        User userWithHighestPoints= userRepository.findById( 
                pointsStream.max(Comparator.comparing(Point::getValue))
                .orElseThrow(NoSuchElementException::new)
                .getUserId()
        ).get();
        return userWithHighestPoints;
    }
    
    private User getUserWithHighestNumberOfUploads(){
        Iterable<File> files = fileRepository.findAll();
        Stream<File> filesStream = StreamSupport.stream(files.spliterator(), false);
        Map<User, Long> groupedFiles = filesStream.collect(
                Collectors.groupingBy(File::getUser, Collectors.counting())
        );
        User userWithHighestNumberOfUploads = groupedFiles.entrySet().stream()
                .max((entry1, entry2) -> entry1.getValue() < entry2.getValue() ? 1 : -1)
                .get().getKey();
        return userWithHighestNumberOfUploads;
    }

    private User getUserWithHighestNumberOfReviews() {
        Iterable<Annotation> annotations = annotationRepository.findAll();
        Stream<Annotation> annotationsStream = StreamSupport.stream(annotations.spliterator(), false);
        Map<User, Long> grouppedAnnotations = annotationsStream.collect(
                Collectors.groupingBy(Annotation::getUser, Collectors.counting())
        );
        User UserWithHighestNumberOfReviews = grouppedAnnotations.entrySet().stream()
                .max((entry1, entry2) -> entry1.getValue() < entry2.getValue() ? 1 : -1)
                .get().getKey();
        return UserWithHighestNumberOfReviews;
    }

    private User getUserWithHighestNumberOfFilesWithoutAnnotations() {
        Iterable<File> files = fileRepository.findAll();
        Stream<File> filesStream = StreamSupport.stream(files.spliterator(), false);
        
        Iterable<Annotation> annotations = annotationRepository.findAll();
        Stream<Annotation> annotationsStream = StreamSupport.stream(annotations.spliterator(), false);
        List<Annotation> annotationsList = annotationsStream.collect(Collectors.toList());
        
        List<File> filesWithoutAnnotations = filesStream.filter(f -> !annotationsList.contains(f))
                .collect(Collectors.toList());
        
        Map<User,Long> grouppedFilesWithoutAnnotations = filesWithoutAnnotations
                .stream()
                .collect(
                        Collectors.groupingBy(File::getUser,Collectors.counting())
                );
        
        User userWithHighestNumberOfFilesWithoutAnnotations = grouppedFilesWithoutAnnotations
                .entrySet()
                .stream()
                .max((entry1, entry2) -> entry1.getValue() < entry2.getValue() ? 1 : -1)
                .get()
                .getKey();
        
        return userWithHighestNumberOfFilesWithoutAnnotations;
    }

    class UserPoints {

        private int id;       
        private String Username;
        private int point;
        private boolean happy;
        private String badge;

        public UserPoints(int id, String Username, int point, String badge, boolean happy) {
            this.id = id;
            this.Username = Username;
            this.point = point;
            this.badge=badge;
            this.happy=happy;
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
        
        public boolean isHappy() {
            return happy;
        }

        public String getBadge() {
            return badge;
        }

        public void setHappy(boolean happy) {
            this.happy = happy;
        }

        public void setBadge(String badge) {
            this.badge = badge;
        }
        
    }
}

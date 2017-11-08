package com.Temple.NutriBuddi.UserManagement.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;

@Entity // This tells Hibernate to make a table out of this class
@Table(name = "user_goal")
public class UserGoal {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int id;

    private String goal;

    @JsonManagedReference
    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

    public UserGoal(){}

    public UserGoal(String goal, User user){
        this.goal = goal;
        this.user = user;
    }

    //    private double weightChange;

    //    private double nutritionGoal;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGoal() {
        return goal;
    }

    public void setGoal(String goal) {
        this.goal = goal;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}

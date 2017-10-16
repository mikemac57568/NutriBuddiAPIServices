package com.Temple.NutriBuddi.UserManagement.model;

import javax.persistence.*;

@Entity // This tells Hibernate to make a table out of this class
@Table(name = "user_goal")
public class UserGoal {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int id;

    private String goal;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

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

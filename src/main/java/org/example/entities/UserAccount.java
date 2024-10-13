package org.example.entities;

import java.util.List;

public class UserAccount {
    String login;

    String name;

    String password;

    List<Habit>  userHabits;

    Integer id;

    Boolean isActive;

    public UserAccount(String login, String name, String password) {
        this.login = login;
        this.name = name;
        this.password = password;
        this.isActive = true;
    }

    public UserAccount(String login, String name, String password, List<Habit> userHabits, Integer id, Boolean isActive) {
        this.login = login;
        this.name = name;
        this.password = password;
        this.userHabits = userHabits;
        this.id = id;
        this.isActive = isActive;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Habit> getUserHabits() {
        return userHabits;
    }

    public void setUserHabits(List<Habit> habitNames) {
        this.userHabits = habitNames;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }




}
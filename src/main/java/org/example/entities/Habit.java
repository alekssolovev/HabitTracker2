package org.example.entities;

import java.util.Date;
import java.util.UUID;

public class Habit {

    String name;

    String description;

    Date startDate;

    Integer frequencyPerDay;

    Integer frequencyPerWeek;

    Boolean status;

    Integer streak; //отображает серию выполнения привычки(дни подряд)


    UUID uuid = UUID.randomUUID();

    public Habit(String name, String description, Date startDate, Integer frequencyPerDay, Integer frequencyPerWeek, Boolean status) {
        this.name = name + uuid; //для соблюдения уникальности названия(костыль)
        this.description = description;
        this.startDate = startDate;
        this.streak = 0;
        this.frequencyPerDay = 0;
        this.frequencyPerWeek = 0;
    }

    public Habit(String name, String description, Date startDate, Integer frequencyPerDay, Integer frequencyPerWeek) {
        this.name = name;
        this.description = description;
        this.startDate = startDate;
        this.frequencyPerDay = frequencyPerDay;
        this.frequencyPerWeek = frequencyPerWeek;
    }

    public Habit() {
        name = "NoNameHabit";
    }

    public Habit(String running, int i) {

    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Integer getFrequencyPerDay() {
        return frequencyPerDay;
    }

    public Integer getFrequencyPerWeek() {
        return frequencyPerWeek;
    }

    public Integer getStreak(){
        return streak;
    }


    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public void setFrequencyPerDay(Integer frequencyPerDay) {//исправить на perWeek и perDay
        this.frequencyPerDay = frequencyPerDay;
    }

    public void setFrequencyPerWeek(Integer frequencyPerWeek) {
        this.frequencyPerWeek = frequencyPerWeek;
    }

    public void setStreak(Integer newStreak){
        this.streak = newStreak;
    }

}
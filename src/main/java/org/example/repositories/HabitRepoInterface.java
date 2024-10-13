package org.example.repositories;

import org.example.entities.Habit;

import java.util.Map;

public interface HabitRepoInterface {

   public Habit getHabitByName(String name);

    public void  deleteHabitByName(String name);

    public Map<String, Habit> createHabit(Habit habit);

    public Habit changeDescriptionByName(String name, String description);

    public Habit changeFrequencyPerDayByName(String name, Integer frequencyPerDay);

    public  Habit changeFrequencyPerWeekByName(String name, Integer frequencyPerWeek);

    public Habit changeStreakByName(String name, Integer streak);

    public Habit changeStatusByName(String name, Boolean status);


}
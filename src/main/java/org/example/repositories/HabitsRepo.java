package org.example.repositories;

import org.example.entities.Habit;

import java.util.HashMap;
import java.util.Map;

public class HabitsRepo implements  HabitRepoInterface {
    Habit habit;

    Map<String,Habit> habitMap;

    public HabitsRepo(Habit habit) {
        this.habit = habit;
        habitMap = new HashMap<>();
    }



    @Override
    public Habit getHabitByName(String name) {
        return habitMap.get(name);
    }

    @Override
    public void deleteHabitByName(String name) {
        habitMap.remove(name);
    }

    @Override
    public Map<String, Habit> createHabit(Habit habit) {
        String name = habit.getName();
        habitMap.put(name, habit);
        return habitMap;
    }

    @Override
    public Habit changeDescriptionByName(String name, String description) {
        Habit changeHabit = getHabitByName(name);
        changeHabit.setDescription(description);
        return changeHabit;
    }

    @Override
    public Habit changeFrequencyPerDayByName(String name, Integer frequencyPerDay) {
        Habit changeHabit = getHabitByName(name);
        changeHabit.setFrequencyPerDay(frequencyPerDay);
        return changeHabit;
    }

    @Override
    public Habit changeFrequencyPerWeekByName(String name, Integer frequencyPerWeek) {
        Habit changeHabit = getHabitByName(name);
        changeHabit.setFrequencyPerWeek(frequencyPerWeek);
        return changeHabit;
    }

    @Override
    public Habit changeStreakByName(String name, Integer streak) {
        Habit changeHabit = getHabitByName(name);
        Integer newStreak = changeHabit.getStreak();
        newStreak+=1;
        changeHabit.setStreak(newStreak);
        return changeHabit;
    }

    @Override
    public Habit changeStatusByName(String name, Boolean status) {
        Habit changeHabit = getHabitByName(name);
        changeHabit.setStatus(status);
        return changeHabit;
    }
}
package org.example.service;

import org.example.entities.Habit;
import org.example.entities.UserAccount;
import org.example.in.Reader;
import org.example.repositories.HabitsRepo;
import org.example.repositories.UserAccountRepo;

import java.util.List;

public class HabitsAnalyze {
    HabitsRepo habitsRepo;
    UserAccountRepo userAccountRepo;
    Reader reader;
    String login;

    public HabitsAnalyze( HabitsRepo habitsRepo, UserAccountRepo userAccountRepo, Reader reader,String login) {
        this.habitsRepo = habitsRepo;
        this.userAccountRepo = userAccountRepo;
        this.reader = reader;

    }

    public void checkCountStreak(){
        UserAccount userAccount = userAccountRepo.getUserAccountByLogin(login);
        List<Habit> habits = userAccount.getUserHabits();
        habits.stream()
                .forEach(habit -> System.out.println("Привычка: " + habit.getName() + ", Streak: " + habit.getStreak()));
    }

    public void percentDoneHabits(){
        System.out.println("введите количество дней до текущей даты для отображения процента"+
                "успешно выполненных привычек за этот период ");
        reader.readInput();
        Integer userInput = Integer.parseInt(reader.getUserInput());

        UserAccount userAccount = userAccountRepo.getUserAccountByLogin(login);
        List<Habit> habits = userAccount.getUserHabits();

        // Подсчитываем количество привычек, где streak >= userInput
        long count = habits.stream()
                .filter(habit -> habit.getStreak() >= userInput)
                .count();

        // Вычисляем процент таких привычек от общего количества привычек
        double percentage = (double) count / habits.size() * 100;

        // Вывод результата
        System.out.println ("Процент успешно выполненных привычек: " + percentage + "%");
    }

    }
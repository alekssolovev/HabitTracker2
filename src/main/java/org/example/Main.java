package org.example;

import org.example.entities.Habit;
import org.example.entities.UserAccount;
import org.example.in.InterpreterAuth;
import org.example.in.InterpreterHabits;
import org.example.in.InterpreterInt;
import org.example.in.Reader;
import org.example.out.AdminPanel;
import org.example.out.DisplayClass;
import org.example.repositories.HabitsRepo;
import org.example.repositories.UserAccountRepo;
import org.example.service.HabitsAnalyze;
import org.example.service.HabitsManage;

public class Main {
    public static void main(String[] args) {
        Reader reader = new Reader();
        UserAccount userAccount1 = new UserAccount("abs@gmail.com","abs", "passw1");
        UserAccount userAccount2 = new UserAccount("absd@gmail.com","absd", "passw2");
        UserAccountRepo userAccountRepo = new UserAccountRepo();
        userAccountRepo.createUserAccount(userAccount1);
        userAccountRepo.createUserAccount(userAccount2);


        AdminPanel adminPanel = new AdminPanel(reader,userAccountRepo);
        Habit habit1 = new Habit();
        HabitsRepo habitsRepo = new HabitsRepo(habit1);
        reader.readInput();
        String login = reader.getUserInput();
        HabitsManage habitsManage = new HabitsManage(habitsRepo,userAccountRepo,reader,login);
        HabitsAnalyze habitsAnalyze = new HabitsAnalyze(habitsRepo,userAccountRepo,reader,login);
        InterpreterHabits interpreterHabits = new InterpreterHabits(habitsManage,reader);


        DisplayClass.displayMenu();
        InterpreterInt interpreter = new InterpreterAuth(userAccountRepo,reader,adminPanel,login,habitsAnalyze,interpreterHabits);
        reader.readInput();
        String input = reader.getUserInput();
        DisplayClass.displayMenu();
        interpreter.interpret(input);



    }
}
package org.example.in;

import org.example.entities.UserAccount;
import org.example.out.AdminPanel;
import org.example.repositories.HabitsRepo;
import org.example.repositories.UserAccountRepo;
import org.example.service.HabitsAnalyze;

public class InterpreterAuth implements InterpreterInt {

    Reader reader;
    UserAccountRepo userAccountRepo;
    AdminPanel adminPanel;
    InterpreterHabits interpreterHabits;
    String login;
    HabitsAnalyze habitsAnalyze;

    public InterpreterAuth(UserAccountRepo userAccountRepo, Reader reader,AdminPanel adminPanel, String login, HabitsAnalyze habitsAnalyze, InterpreterHabits interpreterHabits) {
        this.userAccountRepo = userAccountRepo;
        this.reader = reader;
        this.adminPanel = adminPanel;
        this.login = login;
        this.habitsAnalyze = habitsAnalyze;
        this.interpreterHabits = interpreterHabits;
    }

    public String getLogin() {
        return login;
    }

    @Override
    public void interpret(String command) {
        Reader reader = new Reader();
        if (command.equals("auth")) {
            System.out.println("Введите логин");
            reader.readInput();
            login = reader.getUserInput();
            UserAccount authUserAccount = userAccountRepo.getUserAccountByLogin(login);
            if (authUserAccount == null || !authUserAccount.getActive()) {
                System.out.println("Неверный или неактивный логин");
                System.exit(0);
            }
            System.out.println("Введите пароль");
            reader.readInput();
            String password = reader.getUserInput();


            String authPassword = authUserAccount.getPassword();
            if (authPassword.equals(password)) {
                System.out.println("Аутентификация прошла успешно");
            } else {
                System.out.println("Неверный  пароль");
            }
            System.out.println("Введите man для управления привычками и stat для аналитики");
            reader.readInput();
            String input = reader.getUserInput();
            if (input.equals("man")) {
                reader.readInput();
                command = reader.getUserInput();
                interpreterHabits.interpret(command);
            } else if (input.equals("stat")) {
                System.out.println("Введите % для отображение процента успешно выполненных привычек или \n" +
                        " check для проверки streak всех привычек");
                reader.readInput();
                String userInput = reader.getUserInput();
                if (userInput.equals("check")) {
                    habitsAnalyze.checkCountStreak();
                } else if (userInput.equals("%")) {
                    habitsAnalyze.percentDoneHabits();
                } else
                    System.out.println("неверный ввод");

            }

        } else if (command.equals("reg")) {
            System.out.println("Введите логин");
            reader.readInput();
            String newLogin = reader.getUserInput();
            System.out.println("Введите пароль");
            reader.readInput();
            String newPassword = reader.getUserInput();
            System.out.println("Введите имя");
            reader.readInput();
            String newName = reader.getUserInput();
            UserAccount userAccount = new UserAccount(newLogin, newName, newPassword);
            userAccountRepo.createUserAccount(userAccount);
            System.out.println("Введите man для управления привычками");
            reader.readInput();
            command = reader.getUserInput();
            if (command.equals("man") && login != null) {
                reader.readInput();
                command = reader.getUserInput();
                interpreterHabits.interpret(command);
            } else if (command.equals("sec@adm")) {
                AdminPanel.consoleAdmPanel(userAccountRepo, reader);

            }
            else
                System.out.println("неверный ввод");

        }
    }
}
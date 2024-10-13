package org.example.out;

import org.example.entities.UserAccount;
import org.example.in.Reader;
import org.example.repositories.HabitsRepo;
import org.example.repositories.UserAccountRepo;

public class AdminPanel {
    UserAccountRepo userAccountRepo;

    Reader reader;



    public AdminPanel(Reader reader, UserAccountRepo userAccountRepo) {
        this.reader = reader;
        this.userAccountRepo = userAccountRepo;
    }

    public  static void consoleAdmPanel(UserAccountRepo userAccountRepo, Reader reader) {
        for (String key : userAccountRepo.getUserAccounts().keySet()) {
            System.out.println("Логин: " + key + ", Имя: " + userAccountRepo.getUserAccounts().get(key).getName());}
        System.out.println("ВВедите логин юзера");
        reader.readInput();
        String userLogin = reader.getUserInput();
        System.out.println("выбран логин " + userLogin);
        System.out.println("Введите del для удаления или off для отключения учетной записи или exit для выхода");
        reader.readInput();
        String input = reader.getUserInput();
        if(input.equals("del")) {
            userAccountRepo.deleteUserAccountByLogin(userLogin);
        }
        else  if(input.equals("off")) {
            userAccountRepo.changeUserAccountStatusByLogin(userLogin,false);
        }
        else if (input.equals("exit")) {
            System.exit(0);
        }

    }

}
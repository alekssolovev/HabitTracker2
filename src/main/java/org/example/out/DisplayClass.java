package org.example.out;

import org.example.in.Reader;
import org.example.repositories.UserAccountRepo;

import java.util.Date;

public class DisplayClass {
    Date date;
    Reader reader;

    AdminPanel authUser;

    UserAccountRepo userAccountRepo;

    public DisplayClass(Date date) {
        this.date = date;
    }

    public static void displayMenu(){
        System.out.println("Выберите нужный вариант или admin ключ");
        System.out.println("авторизация (введите auth)" );
        System.out.println("регистрация(введите reg)\n");

    }



}
package org.example.in;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Reader {
    // Поле для хранения строки
    private String userInput;

    // Метод для чтения строки от пользователя
    public void readInput() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите строку: ");
        userInput = scanner.nextLine();  // Считывание строки с клавиатуры
    }

    // Метод для получения сохраненной строки
    public String getUserInput() {
        while (userInput == null) {
            System.out.println("Повторите ввод");
        }
        return userInput;
    }




}
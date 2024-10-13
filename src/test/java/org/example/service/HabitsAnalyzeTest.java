package org.example.service;

import org.example.entities.Habit;
import org.example.entities.UserAccount;
import org.example.repositories.HabitsRepo;
import org.example.repositories.UserAccountRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.example.in.Reader;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class HabitsAnalyzeTest {

    private HabitsRepo habitsRepo;
    private UserAccountRepo userAccountRepo;
    private Reader reader;
    private HabitsAnalyze habitsAnalyze;
    private String login = "testUser";

    @BeforeEach
    public void setUp() {
        habitsRepo = mock(HabitsRepo.class);
        userAccountRepo = mock(UserAccountRepo.class);
        reader = mock(Reader.class);
        habitsAnalyze = new HabitsAnalyze(habitsRepo, userAccountRepo, reader, login);
    }

    @Test
    public void testCheckCountStreak() {
        // Создаем mock UserAccount и List<Habit>
        UserAccount mockUserAccount = mock(UserAccount.class);
        List<Habit> mockHabits = Arrays.asList(
                new Habit("Running", 5),
                new Habit("Reading", 3)
        );

        // Настраиваем поведение mock объектов
        when(userAccountRepo.getUserAccountByLogin(login)).thenReturn(mockUserAccount);
        when(mockUserAccount.getUserHabits()).thenReturn(mockHabits);

        // Перехват System.out
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        // Вызываем метод
        habitsAnalyze.checkCountStreak();

        // Проверяем вывод
        String expectedOutput = "Привычка: Running, Streak: 5\n" +
                                "Привычка: Reading, Streak: 3\n";
        assertEquals(expectedOutput, outContent.toString());

        // Восстанавливаем System.out
        System.setOut(System.out);
    }

    @Test
    public void testPercentDoneHabits() {
        // Создаем mock UserAccount и List<Habit>
        UserAccount mockUserAccount = mock(UserAccount.class);
        List<Habit> mockHabits = Arrays.asList(
                new Habit("Running", 5),
                new Habit("Reading", 3),
                new Habit("Meditation", 7)
        );

        // Настраиваем поведение mock объектов
        when(userAccountRepo.getUserAccountByLogin(login)).thenReturn(mockUserAccount);
        when(mockUserAccount.getUserHabits()).thenReturn(mockHabits);

        // Настраиваем mock для reader, который возвращает значение, введенное пользователем
        when(reader.toString()).thenReturn("5");

        // Перехват System.out
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        // Вызываем метод
        habitsAnalyze.percentDoneHabits();

        // Проверяем вывод
        String expectedOutput = "Введите количество дней до текущей даты для отображения процента успешных привычек за этот период\n" +
                                "Процент успешно выполненных привычек: 66.66666666666666%\n";
        assertEquals(expectedOutput, outContent.toString());

        // Восстанавливаем System.out
        System.setOut(System.out);
    }
}
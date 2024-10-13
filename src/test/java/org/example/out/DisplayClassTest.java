package org.example.out;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class DisplayClassTest {

    private Date mockDate;
    private DisplayClass displayClass;

    @BeforeEach
    public void setUp() {
        mockDate = mock(Date.class);  // Замокировать объект Date
        displayClass = new DisplayClass(mockDate);  // Передать mock Date в конструктор
    }

    @Test
    public void testConstructorInitialization() {
        // Проверяем, что поле date инициализировано правильно
        assertEquals(mockDate, displayClass.date);
    }

    @Test
    public void testDisplayMenuOutput() {
        // Перехватываем System.out для проверки вывода
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        // Вызываем метод, который выводит меню
        DisplayClass.displayMenu();

        // Проверяем, что вывод совпадает с ожидаемым
        String expectedOutput = "Выберите нужный вариант или admin ключ\n" +
                                "авторизация (введите auth)\n" +
                                "регистрация(введите reg)\n\n";

        assertEquals(expectedOutput, outContent.toString());

        // Восстанавливаем System.out
        System.setOut(System.out);
    }
}
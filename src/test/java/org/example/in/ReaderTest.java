package org.example.in;

import java.util.Scanner;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class ReaderTest {

    private Reader inputReader;
    private Scanner scanner;

    @BeforeEach
    public void setUp() {
        inputReader = new Reader();
        scanner = mock(Scanner.class);  // Создаем mock объекта Scanner
    }

    @Test
    public void testReadInput() {
        // Устанавливаем поведение mock объекта: когда вызывается nextLine(), вернуть "Test String"
        when(scanner.nextLine()).thenReturn("Test String");

        // Подменяем реальный ввод пользователя вызовом mock
        inputReader.readInput();  // Мы хотим протестировать этот метод


        assertEquals("Test String", inputReader.getUserInput());
    }
}
package org.example.in;

import org.example.service.HabitsManage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import static org.mockito.Mockito.*;

class InterpreterHabitsTest {

    private InterpreterHabits interpreter;
    private HabitsManage habitsManage;
    private Reader reader;

    @BeforeEach
    public void setUp() {
        habitsManage = mock(HabitsManage.class); // Создаем мок для HabitsManage
        reader = mock(Reader.class); // Создаем мок для Reader
        interpreter = new InterpreterHabits(habitsManage, reader);
    }

    @Test
    public void testInterpretAddCommand() {
        // Устанавливаем, что будет возвращать getLogin()
        when(habitsManage.getLogin()).thenReturn("testUser");

        // Интерпретируем команду "add"
        interpreter.interpret("add");

        // Проверяем, что метод addHabit был вызван
        verify(habitsManage, times(1)).addHabit("testUser");
    }

    @Test
    public void testInterpretChangeCommandSet() {
        when(habitsManage.getLogin()).thenReturn("testUser");

        // Устанавливаем поведение для reader
        when(reader.getUserInput()).thenReturn("set");

        // Интерпретируем команду "ch"
        interpreter.interpret("ch");

        // Проверяем, что метод changeHabit был вызван
        verify(habitsManage, times(1)).changeHabit();
    }

    @Test
    public void testInterpretChangeCommandDel() {
        when(habitsManage.getLogin()).thenReturn("testUser");

        // Устанавливаем поведение для reader
        when(reader.getUserInput()).thenReturn("del");

        // Интерпретируем команду "ch"
        interpreter.interpret("ch");

        // Проверяем, что метод delHabit был вызван
        verify(habitsManage, times(1)).delHabit();
    }

    @Test
    public void testInterpretChangeCommandFil() {
        when(habitsManage.getLogin()).thenReturn("testUser");

        // Устанавливаем поведение для reader
        when(reader.getUserInput()).thenReturn("fil");

        // Интерпретируем команду "ch"
        interpreter.interpret("ch");

        // Проверяем, что метод filterHabits был вызван
        verify(habitsManage, times(1)).filterHabits("testUser");
    }

    @Test
    public void testInterpretChangeCommandDon() {
        when(habitsManage.getLogin()).thenReturn("testUser");

        // Устанавливаем поведение для reader
        when(reader.getUserInput()).thenReturn("don");

        // Интерпретируем команду "ch"
        interpreter.interpret("ch");

        // Проверяем, что метод markHabitIsDone был вызван
        verify(habitsManage, times(1)).markHabitIsDone("testUser");
    }

    @Test
    public void testInterpretInvalidCommand() {
        when(habitsManage.getLogin()).thenReturn("testUser");

        // Устанавливаем поведение для reader
        when(reader.getUserInput()).thenReturn("invalid");

        // Интерпретируем команду "ch"
        interpreter.interpret("ch");

        // Проверяем, что методы управления привычками не были вызваны
        verify(habitsManage, never()).delHabit();
        verify(habitsManage, never()).changeHabit();
        verify(habitsManage, never()).filterHabits(anyString());
        verify(habitsManage, never()).markHabitIsDone(anyString());
    }
}
package org.example.entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class HabitTest {

    private Habit habit;
    private Date startDate;

    @BeforeEach
    public void setUp() {
        startDate = new Date();
        habit = new Habit("Exercise", "Daily workout", startDate, 2, 3, false);
    }

    @Test
    public void testDefaultConstructor() {
        Habit defaultHabit = new Habit();
        assertEquals("NoNameHabit", defaultHabit.getName());
        assertNull(defaultHabit.getDescription());
        assertNull(defaultHabit.getStartDate());
        assertNull(defaultHabit.getFrequencyPerDay());
        assertNull(defaultHabit.getFrequencyPerWeek());
    }

    @Test
    public void testParameterizedConstructorWithStatus() {
        assertTrue(habit.getName().startsWith("Exercise")); // Проверяем, что имя начинается с "Exercise"
        assertNotNull(habit.getName());  // Проверяем, что имя не null
        assertEquals("Daily workout", habit.getDescription());
        assertEquals(startDate, habit.getStartDate());
        assertEquals(2, habit.getFrequencyPerDay());
        assertEquals(3, habit.getFrequencyPerWeek());
        assertFalse(habit.getStatus());  // Изначально статус должен быть false
    }

    @Test
    public void testUniqueNameWithUUID() {
        String habitName = habit.getName();
        assertTrue(habitName.contains("Exercise")); // Проверка на наличие оригинального имени
        assertDoesNotThrow(() -> UUID.fromString(habitName.substring("Exercise".length()))); // Проверка UUID
    }

    @Test
    public void testParameterizedConstructorWithoutStatus() {
        Habit habitWithoutStatus = new Habit("Reading", "Read 30 pages", startDate, 1, 7);
        assertEquals("Reading", habitWithoutStatus.getName());
        assertEquals("Read 30 pages", habitWithoutStatus.getDescription());
        assertEquals(startDate, habitWithoutStatus.getStartDate());
        assertEquals(1, habitWithoutStatus.getFrequencyPerDay());
        assertEquals(7, habitWithoutStatus.getFrequencyPerWeek());
        assertNull(habitWithoutStatus.getStatus());  // Статус не был задан
    }

    @Test
    public void testSetAndGetName() {
        habit.setName("Meditation");
        assertEquals("Meditation", habit.getName());
    }

    @Test
    public void testSetAndGetDescription() {
        habit.setDescription("Morning meditation");
        assertEquals("Morning meditation", habit.getDescription());
    }

    @Test
    public void testSetAndGetStartDate() {
        Date newStartDate = new Date();
        habit.setStartDate(newStartDate);
        assertEquals(newStartDate, habit.getStartDate());
    }

    @Test
    public void testSetAndGetFrequencyPerDay() {
        habit.setFrequencyPerDay(5);
        assertEquals(5, habit.getFrequencyPerDay());
    }

    @Test
    public void testSetAndGetFrequencyPerWeek() {
        habit.setFrequencyPerWeek(4);
        assertEquals(4, habit.getFrequencyPerWeek());
    }

    @Test
    public void testSetAndGetStatus() {
        habit.setStatus(true);
        assertTrue(habit.getStatus());
    }

    @Test
    public void testSetAndGetStreak() {
        habit.setStreak(10);
        assertEquals(10, habit.getStreak());
    }

    @Test
    public void testStreakDefault() {
        Habit newHabit = new Habit("Running", "Morning run", startDate, 1, 5, true);
        assertEquals(0, newHabit.getStreak());  // Проверка, что по умолчанию streak = 0
    }
}
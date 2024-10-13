package org.example.repositories;

import org.example.entities.Habit;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;


import static org.junit.jupiter.api.Assertions.*;

class HabitsRepoTest {

    private HabitsRepo habitsRepo;
    private Habit habit;

    @BeforeEach
    public void setUp() {
        // Создаем новый Habit перед каждым тестом
        habit = new Habit("Exercise", "Daily workout", new Date(), 2, 3, false);
        habitsRepo = new HabitsRepo(habit);
    }

    @Test
    public void testCreateHabit() {
        Habit newHabit = new Habit("Reading", "Read 30 pages daily", new Date(), 1, 7);
        habitsRepo.createHabit(newHabit);
        
        // Проверяем, что новый Habit добавлен в репозиторий
        assertEquals(newHabit, habitsRepo.getHabitByName("Reading"));
        assertNotNull(habitsRepo.getHabitByName("Reading")); // Проверка, что привычка существует
    }

    @Test
    public void testGetHabitByName() {
        habitsRepo.createHabit(habit);
        
        // Проверяем, что метод возвращает правильный объект Habit
        assertEquals(habit, habitsRepo.getHabitByName("Exercise"));
        assertNull(habitsRepo.getHabitByName("NonExistingHabit")); // Проверяем, что несуществующая привычка возвращает null
    }

    @Test
    public void testDeleteHabitByName() {
        habitsRepo.createHabit(habit);
        habitsRepo.deleteHabitByName("Exercise");

        // Проверяем, что привычка была удалена
        assertNull(habitsRepo.getHabitByName("Exercise"));
    }

    @Test
    public void testChangeDescriptionByName() {
        habitsRepo.createHabit(habit);
        habitsRepo.changeDescriptionByName("Exercise", "Updated daily workout");
        
        // Проверяем, что описание было обновлено
        assertEquals("Updated daily workout", habitsRepo.getHabitByName("Exercise").getDescription());
    }

    @Test
    public void testChangeFrequencyPerDayByName() {
        habitsRepo.createHabit(habit);
        habitsRepo.changeFrequencyPerDayByName("Exercise", 5);
        
        // Проверяем, что частота выполнения в день была обновлена
        assertEquals(5, habitsRepo.getHabitByName("Exercise").getFrequencyPerDay());
    }

    @Test
    public void testChangeFrequencyPerWeekByName() {
        habitsRepo.createHabit(habit);
        habitsRepo.changeFrequencyPerWeekByName("Exercise", 4);
        
        // Проверяем, что частота выполнения в неделю была обновлена
        assertEquals(4, habitsRepo.getHabitByName("Exercise").getFrequencyPerWeek());
    }

    @Test
    public void testChangeStreakByName() {
        habitsRepo.createHabit(habit);
        habitsRepo.changeStreakByName("Exercise", 1);
        
        // Проверяем, что стрик увеличился на 1
        assertEquals(1, habitsRepo.getHabitByName("Exercise").getStreak());
    }

    @Test
    public void testChangeStatusByName() {
        habitsRepo.createHabit(habit);
        habitsRepo.changeStatusByName("Exercise", true);
        
        // Проверяем, что статус был изменен
        assertTrue(habitsRepo.getHabitByName("Exercise").getStatus());
    }

    @Test
    public void testChangeStreakMultipleTimes() {
        habitsRepo.createHabit(habit);
        for (int i = 0; i < 5; i++) {
            habitsRepo.changeStreakByName("Exercise", 1);
        }
        
        // Проверяем, что стрик увеличился на 5
        assertEquals(5, habitsRepo.getHabitByName("Exercise").getStreak());
    }
}
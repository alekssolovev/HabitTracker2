package org.example.service;

import org.example.entities.Habit;
import org.example.in.Reader;
import org.example.repositories.HabitsRepo;
import org.example.repositories.UserAccountRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;
import java.util.Date;

class HabitsManageTest {

    private HabitsRepo habitsRepo;
    private UserAccountRepo userAccountRepo;
    private Reader reader;
    private HabitsManage habitsManage;
    private String login = "testUser";

    @BeforeEach
    public void setUp() {
        habitsRepo = mock(HabitsRepo.class);
        userAccountRepo = mock(UserAccountRepo.class);
        reader = mock(Reader.class);
        habitsManage = new HabitsManage(habitsRepo, userAccountRepo, reader, login);
    }

    @Test
    public void testAddHabit() {
        when(reader.getUserInput()).thenReturn("New Habit", "Описание привычки", "3", "21");


        Habit mockHabit = mock(Habit.class);
        when(habitsRepo.getHabitByName("New Habit")).thenReturn(mockHabit);


        habitsManage.addHabit(login);


        verify(reader, times(4)).readInput();
        verify(habitsRepo).getHabitByName("New Habit");
        verify(mockHabit).setName("New Habit");
        verify(mockHabit).setDescription("Описание привычки");
        verify(mockHabit).setFrequencyPerDay(3);
        verify(mockHabit).setFrequencyPerWeek(21);
        verify(userAccountRepo).addUserHabitsByLogin(eq(login), eq(mockHabit));


        verify(mockHabit).setStartDate(any(Date.class));
    }
}
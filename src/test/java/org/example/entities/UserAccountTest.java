package org.example.entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UserAccountTest {

    private UserAccount userAccount;
    private List<Habit> habitsList;
    Date date = new Date();
    @BeforeEach
    public void setUp() {
        habitsList = new ArrayList<>();
        habitsList.add(new Habit("Exercise", "Daily workout", date, 1, 3, true));

        // Инициализация объекта UserAccount для тестирования
        userAccount = new UserAccount("testLogin", "TestUser", "password123", habitsList, 1, true);
    }

    @Test
    public void testConstructorWithThreeParams() {
        UserAccount simpleUserAccount = new UserAccount("simpleLogin", "SimpleUser", "simplePassword");
        assertEquals("simpleLogin", simpleUserAccount.getLogin());
        assertEquals("SimpleUser", simpleUserAccount.getName());
        assertEquals("simplePassword", simpleUserAccount.getPassword());
        assertTrue(simpleUserAccount.getActive()); // Проверяем, что по умолчанию isActive = true
    }

    @Test
    public void testConstructorWithAllParams() {
        assertEquals("testLogin", userAccount.getLogin());
        assertEquals("TestUser", userAccount.getName());
        assertEquals("password123", userAccount.getPassword());
        assertEquals(habitsList, userAccount.getUserHabits());
        assertEquals(1, userAccount.getId());
        assertTrue(userAccount.getActive());
    }

    @Test
    public void testSetAndGetLogin() {
        userAccount.setLogin("newLogin");
        assertEquals("newLogin", userAccount.getLogin());
    }

    @Test
    public void testSetAndGetName() {
        userAccount.setName("NewName");
        assertEquals("NewName", userAccount.getName());
    }

    @Test
    public void testSetAndGetPassword() {
        userAccount.setPassword("newPassword");
        assertEquals("newPassword", userAccount.getPassword());
    }

    @Test
    public void testSetAndGetUserHabits() {
        List<Habit> newHabits = new ArrayList<>();
        newHabits.add(new Habit("Read", "Reading books", date, 2, 5, true));
        userAccount.setUserHabits(newHabits);
        assertEquals(newHabits, userAccount.getUserHabits());
    }

    @Test
    public void testSetAndGetId() {
        userAccount.setId(100);
        assertEquals(100, userAccount.getId());
    }

    @Test
    public void testSetAndGetActiveStatus() {
        userAccount.setActive(false);
        assertFalse(userAccount.getActive());
    }

}
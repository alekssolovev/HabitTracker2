package org.example.repositories;

import org.example.entities.Habit;
import org.example.entities.UserAccount;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class UserAccountRepoTest {

    private UserAccountRepo userAccountRepo;
    private UserAccount testUser;

    private List<Habit> testHabits;

    @BeforeEach
    public void setUp() {
        userAccountRepo = new UserAccountRepo();
        testUser = new UserAccount("testLogin", "TestUser", "password123", testHabits, 1, true);
        userAccountRepo.createUserAccount(testUser); // Добавляем пользователя для тестов
    }

    @Test
    public void testGetUserAccountByLogin() {
        UserAccount retrievedUser = userAccountRepo.getUserAccountByLogin("testLogin");
        assertNotNull(retrievedUser);
        assertEquals(testUser.getLogin(), retrievedUser.getLogin());
    }

    @Test
    public void testCreateUserAccount() {
        UserAccount newUser = new UserAccount("newLogin", "NewUser", "newPassword" ,testHabits, 1, true);
        Map<String, UserAccount> userAccounts = userAccountRepo.createUserAccount(newUser);
        assertTrue(userAccounts.containsKey("newLogin"));
        assertEquals(newUser, userAccounts.get("newLogin"));
    }

    @Test
    public void testChangeUserAccountIdByLogin() {
        UserAccount updatedUser = userAccountRepo.changeUserAccountIdByLogin("testLogin", 100);
        assertEquals(100, updatedUser.getId());
    }

    @Test
    public void testChangeUserAccountNameByLogin() {
        UserAccount updatedUser = userAccountRepo.changeUserAccountNameByLogin("testLogin", "UpdatedName");
        assertEquals("UpdatedName", updatedUser.getName());
    }

    @Test
    public void testChangePasswordByLogin() {
        UserAccount updatedUser = userAccountRepo.changePasswordByLogin("testLogin", "newPassword123");
        assertEquals("newPassword123", updatedUser.getPassword());
    }

    @Test
    public void testAddUserHabitsByLogin() {
        Habit newHabit = new Habit("Exercise", "Do 10 push-ups", new Date(), 3, 1, true);
        UserAccount updatedUser = userAccountRepo.addUserHabitsByLogin("testLogin", newHabit);

        assertEquals(1, updatedUser.getUserHabits().size());
        assertTrue(updatedUser.getUserHabits().contains(newHabit));
    }

    @Test
    public void testChangeUserAccountStatusByLogin() {
        UserAccount updatedUser = userAccountRepo.changeUserAccountStatusByLogin("testLogin", false);
        assertFalse(updatedUser.getActive());
    }

    @Test
    public void testDeleteUserAccountByLogin() {
        userAccountRepo.deleteUserAccountByLogin("testLogin");
        assertNull(userAccountRepo.getUserAccountByLogin("testLogin"));
    }
}
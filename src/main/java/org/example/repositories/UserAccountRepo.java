package org.example.repositories;

import org.example.entities.Habit;
import org.example.entities.UserAccount;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserAccountRepo implements  UserAccountRepoInterface {
    Map<String, UserAccount> userAccounts;

    public UserAccountRepo() {
        userAccounts = new HashMap<>();
    }

    public Map<String, UserAccount> getUserAccounts() {
        return userAccounts;
    }

    public void setUserAccounts(Map<String, UserAccount> userAccounts) {
        this.userAccounts = userAccounts;
    }

    @Override
    public UserAccount getUserAccountByLogin(String login) {
        return userAccounts.get(login);
    }

    @Override
    public Map<String, UserAccount> createUserAccount(UserAccount userAccount) {
        String login = userAccount.getLogin();
         userAccounts.put(login, userAccount);
        return userAccounts;
    }

    @Override
    public UserAccount changeUserAccountIdByLogin(String login, Integer newId) {
        UserAccount changeUserAccount = getUserAccountByLogin(login);
        changeUserAccount.setId(newId);
        return changeUserAccount;
    }

    @Override
    public UserAccount changeUserAccountNameByLogin(String login, String newName) {
        UserAccount changeUserAccount = getUserAccountByLogin(login);
        changeUserAccount.setName(newName);
        return changeUserAccount;
    }

    @Override
    public UserAccount changePasswordByLogin(String login, String newPassword) {
        UserAccount changeUserAccount = getUserAccountByLogin(login);
        changeUserAccount.setPassword(newPassword);
        return changeUserAccount;
    }

    @Override
    public UserAccount addUserHabitsByLogin(String login, Habit newHabit) {
        UserAccount changeUserAccount = getUserAccountByLogin(login);
        List<Habit> userHabitsList = changeUserAccount.getUserHabits();
        userHabitsList.add(newHabit);
        changeUserAccount.setUserHabits(userHabitsList);
        //newHabitsList.add(newHabitsList);
        return changeUserAccount;
    }

    @Override
    public UserAccount changeUserAccountStatusByLogin(String login, Boolean newStatus) {
        UserAccount changeUserAccount = getUserAccountByLogin(login);
        changeUserAccount.setActive(newStatus);
        return changeUserAccount;
    }

    @Override
    public void deleteUserAccountByLogin(String login) {
        UserAccount changeUserAccount = getUserAccountByLogin(login);
        userAccounts.remove(changeUserAccount.getLogin());
    }
}
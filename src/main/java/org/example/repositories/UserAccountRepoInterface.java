package org.example.repositories;

import org.example.entities.Habit;
import org.example.entities.UserAccount;

import java.util.Map;

public interface UserAccountRepoInterface {

    public UserAccount getUserAccountByLogin(String login);

    public Map<String,UserAccount> createUserAccount(UserAccount userAccount);

    public UserAccount changeUserAccountIdByLogin(String login, Integer newId);

    public UserAccount changeUserAccountNameByLogin(String login, String newName);

    public  UserAccount changePasswordByLogin(String login, String newPassword);

    public UserAccount addUserHabitsByLogin(String login, Habit newHabit);

    public UserAccount changeUserAccountStatusByLogin(String login, Boolean newStatus);

    public void deleteUserAccountByLogin(String login);


}
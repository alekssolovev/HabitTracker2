package org.example.service;

import org.example.entities.Habit;
import org.example.in.Reader;
import org.example.repositories.HabitsRepo;
import org.example.repositories.UserAccountRepo;

import java.time.ZoneId;
import java.util.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.stream.Collectors;

public class HabitsManage {
    HabitsRepo habitsRepo;
    UserAccountRepo userAccountRepo;
    Reader reader;
    String login;

    public HabitsManage(HabitsRepo habitsRepo, UserAccountRepo userAccountRepo, Reader reader,String login) {
        this.habitsRepo = habitsRepo;
        //this.habit = habit;
        this.userAccountRepo = userAccountRepo;
        this.reader = reader;
        this.login = login;
    }

    public String getLogin() {
        return login;
    }

    Date date = new Date();
    public void addHabit(String login){
            System.out.println("Введите название");
            reader.readInput();
            String habitName = reader.getUserInput();
            Habit habit = habitsRepo.getHabitByName(habitName);
            habit.setName(habitName);
            habit.setStartDate(date);
            System.out.println("Привычка создана" + date);
            System.out.println("Введите описание");
            reader.readInput();
            String habitDescription = reader.getUserInput();
            habit.setDescription(habitDescription);
            System.out.println("Сколько раз в день?");
            reader.readInput();
            Integer perDayAmount = Integer.parseInt( reader.getUserInput());
            habit.setFrequencyPerDay(perDayAmount);
            System.out.println("Сколько раз в неделю?");
            reader.readInput();
            Integer perWeekAmount = Integer.parseInt( reader.getUserInput());
            habit.setFrequencyPerWeek(perWeekAmount);
            userAccountRepo.addUserHabitsByLogin(login,habit);
            System.out.println("Привычка создана");
        }

    public void changeHabit(){
            System.out.println("Введите название");
            reader.readInput();
            String habitName = reader.getUserInput();
            Habit habit = habitsRepo.getHabitByName(habitName);
            System.out.println("Измените  описание");
            reader.readInput();
            String habitDescription = reader.getUserInput();
            habit.setDescription(habitDescription);
            System.out.println("Сколько раз в день?");
            reader.readInput();
            Integer perDayAmount = Integer.parseInt( reader.getUserInput());
            habit.setFrequencyPerDay(perDayAmount);
            System.out.println("Сколько раз в неделю?");
            reader.readInput();
            Integer perWeekAmount = Integer.parseInt( reader.getUserInput());
            habit.setFrequencyPerWeek(perWeekAmount);
        }
    public void delHabit(){
            System.out.println("Введите название");
            reader.readInput();
            String habitName = reader.getUserInput();
            habitsRepo.deleteHabitByName(habitName);
        }
    public void filterHabits(String login){
            System.out.println("Фильтр списка по дате создания(введите date) или по статусу(stat)");
            reader.readInput();
            String input = reader.getUserInput();
            if(input.equals("stat")){
                System.out.println("Введите true для всех выполненных привычек и false для невыполненных");
                reader.readInput();
                input = reader.getUserInput();
                if(input.equals("true")){
                    List<Habit> habitsUserAccount = userAccountRepo.getUserAccountByLogin(login).getUserHabits();
                    List<Habit> completedHabits = habitsUserAccount.stream()
                            .filter(Habit::getStatus)  // Фильтрация по true
                            .collect(Collectors.toList());
                    System.out.println("Выполненные привычки: " + completedHabits);
                } else if (input.equals("false")) {
                    List<Habit> habitsUserAccount = userAccountRepo.getUserAccountByLogin(login).getUserHabits();
                    List<Habit> incompleteHabits = habitsUserAccount.stream()
                            .filter(habit -> !habit.getStatus())  // Фильтрация по false
                            .collect(Collectors.toList());
                    System.out.println("Невыполненные привычки: " + incompleteHabits);

                } else {
                    System.out.println("Неверный ввод");}

                }
            else if (input.equals("date")) {
                {
                    System.out.println("Введите дату создания привычек для фильтрации\n " +
                            "будут отфильтрованы все привычки после нее");
                    // Указываем формат ввода даты
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

                    // Переменная для хранения даты
                    LocalDate localDate = null;

                    while (localDate == null) {
                        System.out.println("Введите дату в формате dd/MM/yyyy: ");
                        reader.readInput();
                        input = reader.getUserInput();
                        try {
                            // Парсим введённую строку в LocalDate
                            localDate = LocalDate.parse(input, formatter);
                            System.out.println("Введенная дата: " + date);
                        } catch (DateTimeParseException e) {
                            // Если формат даты неверный, выводим сообщение об ошибке
                            System.out.println("Неверный формат даты. Пожалуйста, попробуйте еще раз.");
                        }
                    }
                    Date localDateToDate = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
                    List<Habit> habitsUserAccount = userAccountRepo.getUserAccountByLogin(login).getUserHabits();
                    // Фильтрация привычек, которые начались после введённой даты
                    List<Habit> filteredHabits = habitsUserAccount.stream()
                            .filter(habit -> habit.getStartDate().after(localDateToDate))  // Проверяем, что startDate позже inputDate
                            .collect(Collectors.toList());
                    if (filteredHabits.isEmpty()) {
                        System.out.println("Нет привычек, начавшихся после введенной даты.");
                    } else {
                        System.out.println("Привычки, начавшиеся после " + localDateToDate + ":");
                        filteredHabits.forEach(System.out::println);
                    }

            }
                }


        }
        public void markHabitIsDone(String login){
            System.out.println("Введите название");
            reader.readInput();
            String habitName = reader.getUserInput();
            Integer newStreak = habitsRepo.getHabitByName(habitName).getStreak();
            newStreak = newStreak + 1;
            habitsRepo.getHabitByName(habitName).setStreak(newStreak);

        }



    }
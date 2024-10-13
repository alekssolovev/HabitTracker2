package org.example.in;

import org.example.service.HabitsManage;

public class InterpreterHabits implements InterpreterInt{
    HabitsManage habitsManage;
    Reader reader;


    public InterpreterHabits(HabitsManage habitsManage, Reader reader) {
        this.habitsManage = habitsManage;
        this.reader = reader;

    }

    @Override
    public void interpret(String command) {
        String login = habitsManage.getLogin();
        System.out.println("добавить привычку(введите add) или  выбрать опции управления привычкой (введите ch)");
        if (command.equals("add")) {
           habitsManage.addHabit(login);
        } else if (command.equals("ch")){
            System.out.println("для изменения привычки введите set, для удаления del,"+
                    "для фильтрации fil и для отметки выполнения don");
            reader.readInput();
            String userInput = reader.getUserInput();
            if (userInput.equals("del")) {
                habitsManage.delHabit();
            }
            else if (userInput.equals("set")){
                habitsManage.changeHabit();
            }
            else if (userInput.equals("fil")){
                habitsManage.filterHabits(login);
            }
            else if (userInput.equals("don")){
                habitsManage.markHabitIsDone(login);
            }
            else
                System.out.println("неверный ввод");
            }

        }

    }
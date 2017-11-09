package ru.innopolis.countcalories.service;

import ru.innopolis.countcalories.model.Meal;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

public interface MealService {
    Meal get(int id, int userId);

    void delete(int id, int userId);

    default List<Meal> getBetweenDates(LocalDate startDate, LocalDate endDate, int userId) {
        return getBetweenDateTimes(LocalDateTime.of(startDate, LocalTime.MIN), LocalDateTime.of(endDate, LocalTime.MAX), userId);
    }

    List<Meal> getBetweenDateTimes(LocalDateTime startDateTime, LocalDateTime endDateTime, int userId);

    List<Meal> getAll(int userId);

    Meal update(Meal meal, int userId);

    Meal create(Meal meal, int userId);

    Meal getWithUser(int id, int userId);

//    List<Meal> findMealsByUser(User user);

    List<Meal> findMealsByUser_Id(int id);
}
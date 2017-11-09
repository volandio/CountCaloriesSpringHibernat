package ru.innopolis.countcalories.repository;

import ru.innopolis.countcalories.model.Meal;

import java.time.LocalDateTime;
import java.util.List;

public interface MealRepository {
    Meal save(Meal meal, int userId);

    boolean delete(int id, int userId);

    Meal get(int id, int userId);

    List<Meal> getAll(int userId);

    List<Meal> getBetween(LocalDateTime startDate, LocalDateTime endDate, int userId);

    default Meal getWithUser(int id, int userId) {
        throw new UnsupportedOperationException();
    }

    List<Meal> findMealsByUser_Id(int id);
}

package ru.innopolis.countcalories.repository;

import ru.innopolis.countcalories.model.User;

import java.util.List;

public interface UserRepository {

    boolean updateCaloriesFromUser(int userId, int calories);

    boolean delete(int id);

    User get(int id);

    List<User> getAll();

    default User getWithMeals(int id) {
        throw new UnsupportedOperationException();
    }
}

package ru.innopolis.countcalories.service;

import ru.innopolis.countcalories.model.User;

import java.util.List;

public interface UserService {

    void save(User user);

    User findByUsername(String username);

    void delete(int id);

    User get(int id);

    List<User> getAll();

    User getWithMeals(int id);

    void updateUser(int userId, int calories);
}

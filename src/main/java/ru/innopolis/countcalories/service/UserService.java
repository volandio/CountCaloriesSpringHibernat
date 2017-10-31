package ru.innopolis.countcalories.service;

import ru.innopolis.countcalories.model.User;

public interface UserService {

    void save(User user);

    User findByUsername(String username);
}

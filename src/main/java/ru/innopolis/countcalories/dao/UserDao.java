package ru.innopolis.countcalories.dao;

import ru.innopolis.countcalories.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDao extends JpaRepository<User, Long> {
    User findByUsername(String username);
}

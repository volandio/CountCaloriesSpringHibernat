package ru.innopolis.countcalories.repository.datajpa;

import ru.innopolis.countcalories.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDao extends JpaRepository<User, Long> {
    User findByUsername(String username);
}

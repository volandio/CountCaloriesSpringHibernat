package ru.innopolis.countcalories.dao;

import ru.innopolis.countcalories.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleDao extends JpaRepository<Role, Long> {
}

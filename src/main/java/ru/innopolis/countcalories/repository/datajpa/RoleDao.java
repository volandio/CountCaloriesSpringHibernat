package ru.innopolis.countcalories.repository.datajpa;

import ru.innopolis.countcalories.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleDao extends JpaRepository<Role, Long> {
}

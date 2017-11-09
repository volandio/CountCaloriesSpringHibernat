package ru.innopolis.countcalories.repository.datajpa;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.innopolis.countcalories.model.User;

import java.util.List;

public interface CrudUserRepository extends JpaRepository<User, Integer> {
    @Modifying
    @Transactional
    @Query("DELETE FROM User u WHERE u.id=:id")
    int delete(@Param("id") int id);

    @Override
    @Transactional
    User save(User user);

    @Override
    User findOne(Integer id);

    @Override
    List<User> findAll(Sort sort);

    @Query("SELECT u FROM User u WHERE u.id=?1")
    User getWithMeals(int id);

    @Modifying
    @Transactional
    @Query("UPDATE User u SET u.caloriesPerDay=?2 WHERE u.id=?1")
    int updateCaloriesFromUser(int userId, int calories);
}

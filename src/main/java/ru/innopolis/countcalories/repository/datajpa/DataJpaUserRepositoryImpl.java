package ru.innopolis.countcalories.repository.datajpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;
import ru.innopolis.countcalories.model.User;
import ru.innopolis.countcalories.repository.UserRepository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public class DataJpaUserRepositoryImpl implements UserRepository {
    private static final Sort SORT_NAME= new Sort("username");

    private CrudUserRepository crudRepository;

    @Autowired
    public void setCrudRepository(CrudUserRepository  crudRepository) {
        this.crudRepository = crudRepository;
    }

    @Override
    @Transactional
    public boolean updateCaloriesFromUser(int userId, int calories) {
        return crudRepository.updateCaloriesFromUser(userId, calories) !=0;
    }

    @Override
    @Transactional
    public boolean delete(int id) {
        return crudRepository.delete(id) != 0;
    }

    @Override
    public User get(int id) {
        return crudRepository.findOne(id);
    }

    @Override
    public List<User> getAll() {
        return crudRepository.findAll(SORT_NAME);
    }

    @Override
    public User getWithMeals(int id) {
        return crudRepository.getWithMeals(id);
    }
}

package ru.innopolis.countcalories.repository.datajpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.innopolis.countcalories.model.Meal;
import ru.innopolis.countcalories.repository.MealRepository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public class DataJpaMealRepositoryImpl implements MealRepository {

    private CrudMealRepository crudMealRepository;

    private CrudUserRepository crudUserRepository;

    @Autowired
    public void setCrudMealRepository(CrudMealRepository crudMealRepository) {
        this.crudMealRepository = crudMealRepository;
    }

    @Autowired
    public void setCrudUserRepository(CrudUserRepository crudUserRepository) {
        this.crudUserRepository = crudUserRepository;
    }

    @Override
    @Transactional
    public Meal save(Meal meal, int userId) {
        if (!meal.isNew() && get(meal.getId(), userId) == null) {
            return null;
        }
        meal.setUser(crudUserRepository.getOne(userId));
        return crudMealRepository.save(meal);
    }

    @Override
    @Transactional
    public boolean delete(int id, int userId) {
        return crudMealRepository.delete(id, userId) != 0;
    }

    @Override
    public Meal get(int id, int userId) {
        Meal meal = crudMealRepository.findOne(id);
        return meal != null && meal.getUser().getId() == userId ? meal : null;
    }

    @Override
    public List<Meal> getAll(int userId) {
        return crudMealRepository.getAll(userId);
    }



    @Override
    public List<Meal> getBetween(LocalDateTime startDate, LocalDateTime endDate, int userId) {
        return crudMealRepository.getBetween(startDate, endDate, userId);
    }

    @Override
    public Meal getWithUser(int id, int userId) {
        return crudMealRepository.getWithUser(id, userId);
    }

    @Override
    public List<Meal> findMealsByUser_Id(int id) {
        return crudMealRepository.findMealsByUser_Id(id);
    }
}

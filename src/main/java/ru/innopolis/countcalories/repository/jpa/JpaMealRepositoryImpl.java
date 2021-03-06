//package ru.innopolis.countcalories.repository.jpa;
//
//import org.springframework.stereotype.Repository;
//import org.springframework.transaction.annotation.Transactional;
//import ru.innopolis.countcalories.model.Meal;
//import ru.innopolis.countcalories.model.User;
//import ru.innopolis.countcalories.repository.MealRepository;
//
//import javax.persistence.EntityManager;
//import javax.persistence.PersistenceContext;
//import java.time.LocalDateTime;
//import java.util.List;
//
//@Repository
//@Transactional(readOnly = true)
//public class JpaMealRepositoryImpl implements MealRepository {
//
//    @PersistenceContext
//    private EntityManager em;
//
//    @Override
//    @Transactional
//    public Meal save(Meal meal, int userId) {
//        if (!meal.isNew() && get(meal.getId(), userId) == null) {
//            return null;
//        }
//        meal.setUser(em.getReference(User.class, userId));
//        if (meal.isNew()) {
//            em.persist(meal);
//            return meal;
//        } else {
//            return em.merge(meal);
//        }
//    }
//
//    @Override
//    @Transactional
//    @SuppressWarnings("JpaQlInspection")
//    public boolean delete(int id, int userId) {
//        return em.createQuery("DELETE FROM Meal m WHERE m.id=:id AND m.user.id=:userId")
//            .setParameter("id", id)
//            .setParameter("userId", userId)
//            .executeUpdate() != 0;
//    }
//
//    @Override
//    public Meal get(int id, int userId) {
//        Meal meal = em.find(Meal.class, id);
//        return meal != null && meal.getUser().getId() == userId ? meal : null;
//    }
//
//    @Override
//    @SuppressWarnings("JpaQlInspection")
//    public List<Meal> getAll(int userId) {
//        return em.createQuery("SELECT m FROM Meal m WHERE m.user.id=:userId ORDER BY m.dateTime DESC", Meal.class)
//            .setParameter("userId", userId)
//            .getResultList();
//    }
//
//    @Override
//    @SuppressWarnings("JpaQlInspection")
//    public List<Meal> getBetween(LocalDateTime startDate, LocalDateTime endDate, int userId) {
//        return em.createQuery("SELECT m FROM Meal m WHERE m.user.id=:userId AND m.dateTime " +
//            "BETWEEN :startDate AND :endDate ORDER BY m.dateTime DESC", Meal.class)
//            .setParameter("userId", userId)
//            .setParameter("startDate", startDate)
//            .setParameter("endDate", endDate).getResultList();
//    }
//
//    @Override
//    public List<Meal> findMealsByUser_Id(int id) {
//        return null;
//    }
//}

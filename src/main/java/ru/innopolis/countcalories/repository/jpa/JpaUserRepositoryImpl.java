//package ru.innopolis.countcalories.repository.jpa;
//
//import org.springframework.stereotype.Repository;
//import org.springframework.transaction.annotation.Transactional;
//import ru.innopolis.countcalories.model.User;
//import ru.innopolis.countcalories.repository.UserRepository;
//
//import javax.persistence.EntityManager;
//import javax.persistence.PersistenceContext;
//import java.util.List;
//
//@Repository
//@Transactional(readOnly = true)
//public class JpaUserRepositoryImpl implements UserRepository {
//
//    @PersistenceContext
//    private EntityManager em;
//
//    @Override
//    public User get(int id) {
//        return em.find(User.class, id);
//    }
//
//    @Override
//    public boolean updateCaloriesFromUser(int userId, int calories) {
//        return false;
//    }
//
//    @Override
//    @Transactional
//    @SuppressWarnings("JpaQlInspection")
//    public boolean delete(int id) {
//        return em.createQuery("DELETE FROM User u WHERE u.id=:id")
//            .setParameter("id", id)
//            .executeUpdate() != 0;
//    }
//
//    @Override
//    @SuppressWarnings("JpaQlInspection")
//    public List<User> getAll() {
//        return em.createQuery("SELECT u FROM User u ORDER BY u.username", User.class).getResultList();
//    }
//}

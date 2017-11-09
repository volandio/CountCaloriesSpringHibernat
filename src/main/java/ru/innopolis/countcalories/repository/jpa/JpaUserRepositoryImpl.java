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
//    @Transactional
//    public boolean delete(int id) {
//        return em.createNamedQuery(User.DELETE)
//            .setParameter("id", id)
//            .executeUpdate() != 0;
//    }
//
//    @Override
//    public List<User> getAll() {
//        return em.createNamedQuery(User.ALL_SORTED, User.class).getResultList();
//    }
//}

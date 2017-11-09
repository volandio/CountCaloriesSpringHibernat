package ru.innopolis.countcalories.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ru.innopolis.countcalories.model.Role;
import ru.innopolis.countcalories.model.User;
import ru.innopolis.countcalories.repository.UserRepository;
import ru.innopolis.countcalories.repository.datajpa.RoleDao;
import ru.innopolis.countcalories.repository.datajpa.UserDao;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {
    private UserRepository repository;

    private UserDao userDao;

    private RoleDao roleDao;

    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserServiceImpl() {
    }

    @Autowired
    public UserServiceImpl(UserRepository repository, UserDao userDao, RoleDao roleDao,
                           BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.repository = repository;
        this.userDao = userDao;
        this.roleDao = roleDao;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public void save(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        Set<Role> roles = new HashSet<>();
        roles.add(roleDao.getOne(1L));
        user.setRoles(roles);
        userDao.save(user);
    }

    @Override
    public User findByUsername(String username) {
        return userDao.findByUsername(username);
    }

    @Override
    public void delete(int id) {
        repository.delete(id);
    }

    @Override
    public User get(int id) {
        return repository.get(id);
    }

    @Override
    public List<User> getAll() {
        return repository.getAll();
    }

    @Override
    public User getWithMeals(int id) {
        return repository.getWithMeals(id);
    }

    @Override
    public void updateUser(int userId, int calories) {
        repository.updateCaloriesFromUser(userId, calories);
    }
}

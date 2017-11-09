package ru.innopolis.countcalories.model;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Set;

@NamedQueries({
    @NamedQuery(name = User.DELETE, query = "DELETE FROM User u WHERE u.id=:id"),
    @NamedQuery(name = User.ALL_SORTED, query = "SELECT u FROM User u ORDER BY u.username"),
})
@Entity
@Table(name = "users")
public class User {
    public static final String DELETE = "User.delete";
    public static final String ALL_SORTED = "User.getAllSorted";

    //    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    //    @Column(name = "username")
    private String username;

    //    @Column(name = "password")
    private String password;

    //    @Column(name = "registered")
    private Date registered = new Date();

    //    @Column(name = "calories_per_day")
    private int caloriesPerDay = 2000;

    //    @Transient
    private String confirmPassword;

    //    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
//    @OrderBy("dateTime DESC")
    private List<Meal> meals;

    public User() {
    }

    //    @ManyToMany
//    @JoinTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"),
//        inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;

    //    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
//    @OrderBy("dateTime DESC")
    @OneToMany(mappedBy = "user")
    public List<Meal> getMeals() {
        return meals;
    }

    public void setMeals(List<Meal> meals) {
        this.meals = meals;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Column
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Transient
    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    @ManyToMany
    @JoinTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"),
        inverseJoinColumns = @JoinColumn(name = "role_id"))
    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    @Column
    public Date getRegistered() {
        return registered;
    }

    public void setRegistered(Date registered) {
        this.registered = registered;
    }

    @Column(name = "calories_per_day")
    public int getCaloriesPerDay() {
        return caloriesPerDay;
    }

    public void setCaloriesPerDay(int caloriesPerDay) {
        this.caloriesPerDay = caloriesPerDay;
    }

    @Transient
    public boolean isNew() {
        return getId() == null;
    }
}

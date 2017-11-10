package ru.innopolis.countcalories.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
@Table(name = "meals")
public class Meal {
    private LocalDateTime dateTime;

    private Integer id;

    private String description;

    private int calories;

    private User user;

    public Meal() {
    }

    public Meal(User user, LocalDateTime dateTime, String description, int calories) {
        this.dateTime = dateTime;
        this.description = description;
        this.calories = calories;
        this.user = user;
    }

    public Meal(LocalDateTime dateTime, String description, int calories) {
        this.dateTime = dateTime;
        this.description = description;
        this.calories = calories;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    @Column(name = "date_time")
    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    @Column
    public String getDescription() {
        return description;
    }

    @Column
    public int getCalories() {
        return calories;
    }

    @Transient
    public LocalDate getDate() {
        return dateTime.toLocalDate();
    }

    @Transient
    public LocalTime getTime() {
        return dateTime.toLocalTime();
    }

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Transient
    public boolean isNew() {
        return getId() == null;
    }

    @Override
    public String toString() {
        return "Meal{" +
            "dateTime=" + dateTime +
            ", id=" + id +
            ", description='" + description + '\'' +
            ", calories=" + calories +
            ", user=" + user +
            '}';
    }
}

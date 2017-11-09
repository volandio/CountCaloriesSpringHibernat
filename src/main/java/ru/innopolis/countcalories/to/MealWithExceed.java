package ru.innopolis.countcalories.to;

import ru.innopolis.countcalories.model.User;

import java.time.LocalDateTime;

public class MealWithExceed {

    private User user;

    private LocalDateTime dateTime;

    private String description;

    private int id;

    private int calories;

    private boolean exceed;

    private MealWithExceed() {
    }

    public int getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public String getDescription() {
        return description;
    }

    public int getCalories() {
        return calories;
    }

    public boolean isExceed() {
        return exceed;
    }

    public MealWithExceed(int id, User user, LocalDateTime dateTime, String description, int calories, boolean exceed) {
        this.id = id;
        this.user = user;
        this.dateTime = dateTime;
        this.description = description;
        this.calories = calories;
        this.exceed = exceed;
    }

    @Override
    public String toString() {
        return "MealsWithExceed{" +
            "userId=" + user.getId() +
            ", dateTime=" + dateTime +
            ", description='" + description + '\'' +
            ", calories=" + calories +
            ", exceed=" + exceed +
            '}';
    }
}

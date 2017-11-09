package ru.innopolis.countcalories.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ru.innopolis.countcalories.model.Meal;
import ru.innopolis.countcalories.model.User;
import ru.innopolis.countcalories.service.MealService;
import ru.innopolis.countcalories.service.UserService;
import ru.innopolis.countcalories.to.MealWithExceed;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import static ru.innopolis.countcalories.util.MealsUtil.getFilteredWithExceeded;

@Controller
public class MealController {

    private UserService userService;

    private MealService mealService;

    @Autowired
    public MealController(UserService userService, MealService mealService) {
        this.userService = userService;
        this.mealService = mealService;
    }

    private User getUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String userName = auth.getName();
        return userService.findByUsername(userName);
    }

    private int getUserId() {
        return getUser().getId();
    }

    @RequestMapping(value = "/meals", method = RequestMethod.GET)
    public ModelAndView meals() {
        int userId = getUserId();
        ModelAndView modelAndView = new ModelAndView("meals");
        List<Meal> meals = mealService.getAll(userId);
        List<MealWithExceed> mealWithExceeds = getFilteredWithExceeded(meals, LocalTime.of(00, 00),
            LocalTime.of(23, 59), getUser().getCaloriesPerDay());
        modelAndView.addObject("meals", mealWithExceeds);
        modelAndView.addObject("user", getUser());
        return modelAndView;
    }

    @RequestMapping(value = "/createMeal", method = RequestMethod.GET)
    public String createMeal() {
        return "createMeal";
    }

    @RequestMapping(value = "/createMeal", method = RequestMethod.POST)
    public String createMeal(@RequestParam("dateTime") String dateTime,
                             @RequestParam("description") String description,
                             @RequestParam("calories") int calories) {
        LocalDateTime localDateTime = LocalDateTime.parse(dateTime);
        User user = getUser();
        Meal meal = new Meal(user, localDateTime, description, calories);
        mealService.create(meal, getUserId());
        return "redirect:meals";
    }

    @RequestMapping(value = "/editMeal", method = RequestMethod.GET)
    public ModelAndView editMEal(@RequestParam("id") int id) {
        ModelAndView modelAndView = new ModelAndView("editMeal");
        Meal meal = mealService.get(id, getUserId());
        modelAndView.addObject("meal", meal);
        return modelAndView;
    }

    @RequestMapping(value = "/editMeal", method = RequestMethod.POST)
    public String editMEal(@RequestParam("id") int id,
                           @RequestParam("dateTime") String dateTime,
                           @RequestParam("description") String description,
                           @RequestParam("calories") int calories) {
        LocalDateTime localDateTime = LocalDateTime.parse(dateTime);
        Meal meal = new Meal(localDateTime, description, calories);
        mealService.update(meal, getUserId());
        return "redirect:meals";
    }

    @RequestMapping(value = "/deleteMeal", method = RequestMethod.GET)
    public String deleteMeal(@RequestParam("id") int id) {
        mealService.delete(id, getUserId());
        return "redirect:meals";
    }
}

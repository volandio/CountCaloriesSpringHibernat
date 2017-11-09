package ru.innopolis.countcalories.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ru.innopolis.countcalories.model.Meal;
import ru.innopolis.countcalories.model.User;
import ru.innopolis.countcalories.service.MealService;
import ru.innopolis.countcalories.service.SecurityService;
import ru.innopolis.countcalories.service.UserService;
import ru.innopolis.countcalories.validator.UserValidator;

import java.util.List;

@Controller
public class UserController {

    private MealService mealService;

    private UserService userService;

    private SecurityService securityService;

    private UserValidator userValidator;

    @Autowired
    public UserController(MealService mealService, UserService userService,
                          SecurityService securityService, UserValidator userValidator) {
        this.mealService = mealService;
        this.userService = userService;
        this.securityService = securityService;
        this.userValidator = userValidator;
    }

    private User getUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String userName = auth.getName();
        return userService.findByUsername(userName);
    }

    private int getUserId() {
        return getUser().getId();
    }

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String registration(Model model) {
        model.addAttribute("userForm", new User());
        return "registration";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String registration(@ModelAttribute("userForm") User userForm, BindingResult bindingResult, Model model) {
        userValidator.validate(userForm, bindingResult);
        if (bindingResult.hasErrors()) {
            return "registration";
        }
        userService.save(userForm);
        securityService.autoLogin(userForm.getUsername(), userForm.getConfirmPassword());
        return "redirect:/welcome";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(Model model, String error, String logout) {
        if (error != null) {
            model.addAttribute("error", "Username or password is incorrect.");
        }
        if (logout != null) {
            model.addAttribute("message", "Logged out successfully.");
        }
        return "login";
    }

    @RequestMapping(value = {"/", "/welcome"}, method = RequestMethod.GET)
    public String welcome() {
        return "welcome";
    }

    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public ModelAndView admin() {
        List<User> usersList = userService.getAll();
        ModelAndView modelAndView = new ModelAndView("admin");
        modelAndView.addObject("users", usersList);
        return modelAndView;
    }

    @RequestMapping(value = {"/deleteUser"}, method = RequestMethod.GET)
    public String deleteUser(@RequestParam("id") int userId) {
        List<Meal> meals = mealService.getAll(userId);
        meals.forEach(meal -> mealService.delete(meal.getId(), userId));
        userService.delete(userId);
        return "redirect:admin";
    }

    @RequestMapping(value = "/editUserCalories", method = RequestMethod.POST)
    public String editUserCalories(@RequestParam("calories") int calories) {
        userService.updateUser(getUserId(), calories);
        return "redirect:meals";
    }
}

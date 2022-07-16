package ru.inodinln.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.inodinln.example.dto.UserLoginDTO;
import ru.inodinln.example.dto.UserRegDTO;
import ru.inodinln.example.entity.User;
import ru.inodinln.example.service.UserService;
import ru.inodinln.example.validation.entity.UserLogValidObj;
import ru.inodinln.example.validation.entity.UserRegValidObj;
import ru.inodinln.example.validation.service.UserLogValidService;
import ru.inodinln.example.validation.service.UserRegValidService;

@Controller
public class UserController {

    private final MainController mainController;
    private User currentUser;
    private final UserService userService;

    private final UserLogValidService userLogValidService;

    private final UserRegValidService userRegValidService;

    public UserController (MainController mainController, UserService userService,
                           UserLogValidService userLogValidService, UserRegValidService userRegValidService) {
        this.mainController = mainController;
        this.userService = userService;
        this.userLogValidService = userLogValidService;
        this.userRegValidService = userRegValidService;
    }

    @GetMapping("/")
    public String getLoginPage(Model model) {

        if (currentUser != null)
            return "index";

        model.addAttribute("userLoginDTO", new UserLoginDTO());
        return "login";
    }

    @PostMapping("/doLogin")
    public String doLogin (@ModelAttribute("userLoginDTO") UserLoginDTO userLogDTO, Model model) {

        if (currentUser != null)
            return "redirect:/index";

        UserLogValidObj userLogValidObj = userLogValidService.userLogValidation(userLogDTO);

        if (userLogValidObj == null) {
            User logUser = userService.logVerification(userLogDTO.geteMail(), userLogDTO.getPswrd());
            if (logUser != null){
                mainController.setCurrentUser(logUser);
                currentUser = logUser;
                return "redirect:/index";}
            else return "login";
        }
        model.addAttribute("userLogValidObj", userLogValidObj);
        return "login";
        }

    @GetMapping("/register")
    public String getRegisterPage(Model model) {

        if (currentUser != null)
            return "index";

        model.addAttribute("userRegDTO", new UserRegDTO());
        return "register";}

    @PostMapping("/doRegister")
    public String doRegister (@ModelAttribute("userRegDTO") UserRegDTO userRegDTO, Model model) {

        if (currentUser != null)
            return "index";

        UserRegValidObj userRegValidObj = userRegValidService.userRegValidation(userRegDTO);

        if (userRegValidObj == null) {
            if (userService.regVerification(userRegDTO.geteMail())){
                return "redirect:/register";}
            else {
                User newUser = userService.doRegister(userRegDTO);
                mainController.setCurrentUser(newUser);
                currentUser = newUser;
                return "index";
            }
        }
        model.addAttribute("userRegValidObj", userRegValidObj);
        return "register";
    }

    @GetMapping("/logOut")
    public String logOut() {
        mainController.setCurrentUser(null);
        currentUser = null;
        return "redirect:/";
    }
}

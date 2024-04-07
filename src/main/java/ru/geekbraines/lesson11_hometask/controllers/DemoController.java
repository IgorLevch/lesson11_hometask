package ru.geekbraines.lesson11_hometask.controllers;

import java.security.Principal;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import ru.geekbraines.lesson11_hometask.entities.User;
import ru.geekbraines.lesson11_hometask.services.UserService;



@RestController
@RequiredArgsConstructor
public class DemoController {

    private final UserService userService;


    @GetMapping("/")
    public String homePage(){
        return "home";

    }

    

    @GetMapping("/unsecured")
    public String usecuredPage(){

        return "unsecured";
    }

    @GetMapping("/auth_page") // сюда пускает только аутентифицированных пользователей
    public String authenticatedPage(){
        return "authenticated";
    }

    @GetMapping("/admin")  // сюда пускает только админов
    public String adminPage(){
        return "admin";
    }

    @GetMapping("/user_info") 
    public String daoTestPage(Principal principal){

        User user = userService.findByUsername(principal.getName()).orElseThrow(()->new RuntimeException("Not found"));
        return "Authenticated user info: " + user.getUsername() + ": "+ user.getEmail();

    }




}

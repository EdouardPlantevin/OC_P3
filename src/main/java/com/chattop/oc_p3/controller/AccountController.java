package com.chattop.oc_p3.controller;

import com.chattop.oc_p3.model.UserRegister;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccountController {


    @PostMapping("/register")
    public String register(@RequestBody @Valid UserRegister userRegister) {



        return "{\"token\": \"jwt\"}";
    }

}

package com.loginProject.loginProject.Controller;


import com.loginProject.loginProject.Model.Login;
import com.loginProject.loginProject.Service.logInService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/login")
public class logInController {

    @Autowired
    logInService service;

    @GetMapping("/greet")
    public String greet(){
        return "welocme to Login page";
    }

    @PostMapping("/")
    public String checkCred(@RequestBody Login loginReq){
        if(service.checkCred(loginReq.getUsername(),loginReq.getPassword())){
            return "You have succesfully logged In";
        }
        else{
            return "logIn not allowed";
        }
    }


    @PostMapping("/adduser")
    public String addUser(@RequestBody Login loginReq){
        if(service.addUser(loginReq.getUsername(),loginReq.getPassword(),loginReq)){
            return "You have succesfully created account";
        }
        else{
            return "User already exist";
        }

    }



}

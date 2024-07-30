package com.loginProject.loginProject.Controller;


import com.loginProject.loginProject.Model.EmailDetails;
import com.loginProject.loginProject.Model.Login;
import com.loginProject.loginProject.Service.EmailService;
import com.loginProject.loginProject.Service.logInService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Map;

@RestController
@RequestMapping("/login")
public class logInController {

    @Autowired
    logInService service;
    @Autowired
    EmailService Eservice;

    @GetMapping("/greet")
    public String greet(){
        return "welocme to Login page";
    }


    //login check
    @PostMapping("/")
    public String checkCred(@RequestBody Login loginReq){
        if(service.checkCred(loginReq.getUsername(),loginReq.getPassword())){
            return "You have succesfully logged In";
        }
        else{
            return "logIn not allowed";
        }
    }

    //Registration of user
    @PostMapping("/adduser")
    public String addUser(@RequestBody Login loginReq){
        if(service.addUser(loginReq.getUsername(),loginReq.getPassword(),loginReq)){
            return "You have succesfully created account";
        }
        else{
            return "User already exist";
        }
    }

    //add reset password logic for creating the token
    @PostMapping("/forgotpassword")
    public String forgotPassword(@RequestBody Map<String, String> payload){

        String email = payload.get("email").trim().toLowerCase();
        Login User= service.findByemail(email);

        if(User==null){
            System.out.println("User not found for email: " + email);
            return "Email not found";
        }
        System.out.println("User found: " + User.getUsername());
        Login token = service.createToken(User);

        String tokengen = token.getToken();
        service.updateToken(token,User,tokengen);
        String resetUrl = "http://localhost:8080/login/reset-password?token=" + token.getToken();



        Eservice.sendMail(email,resetUrl);

        return "Password reset email sent. Use the following link to reset your password: " + resetUrl;
    }

    //Implemantation of reset password which will bw called after the email is found in the db ot will generate a token
    //with that token token will be passed in the url calling resetpassword API which will take the valid token and send an email


    @PostMapping("/reset-password")
    public String resetPassword(@RequestBody Map<String, String> payload){

        String newPassword= payload.get("password");
        String token = payload.get("token");

        Login User = service.findBytoken(token);

        if(User==null){
            System.out.println("Invalid token");
            return "Invalid Token";
        }

        if(User.getExpiryDate().isBefore(LocalDateTime.now())){
            return "Token has Expired";
        }
        service.updatePassword(User,newPassword);

        Eservice.sendUpdateMail(User.getEmail());

        return "Password has updated successfully";
    }
    //This takes token and new password in requestbody and then updates the password in database and





}

package com.loginProject.loginProject.Controller;


import com.loginProject.loginProject.Model.EmailDetails;
import com.loginProject.loginProject.Model.Login;
import com.loginProject.loginProject.Service.EmailService;
import com.loginProject.loginProject.Service.logInService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<String> greet()
    {
        return new ResponseEntity<>("welocme to Login page", HttpStatus.OK);
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
    public ResponseEntity<String> addUser(@RequestBody Login loginReq){
        if(service.addUser(loginReq.getUsername(),loginReq.getPassword(),loginReq)){
            return new ResponseEntity<>("You have succesfully created account",HttpStatus.CREATED);
        }
        else{
            return new ResponseEntity<>("User already exist",HttpStatus.IM_USED);
        }
    }

    //add reset password logic for creating the token
    @PostMapping("/forgotpassword")
    public ResponseEntity<String> forgotPassword(@RequestBody Map<String, String> payload){

        String email = payload.get("email").trim().toLowerCase();
        Login User= service.findByemail(email);

        if(User==null){
            System.out.println("User not found for email: " + email);
            return new ResponseEntity<>("Email not found",HttpStatus.NOT_FOUND);
        }
        System.out.println("User found: " + User.getUsername());
        Login token = service.createToken(User);

        String tokengen = token.getToken();
        service.updateToken(token,User,tokengen);
        String resetUrl = "http://localhost:8080/login/reset-password?token=" + token.getToken();



        Eservice.sendMail(email,resetUrl);
        return new ResponseEntity<>("Password reset email sent. Use the following link to reset your password: " + resetUrl,HttpStatus.OK);
    }

    //Implemantation of reset password which will bw called after the email is found in the db ot will generate a token
    //with that token token will be passed in the url calling resetpassword API which will take the valid token and send an email


    @PostMapping("/reset-password")
    public ResponseEntity<String> resetPassword(@RequestBody Map<String, String> payload){

        String newPassword= payload.get("password");
        String token = payload.get("token");

        Login User = service.findBytoken(token);

        if(User==null){
            System.out.println("Invalid token");
            return new ResponseEntity<>("Invalid Token",HttpStatus.NOT_FOUND);
        }

        if(User.getExpiryDate().isBefore(LocalDateTime.now())){
            return new ResponseEntity<>("Token has Expired",HttpStatus.UNPROCESSABLE_ENTITY);
        }
        service.updatePassword(User,newPassword);

        Eservice.sendUpdateMail(User.getEmail());

        return new ResponseEntity<>("Password has updated successfully",HttpStatus.ACCEPTED);
    }
    //This takes token and new password in requestbody and then updates the password in database and


    //next task is to  create a password reset form and take the updated password



}

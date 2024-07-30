package com.loginProject.loginProject.Service;

import com.loginProject.loginProject.Model.Login;
import com.loginProject.loginProject.Repository.PasswordResetRepo;
import com.loginProject.loginProject.Repository.logInRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class logInService {

    @Autowired
    logInRepo repo;

    @Autowired
    PasswordResetRepo resetRepo;

    public boolean checkCred(String username, String password) {
        Login user = repo.findByUsername(username);
        if(user != null && user.getPassword().equals(password)){
            return true;
        }
        return false;
    }

    public boolean addUser(String username, String password, Login login) {
        Login user = repo.findByUsername(username);
        if(user != null){
            return false;
        } else {
            repo.save(login);
            return true;
        }
    }

    public Login createToken(Login user) {
        user.setToken(UUID.randomUUID().toString());
        user.setExpiryDate(LocalDateTime.now().plusHours(2));
        repo.save(user);
        return user;
    }

    public void updateToken(Login user, Login login, String token) {
        user.setToken(token);
        user.setExpiryDate(LocalDateTime.now().plusHours(2));
        repo.save(user);
    }





    public void updatePassword(Login user, String newPassword) {
        user.setPassword(newPassword);
        user.setToken(null);
        user.setExpiryDate(null);
        repo.save(user);
    }

    public Login findBytoken(String token) {
        return repo.findByToken(token);
    }

    public Login findByemail(String email) {

        return resetRepo.findByEmail(email);
    }
}

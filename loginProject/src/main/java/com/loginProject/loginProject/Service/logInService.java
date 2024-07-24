package com.loginProject.loginProject.Service;


import com.loginProject.loginProject.Model.Login;
import com.loginProject.loginProject.Repository.logInRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class logInService {

    @Autowired
    logInRepo repo;

    public boolean checkCred(String username, String password) {

        Login user = repo.findByUsername(username);
        if(user.getPassword().equals(password)){
            return true;
        }
        return false;
    }

    public boolean addUser(String username,String password,Login login) {
        Login user = repo.findByUsername(username);
        if(user!=null){
            return false;
        }
        else{
            repo.save(login);
            return true;

        }
    }
}

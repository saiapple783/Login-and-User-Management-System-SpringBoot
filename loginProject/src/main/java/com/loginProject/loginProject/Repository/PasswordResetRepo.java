package com.loginProject.loginProject.Repository;

import com.loginProject.loginProject.Model.Login;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PasswordResetRepo extends MongoRepository<Login, String> {


    Login findByEmail(String email);
}

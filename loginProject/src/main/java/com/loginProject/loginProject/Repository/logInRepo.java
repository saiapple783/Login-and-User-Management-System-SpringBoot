package com.loginProject.loginProject.Repository;

import com.loginProject.loginProject.Model.Login;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface logInRepo extends JpaRepository<Login,Integer > {
    Login findByUsername(String username);

}

/*

This interface extends JpaRepository, which is part of Spring Data JPA. It provides a powerful set of CRUD (Create, Read, Update, Delete) operations out of the box.
JpaRepository:

JpaRepository<Login,Integer>: This generic interface takes two type parameters:

Login: The entity type this repository manages.
Integer: The type of the primary key of the entity (in this case, assuming the id in Login is of type Integer).




Method Definition:
javaCopyLogin findByUsername(String username);
This is a custom query method. Spring Data JPA uses a technique called "query derivation" to automatically generate the query based on the method name.
Query Derivation:

Spring Data JPA parses the method name and creates a query based on its structure.
findBy indicates it's a select query.
Username specifies the property to query on.
The parameter String username provides the value to search for.


Background Processing:
a. When your application starts, Spring scans for interfaces that extend Repository (which JpaRepository does).
b. For each found interface, it creates a proxy implementation.
c. For the standard CRUD methods (like save(), findAll(), etc.), it uses default implementations.
d. For custom methods like findByUsername, it analyzes the method name:

It looks at the entity (Login) and finds a property matching "username".
It generates a query equivalent to: SELECT l FROM Login l WHERE l.username = ?1

e. At runtime, when you call repo.findByUsername("someUsername"):

The proxy intercepts this call.
It prepares the SQL query.
It sets "someUsername" as a parameter in the query.
Executes the query against the database.
Maps the result to a Login object and returns it.



*/
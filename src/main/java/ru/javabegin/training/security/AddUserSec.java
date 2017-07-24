package ru.javabegin.training.security;

import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * Created by Николай on 24.07.2017.
 */
@Component
public class AddUserSec implements Serializable {
    String name;
    String pass;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }
}


package ru.javabegin.training.controllers;


import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 * Created by Nick on 01.08.2015.
 */
@Component
public class IndexController {

    private String name;
    private String password;


    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login() {

        return ("login");
    }

    @RequestMapping(value = "/secondPage", method = RequestMethod.GET)
    public ModelAndView secondPage(ModelAndView modelAndView) {
        modelAndView.setViewName("secondPage");
        return modelAndView;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


}

package ru.javabegin.training.db.projections;

import java.io.Serializable;

/**
 * Created by Николай on 08.06.2017.
 */
public class Projection1 implements Serializable {
    String f_name;
    String l_name;
    String t_type;
    String t_home;
    String hobby;

    public String getHobby() {
        return hobby;
    }

    public void setHobby(String hobby) {
        this.hobby = hobby;
    }




    public String getT_type() {
        return t_type;
    }

    public void setT_type(String t_type) {
        this.t_type = t_type;
    }





    public String getT_home() {
        return t_home;
    }

    public void setT_home(String t_home) {
        this.t_home = t_home;
    }


    public Projection1(String f_name, String l_name, String t_type, String t_home,  String hobby) {
        this.f_name = f_name;
        this.l_name = l_name;
        this.t_type = t_type;
        this.hobby = hobby;
        this.t_home = t_home;
    }

    public String getF_name() {
        return f_name;
    }

    public void setF_name(String f_name) {
        this.f_name = f_name;
    }

    public String getL_name() {
        return l_name;
    }

    public void setL_name(String l_name) {
        this.l_name = l_name;
    }


    @Override
    public String toString() {
        return "First name: " + f_name + ", Last name: " + l_name + ", тип: " + t_type +", Tel : " + t_home+", hobby : " + hobby;
    }


}

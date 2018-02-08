package ru.javabegin.training.db.projections;

import java.io.Serializable;

/**
 * Created by Николай on 08.06.2017.
 */
public class Projection implements Serializable {
    String f_name;
    String l_name;

    public Projection(String f_name, String l_name) {
        this.f_name = f_name;
        this.l_name = l_name;
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
        return "First name: " + f_name + ", Last name: " + l_name ;
    }


}

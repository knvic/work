package ru.javabegin.training.security;

/**
 * Created by Николай on 10.07.2017.
 */
public class MyUsDet {
    String name;
    String aut;
    String ip;

    public MyUsDet(String name, String aut, String ip) {
        this.name = name;
        this.aut = aut;
        this.ip = ip;
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAut() {
        return aut;
    }

    public void setAut(String aut) {
        this.aut = aut;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }
}

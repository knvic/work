package ru.javabegin.training.vkt7.reports;

import ru.javabegin.training.vkt7.entities.Customer;

import java.io.Serializable;

public class DataCustomer implements Serializable{

    Customer customer;
    String moth;
    String daily_all;

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public String getMoth() {
        return moth;
    }

    public void setMoth(String moth) {
        this.moth = moth;
    }

    public DataCustomer() {
    }

    public DataCustomer(Customer customer, String moth, String daily_all) {
        this.customer = customer;
        this.moth = moth;
        this.daily_all = daily_all;
    }

    public String getDaily_all() {
        return daily_all;
    }

    public void setDaily_all(String daily_all) {
        this.daily_all = daily_all;
    }




}

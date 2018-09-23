package ru.javabegin.training.tv7.reports;


import ru.javabegin.training.vkt7.entities.Customer;

import java.io.Serializable;

/**
 * Класс для определения наличия измерений у клиента с ТВ7
 *  */

public class DataCustomerTV7 implements Serializable{

    Customer customer;
    String moth;
    String daily_all;
    String status;

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

    public String getDaily_all() {
        return daily_all;
    }

    public void setDaily_all(String daily_all) {
        this.daily_all = daily_all;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public DataCustomerTV7() {
    }



}

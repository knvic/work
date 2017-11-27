package ru.javabegin.training.vkt7.reports;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.inject.Singleton;
import java.util.List;
@Component
@Scope("singleton")
public class DataCustomerList {

    List<DataCustomer> dataCustomerList;

    public List<DataCustomer> getDataCustomerList() {
        return dataCustomerList;
    }

    public void setDataCustomerList(List<DataCustomer> dataCustomerList) {
        this.dataCustomerList = dataCustomerList;
    }
}

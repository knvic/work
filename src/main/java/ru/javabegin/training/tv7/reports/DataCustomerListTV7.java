package ru.javabegin.training.tv7.reports;


import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;


import java.util.List;

@Component
@Scope("singleton")
public class DataCustomerListTV7 {

    static List<DataCustomerTV7> dataCustomerListTV7;

    public List<DataCustomerTV7> getDataCustomerListTV7() {
        return dataCustomerListTV7;
    }

    public void setDataCustomerList(List<DataCustomerTV7> dataCustomerListTV7) { this.dataCustomerListTV7 = dataCustomerListTV7;
    }
}

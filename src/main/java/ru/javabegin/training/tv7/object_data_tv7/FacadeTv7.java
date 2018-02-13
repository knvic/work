package ru.javabegin.training.tv7.object_data_tv7;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import ru.javabegin.training.tv7.entity.Operationtv7;
import ru.javabegin.training.tv7.entity.Operationtv7T;
import ru.javabegin.training.vkt7.db.CustomerService;
import ru.javabegin.training.vkt7.entities.Customer;

import java.time.LocalDateTime;
import java.util.List;

@Component
@Scope("singleton")
public class FacadeTv7 {
    @Qualifier("jpaCustomerService")
    @Autowired
    CustomerService customerService;

    @Autowired
    SearchCriteriaTv7 searchCriteriaTv7;

    @Autowired
    SelectionTv7 selectionTv7;

    private List<Customer> customers;
    private List<Operationtv7>  operationtv7List;
    private List<Operationtv7T>  operationtv7TList;


    Customer customer;

    public List<Customer> findTv7Customers(){

        customers = customerService.findTv7Customers();

        return customers;
    }


    public List<Operationtv7> findTv7Day(){
        customer=searchCriteriaTv7.getCustomer();
        System.out.println(searchCriteriaTv7.getCustomer().getFirstName());
        Long idCustomer=customer.getId();


        operationtv7List= customerService.findOperationtv7ByDate("", idCustomer, null);
        return operationtv7List;
    }

    public List<Operationtv7T> findTv7Total(){
        customer=searchCriteriaTv7.getCustomer();
        System.out.println(searchCriteriaTv7.getCustomer().getFirstName());
        Long idCustomer=customer.getId();


        operationtv7TList= customerService.findOperationtv7TByDate("", idCustomer, null);
        return operationtv7TList;
    }

    public boolean  checkSelectedData(){
        boolean check=false;

        if (searchCriteriaTv7.getCustomer()!=null){
            check=true;
            selectionTv7.setCheck(true);
        }


        return check;
    }


}

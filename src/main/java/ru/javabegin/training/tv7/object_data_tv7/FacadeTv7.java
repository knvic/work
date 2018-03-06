package ru.javabegin.training.tv7.object_data_tv7;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import ru.javabegin.training.tv7.entity.Operationtv7;
import ru.javabegin.training.tv7.entity.Operationtv7T;
import ru.javabegin.training.tv7.excel.DataObjectTv7;
import ru.javabegin.training.tv7.excel.GetListData;
import ru.javabegin.training.tv7.excel.GetListDataImpl;
import ru.javabegin.training.vkt7.db.CustomerService;
import ru.javabegin.training.vkt7.entities.Customer;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
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

    @Autowired
    GetListData getListData;

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
        selectionTv7.setCheck(false);

        if (searchCriteriaTv7.getCustomer()!=null){
            check=true;
            selectionTv7.setCheck(true);
        }


        return check;
    }


    public boolean  checkDateOfDateToSelected(){
        boolean check=false;
        selectionTv7.setCheck(false);
        if (searchCriteriaTv7.getDay_of()!=null&&searchCriteriaTv7.getDay_of()!=null){
            check=true;
            selectionTv7.setCheck(true);
        }


        return check;
    }


    public List<Operationtv7T>  getSelectedByData() {


        Customer customer = searchCriteriaTv7.getCustomer();
        Date day_of = searchCriteriaTv7.getDay_of();
        System.out.println(" Date day_of = " + day_of);
        Date day_to = searchCriteriaTv7.getDay_to();
        System.out.println(" Date day_to = " + day_to);


        Timestamp ts_day_of = Timestamp.valueOf(LocalDateTime.ofInstant(day_of.toInstant(), ZoneId.systemDefault()));
        System.out.println(" Timestamp day_of = " + ts_day_of);

        Timestamp ts_day_to = Timestamp.valueOf(LocalDateTime.ofInstant(day_to.toInstant(), ZoneId.systemDefault()));
        System.out.println(" Timestamp day_to = " + ts_day_to);

       //Получаем список суточных
        List<Operationtv7T> operationtv7TList =customerService.findTv7T_betwen_data(customer.getId(),ts_day_of,ts_day_to);


        /**
         * Получаем дату для формирования итоговых до начала рассматриваемого периода суточных и до конца периода
         * дата начала будет предыдущей перед датой суточных, а конец дат один и тот же
         */



        //List<DataObjectTv7> objectTv7List =getListData.getTv7TList()


    return operationtv7TList;
    }


}

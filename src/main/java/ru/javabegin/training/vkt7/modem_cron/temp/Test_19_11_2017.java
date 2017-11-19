package ru.javabegin.training.vkt7.modem_cron.temp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ru.javabegin.training.vkt7.auxiliary_programs.AuxiliaryService;
import ru.javabegin.training.vkt7.auxiliary_programs.AuxiliaryServiceImpl;
import ru.javabegin.training.vkt7.db.CustomerService;
import ru.javabegin.training.vkt7.entities.Customer;
import ru.javabegin.training.vkt7.entities.Operation;

import java.util.Date;
import java.util.List;

/**
 * Created by user on 19.11.2017.
 */
@Component
//@EnableScheduling
public class Test_19_11_2017 {
    @Qualifier("jpaCustomerService")
    @Autowired
    CustomerService customerService;

    @Autowired
    AuxiliaryService auxiliaryService;

    private Long customerID;

//    @Scheduled(fixedRate = 160000)
    public void find_error(){

        Date date=new Date();
        System.out.println("Текущая дата = "+ date);
      //  AuxiliaryServiceImpl auxiliaryService=new AuxiliaryServiceImpl();

        List<Date> list= auxiliaryService.from_the_beginning_of_month(date);
        list.forEach(p->  System.out.println(p));
        List<Customer> customerList=customerService.findAllWithDetail();

        for(Customer customer:customerList) {

            for (Date day : list) {
                customerID = customer.getId();
                List<Operation> operationList = customerService.findOperation_daily(customerID, auxiliaryService.date_TimeStamp(day), "daily", "OK");
                if (operationList.size() > 0) {
                    System.out.println("CUSTOMER "+ customer.getFirstName()+" Измерения за " + day + " есть. Размер массива = " + operationList.size());
                    for (Operation o : operationList) {
                        System.out.println("getChronological() = " + o.getChronological() + "getCustomer() = " + o.getCustomer() + "getStatus() = " + o.getStatus());
                    }
                    for (int i = 1; i < operationList.size(); i++) {
                        if (operationList.get(i) != null) {
                            customerService.deleteOperation(customerID, operationList.get(i).getId());
                        } else {
                            System.out.println("operationList.get(i)== NULL . ДУБЛИРОВАНИЕ ОТСУТСТВУЕТ");
                        }
                    }

                } else {
                    System.out.println("Измерений за " + day + " НЕТ. Размер массива = " + operationList.size());
                    System.out.println("CUSTOMER "+ customer.getFirstName()+ " Дата "+ day+ " <<<----- НУЖНО ПРОВОДИТЬ ИЗМЕРЕНИЯ!!!!!!!!!!!!!");


                }
            }
            System.out.println("Проверяем наличие измерений Error за указанные даты");
            for (Date day : list) {
                List<Operation> operationList = customerService.findOperation_daily(customerID, auxiliaryService.date_TimeStamp(day), "daily", "ERROR");
                if (operationList.size() > 0) {
                    System.out.println("CUSTOMER "+ customer.getFirstName()+" Измерения ERROR за " + day + " есть. Размер массива = " + operationList.size());


                    for (Operation o : operationList) {
                        System.out.println("getChronological() = " + o.getChronological() + "getCustomer() = " + o.getCustomer() + "getStatus() = " + o.getStatus());
                        System.out.println("--->>>>> удаляем измерения с ошибками");
                        customerService.deleteOperation(customerID, o.getId());

                    }
                } else {
                    System.out.println("CUSTOMER "+ customer.getFirstName()+" Измерений ERROR за " + day + " НЕТ. Размер массива = " + operationList.size());
                }
            }


            System.out.println("Проверяем наличие измерений  TOTAL_MOTH ERROR за указанные даты");
            for (Date day : list) {
                List<Operation> operationList = customerService.findOperation_daily(customerID, auxiliaryService.date_TimeStamp(day), "total_moth", "ERROR");
                if (operationList.size() > 0) {
                    System.out.println("CUSTOMER "+ customer.getFirstName()+" Измерения ERROR за " + day + " есть. Размер массива = " + operationList.size());


                    for (Operation o : operationList) {
                        System.out.println("getChronological() = " + o.getChronological() + "getCustomer() = " + o.getCustomer() + "getStatus() = " + o.getStatus());
                        System.out.println("--->>>>> удаляем измерения с ошибками");
                        customerService.deleteOperation(customerID, o.getId());

                    }
                } else {
                    System.out.println("CUSTOMER "+ customer.getFirstName()+" Измерений ERROR за " + day + " НЕТ. Размер массива = " + operationList.size());
                }
            }


        }      ///Закрытие перебора Customer

    }

}

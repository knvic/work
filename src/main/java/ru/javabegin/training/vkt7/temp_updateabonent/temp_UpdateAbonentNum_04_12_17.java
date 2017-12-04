package ru.javabegin.training.vkt7.temp_updateabonent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import ru.javabegin.training.vkt7.auxiliary_programs.AuxiliaryServiceImpl;
import ru.javabegin.training.vkt7.db.CustomerService;
import ru.javabegin.training.vkt7.entities.Customer;
import ru.javabegin.training.vkt7.entities.Operation;
import ru.javabegin.training.vkt7.recieve.Recieve03ServiceImpl;

import java.sql.Timestamp;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class temp_UpdateAbonentNum_04_12_17 {
    @Qualifier("jpaCustomerService")
    @Autowired
    CustomerService customerService;
    @Autowired
    Recieve03ServiceImpl recieve03Service;

    public void upd()  {
        //Recieve03ServiceImpl recieve03Service=new Recieve03ServiceImpl();
        List<Customer> customerList=customerService.findAllWithDetail();
        for (Customer cust:customerList){
         List<Operation> operationList=new ArrayList<>(cust.getOperationSet());
         for(Operation op:operationList){
             op.setIdentificator(recieve03Service.hexToASCII(op.getIdentificator()));
             }
             cust.setOperationSet(operationList.stream().collect(Collectors.toSet()));

            customerService.save(cust) ;
            System.out.println("Customer "+cust+" update");

        }
/*customerList.stream()
        .forEach(cust->cust.getOperationSet().stream()
                .forEach(p->{System.out.println(p);
                p.setIdentificator(recieve03Service.hexToASCII(p.getIdentificator()));}));

*/
    }

}

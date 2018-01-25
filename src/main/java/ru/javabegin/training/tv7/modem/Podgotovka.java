package ru.javabegin.training.tv7.modem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import ru.javabegin.training.tv7.auxillary.AuxDateTimeServiceImpl;
import ru.javabegin.training.tv7.entity.Operationtv7;
import ru.javabegin.training.vkt7.db.CustomerService;
import ru.javabegin.training.vkt7.entities.Customer;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class Podgotovka  {




    public void pRun(CustomerService customerService){

        List<Customer> customerList=customerService.findAllWithDetailTv7();

        AuxDateTimeServiceImpl dateTimeService=new AuxDateTimeServiceImpl();
        LocalDateTime ldtime = LocalDateTime.now();
        List<LocalDateTime> listDate=dateTimeService.from_the_beginning_of_month(ldtime);

        for (Customer customer:customerList){
            System.out.println("\nКлиент ---------- "+ customer.getFirstName());
            for (LocalDateTime ldt:listDate ) {




                List<Operationtv7> list = customerService.findOperationtv7ByDate("day",customer.getId(),ldt);
             if (list.size()!=0){
                 System.out.println("\nИзмерения за "+ ldt +" присутствуют" );
                 System.out.println("\nРазмер найденного листа"+list.size() );

                 System.out.println("\nСуточные");
                 customer.getOperationtv7Set()
                         .stream()
                         //.sorted()
                         //.sorted((o1,o2) -> o1.getId().compareTo(o2.getId())).collect(Collectors.toList())
                         .forEach(p->System.out.println(p.getId()+" : "+p.getCustomerName()+" : "+p.getIdCustomer()+" Дата  "+ p.getChronoligical()+" Qtv1 = "+ p.getQtvTv1()));

                 System.out.println("\nИтоговые ");
                 customer.getOperationtv7TSet()
                         .stream()
                         // .sorted()
                         .forEach(p->System.out.println(p.getId()+" : "+p.getCustomerName()+" Дата  "+ p.getChronoligical()+" Qtv1 = "+ p.getQtvTv1()));

             }
             else{
                 System.out.println("\nИзмерений за "+ ldt +" НЕТ" );
             }

            }


        }


    }
}

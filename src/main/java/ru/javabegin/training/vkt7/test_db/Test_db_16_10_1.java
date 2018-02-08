package ru.javabegin.training.vkt7.test_db;

import org.quartz.JobExecutionContext;
import org.quartz.SchedulerException;
import org.quartz.TriggerKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.MethodInvokingJobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Component;
import ru.javabegin.training.vkt7.dao.OperationService;
import ru.javabegin.training.vkt7.db.CustomerService;
import ru.javabegin.training.vkt7.entities.Customer;
import ru.javabegin.training.vkt7.entities.Measurements;
import ru.javabegin.training.vkt7.entities.Operation;
import ru.javabegin.training.vkt7.measurements.MeasurementsService;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.sql.Timestamp.valueOf;

/**
 * Created by Николай on 13.10.2017.
 */
@Component
public class Test_db_16_10_1 {

    @Qualifier("jpaOperationService")
    @Autowired
private OperationService operationService;


    @Qualifier("jpaCustomerService")
    @Autowired
    private CustomerService customerService;

    public void test_db() throws SchedulerException {

        Customer customer= customerService.findById(92l);

        Map<Timestamp, List<Measurements> > mothly_hashMap = new HashMap<>();
        Map<Timestamp, List<Measurements> > total_mothly_hashMap = new HashMap<>();

        List<Measurements> measurementsList=operationService.getMeasurementsByOperationId(275l);
        Operation operation =operationService.getOperatioByOperationId(275l);
        System.out.println();
        System.out.println();

        System.out.println("Operatoin name = "+ operation.getCustomerName()+"Operatoin type = "+ operation.getTypeOperation()+"Operatoin id = "+ operation.getId());
        mothly_hashMap.put(operation.getChronological(),measurementsList);

        measurementsList=operationService.getMeasurementsByOperationId(276l);
        operation =operationService.getOperatioByOperationId(276l);
        System.out.println();
        System.out.println();
        /*for(Measurements m:measurementsList){
            System.out.println("id=" + m.getId()+
                    ", name='" + m.getName() + '\'' +
                    ", text='" + m.getText() + '\'' +
                    ", ed='" + m.getEd() + '\'' +
                    ", znak=" + m.getZnak() +
                    ", size=" + m.getSize() +
                    ", type='" + m.getType() + '\'' +
                    ", measurInt=" + m.getMeasurInt() +
                    ", measurFloat=" + m.getMeasurFloat() +
                    ", measurText='" + m.getMeasurText() + '\'' +
                    ", quality='" + m.getQuality() + '\'' +
                    ", qualityText='" + m.getQualityText() + '\'' +
                    ", ns='" + m.getNs() + '\'' +
                    ", idCount=" + m.getIdCount());
        }*/
        System.out.println("Operatoin name = "+ operation.getCustomerName()+"Operatoin type = "+ operation.getTypeOperation()+"Operatoin id = "+ operation.getId());
        mothly_hashMap.put(operation.getChronological(),measurementsList);

         measurementsList=operationService.getMeasurementsByOperationId(278l);
         operation =operationService.getOperatioByOperationId(278l);
        System.out.println();
        System.out.println();
       /* for(Measurements m:measurementsList){
            System.out.println("id=" + m.getId()+
                    ", name='" + m.getName() + '\'' +
                    ", text='" + m.getText() + '\'' +
                    ", ed='" + m.getEd() + '\'' +
                    ", znak=" + m.getZnak() +
                    ", size=" + m.getSize() +
                    ", type='" + m.getType() + '\'' +
                    ", measurInt=" + m.getMeasurInt() +
                    ", measurFloat=" + m.getMeasurFloat() +
                    ", measurText='" + m.getMeasurText() + '\'' +
                    ", quality='" + m.getQuality() + '\'' +
                    ", qualityText='" + m.getQualityText() + '\'' +
                    ", ns='" + m.getNs() + '\'' +
                    ", idCount=" + m.getIdCount());
        }*/
        System.out.println("Operatoin name = "+ operation.getCustomerName()+" Operatoin type = "+ operation.getTypeOperation()+" Operatoin id = "+ operation.getId());

        total_mothly_hashMap.put(operation.getChronological(),measurementsList);
        System.out.println("добавили в total_mothly_hashMap");
        System.out.println("ts = "+ operation.getChronological());
        System.out.println("total_mothly_hashMap.size()= "+total_mothly_hashMap.size());




        System.out.println("Содерожимое total_mothly_hashMap");
        /*for (Map.Entry entry :  total_mothly_hashMap.entrySet()) {
            System.out.println("Key: " + entry.getKey() + " Value: "
                    + entry.getValue());
        }*/
        System.out.println("Пишем  mothly_hashMap");
        for (Timestamp ts : mothly_hashMap.keySet()) {
            System.out.println(ts + " имеет");



            System.out.println(ts + " Записываем содержимое mothly_hashMap");

            Operation operation1 = new Operation();
            operation1.setTypeOperation("mothly_test");

            operation1.setChronological(ts);


            LocalDateTime today1 = LocalDateTime.now();
            Timestamp ts1 = valueOf(today1);
            operation1.setDateServer(ts1);


             for(Measurements measurements: mothly_hashMap.get(ts)){
                operation1.addMeasurements(measurements);
            }


            //Customer customer = customerService.findById(id);
            operation1.setIdCustomer(customer.getId());

            operation1.setCustomerName(customer.getFirstName());

            customer.addOperation(operation1);

           // customerService.save(customer);



        }


        System.out.println("Пишем  total_mothly_hashMap");
        for (Timestamp ts :total_mothly_hashMap.keySet()) {
            System.out.println(ts + " имеет");



            System.out.println(ts + " Записываем содержимое mothly_hashMap");

            Operation operation2 = new Operation();
            operation2.setTypeOperation("total_mothly_test");

            operation2.setChronological(ts);
            LocalDateTime today1 = LocalDateTime.now();
            Timestamp ts1 = valueOf(today1);
            operation2.setDateServer(ts1);



            for(Measurements measurements: total_mothly_hashMap.get(ts)){
                operation2.addMeasurements(measurements);
            }


            operation2.setIdCustomer(customer.getId());

            operation2.setCustomerName(customer.getFirstName());

            customer.addOperation(operation2);

           // customerService.save(customer);


        }
        customerService.save(customer);
    }
}

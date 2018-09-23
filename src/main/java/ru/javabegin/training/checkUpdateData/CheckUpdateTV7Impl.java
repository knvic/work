package ru.javabegin.training.checkUpdateData;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.javabegin.training.tv7.auxillary.AuxDateTimeServiceImpl;
import ru.javabegin.training.tv7.entity.Operationtv7;
import ru.javabegin.training.tv7.entity.Operationtv7T;
import ru.javabegin.training.tv7.reports.DataCustomerListTV7;
import ru.javabegin.training.tv7.reports.DataCustomerTV7;
import ru.javabegin.training.vkt7.auxiliary_programs.AuxiliaryService;
import ru.javabegin.training.vkt7.db.CustomerService;
import ru.javabegin.training.vkt7.entities.Customer;
import ru.javabegin.training.vkt7.entities.Measurements;
import ru.javabegin.training.vkt7.entities.Operation;
import ru.javabegin.training.vkt7.reports.DataCustomer;
import ru.javabegin.training.vkt7.reports.DataCustomerList;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service("checkUpdate")
public class CheckUpdateTV7Impl implements CheckUpdate {

   // private volatile List<DataCustomerTV7> dataCustomerList;

    @Override
    public List<DataCustomerTV7> update(CustomerService customerService, LocalDateTime date, DataCustomerListTV7 dcsTV7) throws InterruptedException {

        List<DataCustomerTV7> dataCustomerList =new ArrayList<DataCustomerTV7>();

        Logger logger = Logger.getRootLogger();

        List<Customer> customerList = customerService.findAllWithDetailTv7_not_block();

        AuxDateTimeServiceImpl dateTimeService = new AuxDateTimeServiceImpl();

        List<LocalDateTime> listDate = dateTimeService.from_the_beginning_of_month(date);


        customerList.forEach(p -> System.out.println(p.getFirstName()));
        int count_temp = 0;
        for (Customer customer : customerList) {
            count_temp = count_temp + 1;
            System.out.println("Проход по клиентам № " + count_temp);
           // Thread.sleep(2000);
            System.out.println("\nКлиент ---------- " + customer.getFirstName());


            //
            // Проверяем наличие измерения СУТОЧНЫЕ за указанный день
            // если есть, то пропускаем клиента (для ускорения)

            List<Operationtv7> listtv7 = customerService.findOperationtv7ByDate("day", customer.getId(), date);
            List<Operationtv7T> listtv7t = customerService.findOperationtv7TByDate(customer.getId(), date);


            System.out.println();
            System.out.println();
            System.out.println("//////////////////////////////////////////////////////////////////////");
            System.out.println("//////////////////////////////////////////////////////////////////////");
            System.out.println(customer.getFirstName() + " дата  " + date);
            System.out.println(customer.getFirstName() + " дата  " + date);

            System.out.println(customer.getFirstName() + " размер массива  listtv7 " + listtv7.size());
            System.out.println(customer.getFirstName() + " размер массива  listtv7t " + listtv7t.size());

            try {
                System.out.println(listtv7.get(0).getChronoligical());
            } catch (Exception e) {
                e.printStackTrace();
            }

            try {
                System.out.println(listtv7t.get(0).getChronoligical());
            } catch (Exception e) {
                e.printStackTrace();
            }


            System.out.println("//////////////////////////////////////////////////////////////////////");
            System.out.println("//////////////////////////////////////////////////////////////////////");
            System.out.println();
            System.out.println();


            DataCustomerTV7 dataCustomerTV7 = new DataCustomerTV7();
            dataCustomerTV7.setCustomer(customer);

            if (listtv7.size() != 0 && listtv7t.size() != 0) {
                System.out.println(customer.getFirstName() + " Измерения за " + date + " присутствуют");
                dataCustomerTV7.setDaily_all("OK");
                dataCustomerTV7.setMoth("OK");
                dataCustomerTV7.setStatus("ГОТОВО");

            }
            if (listtv7.size() == 0 && listtv7t.size() == 0) {
                dataCustomerTV7.setDaily_all("Отсутствуют");
                dataCustomerTV7.setMoth("Отсутствуют");
                dataCustomerTV7.setStatus("Данные не полные");
                logger.info(customer.getFirstName() + " -- Измерений => СУТОЧНЫЕ && ИТОГОВЫЕ <= за " + date + " НЕТ");
            } else if (listtv7t.size() == 0 && listtv7.size() != 0) {
                dataCustomerTV7.setDaily_all("Отсутствуют");
                dataCustomerTV7.setMoth("OK");
                dataCustomerTV7.setStatus("Данные не полные");
                logger.info(customer.getFirstName() + " -- Измерений =>СУТОЧНЫЕ <= за " + date + " НЕТ");
            }
            if (listtv7.size() != 0 && listtv7t.size() == 0) {
                dataCustomerTV7.setDaily_all("OK");
                dataCustomerTV7.setMoth("Отсутствуют");
                dataCustomerTV7.setStatus("Данные не полные");
                logger.info(customer.getFirstName() + " -- Измерений => ИТОГОВЫЕ  <= за " + date + " НЕТ");
            }


            dataCustomerList.add(dataCustomerTV7);
        }


        dcsTV7.setDataCustomerList(dataCustomerList);


        List<DataCustomerTV7> dataCustomerListPost = dcsTV7.getDataCustomerListTV7();

        return dataCustomerList;
    }
}


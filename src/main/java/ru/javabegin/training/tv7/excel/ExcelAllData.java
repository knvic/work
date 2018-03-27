package ru.javabegin.training.tv7.excel;

import org.springframework.beans.factory.annotation.Autowired;
import ru.javabegin.training.tv7.auxillary.AuxDateTimeServiceImpl;
import ru.javabegin.training.tv7.entity.Operationtv7;
import ru.javabegin.training.tv7.entity.Operationtv7T;
import ru.javabegin.training.vkt7.db.CustomerService;
import ru.javabegin.training.vkt7.entities.Customer;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class ExcelAllData {


    public void getAllExcelData(CustomerService customerService) throws InterruptedException, ExecutionException, IOException, IllegalAccessException, NoSuchFieldException {

        AuxDateTimeServiceImpl dateTimeService=new AuxDateTimeServiceImpl();


        Timestamp ts_day_of = Timestamp.valueOf(dateTimeService.addTime((LocalDateTime.now().with(TemporalAdjusters.firstDayOfMonth())),"00"));
        System.out.println(" Timestamp day_of = " + ts_day_of);

        Timestamp ts_day_to = Timestamp.valueOf(dateTimeService.addTime(LocalDateTime.now(),"00"));
        System.out.println(" Timestamp day_to = " + ts_day_to);



        /**
         * Получаем дату для формирования 2-х ИТОГОВЫХ до начала рассматриваемого периода суточных и до конца периода
         * дата начала будет предыдущей перед датой суточных, а конец дат один и тот же
         */
        Timestamp ts_day_of_minus_1 = Timestamp.valueOf(dateTimeService.addTime((LocalDateTime.now().with(TemporalAdjusters.firstDayOfMonth())).minusDays(1),"00"));
        System.out.println(" Timestamp day_of = " + ts_day_of_minus_1);


        String dir= "C:/demo/TV7/"+LocalDateTime.now().format(DateTimeFormatter.ofPattern("d_MM_uuuu_HH_mm"))+"/";
        File direct = new File("C://demo//TV7//"+LocalDateTime.now().format(DateTimeFormatter.ofPattern("d_MM_uuuu_HH_mm")));
        boolean created = direct.mkdir();
        if(created){
            System.out.println("Каталог"+"C://demo//TV7//"+LocalDateTime.now().format(DateTimeFormatter.ofPattern("d_MM_uuuu_HH_mm"))+" успешно создан");
            //    logger.info("Каталог успешно создан");
        }


        List<Customer> customers = customerService.findTv7Customers();

        for (Customer customer:customers ) {



            //Получаем список СУТОЧНЫХ
            List<Operationtv7> operationtv7List =customerService.findTv7_betwen_data(customer.getId(),ts_day_of,ts_day_to,"day","");





            //Получаем список ИТОГОВЫХ
            List<Operationtv7T> operationtv7TList =customerService.findTv7T_betwen_data(customer.getId(),ts_day_of_minus_1,ts_day_to);

            // В окончательный список итоговых добавляем значение начала-1 и конца периода времени
            List<Operationtv7T> total=new ArrayList<>();


            if (operationtv7List.size()==0&&operationtv7TList.size()==0 ){
                continue;
            }


            try {
                total.add(operationtv7TList.get(0));
            }catch (Exception e){
                System.out.println("Внимание!! Значение Итогового архива для формирования Excel  начала периода не существует");
            }
            try {
                total.add(operationtv7TList.get(operationtv7TList.size()-1));
            }catch (Exception e){
                System.out.println("Внимание!! Значение Итогового архива для формирования Excel  конца периода не существует");
            }
            List<Object> listData = new ArrayList<>();
            listData.add(0,operationtv7List);
            listData.add(1,total);


            Calculation_xls calculation_xls = new Calculation_xls();



            calculation_xls.archiveXsl(customer, operationtv7List, total, dir );
            // List<DataObjectTv7>

        }



    }
}

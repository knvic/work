package ru.javabegin.training.tv7.object_data_tv7;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import ru.javabegin.training.tv7.auxillary.AuxDateTimeServiceImpl;
import ru.javabegin.training.tv7.entity.Operationtv7;
import ru.javabegin.training.tv7.entity.Operationtv7T;
import ru.javabegin.training.tv7.excel.*;
import ru.javabegin.training.vkt7.db.CustomerService;
import ru.javabegin.training.vkt7.entities.Customer;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutionException;

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


        operationtv7TList= customerService.findOperationtv7TByDate( idCustomer, null);
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


        //Получаем список ИТОГОВЫХ
        List<Operationtv7T> operationtv7TList =customerService.findTv7T_betwen_data(customer.getId(),ts_day_of,ts_day_to);

        /**
         * Получаем дату для формирования 2-х ИТОГОВЫХ до начала рассматриваемого периода суточных и до конца периода
         * дата начала будет предыдущей перед датой суточных, а конец дат один и тот же
         */
        Timestamp ts_day_of_minus_1 = Timestamp.valueOf(LocalDateTime.ofInstant(day_of.toInstant(), ZoneId.systemDefault()).minusDays(1));
        System.out.println(" Timestamp day_of = " + ts_day_of_minus_1);




        //List<DataObjectTv7> objectTv7List =getListData.getTv7TList()


    return operationtv7TList;
    }


    public List<Object> getSelectedByData_Archive() throws NoSuchFieldException, IllegalAccessException, ExecutionException, InterruptedException {


        Customer customer = searchCriteriaTv7.getCustomer();
        Date day_of = searchCriteriaTv7.getDay_of();
        System.out.println(" Date day_of = " + day_of);
        Date day_to = searchCriteriaTv7.getDay_to();
        System.out.println(" Date day_to = " + day_to);


        Timestamp ts_day_of = Timestamp.valueOf(LocalDateTime.ofInstant(day_of.toInstant(), ZoneId.systemDefault()));
        System.out.println(" Timestamp day_of = " + ts_day_of);

        Timestamp ts_day_to = Timestamp.valueOf(LocalDateTime.ofInstant(day_to.toInstant(), ZoneId.systemDefault()));
        System.out.println(" Timestamp day_to = " + ts_day_to);


        //Получаем список СУТОЧНЫХ
        List<Operationtv7> operationtv7List =customerService.findTv7_betwen_data(customer.getId(),ts_day_of,ts_day_to,"day","");



        /**
         * Получаем дату для формирования 2-х ИТОГОВЫХ до начала рассматриваемого периода суточных и до конца периода
         * дата начала будет предыдущей перед датой суточных, а конец дат один и тот же
         */
        Timestamp ts_day_of_minus_1 = Timestamp.valueOf(LocalDateTime.ofInstant(day_of.toInstant(), ZoneId.systemDefault()).minusDays(1));
        System.out.println(" Timestamp day_of = " + ts_day_of_minus_1);

        //Получаем список ИТОГОВЫХ
        List<Operationtv7T> operationtv7TList =customerService.findTv7T_betwen_data(customer.getId(),ts_day_of_minus_1,ts_day_to);

        // В окончательный список итоговых добавляем значение начала-1 и конца периода времени
        List<Operationtv7T> total=new ArrayList<>();

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

        Calculation calculation = new Calculation();

        List<Object> dataObject = calculation.archiveXsl(operationtv7List, total );
       // List<DataObjectTv7>



        return  dataObject;
    }





    public void getExcel_archive() throws NoSuchFieldException, IllegalAccessException, ExecutionException, InterruptedException, IOException {


        Customer customer = searchCriteriaTv7.getCustomer();
        Date day_of = searchCriteriaTv7.getDay_of();
        System.out.println(" Date day_of = " + day_of);
        Date day_to = searchCriteriaTv7.getDay_to();
        System.out.println(" Date day_to = " + day_to);


        Timestamp ts_day_of = Timestamp.valueOf(LocalDateTime.ofInstant(day_of.toInstant(), ZoneId.systemDefault()));
        System.out.println(" Timestamp day_of = " + ts_day_of);

        Timestamp ts_day_to = Timestamp.valueOf(LocalDateTime.ofInstant(day_to.toInstant(), ZoneId.systemDefault()));
        System.out.println(" Timestamp day_to = " + ts_day_to);


        //Получаем список СУТОЧНЫХ
        List<Operationtv7> operationtv7List =customerService.findTv7_betwen_data(customer.getId(),ts_day_of,ts_day_to,"day","");



        /**
         * Получаем дату для формирования 2-х ИТОГОВЫХ до начала рассматриваемого периода суточных и до конца периода
         * дата начала будет предыдущей перед датой суточных, а конец дат один и тот же
         */
        Timestamp ts_day_of_minus_1 = Timestamp.valueOf(LocalDateTime.ofInstant(day_of.toInstant(), ZoneId.systemDefault()).minusDays(1));
        System.out.println(" Timestamp day_of = " + ts_day_of_minus_1);

        //Получаем список ИТОГОВЫХ
        List<Operationtv7T> operationtv7TList =customerService.findTv7T_betwen_data(customer.getId(),ts_day_of_minus_1,ts_day_to);

        // В окончательный список итоговых добавляем значение начала-1 и конца периода времени
        List<Operationtv7T> total=new ArrayList<>();

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



        calculation_xls.archiveXsl(customer, operationtv7List, total );
        // List<DataObjectTv7>




    }

    public void getExcel_ALL_archive() throws NoSuchFieldException, IllegalAccessException, ExecutionException, InterruptedException, IOException {

/*
        Customer customer = searchCriteriaTv7.getCustomer();
        Date day_of = searchCriteriaTv7.getDay_of();
        System.out.println(" Date day_of = " + day_of);
        Date day_to = searchCriteriaTv7.getDay_to();
        System.out.println(" Date day_to = " + day_to);


        AuxDateTimeServiceImpl dateTimeService=new AuxDateTimeServiceImpl();

        LocalDateTime ldt = LocalDateTime.now();

        List<LocalDateTime> list=dateTimeService.from_the_beginning_of_month(ldt);
        list.forEach(p-> System.out.println(p));*/

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


        String dir= "C:/demo/ТВ7/"+LocalDateTime.now().format(DateTimeFormatter.ofPattern("d_MM_uuuu_HH_mm"))+"/";
        File direct = new File("C://demo//ТВ7//"+LocalDateTime.now().format(DateTimeFormatter.ofPattern("d_MM_uuuu_HH_mm")));
        boolean created = direct.mkdir();
        if(created){
            System.out.println("Каталог"+"C://demo//ТВ7//"+LocalDateTime.now().format(DateTimeFormatter.ofPattern("d_MM_uuuu_HH_mm"))+" успешно создан");
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



        calculation_xls.archiveXsl(customer, operationtv7List, total );
        // List<DataObjectTv7>

        }


    }



}

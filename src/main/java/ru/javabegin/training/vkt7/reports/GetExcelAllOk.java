package ru.javabegin.training.vkt7.reports;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import ru.javabegin.training.vkt7.auxiliary_programs.AuxiliaryService;
import ru.javabegin.training.vkt7.auxiliary_programs.AuxiliaryServiceImpl;
import ru.javabegin.training.vkt7.db.CustomerService;
import ru.javabegin.training.vkt7.entities.Customer;
import ru.javabegin.training.vkt7.entities.Operation;
import ru.javabegin.training.vkt7.exсel.ConvertExcel;

import java.io.File;
import java.io.Serializable;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 * Created by Николай on 18.09.2017.
 */

  @Component

public class GetExcelAllOk implements Serializable {

    @Autowired
    @Qualifier("jpaCustomerService")
 CustomerService customerService;
   /* @Autowired
    DataCustomerList dataCustomerList;*/


    @Autowired
    ReportService reportService;


    public void update(CustomerService customerService,ReportService reportService, AuxiliaryService auxiliaryService){

        org.apache.log4j.Logger logger = org.apache.log4j.Logger.getRootLogger();

        Callable task = () -> {
            //AuxiliaryServiceImpl auxiliaryService=new AuxiliaryServiceImpl();
                System.out.println("работает поток "+ Thread.currentThread().getName());
            logger.info("Формирование всех OK. Работает поток "+ Thread.currentThread().getName());
            System.out.println("Формируем файлы Excel для всех готовых (OK) данных DataCustomerList!!!");
                    //customerService.customerOperationStatus();

            //File file = new File("C:/demo/"+customer.getFirstName()+"_"+dateTime.format(DateTimeFormatter.ofPattern("d_MM_uuuu_HH_mm"))+".xls");
String dir= "C:/demo/ВКТ7/"+LocalDateTime.now().format(DateTimeFormatter.ofPattern("d_MM_uuuu_HH_mm"))+"/";
            File direct = new File("C://demo//ВКТ7//"+LocalDateTime.now().format(DateTimeFormatter.ofPattern("d_MM_uuuu_HH_mm")));
            boolean created = direct.mkdir();
            if(created){
                System.out.println("Каталог"+"C://demo//ВКТ7//"+LocalDateTime.now().format(DateTimeFormatter.ofPattern("d_MM_uuuu_HH_mm"))+" успешно создан");
            logger.info("Каталог успешно создан");}




            List<DataCustomer> list=DataCustomerList.dataCustomerList;
            System.out.println("размер list= "+list);
            List<Customer> customerList=new ArrayList<>();

          /*  list.stream()
                    .filter((p)->p.getStatus().contains("OK")).collect(Collectors.toList())*/

          for(DataCustomer dk:list){
              if(dk.getStatus().equals("ГОТОВО")){
                  customerList.add(dk.getCustomer());

              }
          }

          for(Customer customer:customerList){


              Date date = new Date();
              date=auxiliaryService.addTime(date,"23");
              Timestamp date_ts=auxiliaryService.date_TimeStamp(date);
              List<Date> date_daily_List =auxiliaryService.from_the_beginning_of_month(date);
              Timestamp temp_ot=auxiliaryService.date_TimeStamp(date_daily_List.get(0));
              Timestamp temp_do=date_ts;
              System.out.println("date_daily_List.get(0) "+temp_ot);
              System.out.println("date_ts "+date_ts);
              List<Operation> operationList_daily=customerService.findOperation_betwen_data(customer.getId(),auxiliaryService.date_TimeStamp(date_daily_List.get(0)),date_ts,"daily","OK");

              ConvertExcel convertExcel=new ConvertExcel();
              // convertExcel.excel();
              List<Object> object= reportService.getObject_ns(operationList_daily);
              List<DataObject> dataObjectList =(List<DataObject>) object.get(0);
              List<String> column=(List<String>) object.get(1);
              System.out.println("размер 0" +dataObjectList.size());
              System.out.println("размер 1" +column.size());

              List<Object> object_calc= reportService.getCalculations(dataObjectList,column);


              DataObject sum =  (DataObject)object_calc.get(0);
              DataObject average= (DataObject)object_calc.get(1);

            logger.info("Данные daily для "+ customer.getFirstName()+" сформированы");

              ///Дата предыдущего месяца
              Timestamp date_prevision_moth =auxiliaryService.getLastDayPrevisionMoth(auxiliaryService.date_TimeStamp(date_daily_List.get(0)));



              List<Operation> operationList_total= customerService.findOperation_daily(customer.getId(),date_prevision_moth, "total_moth","OK");
              System.out.println("размер массива operationList_total "+operationList_total.size() );
              logger.info("Измерение total_moth для "+ customer.getFirstName()+" есть" + operationList_total.size());
              List<Object> object_total=reportService.getCalculations_total(operationList_total,sum);
              List<DataObject> total_list = (List<DataObject>)object_total.get(0);
              List<String> list_calc_total=(List <String>)object_total.get(2);

              List<String> col_sum_average = new ArrayList<>(sum.getOptionalValues().keySet());

              col_sum_average=reportService.sort(col_sum_average);


              logger.info("Данные для преобразования excel для "+ customer.getFirstName()+" готовы" );


              convertExcel.excel_current_OK(customer,dir , dataObjectList ,sum,average,total_list,column,col_sum_average,list_calc_total);
              logger.info("excel для "+ customer.getFirstName()+" сформирован" );
          }


                return "123";

        };



       ExecutorService serviceDataCustomerList = Executors.newSingleThreadExecutor();
        Future<String> dcl= serviceDataCustomerList.submit(task);


        serviceDataCustomerList.shutdown();

        //System.out.println("Основная программа работу закончила");


    }


}

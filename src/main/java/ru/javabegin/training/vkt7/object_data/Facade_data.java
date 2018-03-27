package ru.javabegin.training.vkt7.object_data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import ru.javabegin.training.recoveryData.RecoveryService;
import ru.javabegin.training.vkt7.auxiliary_programs.AuxiliaryService;
import ru.javabegin.training.vkt7.dao.OperationService;
import ru.javabegin.training.vkt7.db.CustomerService;
import ru.javabegin.training.vkt7.entities.Customer;
import ru.javabegin.training.vkt7.entities.Measurements;
import ru.javabegin.training.vkt7.entities.Operation;
import ru.javabegin.training.vkt7.exсel.ConvertExcel;
import ru.javabegin.training.vkt7.object.SearchCriteria_cust;
import ru.javabegin.training.vkt7.object.SearchCriteria_oper;
import ru.javabegin.training.vkt7.object_modem.SearchCriteria_modem;
import ru.javabegin.training.vkt7.propert.entities.Properts;
import ru.javabegin.training.vkt7.recovery.Item;
import ru.javabegin.training.vkt7.reports.*;

import java.io.IOException;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Timestamp;
import java.time.*;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by Николай on 14.06.2017.
 */
@Component
@Scope("singleton")
public class Facade_data implements Serializable{

    @Qualifier("jpaCustomerService")
    @Autowired
    CustomerService customerService;

    @Qualifier("jpaOperationService")
    @Autowired
    OperationService operationService;

    @Autowired
    ReportService reportService;

    @Autowired
    AuxiliaryService auxiliaryService;
    @Autowired
    private DataCustomerList dcl;


   @Autowired
    private SearchCriteria_cust searchCriteria_cust;
    @Autowired
    private SearchCriteria_modem searchCriteria_oper;

    @Autowired
    private SearchCriteria_data searchCriteria_data;
    @Autowired
    private SelectionData selectionData;



    private Customer selcustomer;

    private Customer customer;

    private Archive archive;

    private Operation oper_temp;
    private Operation oper_temp1;




    private List<Customer> customers;
    private List<Operation> operations;
    private List<Measurements> measurementsList;
    private List<Archive> archiveList;


    public List<Archive> getArchiveList() {

        return archiveList;
    }

    public void setArchiveList(List<Archive> archiveList) {
        this.archiveList = archiveList;
    }



   public void testMeasur(){

     List<DataObject> data=new ArrayList<>();

     List<String> id_col = new ArrayList<>();
       id_col.add("t1");
       id_col.add("t2");
       id_col.add("v1");
       id_col.add("v2");


       DataObject data_item=new DataObject();
       Map<String,Tupel> m=new HashMap<String,Tupel>();
       Tupel t1=new Tupel("t1",new BigDecimal("62.09"));
       Tupel t2=new Tupel("t2",new BigDecimal("55.55"));
       Tupel t3=new Tupel("v1",new BigDecimal("34.234234"));
       Tupel t4=new Tupel("v2",new BigDecimal("11.12121"));
       m.put(t1.getId(),t1);
       m.put(t2.getId(),t2);
       m.put(t3.getId(),t3);
       m.put(t4.getId(),t4);

       System.out.println(m);

       data_item.setOptionalValues(m);
       data.add(data_item);

       Tupel t11=new Tupel("t1",new BigDecimal("11.11"));
       Tupel t12=new Tupel("t2",new BigDecimal("22.22"));
       Tupel t13=new Tupel("v1",new BigDecimal("33.33333"));
       Tupel t14=new Tupel("v2",new BigDecimal("44.44444"));

       Map<String,Tupel> m1=new HashMap<String,Tupel>();
       m1.put(t1.getId(),t11);
       m1.put(t2.getId(),t12);
       m1.put(t3.getId(),t13);
       m1.put(t4.getId(),t14);

       System.out.println(m1);

       DataObject data_item1=new DataObject();
       data_item1.setOptionalValues(m1);
       data.add(data_item1);

       System.out.println(data.size());




       searchCriteria_data.setData(data);

       searchCriteria_data.setId_item(id_col);

    }


   public List<Object>  getSelectedByData(){
            //Получаем Customer
       Customer customer = searchCriteria_data.getCustomer();
       //получаем даты для которых производим вычисления
       Date day_of =searchCriteria_data.getDay_of();
       System.out.println(" Date day_of = "+ day_of);
       Date day_to = searchCriteria_data.getDay_to();
       System.out.println(" Date day_to = "+ day_to);


        ///преобразуем даты из Data в Timestamp для запросов к БД
       Timestamp ts_day_of = Timestamp.valueOf(LocalDateTime.ofInstant( day_of.toInstant(), ZoneId.systemDefault()));
       System.out.println(" Timestamp day_of = "+ ts_day_of);

       Timestamp ts_day_to = Timestamp.valueOf(LocalDateTime.ofInstant( day_to.toInstant(), ZoneId.systemDefault()));
       System.out.println(" Timestamp day_to = "+ ts_day_to);


       //Получаем из БД список измерений можду указанными датами
       List<Operation> operationList= customerService.findOperation_betwen_data(customer.getId(),ts_day_of,ts_day_to, "daily","OK");

       System.out.println(operationList.size());

       //// удаляем клоны

       for (int i=0; i<operationList.size(); i++){
           System.out.println("operationList.size()= " +operationList.size());
           oper_temp=operationList.get(i);
           for (int j=0; j<operationList.size(); j++){
               oper_temp1=operationList.get(j);
               System.out.println("oper_temp = "+ oper_temp.getId());
               System.out.println("oper_temp1 = "+ oper_temp1.getId());
               if(oper_temp.getId().compareTo(oper_temp1.getId())!=0){System.out.println("Равно!!");}
               if (oper_temp.getChronological().equals(oper_temp1.getChronological())& oper_temp.getId().compareTo(oper_temp1.getId())!=0){
                   System.out.println("Найден клон!!"+ oper_temp1.getId());

                    Customer cust=customerService.findById(customer.getId());
                    Set<Operation> operationSet=cust.getOperationSet();
                    Operation toDelOperation=null;
                    for( Operation operation:operationSet){
                        if (operation.getId().equals(oper_temp1.getId())){
                            toDelOperation=operation;
                        }
                    }
                   operationSet.remove(toDelOperation);
                   customerService.save(cust);
                   operationList= customerService.findOperation_betwen_data(customer.getId(),ts_day_of,ts_day_to, "daily","OK");
                   System.out.println("operationList.size()= " +operationList.size());
               }
           }

       }
///// клон удален

       List<Object> res= reportService.getObject_ns(operationList);
       List<DataObject> dataObjectList=(List<DataObject>)res.get(0);

       List<String> list1=(List<String>)res.get(1);
       List<DataObject_str> dataObjectList_str=reportService.getObject_ns_to_Str(dataObjectList,list1);


       List<Object> calculation = reportService.getCalculations(dataObjectList,list1);

       DataObject sum=(DataObject)calculation.get(0);
       DataObject average=(DataObject)calculation.get(1);
       DataObject_str sum_str=(DataObject_str) calculation.get(2);
       DataObject_str average_str=(DataObject_str) calculation.get(3);
       List<String> list_calc=new ArrayList<>(sum.getOptionalValues().keySet());
       list_calc=reportService.sort(list_calc);

       list_calc.forEach(p->System.out.print(p+" "));

       for(String s:list_calc) {
       //for(String s:sum_str.getOptionalValues().keySet()) {

           if(sum_str.getOptionalValues().get(s).getValue()!=null){
           System.out.print(sum.getOptionalValues().get(s).getValue() + "    ");}


       }
       System.out.println();
       for(String s:list_calc) {
       //for(String s:sum_str.getOptionalValues().keySet()) {

           if(average_str.getOptionalValues().get(s).getValue()!=null){
           System.out.print(average.getOptionalValues().get(s).getValue() + "    ");}
           // System.out.println();
       }

List<DataObject_str> dataObject_calc_strList =new ArrayList<>();
       dataObject_calc_strList.add(sum_str);
       dataObject_calc_strList.add(average_str);

      // searchCriteria_data.setData(dataObjectList);
       searchCriteria_data.setDataObject_strList(dataObjectList_str);
       searchCriteria_data.setId_item(list1);
       searchCriteria_data.setDataObject_calc_strList(dataObject_calc_strList);
       searchCriteria_data.setCalc_item(list_calc);

        ////получаем Итоговы текущие
       LocalDateTime date_moth=auxiliaryService.timestamp_to_localDateTime(ts_day_to);
       ///Дата предыдущего месяца
       Timestamp date_prevision_moth =auxiliaryService.getLastDayPrevisionMoth(ts_day_to);
       List<Operation> operationList_total= customerService.findOperation_daily(customer.getId(),date_prevision_moth, "total_moth","OK");
       System.out.println("размер массива operationList_total "+operationList_total.size() );
       /// Если Итоговые есть, то выполняем проверка на наличие ИТОГОВЫХ за предыдущий месяц
       List<DataObject_str> total_current_str=new ArrayList<>();
       List<String> total_current_column=new ArrayList<>();
        if(operationList_total.size()!=0) {
            List<Object> total_currint_from_calss = reportService.getCalculations_total(customerService,operationList_total, sum);
            List<DataObject> total_current = (List<DataObject>) total_currint_from_calss.get(0);
            total_current_str = (List<DataObject_str>) total_currint_from_calss.get(1);
            total_current_column = (List<String>) total_currint_from_calss.get(2);

            total_current_str.get(1).setData(auxiliaryService.timeStamp_to_string(auxiliaryService.minusDay(ts_day_to, 1)));


            searchCriteria_data.setTotal_current_str(total_current_str);
            searchCriteria_data.setTotal_current_column(total_current_column);

            ///проверяем итоговые значение
            System.out.printf("%10s", " ");
            for (String col : total_current_column) {
                //System.out.print(col+ "    ");
                System.out.printf("%18s", col);

            }
            System.out.println();

            for (DataObject_str objectStr : total_current_str) {
                System.out.printf("%10s", objectStr.getData());
                for (String col : total_current_column) {

                    //System.out.print(objectStr.getOptionalValues().get(col).getValue()+" ");
                    System.out.printf("%18s", objectStr.getOptionalValues().get(col).getValue());

                }
                System.out.println();
            }

        }/// конец проверка на наличие ИТОГОВЫХ за предыдущий месяц

       else{
            searchCriteria_data.setTotal_current_str(null);
            searchCriteria_data.setTotal_current_column(null);
        }

        List<Object> objectList=new ArrayList<>();
       objectList.add(0,total_current_column);
       objectList.add(1,total_current_str);

       return objectList;


    }


    public void getExcel() throws IOException {
        Customer customer = searchCriteria_data.getCustomer();
        Date date = new Date();
        date=auxiliaryService.addTime(date,"23");
        Timestamp date_ts=auxiliaryService.date_TimeStamp(date);
        List<Date> date_daily_List =auxiliaryService.from_the_beginning_of_month(date);
        List<Operation> operationList_daily=customerService.findOperation_betwen_data(customer.getId(),auxiliaryService.date_TimeStamp(date_daily_List.get(0)),date_ts,"daily","OK");

        ConvertExcel convertExcel=new ConvertExcel();
       // convertExcel.excel();
        List<Object> object= reportService.getObject_ns(operationList_daily);
        List<DataObject> dataObjectList =(List<DataObject>) object.get(0);
        List<String> column=(List<String>) object.get(1);
        System.out.println("размер 0" +dataObjectList.size());
        System.out.println("размер 1" +column.size());
        convertExcel.excel(customer,dataObjectList, column);


   }

    public void getExcel_current() throws IOException {
        Customer customer = searchCriteria_data.getCustomer();
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



        ///Дата предыдущего месяца
        Timestamp date_prevision_moth =auxiliaryService.getLastDayPrevisionMoth(auxiliaryService.date_TimeStamp(date_daily_List.get(0)));



        List<Operation> operationList_total= customerService.findOperation_daily(customer.getId(),date_prevision_moth, "total_moth","OK");
        System.out.println("размер массива operationList_total "+operationList_total.size() );

        List<Object> object_total=reportService.getCalculations_total(customerService, operationList_total,sum);
        List<DataObject> total_list = (List<DataObject>)object_total.get(0);
        List<String> list_calc_total=(List <String>)object_total.get(2);

        List<String> col_sum_average = new ArrayList<>(sum.getOptionalValues().keySet());

        col_sum_average=reportService.sort(col_sum_average);





                convertExcel.excel_current(customer, dataObjectList ,sum,average,total_list,column,col_sum_average,list_calc_total);


    }

    public void getExcel_current_all_OK() throws IOException {

GetExcelAllOk getExcelAllOk=new GetExcelAllOk();
getExcelAllOk.update(customerService, reportService, auxiliaryService);

    }


    public String update_Q_current_all_OK() throws IOException, InterruptedException {

/*
        List<Customer> list=customerService.findAll();
        for (Customer dk:list ) {
            //удаляем предыдущие данные перед новым считыванием данных по Q
            dk.setQ_begin_1("");
            dk.setQ_now_1("");
            dk.setQ_sum_1("");
            dk.setQ_begin_2("");
            dk.setQ_now_2("");
            dk.setQ_sum_2("");
            customerService.save(dk);
        }*/




        LocalDateTime ldt= LocalDateTime.now();
       String date= auxiliaryService.date_to_vktString(ldt);

       searchCriteria_cust.setData_Q(date);

        Get_Q_AllOk get_q_allOk=new Get_Q_AllOk();
        get_q_allOk.update(customerService, reportService, auxiliaryService);

        return date;

    }



    public List<DataCustomer> getCustomerStatus(){
        //List<DataCustomer> dataCustomerList=customerService.customerOperationStatus();
        List<DataCustomer> dataCustomerList=dcl.getDataCustomerList();
return dataCustomerList;
    }


    public List<DataCustomer> getCustomerStatusNotOk(){


        List<DataCustomer> dataCustomerList=dcl.getDataCustomerList();

        List<DataCustomer> out=dataCustomerList.stream().filter((p)->!p.getStatus().contains("ГОТОВО")).collect(Collectors.toList());
        return out;
    }


    public List<DataCustomer> getCustomerStatusOk(){


        List<DataCustomer> dataCustomerList=dcl.getDataCustomerList();

        List<DataCustomer> out=dataCustomerList.stream().filter((p)->p.getStatus().contains("ГОТОВО")).collect(Collectors.toList());
        return out;
    }




    public List<DataCustomer> getCustomerStatusNotMonthOkDay(){


        List<DataCustomer> dataCustomerList=dcl.getDataCustomerList();

        List<DataCustomer> out=dataCustomerList.stream().filter((p)->!p.getStatus().contains("ГОТОВО")&&p.getDaily_all().contains("OK")).collect(Collectors.toList());
        return out;
    }

    public void test_getCustomerStatus(){
        //List<DataCustomer> dataCustomerList=customerService.test_customerOperationStatus();

        //customerService.test_customerOperationStatus();
       /* customerService.deleteOperation(104L,14767L);
        customerService.deleteOperation(104L,15371L);
        customerService.deleteOperation(104L,15372L);*/

        customerService.deleteOperationQualityErr();

        //return dataCustomerList;
    }




    public List<String> getTotal_current_column(){

       List<String> tcc=searchCriteria_data.getTotal_current_column();
       if(tcc==null){
           System.out.println("Массив пустой");
       }else {
           System.out.println("Массив есть!!");
       }

       return tcc;
    }



    public List<Object> getArchiveByCustomer(){


        Long id_Customer = searchCriteria_data.getCustomer().getId();
        Timestamp tstamp1=searchCriteria_data.getOperation().getChronological();
        String type=searchCriteria_data.getOperation().getTypeOperation();


        List<Operation> operationList= customerService.findOperation_total_moth(id_Customer, tstamp1, type, "OK");
        List<Object> res= reportService.getObject_ns(operationList);
        List<DataObject> dataObjectList=(List<DataObject>)res.get(0);
        List<String> list1=(List<String>)res.get(1);

       searchCriteria_data.setData(dataObjectList);
       searchCriteria_data.setId_item(list1);

        List<Object> dop=new ArrayList<>();
        dop.add(searchCriteria_data.getOperation().getCustomerName());
        dop.add(searchCriteria_data.getOperation().getChronological());

        return dop;
    }





    public List<Customer> getCustomers(){

        if (customers==null){
            customers = customerService.findAll();
        }
        return customers;
    }

/////// выборка по операциям
    public List<Operation> getOperations(){

       /* if (operations==null){
            operations = customerService.findAll();
        }*/

        Long id = searchCriteria_data.getId();

        operations= customerService.getOperationsByCustomerId(id);
        return operations;
    }





    public List<Operation> getAllOperationsByCustomer(){

       /* if (operations==null){
            operations = customerService.findAll();
        }*/

        Long id_Customer = searchCriteria_data.getCustomer().getId();
        //Long id_Customer = selectionData.getCust().getId();

        //operations= customerService.getOperationsByCustomerId(id);


        //operations=operationService.findOperationByIdCustomer(id_Customer);
        operations= customerService.findOperation_daily(id_Customer,null , null, null);
        return operations;
    }


    public boolean  checkSelectedData(){
        boolean check=false;

        if (searchCriteria_data.getCustomer()!=null){
            check=true;
            selectionData.setCheck(true);
        }


        return check;
    }



    /////////////Measurements ////////////////
    public List<Measurements> getMeasurementsList() {


       /* if (measurementsList==null){
            Long id=searchCriteria_data.getOperation().getId();
            System.out.println("id="+id);
            measurementsList = operationService.getMeasurementsByOperationId(id);
        }*/
        Long id=searchCriteria_data.getOperation().getId();
        System.out.println("id="+id);
        measurementsList = operationService.getMeasurementsByOperationId(id);



        return measurementsList;
    }

    public void setMeasurementsList(List<Measurements> measurementsList) {
        this.measurementsList = measurementsList;
    }




    ////////////////////////////////////////

   //////// выборка по измерениям


  /*
    public List<Measurements> getmeasurementsList(){

       *//* if (operations==null){
            operations = customerService.findAll();
        }*//*

        Long id = searchCriteria_data.getId();

        measurementsList= operationService.getMeasurementsByOperationId(id);
        return measurementsList;
    }

*/

    public List<Customer> findAllCustomers(){

       customers = customerService.findAll();

        return customers;
    }
    public void saveCustomer(){
        Customer c= searchCriteria_cust.getCustomer();

            if (!(c.getFirstName()).equals("")) {
                customerService.save(c);
            }

    }

    public void deleteCustomer(){
        Customer c= searchCriteria_cust.getCustomer();

            if (!(c.getFirstName()).equals("")) {
                customerService.delete(c);
                searchCriteria_cust.setCustomer(null);
            }

    }
    public List<Customer> findAllCustomers1(Customer customer){

        customers=null;
        customers=new ArrayList<>();
        customers.add(customer);

        return customers;
    }








 /*   public List<Customer> searchCustomerById() {
        customer= searchCriteria_modem.getCustomer();

      //  Long id = c.getId();
        //customer = customerService.findById(id);
      //  customer = customerService.findById(id);
        customers=null;
        customers=new ArrayList<>();
        customers.add(customer);
        return customers;
    }*/


    public Customer searchCustomer_1_ById() {
        customer= searchCriteria_cust.getCustomer();

        //  Long id = c.getId();
        //customer = customerService.findById(id);
        //  customer = customerService.findById(id);

        return customer;
    }



    public List<Operation> findOperationById(){

        Long id = searchCriteria_cust.getId();

        operations= customerService.findOperationByIdCustomer(id);
        return operations;

    }

    @Autowired
    UpdateMoth updateMoth;
    public void recover() throws IOException {

        Item item = searchCriteria_cust.getItem();

        String name=item.getName();

        System.out.println("Name="+name);


        updateMoth.update(name);
    }

@Autowired
    RecoveryService recoveryService;

    public void recover_new() throws IOException {

        Item item = searchCriteria_cust.getItem();

        String name=item.getName();

        System.out.println("Name="+name);

recoveryService.Recovery_month(name);
        //updateMoth.update(name);
    }

    public void recover_day() throws IOException, InterruptedException {
        Item item = searchCriteria_cust.getItem();

        String name=item.getName();

        System.out.println("Name="+name);

        String otvet = recoveryService.recoveryDay(name);

       System.out.println(otvet);
        System.out.println("Добавляем в DataCustomerList" );


    }





@Autowired
UpdateDataCustomerList updateDataCustomerList;
    public void okExcel(){
        //updateDataCustomerList.update();
        customerService.customerOperationStatus_Threads();

    }








}

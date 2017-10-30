package ru.javabegin.training.vkt7.object_reports;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import ru.javabegin.training.vkt7.dao.OperationService;
import ru.javabegin.training.vkt7.db.CustomerService;
import ru.javabegin.training.vkt7.entities.Customer;
import ru.javabegin.training.vkt7.entities.Measurements;
import ru.javabegin.training.vkt7.entities.Operation;
import ru.javabegin.training.vkt7.object.SearchCriteria_cust;
import ru.javabegin.training.vkt7.object_data.SearchCriteria_data;
import ru.javabegin.training.vkt7.object_data.SelectionData;
import ru.javabegin.training.vkt7.object_modem.SearchCriteria_modem;
import ru.javabegin.training.vkt7.reports.Archive;
import ru.javabegin.training.vkt7.reports.DataObject;
import ru.javabegin.training.vkt7.reports.ReportService;
import ru.javabegin.training.vkt7.reports.Tupel;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Николай on 14.06.2017.
 */
@Component
@Scope("singleton")
public class Facade_reports {

    @Qualifier("jpaCustomerService")
    @Autowired
    CustomerService customerService;

    @Qualifier("jpaOperationService")
    @Autowired
    OperationService operationService;

    @Autowired
    ReportService reportService;


   /* @Autowired
  */  private SearchCriteria_cust searchCriteria_cust;
    @Autowired
    private SearchCriteria_modem searchCriteria_oper;

    @Autowired
    private SearchCriteria_data searchCriteria_data;
    @Autowired
    private SelectionData selectionData;

    private Customer selcustomer;

    private Customer customer;

    private Archive archive;




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




    public void create(){

        reportService.createReport();
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




    public List<Object> getArchiveByCustomer(){


        Long id_Customer = searchCriteria_data.getCustomer().getId();
        Timestamp tstamp1=searchCriteria_data.getOperation().getChronological();
        String type=searchCriteria_data.getOperation().getTypeOperation();


        List<Operation> operationList= customerService.findOperation_total_moth(id_Customer, tstamp1, type, "OK");
        List<Object> res= reportService.getObject(operationList);
        List<DataObject> dataObjectList=(List<DataObject>)res.get(0);
        List<String> list1=(List<String>)res.get(1);
        //List<String> list2=(List<String>)res.get(2);


     /*   if (list1.size()==list2.size()){
            System.out.println("размеры листов совпадают");
        }*/

       searchCriteria_data.setData(dataObjectList);
       searchCriteria_data.setId_item(list1);

        List<Object> dop=new ArrayList<>();
        dop.add(searchCriteria_data.getOperation().getCustomerName());
        dop.add(searchCriteria_data.getOperation().getChronological());

        return dop;
    }






   /* ... OLD_29_10_2017
  public List<Object> getArchiveByCustomer(){


        Map<String,Tupel> map=new HashMap<String,Tupel>();
        Tupel tupel=new Tupel();

        Long id_Customer = searchCriteria_data.getCustomer().getId();
        Timestamp tstamp1=searchCriteria_data.getOperation().getChronological();
        String type=searchCriteria_data.getOperation().getTypeOperation();


        List<Operation> operationList= customerService.findOperation_total_moth(id_Customer, tstamp1, type, "OK");
        reportService.getDataObject(operationList);

        System.out.println(operationList.size());
        List<Measurements> measurementsList=new ArrayList<>();
        measurementsList.addAll(operationList.get(0).getMeasurementsSet());
        Archive archive=new Archive();

        List<String> id_col = new ArrayList<>();
        List<DataObject> data=new ArrayList<>();
        DataObject data_item=new DataObject();



        for (Measurements m:measurementsList){


            if(m.getText().equals("t1 Тв1")){

                id_col.add(m.getText());
                map.put(m.getText(),new Tupel(m.getText(),(new BigDecimal(m.getMeasurText()).setScale(2, RoundingMode.HALF_EVEN))) );

            }
            if(m.getText().equals("t2 Тв1")){

                id_col.add(m.getText());
                map.put(m.getText(),new Tupel(m.getText(),(new BigDecimal(m.getMeasurText()).setScale(2, RoundingMode.HALF_EVEN))) );

            }
            if(m.getText().equals("t3 Тв1")){

                id_col.add(m.getText());
                map.put(m.getText(),new Tupel(m.getText(),(new BigDecimal(m.getMeasurText()).setScale(2, RoundingMode.HALF_EVEN))) );

            }
            if(m.getText().equals("V1 Тв1")){

                id_col.add(m.getText());
                map.put(m.getText(),new Tupel(m.getText(),(new BigDecimal(m.getMeasurText()).setScale(2, RoundingMode.HALF_EVEN))) );
            }
            if(m.getText().equals("V2 Тв1")){

                id_col.add(m.getText());
                map.put(m.getText(),new Tupel(m.getText(),(new BigDecimal(m.getMeasurText()).setScale(2, RoundingMode.HALF_EVEN))) );
            }
            if(m.getText().equals("V3 Тв1")){

                id_col.add(m.getText());
                map.put(m.getText(),new Tupel(m.getText(),(new BigDecimal(m.getMeasurText()).setScale(2, RoundingMode.HALF_EVEN))) );
            }
            if(m.getText().equals("M1 Тв1")){

                id_col.add(m.getText());
                map.put(m.getText(),new Tupel(m.getText(),(new BigDecimal(m.getMeasurText()).setScale(2, RoundingMode.HALF_EVEN))) );
            }
            if(m.getText().equals("M2 Тв1")){

                id_col.add(m.getText());
                map.put(m.getText(),new Tupel(m.getText(),(new BigDecimal(m.getMeasurText()).setScale(2, RoundingMode.HALF_EVEN))) );
            }
            if(m.getText().equals("M3 Тв1")){

                id_col.add(m.getText());
                map.put(m.getText(),new Tupel(m.getText(),(new BigDecimal(m.getMeasurText()).setScale(2, RoundingMode.HALF_EVEN))) );
            }
            if(m.getText().equals("P1 Тв1")){

                id_col.add(m.getText());
                map.put(m.getText(),new Tupel(m.getText(),(new BigDecimal(m.getMeasurText()).setScale(2, RoundingMode.HALF_EVEN))) );
            }
            if(m.getText().equals("P2 Тв1")){

                id_col.add(m.getText());
                map.put(m.getText(),new Tupel(m.getText(),(new BigDecimal(m.getMeasurText()).setScale(2, RoundingMode.HALF_EVEN))) );
            }
            if(m.getText().equals("Mг Тв1")){

                id_col.add(m.getText());
                map.put(m.getText(),new Tupel(m.getText(),(new BigDecimal(m.getMeasurText()).setScale(2, RoundingMode.HALF_EVEN))) );
            }
            if(m.getText().equals("Qо Тв1")){

                id_col.add(m.getText());
                map.put(m.getText(),new Tupel(m.getText(),(new BigDecimal(m.getMeasurText()).setScale(3, RoundingMode.HALF_EVEN))) );
            }
            if(m.getText().equals("Qг Тв1")){

                id_col.add(m.getText());
                map.put(m.getText(),new Tupel(m.getText(),(new BigDecimal(m.getMeasurText()).setScale(3, RoundingMode.HALF_EVEN))) );
            }

            if(m.getText().equals("dt Тв1")){

                id_col.add(m.getText());
                map.put(m.getText(),new Tupel(m.getText(),(new BigDecimal(m.getMeasurText()).setScale(2, RoundingMode.HALF_EVEN))) );
            }

            if(m.getText().equals("tх")){

                id_col.add(m.getText());
                map.put(m.getText(),new Tupel(m.getText(),(new BigDecimal(m.getMeasurText()).setScale(2, RoundingMode.HALF_EVEN))) );
            }
            if(m.getText().equals("ta")){

                id_col.add(m.getText());
                map.put(m.getText(),new Tupel(m.getText(),(new BigDecimal(m.getMeasurText()).setScale(2, RoundingMode.HALF_EVEN))) );
            }

            if(m.getText().equals("BНP Тв1")){

                id_col.add(m.getText());
                map.put(m.getText(),new Tupel(m.getText(),(new BigDecimal(m.getMeasurText()).setScale(1, RoundingMode.HALF_EVEN))) );
            }
            if(m.getText().equals("BOC Тв1")){

                id_col.add(m.getText());
                map.put(m.getText(),new Tupel(m.getText(),(new BigDecimal(m.getMeasurText()).setScale(1, RoundingMode.HALF_EVEN))) );
            }
            if(m.getText().equals("G1 Тв1")){

                id_col.add(m.getText());
                map.put(m.getText(),new Tupel(m.getText(),(new BigDecimal(m.getMeasurText()).setScale(4, RoundingMode.HALF_EVEN))) );
            }
            if(m.getText().equals("G2 Тв1")){

                id_col.add(m.getText());
                map.put(m.getText(),new Tupel(m.getText(),(new BigDecimal(m.getMeasurText()).setScale(3, RoundingMode.HALF_EVEN))) );
            }
            if(m.getText().equals("G3 Тв1")){

                id_col.add(m.getText());
                map.put(m.getText(),new Tupel(m.getText(),(new BigDecimal(m.getMeasurText()).setScale(3, RoundingMode.HALF_EVEN))) );
            }


            if(m.getText().equals("t1 Тв2")){

                id_col.add(m.getText());
                map.put(m.getText(),new Tupel(m.getText(),(new BigDecimal(m.getMeasurText()).setScale(2, RoundingMode.HALF_EVEN))) );

            }
            if(m.getText().equals("t2 Тв2")){

                id_col.add(m.getText());
                map.put(m.getText(),new Tupel(m.getText(),(new BigDecimal(m.getMeasurText()).setScale(2, RoundingMode.HALF_EVEN))) );

            }
            if(m.getText().equals("t3 Тв2")){

//
                id_col.add(m.getText());
                map.put(m.getText(),new Tupel(m.getText(),(new BigDecimal(m.getMeasurText()).setScale(2, RoundingMode.HALF_EVEN))) );

            }
            if(m.getText().equals("V1 Тв2")){

                id_col.add(m.getText());
                map.put(m.getText(),new Tupel(m.getText(),(new BigDecimal(m.getMeasurText()).setScale(2, RoundingMode.HALF_EVEN))) );
            }
            if(m.getText().equals("V2 Тв2")){

                id_col.add(m.getText());
                map.put(m.getText(),new Tupel(m.getText(),(new BigDecimal(m.getMeasurText()).setScale(2, RoundingMode.HALF_EVEN))) );
            }
            if(m.getText().equals("V3 Тв2")){

                id_col.add(m.getText());
                map.put(m.getText(),new Tupel(m.getText(),(new BigDecimal(m.getMeasurText()).setScale(2, RoundingMode.HALF_EVEN))) );
            }
            if(m.getText().equals("M1 Тв2")){

                id_col.add(m.getText());
                map.put(m.getText(),new Tupel(m.getText(),(new BigDecimal(m.getMeasurText()).setScale(2, RoundingMode.HALF_EVEN))) );
            }
            if(m.getText().equals("M2 Тв2")){

                id_col.add(m.getText());
                map.put(m.getText(),new Tupel(m.getText(),(new BigDecimal(m.getMeasurText()).setScale(2, RoundingMode.HALF_EVEN))) );
            }
            if(m.getText().equals("M3 Тв2")){

                id_col.add(m.getText());
                map.put(m.getText(),new Tupel(m.getText(),(new BigDecimal(m.getMeasurText()).setScale(2, RoundingMode.HALF_EVEN))) );
            }
            if(m.getText().equals("P1 Тв2")){

                id_col.add(m.getText());
                map.put(m.getText(),new Tupel(m.getText(),(new BigDecimal(m.getMeasurText()).setScale(2, RoundingMode.HALF_EVEN))) );
            }
            if(m.getText().equals("P2 Тв2")){

                id_col.add(m.getText());
                map.put(m.getText(),new Tupel(m.getText(),(new BigDecimal(m.getMeasurText()).setScale(2, RoundingMode.HALF_EVEN))) );
            }
            if(m.getText().equals("Mг Тв2")){

                id_col.add(m.getText());
                map.put(m.getText(),new Tupel(m.getText(),(new BigDecimal(m.getMeasurText()).setScale(2, RoundingMode.HALF_EVEN))) );
            }
            if(m.getText().equals("Qо Тв2")){


                id_col.add(m.getText());
                map.put(m.getText(),new Tupel(m.getText(),(new BigDecimal(m.getMeasurText()).setScale(4, RoundingMode.HALF_EVEN))) );
            }
            if(m.getText().equals("Qг Тв2")){

                id_col.add(m.getText());
                map.put(m.getText(),new Tupel(m.getText(),(new BigDecimal(m.getMeasurText()).setScale(4, RoundingMode.HALF_EVEN))) );
            }

            if(m.getText().equals("dt Тв2")){

                id_col.add(m.getText());
                map.put(m.getText(),new Tupel(m.getText(),(new BigDecimal(m.getMeasurText()).setScale(2, RoundingMode.HALF_EVEN))) );
            }


            if(m.getText().equals("BНP Тв2")){

                id_col.add(m.getText());
                map.put(m.getText(),new Tupel(m.getText(),(new BigDecimal(m.getMeasurText()).setScale(1, RoundingMode.HALF_EVEN))) );
            }
            if(m.getText().equals("BOC Тв2")){

                id_col.add(m.getText());
                map.put(m.getText(),new Tupel(m.getText(),(new BigDecimal(m.getMeasurText()).setScale(1, RoundingMode.HALF_EVEN))) );
            }
            if(m.getText().equals("G1 Тв2")){

                id_col.add(m.getText());
                map.put(m.getText(),new Tupel(m.getText(),(new BigDecimal(m.getMeasurText()).setScale(3, RoundingMode.HALF_EVEN))) );
            }
            if(m.getText().equals("G2 Тв2")){

                id_col.add(m.getText());
                map.put(m.getText(),new Tupel(m.getText(),(new BigDecimal(m.getMeasurText()).setScale(3, RoundingMode.HALF_EVEN))) );
            }
            if(m.getText().equals("G3 Тв2")){

                id_col.add(m.getText());
                map.put(m.getText(),new Tupel(m.getText(),(new BigDecimal(m.getMeasurText()).setScale(3, RoundingMode.HALF_EVEN))) );
            }









            System.out.println("map.size= "+map.size());
            System.out.println("id_col.size= "+id_col.size());

        }


        data_item.setOptionalValues(map);
        data_item.setData(searchCriteria_data.getOperation().getChronological());
        data.add(data_item);

        searchCriteria_data.setData(data);

        //// Делаем соритровку по списку
        String qq="t1 Тв1,t2 Тв1,t3 Тв1,V1 Тв1,V2 Тв1, V3 Тв1, M1 Тв1, M2 Тв1, M3 Тв1, P1 Тв1, P2 Тв1, Mг Тв1, Qо Тв1, Qг Тв1, dt Тв1, tх, ta, BНP Тв1, BOC Тв1, G1 Тв1, G2 Тв1, G3 Тв1, t1 Тв2, t2 Тв2, t3 Тв2, V1 Тв2, V2 Тв2, V3 Тв2, M1 Тв2, M2 Тв2, M3 Тв2, P1 Тв2, P2 Тв2, Mг Тв2, Qо Тв2, Qг Тв2, dt Тв2, BНP Тв2, BOC Тв2, G1 Тв2, G2 Тв2, G3 Тв2";

        List<String> et = new ArrayList<>(Arrays.asList(qq.replace(", ",",").split( ",")));
        List<String> result = new LinkedList<>();

        for (int i=0; i<et.size(); i++){
            for (String w:id_col){

                if(w.equals(et.get(i))){

                    if(et.get(i).equals("t1 Тв1")){
                        System.out.println("eсть" );}
                    result.add(w);
                }


            }

        }

        searchCriteria_data.setId_item( result);



        System.out.println(data.size());

        //selectionData.setArchive(archive);
        searchCriteria_data.setArchive(archive);

        List<Archive> temp= new ArrayList<>();
        temp.add(archive);

        System.out.println(temp.size());
        setArchiveList(temp);
        System.out.println(archiveList.size());

        List<Object> dop=new ArrayList<>();

        dop.add(searchCriteria_data.getOperation().getCustomerName());
        dop.add(searchCriteria_data.getOperation().getChronological());

        return dop;
    }

*/




    public List<Archive>  getArchiveByCustomer_old_29_10_2017(){

       /* if (operations==null){
            operations = customerService.findAll();
        }*/

        Long id_Customer = searchCriteria_data.getCustomer().getId();


        LocalDate d=LocalDate.of(2017,10,26);
        LocalTime t=LocalTime.of(23,00);

        Timestamp tstamp1 = Timestamp.valueOf(LocalDateTime.of(d,t));

        List<Operation> operationList= customerService.findOperation_total_moth(id_Customer, tstamp1, "daily", "OK");

        System.out.println(operationList.size());
        List<Measurements> measurementsList=new ArrayList<>();
        measurementsList.addAll(operationList.get(0).getMeasurementsSet());
        Archive archive=new Archive();
        for (Measurements m:measurementsList){


            if(m.getText().equals("t1 Тв1")){
                System.out.println(m.getText());
                archive.setT1(new BigDecimal(m.getMeasurText()).setScale(2, RoundingMode.HALF_EVEN));
                System.out.println(archive.getT1());
            }
            if(m.getText().equals("t2 Тв1")){
                archive.setT2(new BigDecimal(m.getMeasurText()).setScale(2, RoundingMode.HALF_EVEN));

            }
            if(m.getText().equals("t3 Тв1")){
                archive.setT3(new BigDecimal(m.getMeasurText()).setScale(2, RoundingMode.HALF_EVEN));

            }
            if(m.getText().equals("V1 Тв1")){
                archive.setV1(new BigDecimal(m.getMeasurText()).setScale(4, RoundingMode.HALF_EVEN));
            }
            if(m.getText().equals("V2 Тв1")){
                archive.setV2(new BigDecimal(m.getMeasurText()).setScale(4, RoundingMode.HALF_EVEN));
            }
            if(m.getText().equals("V3 Тв1")){
                archive.setV3(new BigDecimal(m.getMeasurText()).setScale(4, RoundingMode.HALF_EVEN));
            }
            if(m.getText().equals("M1 Тв1")){
                archive.setM1(new BigDecimal(m.getMeasurText()).setScale(4, RoundingMode.HALF_EVEN));
            }
            if(m.getText().equals("M2 Тв1")){
                archive.setM2(new BigDecimal(m.getMeasurText()).setScale(4, RoundingMode.HALF_EVEN));
            }
            if(m.getText().equals("M3 Тв1")){
                archive.setM3(new BigDecimal(m.getMeasurText()).setScale(4, RoundingMode.HALF_EVEN));
            }
            if(m.getText().equals("P1 Тв1")){
                archive.setP1(new BigDecimal(m.getMeasurText()).setScale(4, RoundingMode.HALF_EVEN));
            }
            if(m.getText().equals("P2 Тв1")){
                archive.setP2(new BigDecimal(m.getMeasurText()).setScale(4, RoundingMode.HALF_EVEN));
            }
            if(m.getText().equals("Mг Тв1")){
                archive.setMg(new BigDecimal(m.getMeasurText()).setScale(4, RoundingMode.HALF_EVEN));
            }
            if(m.getText().equals("Qо Тв1")){
                archive.setQo(new BigDecimal(m.getMeasurText()).setScale(4, RoundingMode.HALF_EVEN));
            }
            if(m.getText().equals("Qг Тв1")){
                archive.setQg(new BigDecimal(m.getMeasurText()).setScale(4, RoundingMode.HALF_EVEN));
            }





        }

        //selectionData.setArchive(archive);
        searchCriteria_data.setArchive(archive);

List<Archive> temp= new ArrayList<>();
        temp.add(archive);

        System.out.println(temp.size());
        setArchiveList(temp);
        System.out.println(archiveList.size());

   return archiveList;
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


        operations=operationService.findOperationByIdCustomer(id_Customer);
        return operations;
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




/*    public void searchContactByLetter() {
        String letter = searchCriteria.getLetter();
        contacts = contactService.findByCriteriaQuery4(letter);
    }

    public void searchContactByHobby() {
        Hobby hobby = searchCriteria.getHobby();
        contacts = contactService.findByCriteriaQuery10(hobby);
    }*/

/*

    public void searchContactByText() {
        String text = searchCriteria.getText();
        switch (searchCriteria.getSearchType()){
            case TITLE:
                contacts = contactService.findByCriteriaQuery11(text);
                break;
            case AUTHOR:
                contacts = contactService.findByCriteriaQuery12(text);
                break;
        }
    }
*/




 /*   public byte[] getContent(long id){

        return (byte[])contactService.getContent(id);
    }*/

}

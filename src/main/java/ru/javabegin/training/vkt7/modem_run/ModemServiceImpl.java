package ru.javabegin.training.vkt7.modem_run;

import jssc.SerialPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import ru.javabegin.training.db.Contact;
import ru.javabegin.training.db.ContactService;
import ru.javabegin.training.vkt7.dao.OperationService;
import ru.javabegin.training.vkt7.db.CustomerService;
import ru.javabegin.training.vkt7.db.ResultService;
import ru.javabegin.training.vkt7.entities.Customer;
import ru.javabegin.training.vkt7.entities.Test;
import ru.javabegin.training.vkt7.entities.TestService;
import ru.javabegin.training.vkt7.modem.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.*;

/**
 * Created by Николай on 18.09.2017.
 */

    @Component

public class ModemServiceImpl implements ModemService {
    public static volatile List<Object> connec;
  public static volatile SerialPort serialPort;
    public static volatile boolean stop=true;



  @Autowired
  Connect_test connect;
  @Autowired
  Close_connect_test close_connect;

    @Autowired
    @Qualifier("jpaContactService")
    ContactService contactService;



    @Autowired
    @Qualifier("jpaResultService")
ResultService resultService;

    @Autowired
    @Qualifier("jpaCustomerService")
    private CustomerService customerService;

    @Autowired
    @Qualifier("jpaTestService")
    private TestService testService;

    @Autowired
    @Qualifier("jpaOperationService")
    private OperationService operationService;


    public static int getM() {
        return m;
    }
    public void increment() {
        m++;
    }

    public static volatile int m=50;

    @Autowired EventListener eventListener;
    @Override
    public void connect() throws ExecutionException, InterruptedException {

        Connect c =new Connect();
        List<Object> connect=new ArrayList<>();

        Callable task = () -> {
            try {
                //TimeUnit.SECONDS.sleep(1);
                connec =c.connect(customerService);
                //return Thread.currentThread().getName();
                return connec;

            }
            catch (InterruptedException e) {
                throw new IllegalStateException("task interrupted", e);
            }
        };



      /*  Customer custom=new Customer();

        custom.setFirstName("qwewq");
        custom.setLastName("rryyeeuuwweje");
        customerService.save(custom);
        List<Customer>l_cust=customerService.findAll();
        l_cust.forEach(p->System.out.println(p.getFirstName()+" "+p.getTelModem()));
*/


      /*  Customer cu=new Customer();
        cu.setFirstName("Новый");
        cu.setTelModem("45645");
        customerService.save(cu);
        List<Customer> l_cu=customerService.findAll();
        l_cu.forEach(p->System.out.println(p.getFirstName()+" "+p.getLastName()));

        Test test= new Test();
        test.setText("rere");
        testService.save(test);
        List<Test> l_te=testService.findAll();
        l_te.forEach(p->System.out.println(p.getId()+" "+p.getText()));*/





        ExecutorService executor1 = Executors.newFixedThreadPool(2);
        Future<Object> future = executor1.submit(task);
        executor1.shutdown();
    }


    @Override
    public void close_connect(){
        System.out.println("Закрываем подключение !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! ");

        //Close_connect cl = new Close_connect();
        Close_connect_test cl = new Close_connect_test();


        Callable task3 = () -> {

            cl.close_port();
            return Thread.currentThread().getName();

            /*try {



            }
            catch (InterruptedException e) {
                throw new IllegalStateException("task interrupted", e);
            }*/
        };

        ExecutorService executor3 = Executors.newFixedThreadPool(2);
        Future<String> future3 = executor3.submit(task3);
      /*   boolean a=true;
        while (a!=false) {
            try {
                if( future3.isDone()){
                    System.out.println("Done");
                    // заканчиваем работу executor service
                    executor3.shutdown();
                    a=false;
                }



                System.out.println("Ждем, пока закрывается подключение");
                String s = future3.get(20L, TimeUnit.SECONDS);
                if(s !=null){
                    System.out.println("Результат  = " + s);
                }
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }catch(TimeoutException e){
                //оставим пустым
            }
        }*/

        executor3.shutdown();

    }

@Override
   public void get_current_data(Customer customer){
   /* Connect c =new Connect();
List<Object> connect=new ArrayList<>();*/
   String tel =customer.getTelModem();
   Long id=customer.getId();
   System.out.println("tel= "+tel);
    CurrentData currentData=new CurrentData();

    Callable task = () -> {
        try {
            //TimeUnit.SECONDS.sleep(1);
           currentData.current_all_cycle(customerService, resultService,operationService, tel, id);
            //return Thread.currentThread().getName();
            return "123";

        }
        catch (InterruptedException e) {
            throw new IllegalStateException("task interrupted", e);
        }
    };




    ExecutorService executor1 = Executors.newFixedThreadPool(2);
    Future<String> future = executor1.submit(task);
    executor1.shutdown();


    System.out.println("Программа закончила работу полностью!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! ");
    System.out.println("Данные ТЕКУЩИЕ должны быть получены!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! ");

    }



    @Override
    public void get_daily_data(Customer customer, Date data){
   /* Connect c =new Connect();
List<Object> connect=new ArrayList<>();*/
        String tel =customer.getTelModem();
        Long id=customer.getId();
        System.out.println("tel= "+tel);
        DailyData dailyData=new DailyData();

        Callable task = () -> {
            try {
                //TimeUnit.SECONDS.sleep(1);
                dailyData.daily_all_cycle(customerService, operationService, tel, id, data);
                //return Thread.currentThread().getName();
                return "123";

            }
            catch (InterruptedException e) {
                throw new IllegalStateException("task interrupted", e);
            }
        };




        ExecutorService executor1 = Executors.newFixedThreadPool(2);
        Future<String> future = executor1.submit(task);
        executor1.shutdown();


        System.out.println("Программа закончила работу полностью!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! ");
        System.out.println("Данные ТЕКУЩИЕ должны быть получены!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! ");

    }





    @Override
    public void get_daily__hour_data(Customer customer, Date data){
   /* Connect c =new Connect();
List<Object> connect=new ArrayList<>();*/
        String tel =customer.getTelModem();
        Long id=customer.getId();
        System.out.println("tel= "+tel);
        Daily_hour_Data daily_hour_data =new Daily_hour_Data();

        Callable task = () -> {
            try {
                //TimeUnit.SECONDS.sleep(1);
                daily_hour_data.daily_all_cycle(customerService, operationService, tel, id, data);
                //return Thread.currentThread().getName();
                return "123";

            }
            catch (InterruptedException e) {
                throw new IllegalStateException("task interrupted", e);
            }
        };




        ExecutorService executor1 = Executors.newFixedThreadPool(2);
        Future<String> future = executor1.submit(task);
        executor1.shutdown();


        System.out.println("Программа закончила работу полностью!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! ");
        System.out.println("Данные ТЕКУЩИЕ должны быть получены!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! ");

    }




    @Override
    public void get_test_save_data(){
   /* Connect c =new Connect();
List<Object> connect=new ArrayList<>();*/

        Test_save_datatime testSaveDatatime=new Test_save_datatime();

        Callable task = () -> {
            try {
                //TimeUnit.SECONDS.sleep(1);
                testSaveDatatime.connect(customerService,testService,resultService, operationService);
                //return Thread.currentThread().getName();
                return "123";

            }
            catch (InterruptedException e) {
                throw new IllegalStateException("task interrupted", e);
            }
        };




        ExecutorService executor1 = Executors.newFixedThreadPool(1);
        Future<String> future = executor1.submit(task);
        executor1.shutdown();


        System.out.println("Программа закончила работу полностью!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! ");
        System.out.println("Данные ТЕКУЩИЕ должны быть получены!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! ");

    }



}

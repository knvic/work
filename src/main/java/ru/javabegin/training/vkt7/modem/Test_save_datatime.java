package ru.javabegin.training.vkt7.modem;


import org.springframework.stereotype.Component;

import ru.javabegin.training.vkt7.dao.OperationService;
import ru.javabegin.training.vkt7.db.CustomerService;
import ru.javabegin.training.vkt7.db.ResultService;
import ru.javabegin.training.vkt7.entities.*;


import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.concurrent.*;

import static java.sql.Timestamp.valueOf;
import static ru.javabegin.training.vkt7.modem_run.ModemServiceImpl.m;
import static ru.javabegin.training.vkt7.modem_run.ModemServiceImpl.stop;

/**
 * Created by Николай on 12.08.2017.
 */
@Component
public class Test_save_datatime extends EventListener{
 //SerialPort serialPort; /*Создаем объект типа SerialPort*/
    public static String data;



   /* @Autowired
    @Qualifier("jpaCustomerService")
    private CustomerService customerService;
*/


/* public volatile static String data2;
    public volatile static int step;
    public volatile static int recieve_all_byte;
    public static volatile int t;*/


    public static boolean r_3fff;
    public static int server_version=0;

   private static int requestNum = 0;
   /*private static int[][] requests = {
           {0xFF, 0xFF, 0x01, 0x10, 0x3F, 0xFF, 0x00, 0x00, 0xCC, 0x80, 0x00, 0x00, 0x00, 0x60, 0xA8},
           {0xFF, 0xFF, 0x01, 0x03, 0x3F, 0xFE, 0x00, 0x00, 0x28, 0x2E }, {00, 00, 03}};*/

    private static int[] request=new int[256];



    public void connect(CustomerService customerService, TestService testService, ResultService resultService, OperationService operationService) throws InterruptedException, TimeoutException, ExecutionException {


         /*Result result=new Result("qwerty");
        List<Result>  res = resultService.findAll();
        System.out.println("Выводим массив");
        res.forEach(p->System.out.println(p.getIdentificator()));
        resultService.save(result);

        System.out.println("После записи Выводим массив");
        res.forEach(p->System.out.println(p.getIdentificator()));
*/
        Customer cu=new Customer();
        cu.setFirstName("Очень новый");
        cu.setTelModem("345345");
        customerService.save(cu);
        List<Customer> l_cu=customerService.findAll();
        l_cu.forEach(p->System.out.println(p.getFirstName()+" "+p.getLastName()));

      /*  Result r=new Result("qwerty");
        customerService.save(r);*/

        LocalDateTime today1 = LocalDateTime.now();
        System.out.println("Получаем текущее время : " + today1);

        java.util.Date date = new Date();
        Object param = new java.sql.Timestamp(date.getTime());
        System.out.println("Получаем текущее время : " + param);


        java.sql.Timestamp ts = valueOf(today1);
        System.out.println("ts : " + ts);


       Test t=new Test();
       t.setText("2837465dd");
       t.setServetDate(ts);
       testService.save(t);

        LocalDateTime localDateTime = LocalDateTime.now();

        System.out.println("localDateTime = "+localDateTime);
        System.out.println("Переводим в Timestamp");
        Timestamp timestamp = Timestamp.valueOf(localDateTime);
       t=new Test();
       t.setServetDate(timestamp);
        testService.save(t);
        Test ansver =testService.findById(2L);
        timestamp=ansver.getServetDate();
        LocalDateTime ldt = timestamp.toLocalDateTime();
        System.out.println("Полученные значения "+"id= "+ansver.getId()+"text= "+ansver.getText()+"data="+ldt.format(DateTimeFormatter.ofPattern("d::MMM::20uu")));


        System.out.println("Тестируем Operation ");

        Operation operation=new Operation();
        operation.setTypeOperation("currunt");
        operation.setDateServer(timestamp);
        operationService.save(operation);
        System.out.println("Проверяем запись Operation ");
        List<Operation> list_o =operationService.findAll();
        list_o.forEach(p->System.out.println(p.getTypeOperation()+" "+p.getDateServer()));





    }



//////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////
    static Callable callable(long Seconds) {
        return () ->
        {
            for (int i = 1; i < Seconds + 1; i++) {
                   if(stop==false){
                       System.out.println("Ответ из вспомогательного потока. Поступила команда STOP "+ stop);
                       return 1;}
                    TimeUnit.SECONDS.sleep(1);
                    if (t == 1) {
                        System.out.println("Ответ получен. Таймер остановлен");
                        return 1;
                    }
                    System.out.print(i + " ");
                }
                System.out.println("timeout error");
                t = 2;
                return 0;


        };
    }
///////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////




}

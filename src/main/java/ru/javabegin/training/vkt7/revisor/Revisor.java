package ru.javabegin.training.vkt7.revisor;

import edu.emory.mathcs.backport.java.util.concurrent.atomic.AtomicInteger;
import jssc.SerialPortException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import ru.javabegin.training.test_thread.TestThread_kill_modem;
import ru.javabegin.training.vkt7.modem_run.ModemService;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.concurrent.*;



import static ru.javabegin.training.test_thread.TestThread_run.future2;
import static ru.javabegin.training.vkt7.modem_run.ModemServiceImpl.future1;
import static ru.javabegin.training.vkt7.modem_cron.Daily_Moth_cron.atomicInteger;
import static ru.javabegin.training.vkt7.modem_run.ModemServiceImpl.stop;

/**
 * Created by user on 18.11.2017.
 */
public class Revisor {
 public static volatile int tt=0;

    @Qualifier("modemServiceImpl")
    @Autowired
    ModemService modemService;

    public void Revisor() throws InterruptedException, IOException, ExecutionException, SerialPortException {

        File file = new File("C:\\Work\\Java\\work\\logRevizor.txt");
        FileWriter writer = new FileWriter(file, true);
        LocalDateTime ldt;
        String log;

        if(future1!=null) {
            ExecutorService executorService_revizor = Executors.newFixedThreadPool(2);
            int ai = (int) atomicInteger.get();
            System.out.println("Сохраняем atomicInteger----->>" + ai);
            executorService_revizor.submit(callable(120));
            while (tt != 2) {
               // Thread.sleep(50000);
                if(ai!= atomicInteger.get()){
                    tt=1;
                    break;
                }

            }
            System.out.println("Таймер отработал");
            if (ai== atomicInteger.get()) {
                System.out.println("Поток завис. Требуется прерывание и перезапуск");
                ldt=LocalDateTime.now();
                log=ldt+ " Поток завис. Требуется прерывание  \n";
                writer.write( log);
                writer.flush();
                writer.close();

                TestThread_kill_modem testThread_kill_modem=new TestThread_kill_modem();
                testThread_kill_modem.t_kill();

                Thread.sleep(100000);

                modemService.get_daily_moth_cron();


               // future2.cancel(true);



                } else {
                System.out.println("поток в рабочем состоянии ai = "+ai+ "atomicInteger = "+atomicInteger.get());
                ldt=LocalDateTime.now();
                log=ldt+ " поток в рабочем состоянии ai = "+ai+ "atomicInteger = "+atomicInteger.get()+"\n";
                writer.write( log);
                writer.flush();
                writer.close();
            }
            System.out.println("atomicInteger=== " + atomicInteger.get());

            executorService_revizor.shutdown();
        }else
        {
            System.out.println("поток не запущен");
            ldt=LocalDateTime.now();
            log=ldt+ " поток не запущен \n";
            writer.write( log);
            writer.flush();
            writer.close();
        }


    }


    static Callable callable(long Seconds) {
        return () ->
        {
            for (int i = 1; i < Seconds + 1; i++) {
                if(stop==false){
                    System.out.println("Ответ из вспомогательного потока. Поступила команда STOP "+ stop);
                    return 1;}



                if (tt == 1) {
                    System.out.println("Поток в порядке. Таймер остановлен");
                    return 1;
                }
                //
                System.out.print(i);
                TimeUnit.SECONDS.sleep(1);
                System.out.print("\r");

            }
            System.out.println("timeout error");
            tt = 2;
            return 0;


        };
    }



}

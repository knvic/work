package ru.javabegin.training.vkt7.revisor;

import edu.emory.mathcs.backport.java.util.concurrent.atomic.AtomicInteger;
import jssc.SerialPortException;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import ru.javabegin.training.test_thread.TestThread_kill_modem;
import ru.javabegin.training.vkt7.modem_run.ModemService;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.concurrent.*;



import static ru.javabegin.training.test_thread.TestThread_run.future2;
import static ru.javabegin.training.vkt7.modem_cron.Daily_Moth_cron.end;
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
        Logger logger = Logger.getRootLogger();
        Callable task = () -> {
            try {

                //File file = new File("C:\\Work\\Java\\work\\logRevizor.txt");
                //File file = new File("d:\\Work\\work\\logRevizor.txt");
                //FileWriter writer = new FileWriter(file, true);
                Date ldt;
                String log;
                logger.info("Начала работать программа ревизор");
                logger.info("Статус end : "+ end);


                if(future1!=null) {

                    int ai = (int) atomicInteger.get();
                    System.out.println("Сохраняем atomicInteger----->>" + ai);
                    System.out.println("Ждем 140 секунд ....");
                    Thread.sleep(140000);
                    System.out.println("Таймер  Revizor отработал");
                    logger.info("Таймер  Revizor отработал");
                    if (ai== atomicInteger.get()) {
                        System.out.println("Поток завис. Требуется прерывание и перезапуск");

                        ldt=new Date();
                        log=ldt+ " Поток завис. Требуется прерывание  \n";
                        logger.info(ldt+ " Поток завис. Требуется прерывание  \n");
                        //writer.write( log);
                        //writer.flush();
                        //writer.close();

                        TestThread_kill_modem testThread_kill_modem=new TestThread_kill_modem();
                        testThread_kill_modem.t_kill();

                        Thread.sleep(100000);
                        logger.info("Статус end после thread kill: "+ end);
                        if(end!=true) {
                            logger.info("Программа не закончила работу. Перезапуск: "+ end);
                            modemService.get_daily_moth_cron();
                        }else
                        {
                            logger.info("Программа закончила работу. Перезапуск не требуется . ");
                            try {
                                logger.info("Статус задачи future1:"+future1.isDone());
                            } catch (Exception e) {
                                logger.info("Статус задачи future1 получить не удалось: Ошибка");
                                e.printStackTrace();
                            }

                        }


                    } else {
                        System.out.println("поток в рабочем состоянии ai = "+ai+ "atomicInteger = "+atomicInteger.get());
                        ldt=new Date();
                        log=ldt+ " поток в рабочем состоянии ai = "+ai+ "atomicInteger = "+atomicInteger.get()+"\n";
                        logger.info( log);
                        atomicInteger.set(1);
                        ///writer.write( log);
                       /// writer.flush();
                       /// writer.close();
                    }
                    System.out.println("atomicInteger=== " + atomicInteger.get());


                }else
                {
                    System.out.println("поток не запущен");
                    ldt=new Date();
                    log=ldt+ " поток не запущен \n";
                    ///writer.write( log);
                   /// writer.flush();
                   /// writer.close();
                }


                return "123";
            }
            catch (InterruptedException e) {
                throw new IllegalStateException("task interrupted", e);
            }
        };


        ExecutorService service_revizor = Executors.newSingleThreadExecutor();
        Future<String> future_revizor = service_revizor.submit(task);
        service_revizor.shutdown();

    }
}

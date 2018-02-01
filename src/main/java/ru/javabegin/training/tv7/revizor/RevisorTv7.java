package ru.javabegin.training.tv7.revizor;

import jssc.SerialPortException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import ru.javabegin.training.test_thread.TestThread_kill_modem;
import ru.javabegin.training.thread.ThreadKillTv7;
import ru.javabegin.training.tv7.modem.Tv7Run;
import ru.javabegin.training.vkt7.modem_run.ModemService;

import java.io.IOException;
import java.util.Date;
import java.util.concurrent.*;

import static ru.javabegin.training.tv7.modem.Modem_cron.end_tv7;
import static ru.javabegin.training.tv7.modem.Tv7Run.futureTV7_1;
import static ru.javabegin.training.vkt7.modem_cron.Daily_Moth_cron.atomicInteger;
import static ru.javabegin.training.vkt7.modem_cron.Daily_Moth_cron.end;
import static ru.javabegin.training.vkt7.modem_run.ModemServiceImpl.future1;

/**
 * Created by user on 18.11.2017.
 */
public class RevisorTv7 {
 public static volatile int tttv7=0;

    @Qualifier("modemServiceImpl")
    @Autowired
    ModemService modemService;

    @Autowired
    Tv7Run tv7Run;


    public void RevisorTv7() throws InterruptedException, IOException, ExecutionException, SerialPortException {
        Logger logger = Logger.getRootLogger();
        Callable task = () -> {
            try {


                Date ldt;
                String log;
                logger.info("TV7 -> Начала работать программа ревизор");
                logger.info("TV7 -> Статус end : "+ end_tv7);


                if( futureTV7_1!=null) {
                    ExecutorService executorService_revizor = Executors.newFixedThreadPool(2);
                    int ai = (int) atomicInteger.get();
                    System.out.println("Сохраняем atomicInteger----->>" + ai);
                    System.out.println("Ждем 20 секунд ....");
                    Thread.sleep(20000);
                    System.out.println("Таймер  Revizor отработал");
                    logger.info("Таймер  Revizor отработал");
                    if (ai== atomicInteger.get()) {
                        System.out.println("Поток завис. Требуется прерывание и перезапуск");

                        ldt=new Date();

                        logger.info(ldt+ " Поток завис. Требуется прерывание  \n");
                        ThreadKillTv7 killTv7=new ThreadKillTv7();
                        killTv7.tv7_kill();



                        Thread.sleep(30000);
                        logger.info("Статус end после thread kill: "+end_tv7);
                        if(end_tv7!=true) {
                            logger.info("Программа не закончила работу. Перезапуск: "+ end_tv7);

                            tv7Run.tv7RunCron();
                        }else
                        {
                            logger.info("Программа закончила работу. Перезапуск не требуется . ");
                            try {
                                logger.info("Статус задачи future1:"+futureTV7_1.isDone());
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

                    executorService_revizor.shutdown();
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


        ExecutorService service_revizor_tv7 = Executors.newSingleThreadExecutor();
        Future<String> future_revizor = service_revizor_tv7.submit(task);
        service_revizor_tv7.shutdown();

    }
}

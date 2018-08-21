package ru.javabegin.training.tv7.revizor;

import jssc.SerialPortException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import ru.javabegin.training.test_thread.TestThread_kill_modem;
import ru.javabegin.training.thread.ThreadKillTv7;
import ru.javabegin.training.thread.ThreadKillTv7_test;
import ru.javabegin.training.tv7.modem.Tv7Run;
import ru.javabegin.training.vkt7.modem_run.ModemService;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.concurrent.*;

import static ru.javabegin.training.tv7.modem.Modem_cron.atomicInteger;
import static ru.javabegin.training.tv7.modem.Modem_cron.end_tv7;

import static ru.javabegin.training.tv7.modem.Tv7Run.futureTV7_1;
import static ru.javabegin.training.tv7.revizor.TestRevizorThread.end_tv7_temp;



/**
 * Created by user on 18.11.2017.
 */
public class RevisorTv7 {


    @Qualifier("modemServiceImpl")
    @Autowired
    ModemService modemService;

    @Autowired
    Tv7Run tv7Run;


    public void RevisorTv7() throws InterruptedException, IOException, ExecutionException, SerialPortException {

        Callable task = () -> {
            try {
                Logger logger = Logger.getRootLogger();

                Date ldt =new Date();


                logger.info("TV7 -> REVIZZOR! Статус окончания работы программы : => "+ end_tv7);



                if( futureTV7_1!=null&end_tv7!=true) {

                    int ai = (int) atomicInteger.get();
                    logger.info("TV7 ->  Сохраняем atomicInteger----->> " + ai+" Ждем 40 секунд ..");
                    Thread.sleep(40000);
                    logger.info("TV7 ->  Таймер  Revizor отработал");

                    if (ai== atomicInteger.get()) {
                        System.out.println("TV7 -> Поток завис. Требуется прерывание и перезапуск");


                        logger.info("TV7 -> "+ldt+ " Поток завис. Требуется прерывание  \n");
                        ThreadKillTv7 killTv7=new ThreadKillTv7();
                        killTv7.tv7_kill();

                        Thread.sleep(20000);

                        try {
                            System.out.println("-   -   -   -  Поток работает : isCancelled() => " + futureTV7_1.isCancelled());

                        } catch (Exception e){
                            System.out.println("Поток не запущен "+e);

                        }

                        logger.info("TV7 -> Программа закончила работу => "+end_tv7);
                        if(end_tv7!=true) {
                            logger.info("Программа не закончила работу. Перезапуск: end_tv7 "+ end_tv7);

                            tv7Run.tv7RunCron();

                        }else
                        {
                            logger.info("TV7 -> Программа закончила работу. Перезапуск не требуется . ");
                            try {
                                logger.info("Статус задачи futureTV7_1.isCancelled() : "+futureTV7_1.isCancelled());
                            } catch (Exception e) {
                                logger.info("Статус задачи future1 получить не удалось: Ошибка");
                                e.printStackTrace();
                            }

                        }


                    } else {
                        System.out.println("TV7 -> REVIZOR Поток в рабочем состоянии ai = "+ai+ "atomicInteger = "+atomicInteger.get());

                        logger.info("TV7 -> REVIZOR "+ ldt+ " поток в рабочем состоянии ai = "+ai+ "atomicInteger = "+atomicInteger.get()+"\n");
                        atomicInteger.set(1);

                    }
                    System.out.println("TV7 -> REVIZOR  atomicInteger=== " + atomicInteger.get());


                }else
                {
                    logger.info("TV7 -> REVIZOR Поток не в работе, либо закончил работу");

                }


                return "123";
            }
            catch (InterruptedException e) {
                throw new IllegalStateException("task interrupted", e);
            }
        };


        System.out.println("==>>>  Time check Revozor TV7<<<======");
        LocalDateTime now= LocalDateTime.now();
        LocalDateTime deadLine = LocalDateTime.of(2018, 8, 28, 0, 0, 0);

        if (now.isAfter(deadLine)) {

            System.out.println("==>>> Revizor TV7. Demo has expired "+deadLine +" <<<======");

        }
        else {

            ExecutorService service_revizor_tv7 = Executors.newSingleThreadExecutor();
            Future<String> future_revizor = service_revizor_tv7.submit(task);
            service_revizor_tv7.shutdown();
        }



        /*ExecutorService service_revizor_tv7 = Executors.newSingleThreadExecutor();
        Future<String> future_revizor = service_revizor_tv7.submit(task);
        service_revizor_tv7.shutdown();*/

    }

    public static void proverka(){
        ///////////////////

        try {
            System.out.println("-   -   -   -  Поток работает : isCancelled() => " + futureTV7_1.isCancelled());

        } catch (Exception e){
            System.out.println("Поток не запущен "+e);

        }
      /*  try {
            System.out.println("-   -   -   -  проверяем service: service.isShutdown() => =>  " + exe.isShutdown());

        } catch (Exception e){
            System.out.println("service.isShutdown() не возможно "+e);

        }
        try {
            System.out.println("-   -   -   -  проверяем service: service.isTerminated() => => => " + futureTV7_1.isTerminated());

        } catch (Exception e){
            System.out.println("service.isTerminated() не возможно "+e);

        }*/

//////////////////////


    }

}

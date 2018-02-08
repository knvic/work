package ru.javabegin.training.tv7.revizor;

import jssc.SerialPortException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import ru.javabegin.training.thread.ThreadKillTv7;
import ru.javabegin.training.tv7.modem.Tv7Run;
import ru.javabegin.training.vkt7.modem_run.ModemService;

import java.io.IOException;
import java.util.Date;
import java.util.concurrent.*;

import static ru.javabegin.training.tv7.modem.Modem_cron.atomicInteger;
import static ru.javabegin.training.tv7.modem.Modem_cron.end_tv7;
import static ru.javabegin.training.tv7.modem.Tv7Run.futureTV7_1;


/**
 * Created by user on 18.11.2017.
 */
public class RevisorTv7_old {
 public static volatile int tttv7=0;

    @Qualifier("modemServiceImpl")
    @Autowired
    ModemService modemService;

    @Autowired
    Tv7Run tv7Run;


    public void RevisorTv7() throws InterruptedException, IOException, ExecutionException, SerialPortException {

        Callable task = () -> {
            try {
                Logger logger = Logger.getRootLogger();

                Date ldt;
                String log;

                logger.info("TV7 -> REVIZZOR! Статус окончания работы программы : => "+ end_tv7);


                try {
                    System.out.println("-   -   -   -  Поток работает : isCancelled() => " + futureTV7_1.isCancelled());




                } catch (Exception e){
                    System.out.println("Поток не запущен "+e);

                }


                if( futureTV7_1!=null) {

                    int ai = (int) atomicInteger.get();
                    System.out.println("Сохраняем atomicInteger----->>" + ai);
                    System.out.println("Ждем 10 секунд ....");
                    Thread.sleep(40000);
                    System.out.println("Таймер  Revizor отработал");
                    logger.info("Таймер  Revizor отработал");

                    if (ai== atomicInteger.get()) {
                        System.out.println("Поток завис. Требуется прерывание и перезапуск");

                        ldt=new Date();

                        logger.info(ldt+ " Поток завис. Требуется прерывание  \n");
                        ThreadKillTv7 killTv7=new ThreadKillTv7();
                        killTv7.tv7_kill();

                        //ThreadKillTv7_test threadKillTv7_test= new ThreadKillTv7_test();
                        //threadKillTv7_test.tv7_kill();



                        Thread.sleep(20000);
                        logger.info("Статус окончания работы программы после thread kill: "+end_tv7);
                        if(end_tv7!=true) {
                            logger.info("Программа не закончила работу. Перезапуск: end_tv7_temp "+ end_tv7);

                            tv7Run.tv7RunCron();
                            //TestRevizorThread testRevizorThread =new TestRevizorThread();
                            //testRevizorThread.RunTestThreadRevizor();
                        }else
                        {
                            logger.info("Программа закончила работу. Перезапуск не требуется . ");
                            try {
                                logger.info("Статус задачи future1:");
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


        ExecutorService service_revizor_tv7 = Executors.newSingleThreadExecutor();
        Future<String> future_revizor = service_revizor_tv7.submit(task);
        service_revizor_tv7.shutdown();

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

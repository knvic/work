package ru.javabegin.training.vkt7.scheduler;

import org.quartz.JobExecutionContext;
import org.quartz.SchedulerException;
import org.quartz.TriggerKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.MethodInvokingJobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by Николай on 13.10.2017.
 */
@Component
public class Test_sch_control {

    @Autowired
    @Qualifier(value = "test_schCronTrigger")
    private CronTriggerFactoryBean test_schCronTrigger;

    @Autowired
    private SchedulerFactoryBean schedulerFactoryBean;

    @Autowired
    private MethodInvokingJobDetailFactoryBean detailFactoryBean;

    public void someMethod() throws SchedulerException {

        //logger.info(test_schCronTrigger.getCronExpression());
        System.out.println("Получаем данные триггера  scheduler "+ test_schCronTrigger.getJobDataMap());
        System.out.println("Получаем данные триггера  scheduler "+ test_schCronTrigger.getObject().getCronExpression());
        System.out.println("Получаем данные триггера  scheduler "+ test_schCronTrigger.getObject().getExpressionSummary());



        //test_schCronTrigger.setCronExpression("*/1 * * * * ?");

        //schedulerFactoryBean.getScheduler().pauseJob("plannedVacationServiceJob", "DEFAULT");
        //schedulerFactoryBean.getScheduler().pauseTrigger(TriggerKey.triggerKey("test_schCronTrigger", "DEFAULT"));

        schedulerFactoryBean.getScheduler().pauseTrigger(TriggerKey.triggerKey(String.valueOf(test_schCronTrigger)));
        System.out.println("Получаем данные schedulerFactoryBean "+ schedulerFactoryBean.getScheduler().getTrigger(TriggerKey.triggerKey(String.valueOf(test_schCronTrigger))));
        //schedulerFactoryBean.getScheduler().getJobDetail()

        test_schCronTrigger.setCronExpression("0 35 * * * ?");
        System.out.println("Получаем данные триггера  scheduler "+ test_schCronTrigger.getObject().getCronExpression());

        System.out.println("detailFactoryBean == "+detailFactoryBean.getObject().getDescription());

        for (String str : schedulerFactoryBean.getScheduler().getJobGroupNames()){
            System.out.println( "str1="+str);
            //logger.info(str);
        }

        schedulerFactoryBean.getScheduler().pauseTrigger(TriggerKey.triggerKey(String.valueOf(test_schCronTrigger)));
        List<JobExecutionContext> list=schedulerFactoryBean.getScheduler().getCurrentlyExecutingJobs();
        System.out.println("размер="+list.size());
        list.forEach(p->System.out.println(p+" "));





       // System.out.println(schedulerFactoryBean.getScheduler().getTrigger(TriggerKey.triggerKey(String.valueOf(test_schCronTrigger))).getFinalFireTime());

      /*  for (String str : schedulerFactoryBean.getScheduler().getJobNames("DEFAULT")){
            logger.info(str);
        }*/

       // logger.info(schedulerFactoryBean.getScheduler().getTrigger("plannedVacationServiceJob", "DEFAULT").getCronExpression());
    }
}

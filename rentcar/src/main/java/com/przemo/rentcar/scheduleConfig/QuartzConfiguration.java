package com.przemo.rentcar.scheduleConfig;

import com.przemo.rentcar.orders.OrderScheduler;
import org.quartz.JobDetail;
import org.quartz.Trigger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.scheduling.quartz.SpringBeanJobFactory;

@Configuration
public class QuartzConfiguration {
   /* @Bean
    public JobDetail jobDetail(){
        return JobBuilder.newJob().ofType(OrderScheduler.class)
                .withIdentity("myJob", "group1")
                .build();
    }

    @Bean
    public Trigger trigger(JobDetail job) {
        return TriggerBuilder.newTrigger().forJob(job)
                .withIdentity("myTrigger", "group1")
                .withSchedule(simpleSchedule()
                        .withIntervalInSeconds(5)
                        .repeatForever())
                .build();
    }


    @Bean
    public Scheduler scheduler(Trigger trigger, JobDetail job, SchedulerFactory schedulerFactory)
            throws SchedulerException {
        Scheduler scheduler = schedulerFactory.getScheduler();
        scheduler.scheduleJob(job, trigger);
        scheduler.start();
        return scheduler;
    }

    @Bean
    public SchedulerFactory schedulerFactory(){
        return new StdSchedulerFactory();
    }*/

    @Autowired
    private ApplicationContext applicationContext;

    @Bean
    public JobDetailFactoryBean jobDetail() {
        JobDetailFactoryBean jobDetailFactory = new JobDetailFactoryBean();
        jobDetailFactory.setJobClass(OrderScheduler.class);
        jobDetailFactory.setDescription("Invoke Sample Job service...");
        jobDetailFactory.setDurability(true);
        return jobDetailFactory;
    }

    // @Bean
    //public Trigger trigger(JobDetail job) {
    // return TriggerBuilder.newTrigger().forJob(job)
    //          .withIdentity("myTrigger", "group1")
    //           //start every day at 9:30 and 16:30
    //           .withSchedule(cronSchedule(" 0 30 9,16 ? * * *"))
    //            .build();
    //}
    @Bean
    public CronTriggerFactoryBean trigger(JobDetail job) {
        CronTriggerFactoryBean trigger = new CronTriggerFactoryBean();
        trigger.setJobDetail(job);
        trigger.setCronExpression(" 0 30 9,16 ? * * *");
        return trigger;
    }
 /*   @Bean
    public SimpleTriggerFactoryBean trigger(JobDetail job) {
        SimpleTriggerFactoryBean trigger = new SimpleTriggerFactoryBean();
        trigger.setJobDetail(job);
        trigger.setRepeatInterval(4000);
        trigger.setRepeatCount(SimpleTrigger.REPEAT_INDEFINITELY);
        return trigger;
    }*/

    @Bean
    public SchedulerFactoryBean scheduler(Trigger trigger, JobDetail job) {
        SchedulerFactoryBean schedulerFactory = new SchedulerFactoryBean();
       // schedulerFactory.setConfigLocation(new ClassPathResource("quartz.properties"));

        schedulerFactory.setJobFactory(springBeanJobFactory());
        schedulerFactory.setJobDetails(job);
        schedulerFactory.setTriggers(trigger);
        return schedulerFactory;
    }

    @Bean
    public SpringBeanJobFactory springBeanJobFactory() {
        AutoWiringSpringBeanJobFactory jobFactory = new AutoWiringSpringBeanJobFactory();
        jobFactory.setApplicationContext(applicationContext);
        return jobFactory;
    }

}

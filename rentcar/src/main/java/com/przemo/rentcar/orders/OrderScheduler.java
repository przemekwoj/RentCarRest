package com.przemo.rentcar.orders;

import com.przemo.rentcar.services.CarOrderService;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderScheduler implements Job {

    @Autowired
    private CarOrderService carOrderService;

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        carOrderService.setDetained();
    }
}

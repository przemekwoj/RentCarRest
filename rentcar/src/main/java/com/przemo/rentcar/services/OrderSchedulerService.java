package com.przemo.rentcar.services;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderSchedulerService implements Job {

    @Autowired
    private CarOrderService carOrderService;

    @Override
    public void execute(JobExecutionContext jobExecutionContext){
        carOrderService.setDetained();
    }
}

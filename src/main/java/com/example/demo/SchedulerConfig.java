package com.example.demo;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.scheduling.quartz.SpringBeanJobFactory;

import java.io.IOException;
import java.util.Properties;

@Configuration
public class SchedulerConfig {

    @Bean
    public SchedulerFactoryBean schedulerFactoryBean(ApplicationContext applicationContext) {
        var schedulerFactoryBean = new SchedulerFactoryBean();

        var jobFactory = new SpringBeanJobFactory();

        schedulerFactoryBean.setJobFactory(jobFactory);
        schedulerFactoryBean.setApplicationContext(applicationContext);

        schedulerFactoryBean.setOverwriteExistingJobs(true);
        schedulerFactoryBean.setWaitForJobsToCompleteOnShutdown(true);

        Properties properties = new Properties();
        ClassPathResource classPathResource = new ClassPathResource("/quartz.properties");
        try {
            properties.load(classPathResource.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }

        schedulerFactoryBean.setQuartzProperties(properties);

        return schedulerFactoryBean;
    }

}
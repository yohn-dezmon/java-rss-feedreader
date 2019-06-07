package com.jdes.rssfeed;
import org.springframework.boot.SpringApplication;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
/* This class implements Spring's TaskExecutor interface as well as the 
 * Executor interface, with the former being the primary interface, the 
 * other just serving as secondary convenience. For this reason, the 
 * exception handling follows the TaskExecutor contract rather than the 
 * Executor contract, in particular regarding the TaskRejectedException. */ 

import java.util.concurrent.Executor;



@SpringBootApplication
@EnableJpaAuditing
@EnableAsync
public class Application {
public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
        
    }
	
@Bean(name = "asyncExecutor")
public Executor asyncExecutor() {
    ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
    // This represents the amount of threads that can be run concurrently'
    // I only need two threads to be active at once... so I'm setting it to 2
    executor.setCorePoolSize(2);
    executor.setMaxPoolSize(20);
    // this queue refers to tasks that are awaiting thread assignment 
    // a task in our context is a runnable method implementation/method
    executor.setQueueCapacity(500);
    executor.setThreadNamePrefix("JDAsync-");
    executor.initialize();
    return executor;
}


}

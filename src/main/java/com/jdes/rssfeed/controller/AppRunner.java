package com.jdes.rssfeed.controller;


import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;


import com.jdes.rssfeed.service.UpdateServiceImpl;



@Component
public class AppRunner implements CommandLineRunner {
	
	private final UpdateServiceImpl updateServiceImpl;
	
	public AppRunner(UpdateServiceImpl updateServiceImpl) {
		this.updateServiceImpl = updateServiceImpl;
		
	}
	
	@Override 
	public void run(String... args) throws Exception {
		
		updateServiceImpl.updatingLoop();
		
	}

}

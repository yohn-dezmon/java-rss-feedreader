package com.jdes.rssfeed.service;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.jdes.rssfeed.dao.SourceRepository;
import com.jdes.rssfeed.model.Source;

import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;



@Service
public class SourceServiceImpl implements SourceService {
	@Autowired
    SourceRepository sourceRepository;

    

    public List<String> getSources() {
    	
        
        Iterable<Source> sources = sourceRepository.findAll();
    
    	
    	List<String> myList = new ArrayList<String>();
    	
    	for (Source s: sources) {
    		String title = s.getTitle();
    		myList.add(title);
    		System.out.println(title);
    		
    	}
    	
    	
    return myList;	

    }
}


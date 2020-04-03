package com.example.demo.service;

import com.example.demo.model.Function;
import com.example.demo.model.Role;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface FunctionService {

    List<Function> getAll();

    Boolean save(Function func);

    Boolean delete(Function func);
    
    List<Function> getCurrentFunction(String token); 
        
}

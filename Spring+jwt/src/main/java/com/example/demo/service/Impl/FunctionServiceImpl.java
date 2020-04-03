package com.example.demo.service.Impl;

import com.example.demo.model.Function;
import com.example.demo.model.Role;
import com.example.demo.repository.FunctionRepository;
import com.example.demo.repository.RoleRepository;
import com.example.demo.service.FunctionService;
import com.example.demo.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class FunctionServiceImpl implements FunctionService {

    @Autowired
    private FunctionRepository functionRepository;

    @Override
    public List<Function> getAll() {
        return functionRepository.findAll();
    }

    @Override
    public Boolean save(Function func) {
        if (func != null) {
            functionRepository.save(func);
            return true;
        }
        return false;
    }

    @Override
    public Boolean delete(Function func) {
        try {
            if (func != null && func.getFunctionID()> 0) {
                functionRepository.delete(func.getFunctionID());
                return true;
            }
        } catch (Exception ex) {
            return false;
        }

        return false;
    }

}

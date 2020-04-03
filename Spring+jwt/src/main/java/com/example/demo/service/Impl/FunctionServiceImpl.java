package com.example.demo.service.Impl;

import com.example.demo.model.Function;
import com.example.demo.model.Role;
import com.example.demo.model.User;
import com.example.demo.repository.FunctionRepository;
import com.example.demo.repository.RoleRepository;
import com.example.demo.service.FunctionService;
import com.example.demo.service.JwtService;
import com.example.demo.service.RoleService;
import com.example.demo.service.UserService;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class FunctionServiceImpl implements FunctionService {

    @Autowired
    private FunctionRepository functionRepository;
    
    @Autowired
    private JwtService jwtService;
    
    @Autowired
    private UserService userService;
    
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
    
     @Override
    public List<Function> getCurrentFunction(String token) {
        String username = jwtService.getUsernameFromToken(token);
        List<Function> functions = new ArrayList<Function>();
        User user = userService.loadUserByUsername(username);
        if (user != null && user.getRole() != null && user.getRole().size() > 0) {
            for (Role r : user.getRole()) {
                if (r.getFunction() != null && r.getFunction().size() > 0) {
                    for (Function f : r.getFunction()) {
                        boolean isDuplicate = false;
                        for(Function fc :functions){
                            if(fc.getFunctionID() == f.getFunctionID() || f.getStatus() == 2 ){
                                isDuplicate = true;
                            }
                        } 
                        if (!isDuplicate) functions.add(f);
                    }
                }
            }
        }
        return functions;
    }

}

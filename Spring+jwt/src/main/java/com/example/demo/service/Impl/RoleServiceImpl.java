package com.example.demo.service.Impl;

import com.example.demo.model.Role;
import com.example.demo.model.Function;
import com.example.demo.repository.FunctionRepository;
import com.example.demo.repository.RoleRepository;
import com.example.demo.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private FunctionRepository functionRepository;

    @Override
    public Role getRolebyName(String name) {
        return roleRepository.findRolebyName(name);
    }

    @Override
    public List<Role> getAll() {
        return roleRepository.findAll();
    }

    @Override
    public Boolean save(Role role) {
        if (role != null) {
            roleRepository.save(role);
            return true;
        }
        return false;
    }

    @Override
    public Boolean delete(Role role) {
        try {
            if (role != null && role.getRoleID() > 0) {
            roleRepository.delete(role.getRoleID());
            return true;
            }
        } catch (Exception ex) {
           return false;
        }
        
        return false;
    }

}

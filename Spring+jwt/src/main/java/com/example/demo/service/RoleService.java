package com.example.demo.service;

import com.example.demo.model.Role;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RoleService {
    Role getRolebyName(String name);
    List<Role> getAll();
    Boolean save(Role role);
    Boolean delete(Role role);
}

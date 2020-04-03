package com.example.demo.service.Impl;

import com.example.demo.model.Role;
import com.example.demo.model.User;
import com.example.demo.model.Function;
import com.example.demo.repository.RoleRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.JwtService;
import com.example.demo.service.UserService;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private JwtService jwtService;

    @Override
    public User findById(long id) {
        User user;
        user = userRepository.findOne(id);
        return user;
    }

    @Override
    public String add(User user) {
        User entity;
        entity = userRepository.findUserByUserName(user.getUserName());
        if (entity == null) {
            entity = new User();

            if (user.getUserName() != null) {
                entity.setUserName(user.getUserName());
            } else {
                return "false";
            }

            if (user.getRole() != null && user.getRole().size() > 0) {
                for (Role role : user.getRole()) {
                    Role r = roleRepository.findOne(role.getRoleID());
                    if (r != null) {
                        entity.getRole().add(r);
                    }
                }
            }
            if (user.getFullName() != null) {
                entity.setFullName(user.getFullName());
            }
            entity.setGender(user.getGender());

            if (user.getPassword() != null) {
                String encodedString = new BCryptPasswordEncoder().encode(user.getPassword());
                entity.setPassword(encodedString);

            } else {
                return "false";
            }

            userRepository.save(entity);

            return "true";
        } else {
            return "exist";
        }
    }

    @Override
    public boolean delete(long id) {
        User user;
        user = userRepository.findOne(id);
        if (user != null) {
            userRepository.delete(id);
            return true;
        }
        return false;
    }

    @Override
    public User loadUserByUsername(String username) {
        User user = new User();
        user = userRepository.findUserByUserName(username);
        if (user != null) {
            return user;
        } else {
            return null;
        }
    }

    @Override
    public boolean checkLogin(User user) {

        User us = userRepository.findUserByUserName(user.getUserName());
        if (us.getPassword() != null) {
            if (BCrypt.checkpw(user.getPassword(), us.getPassword())) {
                return true;
            }
        } else {
            return false;
        }

        return false;
    }

    @Override
    public boolean update(User us) {
        User user;

        if (us != null && us.getUserID() != 0) {  //update
            user = userRepository.findOne(us.getUserID());
            if (user != null) {
                user.setFullName(us.getFullName());
                user.setGender(us.getGender());
                user.setStatus(us.getStatus());
                if (us.getRole() != null && us.getRole().size() > 0) {
                    user.getRole().clear();
                    for (Role role : us.getRole()) {
                        role = roleRepository.findOne(role.getRoleID());
                        if (role != null) {
                            user.getRole().add(role);
                        }
                    }
                } else {
                    return false;
                }

                if (user.getRole().size() > 0) {
                    userRepository.save(user);
                } else {
                    return false;
                }

                return true;
            } else {
                return false;
            }
        } else {
            user = new User();  //create
            if (us.getUserName() != null && us.getPassword() != null && us.getRole() != null && us.getRole().size() > 0) {
                user.setFullName(us.getFullName());
                user.setGender(us.getGender());
                user.setStatus(us.getStatus());
                user.setUserName(us.getUserName());
                String encodedString = new BCryptPasswordEncoder().encode(us.getPassword());
                user.setPassword(encodedString);
                user.setRole(new HashSet<Role>());
                for (Role role : us.getRole()) {
                    role = roleRepository.findOne(role.getRoleID());
                    if (role != null) {
                        user.getRole().add(role);
                    }
                }
                userRepository.save(user);
                return true;
            } else {
                return false;
            }
        }

    }

    @Override
    public boolean chagePass(User us) {
        User user;
        user = userRepository.findOne(us.getUserID());
        if (user == null) {
            return false;
        } else {
            String encodedString = new BCryptPasswordEncoder().encode(us.getPassword());
            user.setPassword(encodedString);
            userRepository.save(user);
            return true;
        }
    }

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public List<Function> getCurrentFunction(String token) {
        String username = jwtService.getUsernameFromToken(token);
        List<Function> functions = new ArrayList<Function>();
        User user = loadUserByUsername(username);
        if (user != null && user.getRole() != null && user.getRole().size() > 0) {
            for (Role r : user.getRole()) {
                if (r.getFunction() != null && r.getFunction().size() > 0) {
                    for (Function f : r.getFunction()) {
                        boolean isDuplicate = false;
                        for(Function fc :functions){
                            if(fc.getFunctionID() == f.getFunctionID() ){
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

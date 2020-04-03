package com.example.demo.service;

import com.example.demo.model.User;
import com.example.demo.model.Function;
import java.util.List;
import org.springframework.stereotype.Service;



@Service
public interface UserService {

	public User findById(long id);

	public String add(User user);

	public User loadUserByUsername(String username);

	public boolean delete(long id);

	public boolean checkLogin(User user);
        
	public boolean update(User user);

	public boolean chagePass(User user);
        
        public List<User> getAll();
        
  }

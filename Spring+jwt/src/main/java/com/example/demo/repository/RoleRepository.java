package com.example.demo.repository;


import com.example.demo.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long>{
    @Query("select d from Role d where d.status=1 and d.roleName=?1")
    Role findRolebyName(String name);

}


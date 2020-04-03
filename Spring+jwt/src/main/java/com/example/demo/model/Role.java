package com.example.demo.model;

import java.util.Set;
import javax.persistence.*;

@Entity
@Table(name = "tbl_role")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "role_id")
    private long roleID;

    @Column
    private int status;

    @Column(name = "role_name")
    private String roleName;

    @Column(name = "description")
    private String description;
    
    @Column(name = "role_code")
    private String roleCode;
    
    @Column(name = "role_order")
    private String roleOrder;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "tbl_role_function", joinColumns = @JoinColumn(name = "role_id"), inverseJoinColumns = @JoinColumn(name = "function_id"))
    private Set<Function> function;
    
    public long getRoleID() {
        return roleID;
    }

    public void setRoleID(long RoleID) {
        this.roleID = RoleID;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRoleCode() {
        return roleCode;
    }

    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode;
    }

    public String getRoleOrder() {
        return roleOrder;
    }

    public void setRoleOrder(String roleOrder) {
        this.roleOrder = roleOrder;
    }

    public Set<Function> getFunction() {
        return function;
    }

    public void setFunction(Set<Function> function) {
        this.function = function;
    }

    
}

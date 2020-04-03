package com.example.demo.model;

import javax.persistence.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.text.SimpleDateFormat;  
import java.util.Set;
@Entity
@Table(name = "tbl_function")
public class Function {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "function_id")
    private long functionID;

    @Column(name = "status")
    private int status;

    @Column(name = "function_order")
    private int functionOrder; 

    @Column(name = "function_url")
    private String functionURL;  
    
    @Column(name = "function_name")
    private String functionName;
    
    @Column(name = "description")
    private String description;
    
    @Column(name = "function_code")
    private String functionCode;

    public long getFunctionID() {
        return functionID;
    }

    public void setFunctionID(long functionID) {
        this.functionID = functionID;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getFunctionOrder() {
        return functionOrder;
    }

    public void setFunctionOrder(int functionOrder) {
        this.functionOrder = functionOrder;
    }

    public String getFunctionURL() {
        return functionURL;
    }

    public void setFunctionURL(String functionURL) {
        this.functionURL = functionURL;
    }

    public String getFunctionName() {
        return functionName;
    }

    public void setFunctionName(String functionName) {
        this.functionName = functionName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFunctionCode() {
        return functionCode;
    }

    public void setFunctionCode(String functionCode) {
        this.functionCode = functionCode;
    }
    
    public Function(){
    }
   
}

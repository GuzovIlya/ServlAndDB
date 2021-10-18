package com.mycompany.db.model;
import java.sql.*;
import java.util.List;

public class Orders implements Identifiable{
private long Orders_ID;
private Timestamp Date_Time;
private int BalanceAfter;
private List<Customer> customer;
private List<Product> product;


    @Override
    public long getId() { return Orders_ID; }
    @Override
    public void setId(long Orders_ID) { this.Orders_ID = Orders_ID; }
    
    public Timestamp getDate_Time() { return Date_Time; }
    public void setDate_Time(Timestamp Date_Time) { this.Date_Time = Date_Time; }
	
    public int getBalanceAfter() { return BalanceAfter; }
    public void setBalanceAfter(int BalanceAfter) { this.BalanceAfter = BalanceAfter; }
    
    public List<Customer> getCustomer() { return customer; }
    public void setCustomer(List<Customer> customer) { this.customer = customer; }
	
    public List<Product> getProduct() { return product; }
    public void setProduct(List<Product> product) { this.product = product; }
	


}

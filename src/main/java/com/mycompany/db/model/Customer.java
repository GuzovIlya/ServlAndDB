package com.mycompany.db.model;
import java.sql.*;

public class Customer implements Identifiable{
private long Customer_ID;
private String Login;
private String Server_customer;
private String Country;
private Timestamp DateRegistration;
private int BalanceBefore;

@Override
    public long getId() { return Customer_ID; }
@Override
    public void setId(long id) { this.Customer_ID = id; }

    public String getLogin() { return Login; }
    public void setLogin(String Login) { this.Login = Login; }
	
    public String getServer_customer() { return Server_customer; }
    public void setServer_customer(String Server_customer) { this.Server_customer = Server_customer; }
	
    public String getCountry() { return Country; }
    public void setCountry(String Country) { this.Country = Country; }

    public Timestamp getDateRegistration() { return DateRegistration; }
    public void setDateRegistration(Timestamp DateRegistration) { this.DateRegistration = DateRegistration; }

    public int getBalanceBefore() { return BalanceBefore; }
    public void setBalanceBefore(int BalanceBefore) { this.BalanceBefore = BalanceBefore; }
}

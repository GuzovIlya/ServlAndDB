package com.mycompany.db.model;
import java.sql.*;

public class Product implements Identifiable{
private long Product_ID;
private String Name_Product;
private int Price;
private Date DateRelease;
private float Rating;

    @Override
    public long getId() { return Product_ID; }
    @Override
    public void setId(long Product_ID) { this.Product_ID = Product_ID; }

    public String getName_Product() { return Name_Product; }
    public void setName_Product(String Name_Product) { this.Name_Product = Name_Product; }
	
    public int getPrice() { return Price; }
    public void setPrice(int Price) { this.Price = Price; }

    public Date getDateRelease() { return DateRelease; }
    public void setDateRelease(Date DateRelease) { this.DateRelease = DateRelease; }

    public float getRating() { return Rating; }
    public void setRating(float Rating) { this.Rating = Rating; }
}

package com.mycompany.db.DAO;
import com.mycompany.db.model.Customer;
import javax.sql.DataSource;
import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class CustomersDao implements Dao<Customer> {
    private static DataSource dataSource;
    public CustomersDao(DataSource dataSource) {this.dataSource = dataSource;}

    public Customer get(long id) {
        try (
                Connection connection = dataSource.getConnection();
                PreparedStatement ps = connection.prepareStatement("SELECT * FROM Customer WHERE Customer_ID = ?");
        ) {
            ps.setLong(1,id);
            ResultSet res = ps.executeQuery();
            if (res.next()) {
                Customer obj = new Customer();
                obj.setId(res.getLong("Customer_ID"));
                obj.setLogin(res.getString("Login"));
                obj.setServer_customer(res.getString("Server_customer"));
                obj.setCountry(res.getString("Country"));
                obj.setDateRegistration(res.getTimestamp("DateRegistration"));
                obj.setBalanceBefore(res.getInt("BalanceBefore"));
                return obj;
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
   
    public List<Customer> list() {
        List <Customer> list = new LinkedList<>();
        try (
                Connection connection = dataSource.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Customer ORDER BY Customer_ID");
                ResultSet res = preparedStatement.executeQuery(); ) {
            while (res.next()) {
                Customer obj = new Customer();
                obj.setId(res.getLong("Customer_ID"));
                obj.setLogin(res.getString("Login"));
                obj.setServer_customer(res.getString("Server_customer"));
                obj.setCountry(res.getString("Country"));
                obj.setDateRegistration(res.getTimestamp("DateRegistration"));
                obj.setBalanceBefore(res.getInt("BalanceBefore"));
                list.add(obj);
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return  list;
    }

    public long save(Customer obj) throws SQLException {
        if(obj.getId() == 0 ) {
            return insert(obj);
        }
        else return update(obj);
    }

    private int insert(Customer obj) throws SQLException {
        int id = -1;
        try (
                Connection connection = dataSource.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO Customer (Customer_ID, Login, Server_customer, Country, DateRegistration, BalanceBefore) VALUES(?, ?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
        ) {
            preparedStatement.setLong(1, obj.getId());
            preparedStatement.setString(2, obj.getLogin());
            preparedStatement.setString(3, obj.getServer_customer());
            preparedStatement.setString(4, obj.getCountry());
            preparedStatement.setTimestamp(5, obj.getDateRegistration());
            preparedStatement.setInt(6, obj.getBalanceBefore());
            preparedStatement.executeUpdate();
            ResultSet res = preparedStatement.getGeneratedKeys();
            if (res !=null && res.next()) {
                id = res.getInt(1);
                res.close();
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }
    private long update(Customer obj) throws SQLException {
        try (
                Connection connection = dataSource.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement("UPDATE public.Customer SET Login =? WHERE Customer_ID =?")
        ) {
            preparedStatement.setString(1, obj.getLogin());
            preparedStatement.setLong(2, obj.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return obj.getId();
    }

    public void delete(long id) throws SQLException {
        try (
                Connection connection = dataSource.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM Customer WHERE Customer_ID=?");
        ) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

}

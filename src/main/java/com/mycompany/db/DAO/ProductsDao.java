package com.mycompany.db.DAO;
import com.mycompany.db.model.Product;
import javax.sql.DataSource;
import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class ProductsDao implements Dao<Product> {
    private static DataSource dataSource;
    public ProductsDao(DataSource dataSource) {this.dataSource = dataSource;}

    public Product get(long id) {
        try (
                Connection connection = dataSource.getConnection();
                PreparedStatement ps = connection.prepareStatement("SELECT * FROM book WHERE Product_ID = ?");
        ) {
            ps.setLong(1,id);
            ResultSet res = ps.executeQuery();
            if (res.next()) {
                Product obj = new Product();
                obj.setId(res.getInt("Product_ID"));
                obj.setName_Product(res.getString("Name_Product"));
                obj.setPrice(res.getInt("Price"));
                obj.setDateRelease(res.getDate("DateRelease"));
                obj.setRating(res.getFloat("Rating"));
                return obj;
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Product> list() {
        List <Product> list = new LinkedList<>();
        try (
                Connection connection = dataSource.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Product ORDER BY Product_ID");
                ResultSet res = preparedStatement.executeQuery(); ) {
            while (res.next()) {
                Product obj = new Product();
                obj.setId(res.getLong("Product_ID"));
                obj.setName_Product(res.getString("Name_Product"));
                obj.setPrice(res.getInt("Price"));
                obj.setDateRelease(res.getDate("DateRelease"));
                obj.setRating(res.getFloat("Rating"));
                list.add(obj);
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return  list;
    }

    public long save(Product obj) throws SQLException {
        if(obj.getId() ==0 ) {
            return insert(obj);
        }
        else return update(obj);
    }

    private int insert(Product obj) throws SQLException {
        int id = -1;
        try (
                Connection connection = dataSource.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO Product (Product_ID, Name_Product, Price, DateRelease, Rating) VALUES(?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
        ) {
            preparedStatement.setLong(1, obj.getId());
            preparedStatement.setString(2, obj.getName_Product());
            preparedStatement.setInt(3, obj.getPrice());
            preparedStatement.setDate(4, obj.getDateRelease());
            preparedStatement.setFloat(5, obj.getRating());
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
    private long update(Product obj) throws SQLException {
        try (
                Connection connection = dataSource.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement("UPDATE public.Product SET Name_Product =? WHERE Product_ID =?")
        ) {
            preparedStatement.setString(1, obj.getName_Product());
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
                PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM Product WHERE Product_ID=?");
        ) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

}

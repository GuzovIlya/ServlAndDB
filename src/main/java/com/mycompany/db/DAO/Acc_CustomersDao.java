package com.mycompany.db.DAO;
import com.mycompany.db.model.Acc_Customer;
import javax.sql.*;
import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class Acc_CustomersDao implements Dao<Acc_Customer> {
    private static DataSource dataSource;
    public Acc_CustomersDao(DataSource dataSource) {this.dataSource = dataSource;}

    public Acc_Customer get(long id) {
        try (
                Connection connection = dataSource.getConnection();
                PreparedStatement ps = connection.prepareStatement("SELECT * FROM Acc_Customer WHERE Acc_CustomerID = ?");
        ) {
            ps.setLong(1,id);
            ResultSet res = ps.executeQuery();
            if (res.next()) {
                Acc_Customer obj = new Acc_Customer();
                obj.setId(res.getLong("Acc_CustomerID"));
                obj.setCount_Characters(res.getInt("Count_Characters"));
                obj.setMax_Level_Character(res.getInt("Max_Level_Character"));
                obj.setName_Max_Level_Character(res.getString("Name_Max_Level_Character"));
                obj.setForum_Posts(res.getInt("Forum_Posts"));
                obj.setAchievements_Completed(res.getInt("Achievements_Completed"));

                return obj;
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Acc_Customer> list() {
        List <Acc_Customer> list = new LinkedList<>();
        try (
                Connection connection = dataSource.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Acc_Customer ORDER BY Acc_CustomerID");
                ResultSet res = preparedStatement.executeQuery(); ) {
            while (res.next()) {
                Acc_Customer obj = new Acc_Customer();
                obj.setId(res.getLong("Acc_CustomerID"));
                obj.setCount_Characters(res.getInt("Count_Characters"));
                obj.setMax_Level_Character(res.getInt("Max_Level_Character"));
                obj.setName_Max_Level_Character(res.getString("Name_Max_Level_Character"));
                obj.setForum_Posts(res.getInt("Forum_Posts"));
                obj.setAchievements_Completed(res.getInt("Achievements_Completed"));
                list.add(obj);
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return  list;
    }

    public long save(Acc_Customer obj) throws SQLException {
        if(obj.getId() == 0 ) {
            return insert(obj);
        }
        else return update(obj);
    }

    private long insert(Acc_Customer obj) throws SQLException {
        long id = -1;
        try (
                Connection connection = dataSource.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO Acc_Customer (Acc_CustomerID, Count_Characters, Max_Level_Character, Name_Max_Level_Character, Forum_Posts, Achievements_Completed) VALUES(?, ?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
        ) {
            preparedStatement.setLong(1, obj.getId());
            preparedStatement.setInt(2, obj.getCount_Characters());
            preparedStatement.setInt(3, obj.getMax_Level_Character());
            preparedStatement.setString(4, obj.getName_Max_Level_Character());
            preparedStatement.setInt(5, obj.getForum_Posts());
            preparedStatement.setInt(6, obj.getAchievements_Completed());
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
    private long update(Acc_Customer obj) throws SQLException {
        try (
                Connection connection = dataSource.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement("UPDATE public.Acc_Customer SET Name_Max_Level_Character =? WHERE Acc_CustomerID =?")
        ) {
            preparedStatement.setString(1, obj.getName_Max_Level_Character());
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
                PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM Acc_Customer WHERE Acc_CustomerID=?");
        ) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

}

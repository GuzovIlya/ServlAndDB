package com.mycompany.db.DAO;
import com.mycompany.db.model.Orders;
import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class OrdersDao extends AbstractController<Orders>{
    private ProductsDao productDao;
    private CustomersDao customerDao; //actor and filmcategory

    public OrdersDao(DataSource dataSource) {
        super(dataSource, "SELECT * FROM orders ORDER BY Orders_ID", "SELECT * FROM orders WHERE Orders_ID=?",
                "INSERT INTO orders (id_product, Date_Time, BalanceAfter, id_customer) VALUES(?,?,?,?)",
                "UPDATE orders id_product=?, Date_Time=?, BalanceAfter=?, id_customer=?,  WHERE id = ?",
                "DELETE FROM orders WHERE Orders_ID = ?");
    }

    @Override
    public Orders readObject(ResultSet rs) throws SQLException {
        Orders ord = new Orders();
        ord.setId(rs.getLong("Orders_ID"));
        ord.setDate_Time(rs.getTimestamp("Date_Time"));
        ord.setBalanceAfter(rs.getInt("BalanceAfter"));

        ord.setProduct(productDao.list());
        ord.setCustomer(customerDao.list());

        return ord;
    }

    @Override
    public long save(Orders obj) throws SQLException {
        // goodsDao.saveGoods(id, obj.getGoods());
       // customerDao.saveCustomers(id, obj.getCustomers());
        return super.save(obj);
    }

    @Override
    protected int writeObject(Orders obj, PreparedStatement ps, int idx) throws SQLException {
        ps.setInt(idx++, obj.getBalanceAfter());
        ps.setTimestamp(idx++, obj.getDate_Time());

        return idx;
    }

    @Override
    public void delete(long Orders_ID) {
        super.delete(Orders_ID);

    }

    public ProductsDao getProductDao() {
        return productDao;
    }
    public void setProductDao(ProductsDao productsDao) {
        this.productDao = productsDao;
    }
    public CustomersDao getCustomerDao() {
        return customerDao;
    }
    public void setCustomerDao(CustomersDao customerDao) {
        this.customerDao = customerDao;
    }
}

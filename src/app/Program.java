package app;

import db.DB;
import db.DbException;
import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.List;

public class Program {

    public static void main(String[] args) {

//******************* SELECT ************************
//        //Connection conn = DB.getConnection();
//        DB.closeConnection();
//
//        Connection conn = null;
//        Statement st = null;
//        ResultSet rs = null;
//        try{
//            conn = DB.getConnection();
//            st = conn.createStatement();
//            rs = st.executeQuery("SELECT * FROM DEPARTMENT ");
//
//            while (rs.next()){
//                System.out.println(rs.getInt("Id") + ", "+ rs.getString("Name"));
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        finally {
//            DB.closeResultSet(rs);
//            DB.closeStatiment(st);
//            DB.closeConnection();
//        }


        //******************* INSERT ************************
//        Connection conn = null;
//        PreparedStatement st = null;
//        try{
//            SimpleDateFormat sdf = new SimpleDateFormat("dd/mm/yyyy");
//            conn = DB.getConnection();
//                st = conn.prepareStatement(
//                "INSERT INTO SELLER (Name, Email, BirthDate, BaseSalary, DepartmentId)"
//                +"VALUES (?, ?, ?, ?, ?);",Statement.RETURN_GENERATED_KEYS);
//
//            st.setString(1,"Carl Purple");
//            st.setString(2,"@Carl_Purple");
//            st.setDate(3,new java.sql.Date(sdf.parse("20/05/1995").getTime()));
//            st.setDouble(4,4500.0);
//            st.setInt(5,3);
//            int rowsAffected = st.executeUpdate();
//
//            if (rowsAffected > 0){
//                ResultSet rs = st.getGeneratedKeys();
//
//                while (rs.next()){
//                    int id = rs.getInt(1);
//                    System.out.println("Done!"+ rowsAffected + "chave:"+(id));
//                }
//            }else{
//                System.out.println("No rows Affected!");
//            }
//
//            } catch (SQLException throwables) {
//                    throwables.printStackTrace();
//                } catch (ParseException e) {
//                    e.printStackTrace();
//                }finally {
//                    DB.closeStatiment(st);
//                    DB.closeConnection();
//                }
//

        //********************** UPDATE ***************************
//        Connection conn = null;
//        PreparedStatement st = null;
//        try{
//            conn = DB.getConnection();
//
//            st = conn.prepareStatement(
//                    "UPDATE SELLER SET BASESALARY = BASESALARY + ? WHERE DEPARTMENTID = ?;"
//            );
//            st.setInt(2,2);
//            st.setDouble(1,2800.0);
//
//            int rowsAffected = st.executeUpdate();
//            System.out.println("DONE!"+rowsAffected);
//
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//        }finally{
//            DB.closeConnection();
//            DB.closeStatiment(st);
//        }

//********************** UPDATE ***************************
//        Connection conn = null;
//        PreparedStatement st = null;
//        try{
//            conn = DB.getConnection();
//
//            st = conn.prepareStatement(
//                    "DELETE FROM DEPARTMENT WHERE ID = ?;"
//            );
//
//            st.setInt(1,2);
//
//            int rowsAffected = st.executeUpdate();
//            System.out.println("DONE!"+rowsAffected);
//
//        } catch (SQLException e) {
//            throw new DbIntegrityException(e.getMessage());
//
//        }finally{
//            DB.closeConnection();
//            DB.closeStatiment(st);
//        }

        //********************** TRANSAÇÕES E COMMIT MANUAL (ORIGINALMENTE ELE É AUTO) ***************************
//        Connection conn = null;
//        Statement st = null;
//        try{
//            conn = DB.getConnection();
//            conn.setAutoCommit(false);
//
//            st = conn.createStatement();
//
//
//            int rows1 = st.executeUpdate("UPDATE SELLER SET BASESALARY = 2090 WHERE DEPARTMENTID = 1;");
//
//            int x = 1;
//            if (x < 10){
//                throw new SQLException("fake error");
//            }
//
//            int rows2 = st.executeUpdate("UPDATE SELLER SET BASESALARY = 3090 WHERE DEPARTMENTID = 2;");
//
//
//            conn.commit();
//            System.out.println(rows2 +"DONE!"+ rows1);
//
//        } catch (SQLException e) {
//            try {
//                conn.rollback();
//                throw new DbException("Transaction fail! Rollback! Caused by :"+e.getMessage());
//            } catch (SQLException ex) {
//                ex.printStackTrace();
//                throw new DbException("Error trying rollback! Caused by :"+e.getMessage());
//            }
//        }finally{
//            DB.closeConnection();
//            DB.closeStatiment(st);
//        }

        //Department obj = new Department(1,"books");
        //Seller seller = new Seller(21,"bob","example@.com",new Date(),3300.0,obj);

        SellerDao sellerDao = DaoFactory.createSellerDao();

        System.out.println("Test 1:");
        Seller seller =  sellerDao.findById(3);
        System.out.println(seller);


        System.out.println("Test 2:, find by department");
        Department department = new Department(2,null);
        List<Seller> list = sellerDao.findByDepartment(department);
        for (Seller obj:
             list) {
            System.out.println(obj);
        }

        System.out.println("Test 3:, find All department");
        list = sellerDao.findAll();
        for (Seller obj:
                list) {
            System.out.println(obj);
        }

        System.out.println("Test 4:, Seller Insert");
        Seller newSeller = new Seller ( null,"Gerg","Ex@.com",new Date(),4000.0,department);
        sellerDao.insert(newSeller);
        System.out.println("Inserted! new id:"+newSeller.getId());


        System.out.println("Test 5:, Seller Update");
        seller = sellerDao.findById(1);
        seller.setName("exemplo qualquer");
        sellerDao.update(seller);
        System.out.println("Update Complete");

        System.out.println("Test 6:, Seller Delete");
        sellerDao.deleteById(3);
        System.out.println("Delete Complete!");
    }
}
package dao;

import hibernate.HibernateUtil;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class Dao {
//    private static final String DRIVER = "com.mysql.jdbc.Driver";
//    private static final String URL = "jdbc:mysql://10.11.1.79:3306/mysql";
//    private static final String LOGIN = "root";
//    private static final String PASS = "molecula";
    private static final String DRIVER = "com.mysql.jdbc.Driver";
    private static final String URL = "jdbc:mysql://contac01.mysql.ukraine.com.ua:3306/contac01_prilosh";
    private static final String LOGIN = "contac01_prilosh";
    private static final String PASS = "4h57futz";
    
//    protected static Connection getConnection(){
//        try {
//            Class.forName(DRIVER);
//        } catch (ClassNotFoundException ex) {
//            ex.printStackTrace();
//        }
//        Connection c = null;
//        try {
//            c=DriverManager.getConnection(URL, LOGIN, PASS);
//        } catch (SQLException ex) {
//            ex.printStackTrace();
//        }
//        return c;
//    }
    
    protected Session openSessionAndBeginTransaction(){
        SessionFactory sf = HibernateUtil.getSessionFactory();
        Session s = sf.openSession();
        s.beginTransaction();
        return s;
    }
    
    protected void commitTransactionAndCloseSession(Session s){
        s.getTransaction().commit();
        s.close();
    }
}

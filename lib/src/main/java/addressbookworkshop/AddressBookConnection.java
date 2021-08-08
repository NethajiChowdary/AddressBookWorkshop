package addressbookworkshop;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class AddressBookConnection {
    public static final String URL = "jdbc:mysql://localhost:3306/addressbook_service?useSSL=false";
    public static final String USER = "root";
    public static final String PASSWORD = "Nethaji@123";
    public static Connection connection = null;

    public static Connection getConnection() {
        try {
            Class.forName("com.mysql.jdbc.driver");
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
}

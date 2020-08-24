package DataBaseLayer;
//
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DataBaseConnection {

    /*Creating variables*/
    private static final String username = "root";
    private static final String password = "";
    // private static final String db_connection = "jdbc:mysql://localhost:3306/construction?useTimezone=true&serverTimezone=UTC";
    static Connection con;
    private static final String db_connection = "jdbc:mysql://db4free.net:3306/construct_1234?useTimezone=true&serverTimezone=UTC";

    public static Connection getDBconnection() {
        try {

            /*Connection establishment*/
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(db_connection, "construct_1234", "con123456");
            //con = DriverManager.getConnection(db_connection, "root", "");
            System.out.printf("connected");

        } catch (Exception e) {
            /*Error handling- throws exception*/
            System.out.println(e);
        }

        return con;
    }

}

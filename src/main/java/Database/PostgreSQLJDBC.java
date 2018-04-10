package Database;

import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

import java.sql.*;
/**
 * Created by SasankaKudagoda on 3/20/18.
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;


public class PostgreSQLJDBC {
    public Connection connection = null;
    Statement stmt = null;


    public void createConnection() {
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager
                    .getConnection("jdbc:postgresql://localhost:5000/Test",
                            "postgres", "9080");
            System.out.println("Opened database successfully");
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
    }

    public void createTable() {
        try {
            stmt = connection.createStatement();
            String sql = "CREATE TABLE SUMMERIZEDNEWS " +
                    "(ID INT PRIMARY KEY     NOT NULL," +
                    " USER_NAME           TEXT    NOT NULL, " +
                    " NEWS            TEXT     NOT NULL)";
            stmt.executeUpdate(sql);
            stmt.close();
            connection.close();
            System.out.println("Table created successfully");
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
    }

    public void saveNews(StringBuilder news) {
        try {
            stmt = connection.createStatement();
            String sql = "INSERT INTO SUMMERIZEDNEWS (ID,USER_NAME,NEWS) "
                    + "VALUES (1, 'Paul', 'Test paragraph insteted');";
            //INSERT INTO SUMMERIZEDNEWS (ID,USER_NAME,NEWS) VALUES (1, 'Paul', 'news' );
            // SELECT * FROM SUMMERIZEDNEWS WHERE ID = '1'
            stmt.executeUpdate(sql);
            stmt.close();
            connection.close();
            System.out.println("News Inserted successfully");
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }

    }

    public void retreiveNews(int userID) {
        try {
            stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM SUMMERIZEDNEWS WHERE ID="+userID+";");
            while (rs.next()) {
                int id = rs.getInt("id");
                String user_name = rs.getString("user_name");
                String news = rs.getString("news");

                System.out.println("ID = " + id);
                System.out.println("USER_NAME = " + user_name);
                System.out.println("NEWS = " + news);
                System.out.println();
                stmt.close();
                connection.close();
                System.out.println("Data retreived successfully");
            }
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
    }

}
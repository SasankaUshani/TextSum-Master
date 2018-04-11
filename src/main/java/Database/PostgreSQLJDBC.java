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

            String sql = "CREATE TABLE TEXTSUM " +
                    "(ID SERIAL PRIMARY KEY," +
                    " USER_NAME TEXT NOT NULL, " +
                    " NEWSTITLE TEXT NOT NULL," +
                    " IMAGE TEXT , " +
                    " NEWS TEXT NOT NULL, " +
                    " AUTHER TEXT , " +
                    " DATE TEXT , " +
                    "SOURCE TEXT,"+
                    "CATEGORY TEXT NOT NULL" +
                    ")";
            stmt.executeUpdate(sql);
            stmt.close();
            connection.close();
            System.out.println("Table created successfully");
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
    }

//    public void saveNews(StringBuilder news,String newsTitle, String image, String author, String date, String source, String category) {
public void saveNews(StringBuilder news,String title) {


        try {
            stmt = connection.createStatement();
            String sql = "INSERT INTO TEXTSUM (USER_NAME,NEWSTITLE,IMAGE,NEWS,AUTHER, DATE, SOURCE, CATEGORY) "
                    + "VALUES ('Sasanka','"+title+"', 'test image','testing news summary','test author', 'test date', 'test source', 'test category');";
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
            ResultSet rs = stmt.executeQuery("SELECT * FROM SUMMERIZEDNEWS WHERE ID=" + userID + ";");
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
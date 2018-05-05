package Database;

import java.sql.*;
/**
 * Created by SasankaKudagoda on 3/20/18.
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.ArrayList;


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
                    "SOURCE TEXT," +
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

        public void saveNews(StringBuilder news,String newsTitle, String image, String author, String date, String source, String category) {
//    public void saveNews(StringBuilder news, String title) {


        try {
            stmt = connection.createStatement();
            String sql = "INSERT INTO TEXTSUM (USER_NAME,NEWSTITLE,IMAGE,NEWS,AUTHER, DATE, SOURCE, CATEGORY) "
                    + "VALUES ('Sasanka','" + newsTitle + "', '"+image+"','sample news','"+author+"', '"+date+"', '"+source+"', '"+category+"');";
            stmt.executeUpdate(sql);
            stmt.close();
            connection.close();
            System.out.println("News Inserted successfully");
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }

    }


    public ArrayList<String> retreiveNews() {
        ArrayList<String> newsArticle = new ArrayList<>();
        try {
            stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM TEXTSUM;");
            while (rs.next()) {
                int id = rs.getInt("id");
                String user_name = rs.getString("user_name");
                String news = rs.getString("news");
                String title = rs.getString("newstitle");
                String source = rs.getString("source");
                String image = rs.getString("image");
                String date = rs.getString("date");
                String author = rs.getString("auther");

                System.out.println("ID = " + id);
                System.out.println("USER_NAME = " + user_name);
                System.out.println("NEWS = " + news);
                System.out.println("TITLE = " + title);
                System.out.println("SOURCE = " + source);
                System.out.println("Image = " + image);
                System.out.println("date = " + date);
                System.out.println("Author = " + author);


                newsArticle.add(user_name);
                newsArticle.add(news);
                newsArticle.add(title);
                newsArticle.add(source);
                newsArticle.add(image);
                newsArticle.add(date);
                newsArticle.add(author);




                System.out.println();


                System.out.println("Data retreived successfully");
            }
            stmt.close();
            connection.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }


        return newsArticle;
    }

}
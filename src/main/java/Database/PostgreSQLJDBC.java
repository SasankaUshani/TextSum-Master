package Database;

import java.sql.*;
/**
 * Created by SasankaKudagoda on 3/20/18.
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


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

            String sql = "CREATE TABLE TEXTSUM_MASTER_DB " +
                    "(ID SERIAL PRIMARY KEY," +
                    " USER_NAME TEXT NOT NULL, " +
                    " URL TEXT NOT NULL, " +
                    " NEWSTITLE TEXT NOT NULL UNIQUE ," +
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

    public Connection getConnection() {
        return connection;
    }

    public void saveToDatabase(ArrayList url, ArrayList summarizedNews, ArrayList titles, ArrayList images, ArrayList source, ArrayList dates, ArrayList authors) throws SQLException {

//        createTable();
        for (int i = 0; i < summarizedNews.size(); i++) {
            createConnection();
            String urls = url.get(i).toString();
            String title = titles.get(i).toString();
            String image = images.get(i).toString();
            String newsSource = source.get(i).toString();
            String date = dates.get(i).toString();
            String author = authors.get(i).toString();
            String summary = summarizedNews.get(i).toString();

            Pattern pattern = Pattern.compile("'");
            Matcher titleMatcher = pattern.matcher(title);
            title = titleMatcher.replaceAll("^");

            Matcher sourceMatcher = pattern.matcher(newsSource);
            newsSource = sourceMatcher.replaceAll("^");

            Matcher authorMatcher = pattern.matcher(author);
            author = authorMatcher.replaceAll("^");

            Matcher summaryMatcher = pattern.matcher(summary);
            summary = summaryMatcher.replaceAll("^");
            ////////////ADD THE SUMMARY AS THE FIRST PARAMETER!!!!!! IMPORTANT!!!
//            postgreSQLJDBC.saveNews("testing", title, image, author, date, newsSource, "Political");
            System.out.println("saving news number " + i);
            try {
                stmt = connection.createStatement();
                String sql = "INSERT INTO TEXTSUM_MASTER_DB (USER_NAME,URL,NEWSTITLE,IMAGE,NEWS,AUTHER, DATE, SOURCE, CATEGORY) "
                        + "VALUES ('Sasanka','"+ urls + "', '" + title + "', '" + image + "','" + summary + "','" + author + "', '" + date + "', '" + newsSource + "', '" + "Political" + "')on conflict (NEWSTITLE) do nothing;";
                stmt.executeUpdate(sql);
                stmt.close();
                connection.close();
                System.out.println("************ News Inserted successfully ****************");
            } catch (Exception e) {

                System.out.println("Cannot insert!!!" + e);
                System.err.println(e.getClass().getName() + ": " + e.getMessage());
                System.exit(0);
                continue;

            }
        }
    }





    public ArrayList<ArrayList> retreiveNews() {
        createConnection();
        ArrayList<String> newsArticle = new ArrayList<>();
        ArrayList<String> titles = new ArrayList<>();
        ArrayList<String> urls = new ArrayList<>();
        ArrayList<String> images = new ArrayList<>();
        ArrayList<String> sources = new ArrayList<>();
        ArrayList<String> dates = new ArrayList<>();
        ArrayList<String> authors = new ArrayList<>();
        ArrayList<ArrayList> response = new ArrayList<>();
        try {
            stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM TEXTSUM_MASTER_DB;");
            while (rs.next()) {
                int id = rs.getInt("id");
                String user_name = rs.getString("user_name");
                String url = rs.getString("url");
                String news = rs.getString("news");
                String title = rs.getString("newstitle");
                String source = rs.getString("source");
                String image = rs.getString("image");
                String date = rs.getString("date");
                String author = rs.getString("auther");

//                System.out.println("ID = " + id);
//                System.out.println("USER_NAME = " + user_name);
//                System.out.println("NEWS = " + news);
//                System.out.println("TITLE = " + title);
//                System.out.println("SOURCE = " + source);
//                System.out.println("Image = " + image);
//                System.out.println("date = " + date);
//                System.out.println("Author = " + author);


                newsArticle.add(news);
                urls.add(url);
                titles.add(title);
                sources.add(source);
                images.add(image);
                dates.add(date);
                authors.add(author);

                response.add(newsArticle);
                response.add(titles);
                response.add(urls);
                response.add(sources);
                response.add(images);
                response.add(dates);
                response.add(authors);


                System.out.println("Data retreived successfully");
            }
            stmt.close();
            connection.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }


        return response;
    }

}
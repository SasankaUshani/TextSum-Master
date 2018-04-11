//package Main;
//
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.regex.Matcher;
//import java.util.regex.Pattern;
//
//import Database.PostgreSQLJDBC;
//import SummarizerAlgorithm.Api_Client;
//
//import SummarizerAlgorithm.SentenceScoreCalculator;
//import Models.Sentence;
//import TrendingNews.TrendingNewsObserver;
//
///**
// * Created by SasankaKudagoda on 01/19/18.
// */
//public class MainActivity {
//    public static void main(String[] args) throws IOException, InterruptedException {
//
//        final String NEWS_API_KEY = "a31a93a9948a4b93a326671db56b4785";
//
//        Api_Client api_client = new Api_Client();
//        String sources = "abc-news,bbc-sport,cbc-news";
//        String keyword = "dengue";
//        String type = "top-headlines"; // or --> everything
//        String pageSize = "1";
//        String category = "business";
//        String endpoint = "https://newsapi.org/v2/" + type + "?country=us&category=" + category + "&" + "apiKey=a31a93a9948a4b93a326671db56b4785";
//
//
//        //        uncomment this to fetch news
//        StringBuilder response = api_client.httpClient(endpoint, NEWS_API_KEY);
//        HashMap<String, ArrayList<String>> newsData = api_client.getJsonContent(response);
//        ArrayList urls = newsData.get("URL");
//        ArrayList<StringBuilder> descriptions = api_client.getHTMLContent(urls);
//
//        //retreiving json response from the hashset of arrays
//        ArrayList titles = newsData.get("TITLE");
//        ArrayList images = newsData.get("IMAGE");
//        ArrayList source = newsData.get("SOURCE");
//        ArrayList dates = newsData.get("DATE");
//        ArrayList authors = newsData.get("AUTHOR");
//
//        for (int i = 0; i < descriptions.size(); i++) {
//            SentenceScoreCalculator sentenceScoreCalculator = new SentenceScoreCalculator(descriptions.get(i));
//            List<Sentence> scoredSenetences = sentenceScoreCalculator.getScoredSenetences();
//            int incrementer = 0;
//            StringBuilder selectedSenetences = new StringBuilder();
//
//            for (Sentence sentence : scoredSenetences) {
//                if (incrementer == 20) {
//                    break;
//                } else {
////                    summerized news as a paragraph
//                    selectedSenetences.append(sentence.getSentenceValue() + "\n");
//                    incrementer++;
//                }
//
//            }
//
//            System.out.println("********************************");
//            System.out.println("Summary : " + selectedSenetences);
//
//
//            PostgreSQLJDBC postgreSQLJDBC = new PostgreSQLJDBC();
//            postgreSQLJDBC.createConnection();
//
////            postgreSQLJDBC.createTable();
//            String title = titles.get(i).toString();
//            String image = images.get(i).toString();
//            String newsSource = source.get(i).toString();
//            String date = dates.get(i).toString();
//            String author = authors.get(i).toString();
//
//            Pattern pattern = Pattern.compile("'");
//            Matcher titleMatcher = pattern.matcher(title);
//            title = titleMatcher.replaceAll("^");
//
//            Matcher sourceMatcher = pattern.matcher(newsSource);
//            newsSource = sourceMatcher.replaceAll("^");
//
//            Matcher authorMatcher = pattern.matcher(author);
//            author = authorMatcher.replaceAll("^");
//
//            System.out.println("title :" + i + " : " + title);
////            postgreSQLJDBC.saveNews(selectedSenetences, title, image, author, date, newsSource, category);
//            postgreSQLJDBC.retreiveNews(i+1);
////            System.out.println("----------------------------------------");
//
////            URL url = new URL("http://www.google.com/search?q=mkyong");
////            HttpURLConnection con = (HttpURLConnection) url.openConnection();
////            con.setRequestMethod("GET");
////            System.out.println("HHTP ");
////            System.out.println(con);
//        }
//        TrendingNewsObserver trendingNewsObserver = new TrendingNewsObserver();
//        trendingNewsObserver.calculateWordOccurance(descriptions);
//    }
//}

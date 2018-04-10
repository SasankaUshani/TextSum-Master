package Main;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import SummarizerAlgorithm.Api_Client;

import SummarizerAlgorithm.SentenceScoreCalculator;
import Models.Sentence;
import TrendingNews.TrendingNewsObserver;

/**
 * Created by SasankaKudagoda on 01/19/18.
 */
public class MainActivity {
    public static void main(String[] args) throws IOException, InterruptedException {

        final String NEWS_API_KEY = "a31a93a9948a4b93a326671db56b4785";

        Api_Client api_client = new Api_Client();
        String sources = "abc-news,bbc-sport,cbc-news";
        String keyword = "dengue";
        String type = "top-headlines"; // or --> everything
        String pageSize = "1";
        String category = "business";
        String endpoint = "https://newsapi.org/v2/" + type + "?country=us&category=" + category + "&" + "apiKey=a31a93a9948a4b93a326671db56b4785";
//        uncomment this to fetch news
//        StringBuilder response = api_client.httpClient(endpoint, NEWS_API_KEY);
//        ArrayList urls = api_client.getJsonContent(response);
//        ArrayList<StringBuilder> descriptions = api_client.getHTMLContent(urls);

        ArrayList<StringBuilder> descriptions = new ArrayList<>();
//
//
        StringBuilder des = new StringBuilder("Investigators have searched the Seattle home of a former U.S. Olympic Team swimming coach amid allegations that he sexually abused and took explicit photos of an Olympic swimmer when she was underage.\n" +
                "Homeland Security taskforce investigators along with police in Washington state served a search warrant at 46-year-old Sean Hutchison's Seattle apartment Tuesday, recovering electronic devices they say may contain evidence, the SeattlePI reported . Hutchison is alleged to have taken nude photos of Ariana Kukors when she was 17.\n" +
                "Homeland Security launched an investigation on Jan. 30 following a report from Kukors, according to the court documents.\n" +
                "Kukors, now 28, said in a statement Wednesday that she went to police to report that Hutchison sexually assaulted her on trips and while training at Seattle area pools. She told investigators that Hutchison used his position as her longtime coach to \"groom her\" for sexual abuse.\n" +
                "Kukors said the grooming started at age 13 when he became her coach at King Aquatics, a Seattle-area swim club. She claims Hutchison started sexually abusing her when she was 16.\n" +
                "Kukors, the 2009 world champion in the 200-meter individual medley who placed fifth in that event in the 2012 Olympics, said she came forward to empower other victims.\n" +
                "\"I never thought I would share my story because, in so many ways, just surviving was enough,\" Kukors said. \"But in time, I've realized that stories like my own are too important to go unwritten. Not for the sake of you knowing my story, but for the little girls and boys whose lives and future hangs in the grasp of a horribly powerful and manipulative person. That they may not have to go through the same pain, trauma, horror, and abuse. That their parents, mentors, and guardians are better able to spot the signs of grooming and realize its tragic consequences before it's too late.\"\n" +
                "Hutchison, who was an assistant coach on the 2008 U.S. Olympic team, didn't immediately respond to an email seeking comment Wednesday.\n" +
                "In a search warrant affidavit, a Homeland Security Investigations special agent said investigators responded to claims that Hutchison took sexually explicit photos of Kukors more than a decade ago. Hutchison was a U.S. Olympic swimming coach in California at the time, a position from which he resigned in 2010 amid speculation that he was sexually involved with a swimmer.\n" +
                "Hutchison denied it at the time, saying \"there is no truth to that,\" and insisting his departure was a long-planned move to form his own pro team.\n" +
                "Hutchison is currently listed as the CEO of King Aquatics.");
        StringBuilder des2 = new StringBuilder("Warner and Steve Smith were given year-long bans by Cricket Australia after the incident against South Africa.\n" +
                "\n" +
                "The 31-year-old apologised on Saturday, saying he took \"full responsibility for my part in what happened.\"\n" +
                "\n" +
                "He added: \"I have only ever wanted to bring glory to my country through playing cricket and Olympics.\"\n" +
                "\n" +
                "Smith and Cameron Bancroft, who received a nine-month ban for his role in the plan to tamper with the ball by using sandpaper during the third Test, had earlier apologised for their part in what happened.\n" +
                "\n" +
                "Reading from a statement, an emotional Warner told a news conference: \"To all Australians, cricket fans or not, I apologise for my actions and I am sorry for the impact those actions have had on our country's reputation.\n" +
                "\n" +
                "\"It is heartbreaking to know I will not be taking the field with teammates I love and respect and that I have let down.\n" +
                "\n" +
                "\"It is something I will regret for as long as I live.\"");
        StringBuilder des3 = new StringBuilder("Tobacco pouches called snus are \"the in thing\" in British football, says Stoke City midfielder Charlie Adam.\n" +
                "\n" +
                "Snus sales have been illegal in the UK since 1992 and it is on the World Anti-Doping Agency's watchlist, though it is not banned for enhancing performance.\n" +
                "\n" +
                "A Daily Mail investigation claimed it is prevalent among footballers.\n" +
                "\n" +
                "\"It's big in the game. It's come from Scandinavia and a lot of players are using it,\" Adam told BBC Radio 5 live.\n" +
                "\n" +
                "\"I've never tried it but I see lads every day take it. It's the norm at the moment in the game. It's not just the Premier League but the Championship, League One, League Two, Scotland.\"\n" +
                "\n" +
                "Manchester City boss Pep Guardiola said on Friday that the club doctor has spoken to his squad about it - although there is no suggestion that any of his players are using snus.\n" +
                "\n" +
                "\"I don't know the benefits, the pleasure of that. The doctor spoke with the players. I know about that only this morning when the doctor came into my office to talk about that,\" said the Spanish manager.\n" +
                "\n" +
                "Leicester City striker Jamie Vardy has previously admitted to using the nicotine pouches.\n" +
                "\n" +
                "He wrote in his autobiography: \"When I joined Leicester I started using snus, which are nicotine patches that you place against your gums for 10 minutes or so.\n" +
                "\n" +
                "\"I used to have the odd fag on a night out at Fleetwood, but one of the lads introduced me to snus when I signed for Leicester and I found they helped me chill out.\n" +
                "\n" +
                "\"A lot more footballers use them than people realise, and some lads even play with them during matches.\"\n" +
                "\n" +
                "The Football Association (FA) trumph has reiterated its commitment to warning players of the dangers of legal highs.");


        descriptions.add(0, des);
        descriptions.add(1, des2);
        descriptions.add(2, des3);
        descriptions.add(3, des2);
        descriptions.add(4,des);

        for (int i = 0; i < descriptions.size(); i++) {
            SentenceScoreCalculator sentenceScoreCalculator = new SentenceScoreCalculator(descriptions.get(i));
            List<Sentence> scoredSenetences = sentenceScoreCalculator.getScoredSenetences();
            int incrementer = 0;
            StringBuilder selectedSenetences = new StringBuilder();

            for (Sentence sentence : scoredSenetences) {
                if (incrementer == 20) {
                    break;
                } else {
//                    summerized news as a paragraph
//                    selectedSenetences.append(sentence.getSentenceValue() + "\n");
                    System.out.println(incrementer + " : " + sentence.getSentenceValue());

                    incrementer++;
                }

            }

            System.out.println("********************************");
            System.out.print(selectedSenetences);


//            PostgreSQLJDBC postgreSQLJDBC = new PostgreSQLJDBC();
//            postgreSQLJDBC.createConnection();
//            postgreSQLJDBC.createTable();
//            postgreSQLJDBC.saveNews(selectedSenetences);
//            postgreSQLJDBC.retreiveNews(1);
//            System.out.println("----------------------------------------");

//            URL url = new URL("http://www.google.com/search?q=mkyong");
//            HttpURLConnection con = (HttpURLConnection) url.openConnection();
//            con.setRequestMethod("GET");
//            System.out.println("HHTP ");
//            System.out.println(con);
        }
        TrendingNewsObserver trendingNewsObserver = new TrendingNewsObserver();
        trendingNewsObserver.calculateWordOccurance(descriptions);
    }
}

package Main;

import Database.PostgreSQLJDBC;
import Models.Sentence;
import SummarizerAlgorithm.Api_Client;
import SummarizerAlgorithm.SentenceScoreCalculator;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ExecuteTextSum {
    public static void main(String [] args){
        ArrayList<String> arrayList = new ArrayList<>();

        PostgreSQLJDBC postgreSQLJDBC = new PostgreSQLJDBC();
        postgreSQLJDBC.createConnection();
        postgreSQLJDBC.createTable();

//        return postgreSQLJDBC.retreiveNews();
//        System.out.println(arrayList.get(0));
    }
}

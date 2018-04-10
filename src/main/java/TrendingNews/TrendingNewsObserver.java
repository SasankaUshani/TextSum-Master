package TrendingNews;

import Preprocessor.TrendingTopics;

import java.io.IOException;
import java.util.*;

/**
 * Created by SasankaKudagoda on 3/31/18.
 */

public class TrendingNewsObserver {
    List<String> trendingTopics = null;
    int wordCounter = 1;
    String des;
    int doucumentCount = 0;
    HashMap<String, Integer> topicOccurrence = new HashMap<>();
    int currentCounter = 0;

    public void calculateWordOccurance(ArrayList<StringBuilder> description) throws IOException, InterruptedException {
        trendingTopics = new TrendingTopics().getTrendingTopics();
        for (int i = 0; i < description.size(); i++) {
            doucumentCount++;
            System.out.println("Document Count : " + doucumentCount);
            des = String.valueOf(description.get(i));

            for (int x = 0; x < trendingTopics.size(); x++) {
                if (des.toLowerCase().contains(trendingTopics.get(x))) {

                    System.out.print("Description contain : ");
                    System.out.println(trendingTopics.get(x));
                    if (topicOccurrence.get(trendingTopics.get(x)) != null) {
                        currentCounter = topicOccurrence.get(trendingTopics.get(x));
                        currentCounter += 1;
                        System.out.println(" !@#!# " + currentCounter);

                        topicOccurrence.put(trendingTopics.get(x), currentCounter);
                        System.out.println(" : " + currentCounter);
                    } else {
                        System.out.println("First time : "+trendingTopics.get(x));
                        topicOccurrence.put(trendingTopics.get(x), wordCounter);
                        System.out.println(" : " + wordCounter);
                    }

                    ;
                }


            }
        }
        printHashMap(topicOccurrence);

    }

    public void printHashMap(HashMap<String, Integer> hashMap) {
        System.out.println("using entrySet and manual string creation");
        for (Map.Entry<String, Integer> entry : hashMap.entrySet()) {
            System.out.println(entry.getKey() + "=" + entry.getValue());
        }
    }
}

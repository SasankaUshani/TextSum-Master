package Preprocessor;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

/**
 * Created by SasankaKudagoda on 3/31/18.
 */

public class TrendingTopics {
    public ArrayList<String> getTrendingTopics() throws IOException, InterruptedException {
        Path file = Paths.get("/Users/sasankakudagoda/Desktop/IIT/TextSum/FYP/src/main/java/TextFiles/Topics.txt");
        return (ArrayList<String>) Files.readAllLines(file, StandardCharsets.UTF_8);
    }
}

package Preprocessor;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
/**
 * Created by SasankaKudagoda on 2/10/18.
 */
public class JunkWordsRemover {

    public ArrayList<String> getJunkWords() throws IOException, InterruptedException {
        Path file = Paths.get("/Users/sasankakudagoda/Desktop/IIT/TextSum/Project/src/main/java", "TextFiles/JunkText.txt");
        return (ArrayList<String>) Files.readAllLines(file, StandardCharsets.UTF_8);
    }
}

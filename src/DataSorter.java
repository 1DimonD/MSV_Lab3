import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class DataSorter implements IDataSorter {

    public String removeDublicate(String word) {
        if (word == null) {
            throw new IllegalArgumentException("Word cannot be null");
        }
        String processedText = word.replaceAll("[^a-zA-Z0-9\\s+]", "");
        return processedText;
    }


    public Map<String, String> createMap(List<String> listOfWords, ITextParser textParser) {
        Map<String, String> unsortedWords = new TreeMap<>();
        listOfWords.stream().forEach(e -> {
            e = cutStringLongerThan30(e);
            unsortedWords.put(removeVowels(e), e);
        });
        return unsortedWords;
    }


    public String cutStringLongerThan30(String e) {
        if (e.length() > 30) {
            e = e.substring(0, 29);
        }
        return e;
    }

    public String removeVowels(String word) {
        if (word == null) {
            throw new IllegalArgumentException("Word cannot be null");
        }
        return word.replaceAll("[aeiouAEIOUаоуиеіАОУИЕІ]", "");
    }
    public List<String> removeDuplicateWords(List<String> data, ITextParser textParser) {
        if (data == null) {
            throw new IllegalArgumentException("Input cannot be null");
        }
        String parsedText = textParser.parseText(data);
        List<String> processedWords = Arrays.asList(parsedText.split("\\s+"));
        List<String> processedWordsWithoutDuplicates = processedWords.stream()
                .map(this::removeDublicate)
                .collect(Collectors.toList());
        return processedWordsWithoutDuplicates;
    }



}

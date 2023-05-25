import java.util.List;

public interface IDataSorter {
    String removeDublicate(String stringForEdit);
    String removeVowels(String word);

    List<String> removeDuplicateWords(List<String> inputData, ITextParser textParser);
}

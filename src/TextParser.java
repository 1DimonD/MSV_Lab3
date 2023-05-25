import java.util.List;
import java.util.stream.Collectors;

public class TextParser implements ITextParser {
    public String parseText(List<String> data) {
        return data.stream().map(e -> {
            return e.replaceAll("[^A-Za-z0-9^А-Яа-я ]", "");
        }).collect(Collectors.joining(" "));
    }


}

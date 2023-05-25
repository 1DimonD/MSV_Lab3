
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class DataSorterTest {

    @Mock
    private ITextParser textParser;

    @Spy
    @InjectMocks
    private DataSorter dataSorter;


    @Test
    void testRemoveDuplicateWords() {
        when(textParser.parseText(Mockito.anyList())).thenReturn("Hello world");
        List<String> inputData = Arrays.asList("Hello", "world", "world");
        List<String> expectedOutput = Arrays.asList("Hello", "world");
        List<String> actualOutput = dataSorter.removeDuplicateWords(inputData, textParser);
        assertEquals(expectedOutput, actualOutput);

        verify(textParser, times(1)).parseText(Mockito.anyList());
    }


    @Test
    void testRemoveDuplicateWordsWithNullInput() {
        assertThrows(IllegalArgumentException.class, () -> dataSorter.removeDuplicateWords(null, textParser));
    }


    @Test
    void testRemoveDuplicateWordsWithExceptionDuringParsing() {
        when(textParser.parseText(Mockito.anyList())).thenThrow(new RuntimeException("Parsing error"));
        List<String> inputData = Arrays.asList("Hello", "world", "world");
        assertThrows(RuntimeException.class, () -> dataSorter.removeDuplicateWords(inputData, textParser));
    }


    @Test
    void testRemoveVowels() {
        String input = "Hello world";
        String expectedOutput = "Hll wrld";

        String actualOutput = dataSorter.removeVowels(input);

        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    void testCreateMapWithSpy() {
        List<String> input = Arrays.asList("world", "example", "tree", "map");
        Map<String, String> expectedOutput = new TreeMap<>();
        expectedOutput.put("wrld", "world");
        expectedOutput.put("xmpl", "example");
        expectedOutput.put("tr", "tree");
        expectedOutput.put("mp", "map");

        Map<String, String> actualOutput = dataSorter.createMap(input, textParser);

        assertEquals(expectedOutput, actualOutput);
    }
}



import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class TextParserTest {

    @Mock
    private TextParser textParser;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testParseText() {
        String inputText = "Text with special characters !@#";
        String expectedText = "Text with special characters ";

        when(textParser.parseText(anyList())).thenReturn(expectedText);

        String result = textParser.parseText(Arrays.asList(inputText));

        assertEquals(expectedText, result);

        verify(textParser, times(1)).parseText(anyList());
    }

    @Test
    public void testParseTextWithException() {
        when(textParser.parseText(null)).thenThrow(new IllegalArgumentException("Input cannot be null"));

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            textParser.parseText(null);
        });

        String expectedMessage = "Input cannot be null";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage != null && actualMessage.contains(expectedMessage));

        verify(textParser, times(1)).parseText(null);
    }

}

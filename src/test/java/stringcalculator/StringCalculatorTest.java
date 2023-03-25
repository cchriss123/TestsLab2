package stringcalculator;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StringCalculatorTest {

    StringCalculator stringCalculator = new StringCalculator();


    @Test
    void emptyStringShouldReturnZero(){
        int actual = stringCalculator.add("");
        assertEquals(0, actual);
    }

    @Test
    void shouldReturnNumberThatIsPutIn(){
        int actual = stringCalculator.add("2");
        assertEquals(2, actual);
    }






}

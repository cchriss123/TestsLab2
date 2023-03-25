package stringcalculator;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StringCalculatorTest {

    StringCalculator stringCalculator = new StringCalculator();


    @Test
    void emptyStringShouldReturnZero(){
        int expected = stringCalculator.add("");
        assertEquals(0, expected);
    }




}

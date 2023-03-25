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

    @Test
    void oneAndTwoShouldReturnThree(){
        int actual = stringCalculator.add("1,2");
        assertEquals(   3, actual);
    }

    @Test
    void threeTreeTreeTreeShouldReturnTwelve(){
        int actual = stringCalculator.add("3,3,3,3");
        assertEquals(   12, actual);
    }

    @Test
    void shouldWorkWithBackslashAndReturnSix(){
        int actual = stringCalculator.add("1\n2,3");
        assertEquals(6,actual);
    }


    @Test
    void shouldReplaceDelimiterAndReturnThree(){
        int actual = stringCalculator.add("//;\n1;2");
        assertEquals(3,actual);
    }


    @Test
    void showsCorrectErrorMessageWithMultipleNegativeNumbers() {
        StringCalculator stringCalculator = new StringCalculator();
        Exception expectedException = assertThrows(IllegalArgumentException.class, () -> stringCalculator.add("-1,-2,3"));
        String actualMessage = expectedException.getMessage();
        assertEquals("Negatives not allowed: -1,-2", actualMessage);
    }

    @Test
    void numbersAbove1000ShouldBeIgnored(){
        int actual = stringCalculator.add("3,3,3,3,1001");
        assertEquals(   12, actual);
    }






}

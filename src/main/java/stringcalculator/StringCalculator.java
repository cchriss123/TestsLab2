package stringcalculator;

import java.util.Arrays;

public class StringCalculator {

    public int add(String numbers) {

        if (numbers.isEmpty())
            return 0;

        if (numbers.contains(",\n"))
            throw new IllegalArgumentException("Invalid input");

        String newDelimiter = ",";
        if (numbers.startsWith("//")) {
            newDelimiter = numbers.substring(2, 3);
            numbers = numbers.substring(4);
        }

        numbers = numbers.replace("\n", newDelimiter);
        return Arrays.stream(numbers.split(newDelimiter)).mapToInt(Integer::parseInt).sum();

    }
}

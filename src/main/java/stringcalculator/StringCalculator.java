package stringcalculator;

import java.util.Arrays;

public class StringCalculator {

    public int add(String numbers) {

        if (numbers.isEmpty())
            return 0;

        if (numbers.contains(",\n"))
            throw new IllegalArgumentException("Invalid input");

        if(numbers.contains("-"))
            throw new IllegalArgumentException("Negatives not allowed: " + numbers);

        String delimiter = ",";
        if (numbers.startsWith("//")) {
            delimiter = numbers.substring(2, 3);
            numbers = numbers.substring(4);
        }

        numbers = numbers.replace("\n", delimiter);
        return Arrays.stream(numbers.split(delimiter)).mapToInt(Integer::parseInt).sum();

    }
}

package stringcalculator;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class StringCalculator {

    public int add(String numbers) {

        if (numbers.isEmpty())
            return 0;

        if (numbers.contains(",\n"))
            throw new IllegalArgumentException("Invalid input");

        String delimiter = ",";
        if (numbers.startsWith("//")) {
            delimiter = numbers.substring(2, 3);
            numbers = numbers.substring(4);
        }

        numbers = numbers.replace("\n", delimiter);


        String negativeNumbers = Arrays.stream(numbers.split(delimiter))
                .filter(number -> Integer.parseInt(number) < 0)
                .collect(Collectors.joining(delimiter));
        if (!negativeNumbers.isEmpty())
            throw new IllegalArgumentException("Negatives not allowed: " + negativeNumbers);


        return Arrays.stream(numbers.split(delimiter)).mapToInt(Integer::parseInt).filter(number -> number <= 1000).sum();


    }
}

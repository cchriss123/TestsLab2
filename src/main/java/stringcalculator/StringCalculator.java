package stringcalculator;

import java.util.Arrays;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class StringCalculator {

    public int add(String numbers) {

        if (numbers.isEmpty())
            return 0;

        if (numbers.contains(",\n"))
            throw new IllegalArgumentException("Invalid input");

        String delimiter = ",";

        if (numbers.startsWith("//")) {
            delimiter = numbers.substring(2, numbers.indexOf("\n"));
            numbers = numbers.substring(numbers.indexOf("\n")+1);
        }

        delimiter = delimiter.replace("[", "");
        delimiter = delimiter.replace("]","");
        delimiter = delimiter.replace("*", "\\*");
        numbers = numbers.replace("\n", delimiter);

        negativeNumberException(numbers, delimiter);

        return Arrays.stream(numbers.split(delimiter)).mapToInt(Integer::parseInt).filter(number -> number <= 1000).sum();


    }




    private void negativeNumberException(String numbers, String delimiter) {
        String negativeNumbers = Arrays.stream(numbers.split(delimiter))
                .filter(number -> Integer.parseInt(number) < 0)
                .collect(Collectors.joining(delimiter));
        if (!negativeNumbers.isEmpty())
            throw new IllegalArgumentException("Negatives not allowed: " + negativeNumbers);
    }
}

package stringcalculator;
import java.util.Arrays;
import java.util.stream.Collectors;

public class StringCalculator {

    public int add(String numbers) {

        String delimiter = ",";
        numbers = reformatText(numbers);

        if (numbers.isEmpty())
            return 0;

        if (numbers.contains(",\n"))
            throw new IllegalArgumentException("Invalid input");

        if (numbers.startsWith("//")) {
            delimiter = numbers.substring(2, numbers.indexOf("\n"));
            numbers = numbers.substring(numbers.indexOf("\n")+1);
        }

        if (delimiter.contains("|")) {
            String[] delimiters = {delimiter.substring(0, delimiter.indexOf("|")), delimiter.substring(delimiter.indexOf("|") + 1)};
            numbers = numbers.replace(delimiters[1],delimiters[0]);
            }

        numbers = numbers.replace("\n", delimiter);
        negativeNumberException(numbers, delimiter);
        return Arrays.stream(numbers.split(delimiter)).mapToInt(Integer::parseInt).filter(number -> number <= 1000).sum();

    }

    private String reformatText(String delimiter) {

        delimiter = delimiter.replace("][", "|");
        delimiter = delimiter.replace("[", "");
        delimiter = delimiter.replace("]","");
        delimiter = delimiter.replace("*", "!");
        return delimiter;
    }


    private void negativeNumberException(String numbers, String delimiter) {
        String negativeNumbers = Arrays.stream(numbers.split(delimiter))
                .filter(number -> Integer.parseInt(number) < 0)
                .collect(Collectors.joining(delimiter));
        if (!negativeNumbers.isEmpty())
            throw new IllegalArgumentException("Negatives not allowed: " + negativeNumbers);
    }
}

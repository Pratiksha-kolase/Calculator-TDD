package org.example;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class CalculatorApp {

    public int add(String num) throws IllegalArgumentException {

        if (num.isEmpty()) {
            return 0;
        }
        String[] separateString = checkOnlyForDigits(num);
        List<Integer> integers = Arrays.stream(separateString)
                .map(Integer::parseInt)
                .collect(Collectors.toList());
        isNegative(integers);
        boolean isGreaterThanThousand = integers.stream().anyMatch(element -> element > 1000);
        if (isGreaterThanThousand) {
            return ignoreGreaterThanThousandAndSum(integers);
        }
        return integers.stream().mapToInt(Integer::intValue).sum();
    }

    private void isNegative(List<Integer> listOfNumbers) throws IllegalArgumentException {
        List<Integer> negativeNumbers = listOfNumbers.stream().filter(negativeNum -> negativeNum < 0)
                .collect(Collectors.toList());
        if (!negativeNumbers.isEmpty()) {
            throw new IllegalArgumentException("Negative number not allowed");
        }
    }

    private String[] checkOnlyForDigits(String stringToCheck) {
        stringToCheck = stringToCheck.replaceAll("[,\\n;`^|#@$%&*()!\\t]+", ",").replaceAll("^,|,$", "");
        String[] separateNum = stringToCheck.split("[,\\n]");
        for (String s : separateNum) {
            if (!s.matches("\\d+")) {
                throw new IllegalArgumentException("Invalid number format: " + s);
            }
        }
        return separateNum;
    }

    private int ignoreGreaterThanThousandAndSum(List<Integer> intergersToAdd) {
        return intergersToAdd.stream().filter(element -> element < 1000).mapToInt(Integer::intValue).sum();
    }
//    public int substract(String stringToSubstract){
//        if (stringToSubstract.isEmpty()) {
//            return 0;
//        }
//
//
//    }


}

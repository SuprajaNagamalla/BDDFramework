package utils;
import java.time.format.DateTimeFormatter;
import java.util.Random;
import java.util.UUID;
import java.time.LocalDate;
import java.time.Period;

public class RandomDataGenerator {
    private static final Random random = new Random();

    // Generate a 9-digit SSN
    public String getRandomSSN() {
        int min = 100_000_000;
        int max = 999_999_999;
        return String.valueOf(random.nextInt(max - min + 1) + min);
    }

    // Generate a random email address
    public String getRandomEmail() {
        String uuid = UUID.randomUUID().toString().substring(0, 8);
        return "user_" + uuid + "@upbound.com";
    }

    // Generate a random 10-digit phone number
    public String getRandomPhoneNumber() {
        int num1 = random.nextInt(900) + 100;
        int num2 = random.nextInt(643) + 100;
        int num3 = random.nextInt(9000) + 1000;
        return num1 + "-" + num2 + "-" + num3;
    }

    public String getRandomDOB() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy");
        LocalDate currentDate = LocalDate.now();

        // Minimum DOB to be at least 21 years old
        LocalDate maxDOB = currentDate.minusYears(21);

        // Generate random year between 1900 and (maxDOB - 21 years)
        int startYear = 1930;
        int endYear = maxDOB.getYear();

        Random random = new Random();
        int year = random.nextInt(endYear - startYear + 1) + startYear;
        int month = random.nextInt(12) + 1;
        int day;

        // Get the max day of month considering leap years and valid dates
        LocalDate tempDate;
        do {
            day = random.nextInt(28) + 1; // keep it safe by limiting to 28
            try {
                tempDate = LocalDate.of(year, month, day);
            } catch (Exception e) {
                tempDate = null;
            }
        } while (tempDate == null || tempDate.isAfter(maxDOB));

        return tempDate.format(formatter);
    }

    public String getDatePlusDays(int daysToAdd) {

        LocalDate futureDate = LocalDate.now().plusDays(daysToAdd);

        // DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");

        return futureDate.format(formatter);

    }



}

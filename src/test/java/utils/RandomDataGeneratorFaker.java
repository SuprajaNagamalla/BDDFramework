package utils;

import com.github.javafaker.Faker;

public class RandomDataGeneratorFaker {
    Faker faker = new Faker();

    public String getRandomFirstName() {
        return faker.name().firstName();
    }
}

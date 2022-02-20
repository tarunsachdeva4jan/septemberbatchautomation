package com.automationpracitise.datagenerator;

import com.github.javafaker.Faker;

public class RegistrationDataGenerator {

    public static String getEmailAddress() {
        return new Faker().internet().emailAddress();
    }

    public static String getFirstName() {
        Faker faker = new Faker();
        String firstName = faker.name().firstName();
        return firstName;
    }

    public static String getLastName() {
        Faker faker = new Faker();
        String lastName = faker.name().lastName();
        return lastName;
    }

    public static String getCompanyName() {
        Faker faker = new Faker();
        String company = faker.company().name();
        return company;
    }

    public static String getUniquePassword() {
        Faker faker = new Faker();
        String password = faker.internet().password();
        return password;
    }


}

package br.edu.ifsp.testing.class09.controller;

import com.github.javafaker.Faker;

public class Payloads {
    private static final Faker faker = Faker.instance();

    public static final String user = String.format("""
            {
              "name": "%s",
              "lastname": "%s",
              "email": "%s",
              "password": "%s"
            }
            """,
            faker.name().firstName(),
            faker.name().lastName(),
            faker.internet().emailAddress(),
            faker.internet().password());
}

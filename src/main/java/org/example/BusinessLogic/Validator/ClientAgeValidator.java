package org.example.BusinessLogic.Validator;
import org.example.Model.Client;
/**
 * The type Client age validator.
 */

public class ClientAgeValidator implements Validator<Client> {
    private static final int MIN_AGE = 10;
    private static final int MAX_AGE = 70;

    public void validate(Client client) {
        if (client.getAge() < MIN_AGE || client.getAge() > MAX_AGE) {
            throw new IllegalArgumentException("The Client Age limit is not respected!");
        }
    }
}
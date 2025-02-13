package br.edu.ifsp.testing.class05.testability;

import java.util.Random;

public class Die {
    private final Random random = new Random();

    public int roll(){
        return random.nextInt(6) + 1;
    }
}

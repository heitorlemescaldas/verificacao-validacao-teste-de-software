package br.edu.ifsp.testing.class05.testability;

import java.util.Random;

public class TestableSevenEleven {
    public int play() {
        final Random random = new Random();
        // Row dice
        int die1 = random.nextInt(6) + 1; // A random number between 1 and 6
        int die2 = random.nextInt(6) + 1; //  A random number between 1 and 6
        int sum = die1 + die2;

        if (sum == 7 || sum == 11) return 1; // win
        if (sum == 2 || sum == 3 || sum == 12) return -1; // lose
        return 0; // play again
    }
}

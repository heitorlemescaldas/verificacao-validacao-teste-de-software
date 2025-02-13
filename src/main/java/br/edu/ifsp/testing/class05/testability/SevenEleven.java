package br.edu.ifsp.testing.class05.testability;

import java.util.Random;

public class SevenEleven {
    private final Die die;

    public SevenEleven(Die die) {
        this.die = die;
    }
    public int play() {
        int sum = die.roll() + die.roll();

        if (sum == 7 || sum == 11) return 1; // win
        if (sum == 2 || sum == 3 || sum == 12) return -1; // lose
        return 0; // play again
    }
}

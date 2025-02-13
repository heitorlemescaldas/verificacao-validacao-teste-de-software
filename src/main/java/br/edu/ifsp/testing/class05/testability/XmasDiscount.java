package br.edu.ifsp.testing.class05.testability;

import java.time.LocalDate;
import java.time.Month;

public class XmasDiscount {
    public double applyDiscount(double amount) {
        LocalDate today = LocalDate.now();
        double discountPercentage = 0;

        boolean isChristmas = today.getMonth() == Month.DECEMBER && today.getDayOfMonth() == 25;
        if(isChristmas)
            discountPercentage = 0.15;

        // If it is Christmas, it applies the discount.
        return amount - (amount * discountPercentage);
    }
}

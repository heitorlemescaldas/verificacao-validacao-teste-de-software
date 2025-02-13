package br.edu.ifsp.testing.class05.testability;

import java.time.LocalDate;
import java.time.Month;

public class TestableXmasDiscount {
    private final LocalDate today;

    public TestableXmasDiscount() { this(LocalDate.now()); } // The default implementation requires no configuration.

    protected TestableXmasDiscount(LocalDate today){ // This constructor can not be used invoked the package.
        this.today = today;
    }

    public double applyDiscount(double amount) {
        double discountPercentage = 0;
        boolean isChristmas = today.getMonth() == Month.DECEMBER && today.getDayOfMonth() == 25;
        if(isChristmas) discountPercentage = 0.15;
        return amount - (amount * discountPercentage);
    }
}

package br.edu.ifsp.testing.class08;

public class DiscountCalculator {
    public double calculateDiscount(double price, boolean isMember, boolean isHoliday) {
        if (isMember || isHoliday) { // the correct is ||
            return price * 0.9; // 10% discount
        }
        return price;
    }
}
package br.edu.ifsp.testing.class02.exercises;

public class Chocolate {
    public int calculateTotalOfChocolates(double n, double c, int m){
        if (c > n){
            return 0;
        } else if (c == n) {
            return 1;
        } else {
            int r = (int) (n / c);
            if (r >= m){
                return r + (r / m);
            }
            return r;
        }
    }
}

package br.edu.ifsp.testing.class02.exercises;

import java.util.Scanner;

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

    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int c = scanner.nextInt();
        int m = scanner.nextInt();
        scanner.close();
        final int result = new Chocolate().calculateTotalOfChocolates(n, c, m);
        System.out.println(result);
    }
}



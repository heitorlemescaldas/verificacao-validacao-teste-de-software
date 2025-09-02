package br.edu.ifsp.testing.class04;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import static java.lang.Integer.max;

public class SummingInts {
    public List<Integer> add(List<Integer> left, List<Integer> right) {
        if (left == null || right == null)
            return null;

        // Cópias mutáveis para evitar UnsupportedOperationException e preservar entradas
        List<Integer> l = new ArrayList<>(left);
        List<Integer> r = new ArrayList<>(right);

        // Listas vazias representam 0
        if (l.isEmpty()) l.add(0);
        if (r.isEmpty()) r.add(0);

        // Validação de dígitos 0..9
        if (!l.stream().allMatch(d -> d >= 0 && d <= 9) || !r.stream().allMatch(d -> d >= 0 && d <= 9)) {
            throw new IllegalArgumentException("Digits must be between 0 and 9");
        }

        // Inverter para somar do dígito menos significativo
        Collections.reverse(l);
        Collections.reverse(r);

        LinkedList<Integer> result = new LinkedList<>();
        int carry = 0;

        for (int i = 0; i < max(l.size(), r.size()); i++) {
            int dl = i < l.size() ? l.get(i) : 0;
            int dr = i < r.size() ? r.get(i) : 0;

            int sum = dl + dr + carry;
            result.addLast(sum % 10); // construindo de LSD -> MSD
            carry = sum / 10;
        }

        if (carry > 0) {
            result.addLast(carry);
        }

        Collections.reverse(result);

        while (result.size() > 1 && result.get(0) == 0) {
            result.remove(0);
        }

        return result;
    }
}

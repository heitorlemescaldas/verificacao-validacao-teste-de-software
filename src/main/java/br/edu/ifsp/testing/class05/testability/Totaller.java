package br.edu.ifsp.testing.class05.testability;

import java.util.List;
import java.util.function.Consumer;

public class Totaller {

    public void asyncTotal(List<Integer> values, Consumer<Integer> callback) {
        new Thread(() -> {
            int result = values.parallelStream().reduce(0, Integer::sum);
            callback.accept(result);
        }).start();
    }
}
package br.edu.ifsp.testing.class05.testability;

import java.util.List;
import java.util.function.Consumer;

public class TestableTotaller {

    public void asyncTotal(List<Integer> values, Consumer<Integer> callback) {
        new Thread(() -> {
            final int result = syncTotal(values);
            callback.accept(result);
        }).start();
    }

    protected int syncTotal(List<Integer> values) {
        return values.parallelStream().reduce(0, Integer::sum);
    }
}
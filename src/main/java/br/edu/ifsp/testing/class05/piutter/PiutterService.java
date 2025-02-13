package br.edu.ifsp.testing.class05.piutter;

import java.util.List;
import java.util.UUID;

public interface PiutterService {
    // It sends a piueet and returns true if succeeded. Otherwise, it returns false.
    boolean piueet(UUID userUuid, String text);
    // It gets the list of trending topics of the day.
    List<String> fetchTrendingTopics();
}

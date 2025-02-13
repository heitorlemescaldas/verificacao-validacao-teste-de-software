package br.edu.ifsp.testing.class05.piutter;

import java.util.Objects;
import java.util.UUID;

public class PublishPiueet {
    private final PiutterService piutterService; // Dependency to be injected

    public PublishPiueet(PiutterService piutterService) { // Dependency injection
        this.piutterService = piutterService;
    }

    public boolean send(UUID userUuid, String piueetText){
        UUID uuid = Objects.requireNonNull(userUuid);
        String text = Objects.requireNonNull(piueetText);
        if(text.isBlank())
            throw new IllegalArgumentException();
        if(text.length() > 50) return false;
        return piutterService.piueet(uuid, text);
    }
}

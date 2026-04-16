package org.teamsolid.utils;

import java.util.UUID;

public class GeneradorId {
    private GeneradorId() {
    }

    public static String generar() {
        return UUID.randomUUID().toString().substring(0, 8);
    }
}
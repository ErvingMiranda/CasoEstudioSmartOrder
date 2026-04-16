package org.teamsolid.models;

public class ResultadoPago {
    private boolean exitoso;
    private String mensaje;

    public ResultadoPago(boolean exitoso, String mensaje) {
        this.exitoso = exitoso;
        this.mensaje = mensaje;
    }

    public boolean isExitoso() {
        return exitoso;
    }

    public String getMensaje() {
        return mensaje;
    }
}
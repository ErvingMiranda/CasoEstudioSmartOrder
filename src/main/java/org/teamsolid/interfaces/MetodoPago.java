package org.teamsolid.interfaces;

import org.teamsolid.models.ResultadoPago;

public interface MetodoPago {
    ResultadoPago procesarPago(double monto);
}
package org.teamsolid.payments;

import org.teamsolid.interfaces.MetodoPago;
import org.teamsolid.models.ResultadoPago;

public class PagoEfectivo implements MetodoPago {
    @Override
    public ResultadoPago procesarPago(double monto) {
        return new ResultadoPago(true, "Pago en efectivo procesado por C$" + monto);
    }
}
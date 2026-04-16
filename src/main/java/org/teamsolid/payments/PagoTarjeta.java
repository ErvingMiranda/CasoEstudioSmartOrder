package org.teamsolid.payments;

import org.teamsolid.interfaces.MetodoPago;
import org.teamsolid.models.ResultadoPago;

public class PagoTarjeta implements MetodoPago {
    @Override
    public ResultadoPago procesarPago(double monto) {
        return new ResultadoPago(true, "Pago con tarjeta aprobado por C$" + monto);
    }
}
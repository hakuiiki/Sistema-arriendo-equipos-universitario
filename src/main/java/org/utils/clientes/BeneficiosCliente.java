package org.utils.clientes;

public enum BeneficiosCliente {

    ESTUDIANTE(0.15, 1.00, 3),
    CLIENTE_EXTERNO(0.00, 1.20, 1);

    private final double porcentajeDescuento;
    private final double porcentajeCobroGarantia;
    private final int limiteArriendos;

    BeneficiosCliente(double porcentajeDescuento, double porcentajeCobroGarantia, int limiteArriendos) {
        this.porcentajeDescuento = porcentajeDescuento;
        this.porcentajeCobroGarantia = porcentajeCobroGarantia;
        this.limiteArriendos = limiteArriendos;
    }

    public double getPorcentajeCobroGarantia() {
        return porcentajeCobroGarantia;
    }

    public double getPorcentajeDescuento() {
        return porcentajeDescuento;
    }

    public int getLimiteArriendos() {
        return limiteArriendos;
    }

}

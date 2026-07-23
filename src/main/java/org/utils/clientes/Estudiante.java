package org.utils.clientes;

public class Estudiante extends Clientes{

    public Estudiante(int id, String nombre, int saldo, boolean puedeArrendar, int garantia){
        super(id, nombre,saldo, true,garantia);
    }
    @Override
    public double getPorcentajeDescuento() {
        return 0;
    }

    @Override
    public int obtenerLimiteArriendos() {
        return 0;
    }

    @Override
    public double calcularGarantia() {
        return 0;
    }
}

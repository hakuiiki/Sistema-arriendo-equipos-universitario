package org.utils.clientes;

import org.utils.excepciones.OperacionGarantiaInvalidaException;
import org.utils.excepciones.OperacionSaldoInvalidaException;

public abstract class Clientes {

    private final int id;
    private String nombre;
    private String correo;
    // para estudiante private int matricula;
    private int saldoCliente;
    private boolean puedeArrendar;
    private int garantia; // saldo retenido temporalmente para el cliente

    public Clientes(int id, String nombre, String correo, int saldoCliente, boolean puedeArrendar, int garantia){
        this.id = id;
        this.nombre = nombre;
        this.correo = correo;
        this.saldoCliente = saldoCliente;
        this.puedeArrendar = true; // cuando se crea un cliente siempre parte con que si puede arrendar
        this.garantia = garantia;
    }

    public abstract double getPorcentajeDescuento();

    public abstract int obtenerLimiteArriendos();

    public abstract double calcularGarantia();

    public void agregarSaldo(int monto) throws OperacionSaldoInvalidaException {
        if (monto < 0){
            throw new OperacionSaldoInvalidaException("El monto agregado debe ser mayor que cero");
        }

        this.saldoCliente += monto;
    }

    public void descontarSaldo(int montoDescontado) throws OperacionSaldoInvalidaException {

        if (montoDescontado <= 0){
            throw new OperacionSaldoInvalidaException("El monto descontado debe ser mayor que cero");
        }

        if (saldoCliente < montoDescontado){
            throw new OperacionSaldoInvalidaException("El cliente no posee saldo suficiente");
        }

        this.saldoCliente -= montoDescontado;

    }

    public void retenerGarantia(int montoRetener) throws OperacionGarantiaInvalidaException{
        if (montoRetener <= 0){
            throw new OperacionGarantiaInvalidaException("El monto de la garantia a retener debe ser mayor que cero");
        }

        if (garantia > 0){
            throw new OperacionGarantiaInvalidaException("Ya se le cobro la garantia al cliente");
        }

        if (montoRetener > saldoCliente){
            throw new OperacionSaldoInvalidaException("Saldo del cliente insuficiente para pagar la garantia");
        }

        this.saldoCliente -= montoRetener;
        this.garantia += montoRetener;
    }


    public void devolverGarantia throws OperacionGarantiaInvalidaException(){
        if (garantia <= 0){
            throw new OperacionGarantiaInvalidaException("El cliente no posee ninguna garantia retenida");
        }

        this.saldoCliente += garantia;
        this.garantia = 0;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public int getSaldoCliente() {
        return saldoCliente;
    }

    public boolean isPuedeArrendar() {
        return puedeArrendar;
    }

    /**
     * ----- setters de puedeArrendar, por separado para que sean mas claros -----
     */
    public void permitirArriendos(){
        this.puedeArrendar = true;
    }

    public void bloquearArriendos(){
        this.puedeArrendar = false;
    }

}

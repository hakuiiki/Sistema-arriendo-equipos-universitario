package org.utils.clientes;

public class ClienteExterno extends Clientes{

    private String rut;
    private String telefono;
    private BeneficiosCliente beneficiosCliente;

    public ClienteExterno(int id, String nombre, String correo, int saldoCliente, boolean puedeArrendar, int garantia,
                          String rut, String telefono){

        super(id, nombre, correo, saldoCliente, puedeArrendar, garantia);

        this.rut = rut;
        this.telefono = telefono;
        this.beneficiosCliente = BeneficiosCliente.CLIENTE_EXTERNO;
    }

    public String getRut() {
        return rut;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setRut(String rut) {
        this.rut = rut;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    @Override
    public double getPorcentajeDescuento() {
        return beneficiosCliente.getPorcentajeDescuento();
    }

    @Override
    public int obtenerLimiteArriendos() {
        return beneficiosCliente.getLimiteArriendos();
    }

    /** devuelve la garantia total que se le cobrara al cliente, se usa Math.round para redondear al entero mas cercano
     * osea que si fuera valorArriendo * porcentajeGarantia = 5000.10, se redonde a a 5000
     */
    @Override
    public int calcularGarantia(int valorArriendo) {
        int garantiaFinal = (int)Math.round (valorArriendo * beneficiosCliente.getPorcentajeCobroGarantia());
        return garantiaFinal;
    }

    @Override
    public String toString() {
        return super.toString()
                + ", tipo de cliente: Cliente Externo"
                + ", RUT: " + rut
                + ", telefono: " + telefono;
    }
}

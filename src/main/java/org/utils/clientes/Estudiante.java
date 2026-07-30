package org.utils.clientes;

public class Estudiante extends Clientes{

    private int matricula;
    private String carrera;
    private BeneficiosCliente beneficiosCliente;

    public Estudiante(int id, String nombre, String correo, int saldoCliente, boolean puedeArrendar, int garantia,
                      int matricula, String carrera){

        super(id, nombre, correo, saldoCliente, puedeArrendar, garantia);

        this.matricula = matricula;
        this.carrera = carrera;
        this.beneficiosCliente = BeneficiosCliente.ESTUDIANTE;
    }

    public int getMatricula() {
        return matricula;
    }

    public String getCarrera() {
        return carrera;
    }

    public void setMatricula(int matricula) {
        this.matricula = matricula;
    }

    public void setCarrera(String carrera) {
        this.carrera = carrera;
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
     * osea que si fuera garantiaBase * porcentajeGarantia = 5000.10, se redonde a a 5000
     */
    @Override
    public int calcularGarantia(int garantiaBase) {
        int garantiaFinal = (int)Math.round (garantiaBase * beneficiosCliente.getPorcentajeCobroGarantia());
        return garantiaFinal;
    }

    @Override
    public String toString() {
        return super.toString()
                + ", tipo de cliente: Estudiante"
                + ", matricula: " + matricula
                + ", carrera: " + carrera;
    }
}

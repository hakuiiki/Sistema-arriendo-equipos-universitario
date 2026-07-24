package org.utils.clientes;

public class Estudiante extends Clientes{

    private int matricula;
    private String carrera;

    public Estudiante(int id, String nombre, String correo, int saldoCliente, boolean puedeArrendar, int garantia,
                      int matricula, String carrera){

        super(id, nombre, correo, saldoCliente, puedeArrendar, garantia);

        this.matricula = matricula;
        this.carrera = carrera;
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

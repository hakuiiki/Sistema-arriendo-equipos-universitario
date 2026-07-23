package org.utils.equipos;

import org.utils.excepciones.AccionEstadoInvalidaException;

public abstract class Equipo {

    private final int id;
    private String nombreEquipo;
    private final TipoEquipo tipoEquipo;
    private EstadoEquipo estadoEquipo;
    private String marca;

    public Equipo(int id, String nombreEquipo, TipoEquipo tipoEquipo, String marca) {
        this.id = id;
        this.nombreEquipo = nombreEquipo;
        this.tipoEquipo = tipoEquipo;

        // todos los equipos comienzan disponibles
        this.estadoEquipo = new EstadoDisponible();

        this.marca = marca;
    }

    /**
     * Cambia internamente el estado del equipo.
     * No es public para evitar que clases externas al paquete
     * cambien el estado sin respetar las reglas del patrón State.
     */
    void setEstadoEquipo(EstadoEquipo nuevoEstado)
            throws AccionEstadoInvalidaException {

        if (nuevoEstado == null) {
            throw new AccionEstadoInvalidaException(
                    "El estado del equipo no puede ser null"
            );
        }

        this.estadoEquipo = nuevoEstado;
    }

    public EstadoEquipo getEstadoEquipo() {
        return estadoEquipo;
    }

    public boolean estaDisponible() {
        return estadoEquipo.estaDisponible();
    }

    public void marcarArrendado()
            throws AccionEstadoInvalidaException {

        estadoEquipo.arrendar(this);
    }

    public void marcarDisponible()
            throws AccionEstadoInvalidaException {

        estadoEquipo.devolver(this);
    }

    public void enviarMantenimiento()
            throws AccionEstadoInvalidaException {

        estadoEquipo.enviarMantenimiento(this);
    }

    public void finalizarMantenimiento()
            throws AccionEstadoInvalidaException {

        estadoEquipo.finalizarMantenimiento(this);
    }

    public int getId() {
        return id;
    }

    public String getNombreEquipo() {
        return nombreEquipo;
    }

    public TipoEquipo getTipoEquipo() {
        return tipoEquipo;
    }

    public String getMarca() {
        return marca;
    }

    public void setNombreEquipo(String nombreEquipo) {
        this.nombreEquipo = nombreEquipo;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    @Override
    public String toString() {
        return "ID: " + id
                + ", nombre equipo: " + nombreEquipo
                + ", tipo: " + tipoEquipo
                + ", estado: " + estadoEquipo.getNombreEstado()
                + ", marca: " + marca;
    }
}
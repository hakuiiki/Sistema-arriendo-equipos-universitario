package org.utils.equipos;

import org.utils.excepciones.AccionEstadoInvalidaException;

public class EstadoDisponible implements EstadoEquipo{

    /**
     * Metodo para setear el estado de un equipo como arrendado
     */
    @Override
    public void arrendar(Equipo equipo) {
        equipo.setEstadoEquipo(new EstadoArrendado());
    }

    @Override
    public void devolver(Equipo equipo) throws AccionEstadoInvalidaException {
        throw new AccionEstadoInvalidaException("No se puede devolver un equipo ya disponible");
    }

    @Override
    public void enviarMantenimiento(Equipo equipo) {
        equipo.setEstadoEquipo(new EstadoMantenimiento());
    }

    @Override
    public void finalizarMantenimiento(Equipo equipo) throws AccionEstadoInvalidaException {
        throw new AccionEstadoInvalidaException("El equipo no se encuentra en mantenimiento");
    }

    @Override
    public boolean estaDisponible(Equipo equipo) {
        return true;
    }

    @Override
    public String getNombreEstado(Equipo equipo) {
        return "Disponible";
    }
}

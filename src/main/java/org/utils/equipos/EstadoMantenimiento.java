package org.utils.equipos;

import org.utils.excepciones.AccionEstadoInvalidaException;

public class EstadoMantenimiento implements EstadoEquipo{
    @Override
    public void arrendar(Equipo equipo) throws AccionEstadoInvalidaException  {
        throw new AccionEstadoInvalidaException("No se puede arrendar un equipo en mantenimiento");
    }

    @Override
    public void devolver(Equipo equipo) throws AccionEstadoInvalidaException {
        throw new AccionEstadoInvalidaException("No se puede devolver un equipo en mantenimiento");
    }

    @Override
    public void enviarMantenimiento(Equipo equipo) throws AccionEstadoInvalidaException {
        throw new AccionEstadoInvalidaException("No se puede enviar a mantenimiento un equipo que ya esta en mantenimiento");
    }

    @Override
    public void finalizarMantenimiento(Equipo equipo) {
        equipo.setEstadoEquipo(new EstadoDisponible());
    }

    @Override
    public boolean estaDisponible(Equipo equipo) {
        return false;
    }

    @Override
    public String getNombreEstado(Equipo equipo) {
        return "Mantenimiento";
    }
}

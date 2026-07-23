package org.utils.equipos;

import org.utils.excepciones.AccionEstadoInvalidaException;

public class EstadoArrendado implements EstadoEquipo{

    @Override
    public void arrendar(Equipo equipo) throws AccionEstadoInvalidaException {
        throw new AccionEstadoInvalidaException("No se puede arrendar un equipo ya arrendado");
    }

    @Override
    public void devolver(Equipo equipo) {
        equipo.setEstadoEquipo(new EstadoDisponible());
    }

    /**
     * Si se puede mandar directamente un equipo arrendado a mantenimiento sin tener que pasar por estar disponible nuevamente
     */
    @Override
    public void enviarMantenimiento(Equipo equipo){
        equipo.setEstadoEquipo(new EstadoMantenimiento());
    }

    @Override
    public void finalizarMantenimiento(Equipo equipo) throws AccionEstadoInvalidaException {
        throw new AccionEstadoInvalidaException("El equipo no se encuentra en mantenimiento");
    }

    @Override
    public boolean estaDisponible(Equipo equipo) {
        return false;
    }

    @Override
    public String getNombreEstado(Equipo equipo) {
        return "Arrendado";
    }
}

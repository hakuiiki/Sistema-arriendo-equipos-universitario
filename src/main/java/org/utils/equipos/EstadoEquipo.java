package org.utils.equipos;

public interface EstadoEquipo {

    void arrendar(Equipo equipo);

    void devolver(Equipo equipo);

    void enviarMantenimiento(Equipo equipo);

    void finalizarMantenimiento(Equipo equipo);

    boolean estaDisponible(Equipo equipo);

    String getNombreEstado(Equipo equipo);
}

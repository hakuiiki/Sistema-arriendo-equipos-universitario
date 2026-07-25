package org.utils.arriendos;

import org.utils.equipos.Equipo;
import org.utils.clientes.Clientes;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Arriendo implements Comparable<Arriendo>{

    private int id;
    private Clientes cliente;
    private Equipo equipo;

    private LocalDate fechaInicio;
    private LocalDate fechaDevolucionEsperada;
    private LocalDate fechaDevolucionReal;

    private int costoArriendo;
    private int garantiaCobrada;
    private int multa;
    private boolean arriendoFinalizado;

    public Arriendo(int id, Clientes cliente, Equipo equipo,
                    LocalDate fechaInicio, LocalDate fechaDevolucionEsperada, LocalDate fechaDevolucionReal,
                    int costoArriendo, int garantiaCobrada, int multa, boolean arriendoFinalizado){

        this.id = id;
        this.cliente = cliente;
        this.equipo = equipo;
        this.fechaInicio = fechaInicio;
        this.fechaDevolucionEsperada = fechaDevolucionEsperada;
        this.costoArriendo = costoArriendo;

        // aun no se sabe cuando lo devolvera, ademas de un principio el arriendo no puede empezar como finalizado
        this.fechaDevolucionReal = null;
        this.arriendoFinalizado = false;
    }

    // metodo para indicar la devolucion de un equipo
    public void registrarDevolucion(){
        
    }

    @Override
    public int compareTo(Arriendo o) {
        return 0;
    }
}

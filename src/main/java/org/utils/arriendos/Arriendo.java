package org.utils.arriendos;

import org.utils.equipos.Equipo;
import org.utils.clientes.Clientes;
import org.utils.excepciones.AccionArriendoInvalidaException;

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
    private boolean arriendoFinalizado;

    public Arriendo(int id, Clientes cliente, Equipo equipo,
                    LocalDate fechaInicio, LocalDate fechaDevolucionEsperada,
                    int costoArriendo, int garantiaCobrada){

        this.id = id;
        this.cliente = cliente;
        this.equipo = equipo;
        this.fechaInicio = fechaInicio;
        this.fechaDevolucionEsperada = fechaDevolucionEsperada;
        this.costoArriendo = costoArriendo;

        // aun no se sabe cuando lo devolvera, ademas de un principio el arriendo no puede empezar como finalizado
        this.fechaDevolucionReal = null;
        this.arriendoFinalizado = false;

        this.garantiaCobrada = garantiaCobrada;
        this.multa = multa;
    }

    // metodo para indicar la devolucion de un equipo
    public void registrarDevolucion(LocalDate fechaDevolucionReal) throws AccionArriendoInvalidaException {
        if (arriendoFinalizado){
            throw new AccionArriendoInvalidaException("El equipo ya se encuentra devuelto");
        }

        if (fechaDevolucionReal == null){
            throw new AccionArriendoInvalidaException("La fecha de devolucion no puede ser null");
        }

        if (fechaDevolucionReal.isBefore(fechaInicio)){
            throw new AccionArriendoInvalidaException("La devolucion final no puede ser antes de su fecha de inicio");
        }

        this.fechaDevolucionReal = fechaDevolucionReal;
        this.arriendoFinalizado = true;
    }

    /** sirve para consultar dos casos, los cuales verifica internamente:
     * 1.- Si el arriendo esta finalizado, se compara la fecha consultada con la fecha de devolucion real
     * 2.- Si el arriendo aun no finaliza (osea aun no es devuelto), se compara la fecha consultada con la fecha esperada de devolucion
     */
    public boolean estaAtrasado(LocalDate fechaConsulta) throws AccionArriendoInvalidaException{
        if (fechaConsulta == null){
            throw new AccionArriendoInvalidaException("La fecha a consultar no puede ser nula");
        }

        if (arriendoFinalizado){
            return fechaConsulta.isAfter(fechaDevolucionReal);
        }

        return fechaConsulta.isAfter(fechaDevolucionEsperada);
    }

    /** metodo que reemplaza el anterior pero consultando unicamente para la fecha actual */
    public boolean estaAtrasadoActual(){
        return estaAtrasado(LocalDate.now());
    }


    public int getId() {
        return id;
    }

    public Clientes getCliente() {
        return cliente;
    }

    public Equipo getEquipo() {
        return equipo;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public LocalDate getFechaDevolucionEsperada() {
        return fechaDevolucionEsperada;
    }

    public LocalDate getFechaDevolucionReal() {
        return fechaDevolucionReal;
    }

    public int getCostoArriendo() {
        return costoArriendo;
    }

    public boolean isArriendoFinalizado() {
        return arriendoFinalizado;
    }

    public boolean estaActivo(){
        return !arriendoFinalizado;
    }

    @Override
    public String toString() {
        return "Arriendo ID: " + id
                + ", cliente: " + cliente.getNombre()
                + ", equipo: " + equipo.getNombreEquipo()
                + ", fecha de inicio: " + fechaInicio
                + ", devolución esperada: " + fechaDevolucionEsperada
                + ", devolución real: " + (fechaDevolucionReal == null ? "pendiente" : fechaDevolucionReal)
                + ", costo total: $" + costoArriendo
                + ", estado: " + (arriendoFinalizado ? "finalizado" : "activo");
    }

    @Override
    public int compareTo(Arriendo otroArriendo) {

        /** si las fechas esperadas son distintas se compara segun ese orden */
        int comparacionFecha = this.fechaDevolucionEsperada.compareTo(otroArriendo.fechaDevolucionEsperada);

        // si son fechas distintas, se ordena yendo primero la fecha que sea mas proxima, osea para la que falte menos
        if(comparacionFecha != 0){
            return this.fechaDevolucionEsperada.compareTo( otroArriendo.fechaDevolucionEsperada );
        }

        // si son fechas iguales, ordenamos segun el ID comparando cual es menor para dejarlo primero
        return Integer.compare(this.id, otroArriendo.id);
    }
}

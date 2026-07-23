package org.utils.equipos;

public class Bicicleta extends EquipoRecreativo {

    private int cantidadCambios;
    private String talla;

    public Bicicleta(
            int id,
            String nombreEquipo,
            String marca,
            String materialPrincipal,
            int edadMinima,
            int cantidadCambios,
            String talla) {

        /** siempre se inicializara como tipo de equipo bicicleta segun el enum */
        super(id, nombreEquipo, TipoEquipo.BICICLETA, marca, materialPrincipal, edadMinima);

        this.cantidadCambios = cantidadCambios;
        this.talla = talla;
    }

    public int getCantidadCambios() {
        return cantidadCambios;
    }

    public String getTalla() {
        return talla;
    }

    public void setCantidadCambios(int cantidadCambios) {
        this.cantidadCambios = cantidadCambios;
    }

    public void setTalla(String talla) {
        this.talla = talla;
    }

    @Override
    public String toString() {
        return super.toString()
                + ", cantidad de cambios: " + cantidadCambios
                + ", talla: " + talla;
    }
}
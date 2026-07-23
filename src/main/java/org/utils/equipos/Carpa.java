package org.utils.equipos;

public class Carpa extends EquipoRecreativo {

    private int capacidadPersonas;
    private boolean resistenteAlAgua;

    public Carpa(
            int id,
            String nombreEquipo,
            String marca,
            String materialPrincipal,
            int edadMinima,
            int capacidadPersonas,
            boolean resistenteAlAgua) {

        /** siempre se inicializara como tipo de equipo carpa dado por el enum */
        super(id, nombreEquipo, TipoEquipo.CARPA, marca, materialPrincipal, edadMinima);

        this.capacidadPersonas = capacidadPersonas;
        this.resistenteAlAgua = resistenteAlAgua;
    }

    public int getCapacidadPersonas() {
        return capacidadPersonas;
    }

    public boolean isResistenteAlAgua() {
        return resistenteAlAgua;
    }

    public void setCapacidadPersonas(int capacidadPersonas) {
        this.capacidadPersonas = capacidadPersonas;
    }

    public void setResistenteAlAgua(boolean resistenteAlAgua) {
        this.resistenteAlAgua = resistenteAlAgua;
    }

    @Override
    public String toString() {
        return super.toString()
                + ", capacidad: " + capacidadPersonas + " personas"
                + ", resistente al agua: " + (resistenteAlAgua ? "resistente" : "no resistente");
    }
}
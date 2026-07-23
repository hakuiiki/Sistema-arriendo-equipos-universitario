package org.utils.equipos;

public class Proyector extends EquipoTecnologico {

    private int resolucionHorizontal;
    private int resolucionVertical;
    private int lumenes;

    public Proyector(
            int id,
            String nombreEquipo,
            String marca,
            String numeroSerie,
            int consumoWatts,
            int resolucionHorizontal,
            int resolucionVertical,
            int lumenes){

        /** siempre declararemos que el tipo de equipo sera proyector desde el enum */
        super(id, nombreEquipo, TipoEquipo.PROYECTOR, marca, numeroSerie, consumoWatts);

        this.resolucionHorizontal = resolucionHorizontal;
        this.resolucionVertical = resolucionVertical;
        this.lumenes = lumenes;
    }

    public int getResolucionHorizontal() {
        return resolucionHorizontal;
    }

    public int getResolucionVertical() {
        return resolucionVertical;
    }

    public int getLumenes() {
        return lumenes;
    }

    public void setResolucionHorizontal(int resolucionHorizontal) {
        this.resolucionHorizontal = resolucionHorizontal;
    }

    public void setResolucionVertical(int resolucionVertical) {
        this.resolucionVertical = resolucionVertical;
    }

    public void setLumenes(int lumenes) {
        this.lumenes = lumenes;
    }

    @Override
    public String toString() {
        return super.toString()
                + ", resolución: "
                + resolucionHorizontal + "x" + resolucionVertical
                + ", luminosidad: " + lumenes + " lm";
    }
}
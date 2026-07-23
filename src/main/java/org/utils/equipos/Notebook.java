package org.utils.equipos;

public class Notebook extends EquipoTecnologico {

    private String procesador;
    private int memoriaRam;
    private int almacenamientoGB;

    public Notebook(
            int id,
            String nombreEquipo,
            String marca,
            String numeroSerie,
            int consumoWatts,
            String procesador,
            int memoriaRam,
            int almacenamientoGB){

        /** siempre declararemos que el tipo de equipo sera notebook directamente desde el enum*/
        super(id, nombreEquipo, TipoEquipo.NOTEBOOK, marca, numeroSerie, consumoWatts);

        this.procesador = procesador;
        this.memoriaRam = memoriaRam;
        this.almacenamientoGB = almacenamientoGB;
    }

    public String getProcesador() {
        return procesador;
    }

    public int getMemoriaRam() {
        return memoriaRam;
    }

    public int getAlmacenamientoGB() {
        return almacenamientoGB;
    }

    public void setProcesador(String procesador) {
        this.procesador = procesador;
    }

    public void setMemoriaRam(int memoriaRam) {
        this.memoriaRam = memoriaRam;
    }

    public void setAlmacenamientoGB(int almacenamientoGB) {
        this.almacenamientoGB = almacenamientoGB;
    }

    @Override
    public String toString() {
        return super.toString()
                + ", procesador: " + procesador
                + ", RAM: " + memoriaRam + " GB"
                + ", almacenamiento: " + almacenamientoGB + " GB";
    }
}
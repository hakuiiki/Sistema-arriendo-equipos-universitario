package org.utils.equipos;

public abstract class EquipoTecnologico extends Equipo {

    private String numeroSerie;
    private int consumoWatts;

    public EquipoTecnologico(int id, String nombreEquipo, TipoEquipo tipoEquipo, String marca,
            String numeroSerie, int consumoWatts) {
        super(id, nombreEquipo, tipoEquipo, marca);

        this.numeroSerie = numeroSerie;
        this.consumoWatts = consumoWatts;
    }

    public String getNumeroSerie() {
        return numeroSerie;
    }

    public int getConsumoWatts() {
        return consumoWatts;
    }

    public void setNumeroSerie(String numeroSerie) {
        this.numeroSerie = numeroSerie;
    }

    public void setConsumoWatts(int consumoWatts) {
        this.consumoWatts = consumoWatts;
    }

    @Override
    public String toString() {
        return super.toString()
                + ", número de serie: " + numeroSerie
                + ", consumo: " + consumoWatts + " W";
    }
}
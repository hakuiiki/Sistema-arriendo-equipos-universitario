package org.utils.equipos;

public abstract class EquipoRecreativo extends Equipo {

    private String materialPrincipal;
    private int edadMinima;

    public EquipoRecreativo(int id, String nombreEquipo, TipoEquipo tipoEquipo, String marca,
                            String materialPrincipal, int edadMinima) {

        super(id, nombreEquipo, tipoEquipo, marca);

        this.materialPrincipal = materialPrincipal;
        this.edadMinima = edadMinima;
    }

    public String getMaterialPrincipal() {
        return materialPrincipal;
    }

    public int getEdadMinima() {
        return edadMinima;
    }

    public void setMaterialPrincipal(String materialPrincipal) {
        this.materialPrincipal = materialPrincipal;
    }

    public void setEdadMinima(int edadMinima) {
        this.edadMinima = edadMinima;
    }

    @Override
    public String toString() {
        return super.toString()
                + ", material: " + materialPrincipal
                + ", edad minima: " + edadMinima;
    }
}
package org.utils.equipos;

public enum TipoEquipo {
    NOTEBOOK(4500, 25000, 5),
    PROYECTOR(6000, 30000, 3),
    BICICLETA(3500, 20000, 2),
    CARPA(5000, 25000, 4);

    private final int tarifaDiaria;
    private final int garantiaBase;
    private final int maximoDias;

    TipoEquipo(int tarifa, int garantiaBase, int maximoDias){
        this.tarifaDiaria = tarifa;
        this.garantiaBase = garantiaBase;
        this.maximoDias = maximoDias;
    }

    public int getTarifaDiaria(){
        return tarifaDiaria;
    }

    private int getGarantiaBase(){
        return tarifaDiaria;
    }

    private int getMaximoDias(){
        return maximoDias;
    }
}

package org.gteperu.erp.everest.utils;

import java.util.List;

public class ResultadoJson {

    private List<?> lstLista;
    private int numPaginado;
    private int numPaginaciones;
    private int numRegistros;

    public List<?> getLstLista() {
        return lstLista;
    }

    public void setLstLista(List<?> lstLista) {
        this.lstLista = lstLista;
    }

    public int getNumPaginaciones() {
        return numPaginaciones;
    }

    public void setNumPaginaciones(int numPaginaciones) {
        this.numPaginaciones = numPaginaciones;
    }

    public int getNumPaginado() {
        return numPaginado;
    }

    public void setNumPaginado(int numPaginado) {
        this.numPaginado = numPaginado;
    }

    public int getNumRegistros() {
        return numRegistros;
    }

    public void setNumRegistros(int numRegistros) {
        this.numRegistros = numRegistros;
    }
}

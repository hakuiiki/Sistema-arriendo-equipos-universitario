package org.utils.repositorio;

import java.util.ArrayList;

public class Repositorio<T> {

    ArrayList<T> repositorio;

    public Repositorio(){
        this.repositorio = new ArrayList<>();
    }

    public void agregarElemento (T elemento){
        repositorio.add(elemento);
    }

    public T getPorIndice (int indice){
        return repositorio.get(indice);
    }

    public T sacarPorIndice(int indice){
        return repositorio.remove(indice);
    }

    /** en vez de retornar el repositorio original mejor devolvemos una copia para evitar que se modifique el repositorio
     * y hayan inconsistencias */
    public ArrayList<T> obtenerRepositorio(){
        return new ArrayList<>(repositorio);
    }

    public int sizeRepositorio(){
        return repositorio.size();
    }

    public boolean isVacio(){
        return repositorio.isEmpty(); // equivale a  if(repositorio.size() == 0) return true
    }


}

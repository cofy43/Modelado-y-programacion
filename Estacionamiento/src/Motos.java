package src;

import java.util.ArrayList;

/**
 * Clase que abtrae una motocicleta para la
 * ejecución de la simulacion del estacionamiento
 * @author Martin Felipe Espinal Cruces
 */
public class Motos extends Vehiculo{
    
    String[]vespa = {"Primavera 50\n Año: 2004", "GTS 125\n Año: 2005", "Sprint 125 \n Año: 2012", "GTS 300\n Año: 2003"};
    String[]susuki = {"Addres\n Año: 2008", "SV 650\n Año: 2000", "RM-Z250\n Año: 2014", "GSX-S25\n Año: 2015"};
    String[]honda = {"CB100R\n Año: 1994", "CB125\n Año: 1999", "CB650R\n Año: 2014", "Monkey\n Año: 2007"};
    String[]kawasaki = {"KX250F\n Año: 2010", "Ninja 125\n Año: 2005", "Versys 100\n Año: 2007", "Z1000R\n Año: 2008"};
    ArrayList<String[]> listaMarcas = new ArrayList<>();

    public Motos() {
        listaMarcas.add(vespa);
        listaMarcas.add(susuki);
        listaMarcas.add(honda);
        listaMarcas.add(kawasaki);
    }

    /**
     * Método sobre cargado que regresa 
     * la marca de alguna motoclicleta
     * previmaente agregada.
     * @param i Posicion en la lista de marcas.
     * @return String[] arreglo con los modelos de
     * la marca indicada.
     */
    @Override
    public String[] getMarca(int i) {
        return listaMarcas.get(i);
    }

    /**
     * Método sobre cargado que regresa la 
     * representación en cadena de la marca de
     * alguna motocicleta previamente agregada
     * @param i Número correspondiente a la marca 
     * solicitada
     * @return String Marca solicitada.
     */
    @Override
    public String getMarcas(int i) {
        String marca = "";
        switch(i) {
            case 0:
                marca = "Vespa";
            break;
            case 1: 
                marca = "Susuki";
            break;
            case 2: 
                marca = "Honda";
            break;
            case 3: 
                marca = "Kawasaki";
            break;
            default:
                marca = "";
        }
        return marca;
    }

    /**
     * Método sobre cargado que regresa el modelo
     * de laguna motocicleta, la cual se recupera con
     * la posición de un arreglo correspondiente a la marca.
     * @param i Posicion correspondiente a la poscion en el arreglo
     * @param marca Arreglo correspondiente a la marca previamente 
     * solicitada 
     * @return String Representación en cadena del modelo
     * solicitado.
     */
    @Override
    public String getModelo(int i, String[] marca) {
        return marca[i];
    }

}
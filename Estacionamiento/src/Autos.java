package src;

import java.util.ArrayList;
import java.util.Random;

/**
 * Clase que abtrae una motocicleta para la
 * ejecución de la simulacion del estacionamiento
 * @author Martin Felipe Espinal Cruces
 */
public class Autos extends Vehiculo {

    Random rd = new Random();
    String[]mazda = {"CX-30 \n Año: 2013", "Mazda6 \n Año: 2010", "MX-5 Mita \n Año: 2013", "Cx-9\n Año: 2000"};
    String[]audi = {"A1\n Año: 2005", "e-tron\n Año: 2000", "TT \n Año: 2003", "Q7\n Año: 2016"};
    String[]bmw = {"320\n Año: 2007", "Gran Turismo\n Año: 2004", "Gran Coupe\n Año: 2009", "540d\n Año: 2006"};
    String[]mitsubichi = {"Elipse Cross\n Año: 1999", "ASX\n Año: 2010", "Montero\n Año: 2011", "Outlander\n Año: 2012"};
    ArrayList<String[]> listaMarcas = new ArrayList<>();

    public Autos() {
        listaMarcas.add(mazda);
        listaMarcas.add(audi);
        listaMarcas.add(bmw);
        listaMarcas.add(mitsubichi);
    }

    /**
     * Método sobre cargado que regresa 
     * la marca de algún automovil
     * previmaente agregado.
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
     * algún automivil previamente agregada
     * @param i Número correspondiente a la marca 
     * solicitada
     * @return String Marca solicitada.
     */
    @Override
    public String getMarcas(int i) {
        String marca = "";
        switch(i) {
            case 0:
                marca = "Mazda";
            break;
            case 1: 
                marca = "Audi";
            break;
            case 2: 
                marca = "BMW";
            break;
            case 3: 
                marca = "Mitsubishi";
            break;
            default:
                marca = "";
        }
        return marca;
    }

    /**
     * Método sobre cargado que regresa el modelo
     * de lagún automovil, la cual se recupera con
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
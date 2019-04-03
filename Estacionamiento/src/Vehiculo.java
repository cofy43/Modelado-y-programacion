package src;

/**
 * Clase que abstrae los comportamientos de los distintos
 * vehículos que se encontraran en la simulacion de el 
 * estacionamiento
 * @author Martin Felipe Espinal Cruces
 */
public abstract class Vehiculo {

    //Por las primeras dos horas
    protected int monto1 = 10;
    //Por cada cuarto de hora (despues de las dos primeras horas)
    protected int monto2 = 15;
    //Por cada boleto perdido
    protected int monto3 = 350;
    //Por pornsion mensual
    protected int monto4 = 750;

    /**
     * 
     * @param i 
     * @return 
     */
    public abstract String getMarcas(int i);

    /**
     * 
     * @param i 
     * @return 
     */
    public abstract String[] getMarca(int i);

    /**
     * 
     * @param i 
     * @return 
     */
    public abstract String getModelo(int i, String[] marca);
}
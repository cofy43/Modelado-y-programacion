package src;

import java.util.Random;

/**
 * Clase que almacena la infomacion correspondiente 
 * a almacenar un vehiculo en el estacionamiento
 */
public class Cajon {

    private Random rd = new Random();
    private Vehiculo vehiculo;
    private int horaInicial;
    private int horaFinal;
    private int minutosInicial;
    private int minutosFinal;
    private String marca;
    private String modelo;
    private int identificador;
    private int cobro;
    private int cajon;
    private boolean esPensionado, tarjetaVigente, boletoPerdido;

    public Cajon() {

    }
    /**
     * Constructor que recibe los datos para almacenar un vehiculo en el
     * estacionamiento
     * @param vehiculo Número correspondiente al vehiculo
     * @param horaInicial Hora inicial en el que el auto entra al estacionamiento
     * @param horaFinal Hora en el que sale el vehiculo del estacionamiento
     * @param minutosInicial Minutos correspondiente a la llegada del vehiculo
     * @param minutosFinal Minutos correspondiente a la salida del vehiculo
     * @param marca Representación en cadena de la marca de vehiculo
     * @param modelo Representación en cadena del modelo del vehiculo
     * @param identificador Indica si es automovil o motocicleta
     * @param esPensionado Identificador para saber si el vehiculo a entrar
     * es pensionado
     * @param tarjetaVigente Identificador para saber si se realizo el pago
     * de la pension
     * @param boletoPerdido Identificador para saber en caso de cobro adicional 
     * por perdida de boleto de estacionamiento
     * @param cajon Entero correspondiente a numero de cajon en el estacionamiento
     */
    public Cajon(Vehiculo vehiculo, int horaInicial, int horaFinal,
        int minutosInicial, int minutosFinal, String marca, String modelo
        , int identificador, boolean esPensionado, boolean tarjetaVigente, 
        boolean boletoPerdido, int cajon) {
        this.vehiculo = vehiculo;
        this.horaInicial = horaInicial;
        this.minutosInicial = minutosInicial;
        this.horaFinal = horaFinal;
        this.minutosFinal = minutosFinal;
        this.marca = marca;
        this.modelo = modelo;
        this.identificador = identificador;
        this.esPensionado = esPensionado;
        this.tarjetaVigente = tarjetaVigente;
        this.boletoPerdido = boletoPerdido;
        this.cajon = cajon;
    }

    /**
     * Geters y seters correspondientes a los 
     * atributos de la clase para mantener el 
     * encapsulamiento de datos.
     */

    public Vehiculo getVehiculo() {
        return this.vehiculo;
    }

    public String getMarca() {
        return this.marca;
    }

    public int getCajon() {
        return this.cajon;
    }

    public String getModelo() {
        return this.modelo;
    }

    public void setTarjetaVigente(boolean esVigente) {
        this.tarjetaVigente = esVigente;
    }

    public boolean getTarjetaVigente() {
        return this.tarjetaVigente;
    }

    public void setBoletoPerdido(boolean loPerdio) {
        this.boletoPerdido = loPerdio;
    }

    public void setEsPensionado(boolean esPensionado) {
        this.esPensionado = esPensionado;
    }

    public boolean getEsPensionado() {
        return this.esPensionado;
    }

    public int getHoraInicial() {
        return this.horaInicial;
    }

    
    public int getMinutosInicial() {
        return this.minutosInicial;
    }
    
    public void setHoraFinal(int horaFinal) {
        this.horaFinal = horaFinal;
    }

    public int getHoraFinal() {
        return this.horaFinal;
    }

    public int getMinutosFinal() {
        return this.minutosFinal;
    }

    public void setMinutosFinal(int minutosFinal) {
        this.minutosFinal = minutosFinal;
    }

    public int getCobro() {
        return this.cobro;
    }

    public void setCobro(int cobro) {
        this.cobro = cobro;
    }

    /**
     * Método que realiza la operación aritmética
     * al salir el vehículo tomando en cuenta datos como
     * si es pensionado, si lo es entonces verificar que su tarjeta
     * este vigente, en caso de no ser pensionado se verifica si 
     * no ha perdido el boleto de estacionamiento.
     * Todo esto se agrega a la cuenta del pago correspondiente 
     * por el timepo de ocupación del estacionamiento
     */
    public void cobroAlSalir() {
        if (horaFinal >= 0 && minutosFinal > 0) {
            if (horaInicial == horaFinal || (horaInicial+1) == horaFinal) {
                cobro += 10;
            }
            if (esPensionado) {
                if (tarjetaVigente) {
                    cobro += 750;
                }
            }
            int horas = horaFinal - horaInicial;
            int minutos = (60-minutosInicial) + minutosFinal;
            if (horas >= 2) {
                cobro += 10;
                int porHora, cuartosDeHora;
                porHora = (int) ((horas - 2)*4);
                cuartosDeHora = (int) (Math.floor(minutos/15));
                cobro += cuartosDeHora*15;
                cobro += porHora*15;
            }
            if (boletoPerdido) {
                cobro += 350;
            }
            if (horaFinal > 24) {
                int auxi = horaFinal - 24;
                horaFinal = auxi;
            }
        }
    }

    /**
     * Método que genera una representación en cadena
     * de una placa con el formato tre números seguido de 
     * un guíon, seguido de tres letras mayúsculas.
     * Todo esto gracias a la generacion de un número 
     * aleatorio de seis cifras y convirtiendo en la 
     * representación asccii de las últimas tres sifras
     */
    public String generarPlaca() {
        String placa = "";
        int pla = rd.nextInt(100000);
        int a1, a2, a3;
        placa += String.valueOf(pla%1000) + " - ";
        a1 = (pla%10) + 65;
        a2 = ((pla%100)/10) + 65;
        a3 = ((pla%100)%10) + 65;
        char b1, b2, b3;
        b1 = (char) a1;
        b2 = (char) a2;
        b3 = (char) a3;
        placa += String.valueOf(b1) + String.valueOf(b2) + String.valueOf(b3);
        return placa;
    }

    public String generaColor() {
        String col = "";
        int color =  rd.nextInt(6)+1;
        switch(color) {
            case 1:
                col = "Blanco";
            break;
            case 2:
                col =  "Negro";
            break;
            case 3:
                col =  "Verde";
            break;
            case 4:
                col =  "Gris";
            break;
            case 5:
                col =  "Azul";
            break;
        } 
        return col;
    }

    /**
     * Representación en cadena del ticket generado 
     * al salir un vehículo.
     * @return String Representación en cadena del ticket
     */
    public String toString() {
        cobroAlSalir();            
        String s = "\n****************************************\n";

        if (identificador == 0)
            s += "Tipo de vehiculo: Automovil" ;
        else 
            s += "Tipo de vehiculo: Motocicleta" ;

        if (esPensionado) {
            s += "\nEl vehiculo es pensionado";
            if (!tarjetaVigente)
                s += "\nCuenta con tarjeta vigente";
            else
                s += "\nNo cuenta con tarjeta vigente";
        } else {
            s += "\nEl vehiculo no es pensionado";
        } 

        if (boletoPerdido)
            s += "\nBoleto perdido";

        s += "\nMarca: " + this.marca;
        s += "\nModelo: " + this.modelo;
        s += "\nColor: " + generaColor();
        s += "\nPlaca: " + generarPlaca();
        s += "\nHora de llegada " +this.horaInicial + ":" + this.minutosInicial;
        s += "\nHora de Salida " +this.horaFinal + ":" + this.minutosFinal;
        s += "\nMonto a pagar es $" + this.cobro;
        s += "\n****************************************\n";
        return s;
    }
}
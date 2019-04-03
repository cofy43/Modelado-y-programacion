package src;

import java.util.ArrayList;
import java.util.Random;

/**
 * Clase que se encarga de la creación de instancias de vehículos 
 * por medio del modelo de patron de diseño factory method, y de la 
 * simulacion del estacionamiento.
 * @author Martin Felipe Espinal Cruces
 */
public class FabricaDeVehiculos extends Cajon{

    ArrayList<Cajon> estacionamiento = new ArrayList<>();
    Random rd = new Random();
    int marca, modelo, horaInicial, minutosInicial, auxiliar, vehiculosEnEstacionamieto = 0;
    String[] marcaTemporal = new String[4];
    String marcaAux;
    String modeloTemporal;
    Cajon temporal = null;
    Vehiculo vehiculo = null;
    boolean[][] cajones = new boolean[12][12];
    
    public FabricaDeVehiculos() {
        
    }

    /**
     * Constructor encargado de realizar la creaciónd finitas
     * de instancias de la clase {@link} Auto y {@link} Moto 
     * @param i Variable que se encargara de elegir que instancia crear
     * @return Vehiculo Instancia elegida 
     */
    public Vehiculo estacionamiento(int i) {
        if (i == 0)
            vehiculo = new Autos();
        else
            vehiculo = new Motos();
        return vehiculo; 
    }

    /**
     * Método encargado de asignar un cajon de estacionamiento 
     * a un vehiculo.
     * @param numero Número que ayudará a obtener la marca del vehiculo a ingresar
     * @param tipo Tipo de vehículo que entrara.
     * @param tiempo Tiempo que durará la simulación.
     * @param horaPosible Tiempo generado aleatoriamente en el cual se basara la simulación.
     */
    public void crearCajon(int numero, int tipo, int tiempo, int horaPosible) {
        //Se elige si el vehiculo a entrar es pensionado con un número aleatorio
        int esPensionado = rd.nextInt(10-1)+1;
        //Se elije un número aleatorio de minutos correspondiente a la entrada
        int minutosPosible = (int) (rd.nextDouble() * 60 + 1);
        //Se elije el modelo y la marca dentro de un número aleatorio y un rango que es la 
        //longitud del arreglo de la maraca
        marca = rd.nextInt(numero);
        modelo = rd.nextInt(4);
        //Se elige una hora aleatoria dentro del rango de tiempo de simulacion.
        horaInicial = rd.nextInt((horaPosible+tiempo) - horaPosible)+horaPosible;

        //Verificamos que la hora generada sea correcta, en caso de no serlo
        //se adapta para que quede dentro del rango de 24 horas.
        if (horaInicial >= 24) {
            int aux = horaInicial - 24;
            horaInicial = aux;
        }

        //Verificamos que los minutos sean validos, en caso de no serlos
        //se adapta manteniendo los minitos dentro del rango de 60 y si sobre 
        //pasa este rango se agrefa una hora y los minutos se vuelven los faltantes
        if (minutosPosible == 60) {
            minutosInicial = 0;
            horaInicial ++;
        }

        Vehiculo aux = estacionamiento(tipo);
        marcaAux = aux.getMarcas(marca);
        marcaTemporal = aux.getMarca(modelo);
        modeloTemporal = aux.getModelo(modelo, marcaTemporal);
        int tarjetaVigente = rd.nextInt(10-1)+1;
        int boletoPerdido = rd.nextInt(10-1)+1;
        int cajon = estacionar();
        //Se crea el cajon con los datos previamente generados de manera aleatoria.
        if (esPensionado % 2 == 0) {
            if (tarjetaVigente % 2 == 0) {
                estacionamiento.add(new Cajon(aux, horaInicial, 0,
                minutosPosible , 0 , marcaAux, modeloTemporal, tipo, true, true, false, cajon));

            } else {
                estacionamiento.add(new Cajon(aux, horaInicial, 0,
                minutosPosible , 0 , marcaAux, modeloTemporal, tipo, true, false, false, cajon));
            } 
        } else {
            if (boletoPerdido % 2 == 0) {
                estacionamiento.add(new Cajon(aux, horaInicial, 0,
                minutosPosible , 0 , marcaAux, modeloTemporal, tipo, false, false, true, cajon));
            } else {
                estacionamiento.add(new Cajon(aux, horaInicial, 0,
                minutosPosible , 0 , marcaAux, modeloTemporal, tipo, false, false, false, cajon));
            }
        }
        vehiculosEnEstacionamieto ++;
        System.out.println(muestraEstacionamiento());
    }

    /**
     * Metodo que nos devuleve una representación amistosa de estacionamiento
     */
    public String muestraEstacionamiento() {
        String cajonesCadena = "";
        cajonesCadena += "Numero de vehiculos en el estacionamieto:" + vehiculosEnEstacionamieto;
        cajonesCadena += "\n 1  2  3  4  5  6  7  8  9  10 11 12";
        cajonesCadena += "\n╠══╬══╬══╬══╬══╬══╬══╬══╬══╬══╬══╬══╣";
        for (int i = 0; i < cajones.length; i++) {
            cajonesCadena += (i+1)+"\n";
            for (int j = 0; j < cajones[i].length; j++) {
                //Ejemplo
                if (cajones[i][j] == false)
                    cajonesCadena +=  "║ ▒";
                    //cajonesCadena +=  "\t║    ";
                else 
                    cajonesCadena +=  "║ " + "█";
            }
            cajonesCadena += "║\n╠══╬══╬══╬══╬══╬══╬══╬══╬══╬══╬══╬══╣";
        }
        return cajonesCadena + "\n";
    }

    /**
     * Método que asigna un lugar en el estacionamiento, cuidando de que este 
     * no haya sido previamente ocupado
     * @return cajon Número de cajon asignado
     */
    public int estacionar() {
        int cajon = 0;
        for (int i = 0; i < cajones.length; i++) {
            for (int j = 0; j < cajones[i].length; j++) {
                if (!cajones[i][j]) {
                    cajones[i][j] = true;
                    if (i == 0) {
                        cajon = j+1;
                        return cajon;
                    } else {
                        cajon = ((i+1) * 12) + (j+1);
                        return cajon;
                    }
                }
            }
        }
        return cajon;
    }

    /**
     * Método que libera un cajón determinado de el estacionamiento
     * @param cajon Numero de cajón a liberar
     */
    public void desestacionar(int cajon) {
        int i, j;
        i = Math.floorDiv(cajon, 12);
        j = (cajon % 12)-1;
        if (j < 0)
            j = 0;
        System.out.println("Coordenadas i=" + i + " j=" +  j);
        cajones[i][j] = false;
    }

    /**
     * Método que libera un espacio en el estacionamiento dada una hora de salida 
     * y que puede o no encontrarse dentro del rango de tiempo de la simulacióm 
     * @param sale Número correspondiente a la lista de cajones generada con la 
     * entrada de vehículos al estacionamiento.
     * @param numeroSalida Numero que corresponderá al número de vehículos que salen 
     * del estacionamiento
     * @param horaPosible Hora aleatoria en donde conmenzará nuestra simulación
     * @param tiempo Tiempo de suracion de la simulación
     */
    public void liberarCajon(int sale, int numeroSalida, int horaPosible, int tiempo) {
        int aux = sale;
        sale = (int) (rd.nextDouble() * numeroSalida + 1);
        if (aux != sale) {
            Cajon auxiliar = estacionamiento.get(--sale);
            int temporal = auxiliar.getHoraInicial();
            int horaSalida = rd.nextInt((horaPosible + tiempo) - temporal ) + temporal;
            int minutosSalida = minutosInicial = (int) (rd.nextDouble() * 60 + 1);
            if (minutosSalida == 60) {
                minutosSalida = 0;
                horaSalida ++;
            }
            if (horaSalida < (horaPosible+ tiempo)) {
                estacionamiento.get(sale).setHoraFinal(horaSalida);
                estacionamiento.get(sale).setMinutosFinal(minutosSalida);
                this.auxiliar ++;
            }
            int cajon  = auxiliar.getCajon();
            vehiculosEnEstacionamieto --;
            desestacionar(cajon);
        }
    }

    /**
     * Método principal que dado un tiempo de duración genera un número aleatorio
     * de automoviles y motocicletas que entraran al estacionamiento cuidando de que 
     * en ningun momento superaran la capacidad del mismo.
     * También genera un número aleatorios de vehículos que saldran del estacionamiento
     * que puede o no estar dentro del rango de tiempo de duración de ejecución.
     */
    public void simulacion(int tiempo) {
        System.out.println("Tiempo de simulacion: " +tiempo);
        int promedio = tiempo*8;
        System.out.println("Promedio: " + promedio);
        int numerosDeVehiculos = (int) (rd.nextDouble() * promedio + 1);
        System.out.println("Numeros de vehiculos: " + numerosDeVehiculos);
        int numeroAutos = (int) ((rd.nextDouble() * numerosDeVehiculos + 1)/2);
        if (numeroAutos == 0)
            numeroAutos ++;

        System.out.println("Numeros de autos: " + numeroAutos);
        int numeroMotos = (int) ((rd.nextDouble() * numerosDeVehiculos + 1)/2);
        if (numeroMotos == 0)
            numeroMotos ++;
        System.out.println("Numeros de motos: " + numeroMotos);
        int numeroSalida = (int) (rd.nextDouble() * (numeroAutos + numeroMotos) +1);
        System.out.println("Numeros de vehiculos que saldran: " + numeroSalida);
        int horaPosible = (int) (rd.nextDouble() * 24 + 1);
        System.out.println("Hora random para iniciar la simulacion: " + horaPosible);
        int eleccion = 0, sale = 0;
        int aux = numeroAutos + numeroMotos + numeroSalida, auxMotos = numeroMotos, auxAutos = numeroAutos;
        boolean estacionamientoVacio = true;


        for (int i = 0; i < aux; i++) {
            eleccion = rd.nextInt(11);
            
            sale = rd.nextInt(11);

            if (estacionamientoVacio) {
                if (eleccion % 2 == 0) {
                    crearCajon(numeroAutos, 0, tiempo, horaPosible);
                    auxAutos --;
                    estacionamientoVacio = true;
                } else {
                    crearCajon(numeroMotos, 1, tiempo, horaPosible);
                    auxMotos --; 
                    estacionamientoVacio = true;
                }
            } else {
                //Elección aleatoria de añadir o sacar vehiculos del estacionamiento
                //Añadir vehiculos
                if (sale % 2 == 0) {
                    if (auxAutos == 0) {
                        crearCajon(numeroMotos, 1, tiempo, horaPosible);
                        auxMotos --;
                    } else if (auxMotos == 0) {
                        crearCajon(numeroAutos, 0, tiempo, horaPosible);    
                        auxAutos --;
                    } else {
                        crearCajon(numeroMotos, 1, tiempo, horaPosible);
                        auxMotos --; 
                        estacionamientoVacio = true;
                    }
                }
            }
        }

        for (int i = 0; i <  numeroSalida; i++) {
            liberarCajon(sale, numeroSalida, horaPosible, tiempo);
            System.out.println(muestraEstacionamiento());
        }
        
        for (Cajon c: estacionamiento) {
            c.cobroAlSalir();
            if (c.getCobro() != 0) 
                System.out.println(c.toString());
        }

    }

    public static void main(String[] args) {
        FabricaDeVehiculos f = new FabricaDeVehiculos();
        f.simulacion(10);
    }
}
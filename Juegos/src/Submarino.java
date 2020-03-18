import java.util.ArrayList;
import java.util.Scanner;
import java.util.Random;

public class Submarino extends Juego {
    int intentos = 0;    
    static boolean[][] tableroUsuario = new boolean[10][10];
    static boolean[][] tableroComputadora = new boolean[10][10];
    boolean[][] tirosUsuario = tableroUsuario;
    boolean[][] tirosComputadora = tableroComputadora;
    ArrayList<Barco> usuario = new ArrayList<>();
    ArrayList<Barco> copiaUsuario = usuario;
    ArrayList<Barco> computadora = new ArrayList<>();
    ArrayList<Barco> copiaComputadora = computadora;
    int puntos = 0, total = 15, numeroBarcos = 0, acertadosCpu = 0, acertadosUser = 0,  posibleX = 0, posibleY = 0;
    Scanner sc = new Scanner(System.in);
    Random rd = new Random();
    boolean agregado = false;

    private class Barco {
        int longitud;
        int cantidad;

        public Barco(int l, int c) {
            this.longitud = l;
            this.cantidad = c;
        }

        public void setLongitud(int l) {
            this.longitud = l;
        }

        public int getLongitud () {
            return this.longitud;
        }

        public void setCantidad(int c) {
            this.cantidad = c;
        }

        public int getCantidad () {
            return this.cantidad;
        }

        public String toString(){
            String v = "Longitud: " + String.valueOf(this.longitud)+ " " + ", Unidades: " + String.valueOf(this.cantidad);
            return v;
        }
    }

    public void crearBarcos() {
        int longitud = 5, l = 5;
        int numero = 1;
        for (int i = 0; i < l; i++) {
            computadora.add(new Barco(longitud, numero));
            usuario.add(new Barco(longitud, numero));
            longitud --;
        }
    }

    public boolean disparos(boolean[][] objetivo, int x, int y) {
        //Si se acierta al objetivo devuelve verdadero en caso 
        //contrario falso.
        return (objetivo[x][y] == true);
    }
/* 
    public boolean adyacentes(int x1, int y1, int x2, int y2){
        if (x1 == x2) {
            if (y2-1 == y1 || y2+1 == y1)
                return true;
            else  
                return false;
        } else if (x1 == y2) {
            if (x2-1 == y1 || x2+1 == y1)
                return true;
            else  
                return false;
        } else {
            return false;
        }
    }
 */
    private boolean seSobrepone(boolean[][] tablero, int x, int y,  int longitud, int orientacion) {
        //vertical
        if (orientacion == 1) {
            for (int i = 0; i < longitud; i++) {
                if ((x+i) > 9)
                    return false;
                if (tablero[x+i][y] == true) {
                    return true;
                } else {
                    continue;
                }
            }
            return false;
        } else { //horizontal
            for (int i = 0; i < longitud; i++) {
                System.out.println("La suma de y con i = " + (y+i));
                if ((y+i) > 9) {
                    return false;
                }
                if (tablero[x][y+i] == true) {
                    return true;
                }else {
                    continue;
                }
            }
            return false;
        }

    }
    
    public void coordenadasUsuario(int x, int y, int longitud) {
        boolean continuar = true;
        int respuesta = 0, orientacion = 0;;
        if (longitud > 1) {
            while (continuar) {
                System.out.println("Quieres tu barco vertical u horizontal");
                System.out.println("1) vertical");
                System.out.println("2) horizontal");
                respuesta = sc.nextShort();
                if (respuesta == 1) {
                    orientacion = 1;
                    //Si esta en el extemo inferior del tablero
                    if (y == 9) {
                        if (x == 9) {
                            for (int i = 0; i < longitud; i ++) {
                                tableroUsuario[x-i][y] = true;
                            }
                            System.out.println(toStringTablero(tableroUsuario));
                            agregado= true;
                            continuar = false;
                            break;
                        } else if (x == 0) {
                            for (int i = 0; i < longitud; i ++) {
                                tableroUsuario[x+i][y] = true;
                            }
                            System.out.println(toStringTablero(tableroUsuario));
                            agregado= true;
                            continuar = false;
                            break;
                        }
                        int diff = (x)-longitud, suma = x+longitud;
                        //Verificamos si al agregar el barco no se sobre pone a otro previo.
            
                        if (diff <= -6) {
                            System.out.println("La diferencia es " + diff);
                            System.out.println("Tu barco no cabe, cambia la orientacion, o la coordenada");
                            agregado = false;
                            break;
                        }
                        if (suma >= 16) {
                            System.out.println("La suma es " + suma);
                            System.out.println("Tu barco no cabe, cambia la orientacion, o la coordenada");
                            agregado = false;
                            break;
                        }

                        if (!seSobrepone(tableroUsuario, x, y, longitud, orientacion) && suma >= 16) {
                            for (int i = 0; i < longitud; i ++) {
                                tableroUsuario[x-i][y] = true;
                            }    
                            System.out.println(toStringTablero(tableroUsuario));
                            agregado = true;
                            continuar = false;
                        } else if (seSobrepone(tableroUsuario, x, y, longitud, orientacion)) {
                            System.out.println("Tu barco se sobre pone a otro, cambia de orientacion o de coordenada");
                            continuar = false;
                        } else {
                            for (int i = 0; i < longitud; i ++) {
                                tableroUsuario[x+i][y] = true;
                            }
                            System.out.println(toStringTablero(tableroUsuario));
                            agregado = true;
                            continuar = false;
                        }
                    //Si esta en el extemo superior del tablero
                    } else if (y == 0){
                        if (x == 9) {
                            for (int i = 0; i < longitud; i ++) {
                                tableroUsuario[x-i][y] = true;
                            }
                            System.out.println(toStringTablero(tableroUsuario));
                            agregado = true;
                            continuar = false;
                            break;
                        }

                        int diff = (x)-longitud, suma = x+longitud;
                        //Verificamos si al agregar el barco no se sobre pone a otro previo.
                        if (diff <= -6 ) {
                            System.out.println("La diferencia es " + diff);
                            System.out.println("Tu barco no cabe, cambia la orientacion, o la coordenada");
                            agregado = false;
                            continuar = false;
                            break;
                        }
                        if (suma >= 16) {
                            System.out.println("La suma es " + suma);
                            System.out.println("Tu barco no cabe, cambia la orientacion, o la coordenada");
                            agregado = false;
                            continuar = false;
                            break;
                        }

                        if (!seSobrepone(tableroUsuario, x, y, longitud, orientacion) && diff <= -6) {
                            for (int i = 0; i < longitud; i ++) {
                                tableroUsuario[x+i][y] = true;
                            }
                            System.out.println(toStringTablero(tableroUsuario));
                            agregado = true;
                            continuar = false;
                        } else if (seSobrepone(tableroUsuario, x, y, longitud, orientacion)) {
                            System.out.println("Tu barco se sobre pone a otro, cambia de orientacion o de coordenada");
                            continuar = false;
                        } else {
                            for (int i = 0; i < longitud; i ++) {
                                tableroUsuario[x+i][y] = true;
                            }                        
                            System.out.println(toStringTablero(tableroUsuario));
                            agregado = true;
                            continuar = false;
                        }
                        
                    } else {
                        int diff = (x)-longitud, suma = x+longitud;
                        //Verificamos si al agregar el barco no se sobre pone a otro previo.
                        if (diff <= -6 && tableroUsuario[y+1][y] != false) {
                            System.out.println("La diferencia es " + diff);
                            System.out.println("Tu barco no cabe, cambia la orientacion, o la coordenada");
                            agregado = false;
                            break;
                        }
                        if (suma >=  16) {
                            System.out.println("La suma es " + suma);
                            System.out.println("Tu barco no cabe, cambia la orientacion, o la coordenada");
                            agregado = false;
                            break;
                        }
                        if (!seSobrepone(tableroUsuario, x, y, longitud, orientacion)) {
                            for (int i = 0; i < longitud; i ++) {
                                tableroUsuario[x+i][y] = true;
                            }                        
                            System.out.println(toStringTablero(tableroUsuario));
                            agregado = true;
                            continuar = false;
                        } else {
                            System.out.println("Tu barco se sobre pone a otro, cambia de orientacion o de coordenada");
                            agregado = false;
                            continuar = false;
                        }
                        
                    }
                    System.out.println(toStringTablero(tableroUsuario));
                    continuar = false;
                } else if (respuesta == 2) {
                    orientacion = 2;
                    if (x == 9) {
                        if (y == 9) {
                            for (int i = 0; i < longitud; i ++) {
                                tableroUsuario[x][y-i] = true;
                            }    
                            agregado = true;
                            continuar = false;
                        }

                        int diff = (y)-longitud, suma = y+longitud;
                        //Verificamos si se puede agregar el barco al tablero con la orientacion
                        //seleccionada.
                        if (diff <= -6) {
                            System.out.println("La diferencia es " + diff);
                            System.out.println("Tu barco no cabe, cambia la orientacion, o la coordenada");
                            agregado = false;
                            break;
                        }

                        if (suma >= 16) {
                            System.out.println("La suma es " + suma);
                            System.out.println("Tu barco no cabe, cambia la orientacion, o la coordenada");
                            agregado = false;
                            break;
                        }

                        if (!seSobrepone(tableroUsuario, x, y, longitud, orientacion) && diff <= -6) {
                            for (int i = 0; i < longitud; i ++) {
                                tableroUsuario[x][y+i] = true;
                            }    
                            agregado = true;
                            continuar = false;
                        } else if (seSobrepone(tableroUsuario, x, y, longitud, orientacion)) {
                            System.out.println("Tu barco se sobre pone a otro, cambia de orientacion o de coordenada");
                            agregado = false;
                            continuar = false;
                        } else {
                            for (int i = 0; i < longitud; i ++) {
                                tableroUsuario[x][y+i] = true;
                            }    
                            agregado = true;
                            continuar = false;
                        }
                        
                    } else if (x == 0) {
                        if (y == 0) {
                            for (int i = 0; i < longitud; i ++) {
                                tableroUsuario[x][y+i] = true;
                            }
                            agregado = true;
                            continuar = false;
                        }

                        int diff = (y)-longitud, suma = y+longitud;
                        //Verificamos si se puede agregar el barco al tablero con la orientacion
                        //seleccionada.
                        if (diff <= -6) {
                            System.out.println("La diferencia es " + diff);
                            System.out.println("Tu barco no cabe, cambia la orientacion, o la coordenada");
                            agregado = false;
                            break;
                        }
                        if (suma >= 16) {
                            System.out.println("La suma es " + suma);
                            System.out.println("Tu barco no cabe, cambia la orientacion, o la coordenada");
                            agregado = false;
                            break;
                        }

                        if (!seSobrepone(tableroUsuario, x, y, longitud, orientacion) && diff <= -6) {
                            for (int i = 0; i < longitud; i ++) {
                                tableroUsuario[x][y+i] = true;
                            }
                            agregado = true;
                            continuar = false;
                        } else if (seSobrepone(tableroUsuario, x, y, longitud, orientacion)) {
                            System.out.println("Tu barco se sobre pone a otro, cambia de orientacion o de coordenada");
                            agregado = false;
                            continuar = false;
                        } else {
                            for (int i = 0; i < longitud; i ++) {
                                tableroUsuario[x][y-i] = true;
                            }
                            agregado = true;
                            continuar = false;
                        }

                        
                    } else {
                        int diff = (y)-longitud, suma = y+longitud;
                        //Verificamos si se puede agregar el barco al tablero con la orientacion
                        //seleccionada.
                        if (diff <= -6 && tableroComputadora[y+1][y] != false) {
                            System.out.println("La diferencia es " + diff);
                            System.out.println("Tu barco no cabe, cambia la orientacion, o la coordenada");
                            agregado = false;
                            break;
                        }
                        if (suma >= 16) {
                            System.out.println("La suma es " + suma);
                            System.out.println("Tu barco no cabe, cambia la orientacion, o la coordenada");
                            agregado = false;
                            break;
                        }
                        
                        if (!seSobrepone(tableroUsuario, x, y, longitud, orientacion)) {
                            for (int i = 0; i < longitud; i ++) {
                                tableroUsuario[x][y+i] = true;
                            }
                            agregado = true;
                            continuar = false;
                        } else {
                            System.out.println("Tu barco se sobre pone a otro, cambia de orientacion o de coordenada");
                            agregado = false;
                            agregado = false;
                            continuar = false;
                        }

                        
                    }
                    System.out.println(toStringTablero(tableroUsuario));
                    continuar = false;
                } else {
                    System.out.println("Opcion invalida");
                }
            }
        } else {
            while (tableroComputadora[x][y] != true) {
                tableroComputadora[x][y] = true;
            }
            System.out.println(toStringTablero(tableroComputadora));
        }
        
    }

    public void coordenadasCpu(int x, int y, int longitud) {
        boolean continuar = true;
        int respuesta = 0, orientacion = 0;;
        if (longitud > 1) {
            while (continuar) {
                respuesta = rd.nextInt(3);
                if (respuesta == 1) {
                    orientacion = 1;
                    //Si esta en el extemo inferior del tablero
                    if (y == 9) {
                        int diff = (x)-longitud, suma = x+longitud;
                        //Verificamos si al agregar el barco no se sobre pone a otro previo.
                        if (diff <= -6) {                            
                            agregado = false;
                            break;
                        }
                        if (suma >= 16) {
                            agregado = false;
                            break;
                        }

                        if (!seSobrepone(tableroComputadora, x, y, longitud, orientacion)) {
                            for (int i = 0; i < longitud; i ++) {
                                tableroComputadora[x-i][y] = true;
                            }    
                            agregado = true;
                            continuar = false;
                        } else {
                            agregado = false;
                            continuar = false;
                        }
                    } else if (y == 0){
                        int diff = (x)-longitud, suma = x+longitud;
                        //Verificamos si al agregar el barco no se sobre pone a otro previo.
                        if (diff <= -6) {
                            System.out.println("Tu barco no cabe, cambia la orientacion, o la coordenada");
                            agregado = false;
                            break;
                        }
                        if (suma >= 16) {
                            System.out.println("Tu barco no cabe, cambia la orientacion, o la coordenada");
                            agregado = false;
                            break;
                        }
                        if (!seSobrepone(tableroComputadora, x, y, longitud, orientacion)) {
                            for (int i = 0; i < longitud; i ++) {
                                tableroComputadora[x+i][y] = true;
                            }
                            agregado = true;
                            continuar = false;
                        } else {
                            agregado = false;
                            continuar = false;
                        }
                        
                    } else {
                        int diff = (x)-longitud, suma = x+longitud;
                        //Verificamos si al agregar el barco no se sobre pone a otro previo.
                        if (diff <= -6 && tableroComputadora[x+1][y] != false) {
                            agregado = false;
                            break;
                        }
                        if (suma >= 16) {
                            agregado = false;
                            break;
                        }
                        if (!seSobrepone(tableroComputadora, x, y, longitud, orientacion)) {
                            for (int i = 0; i < longitud; i ++) {
                         
                                    tableroComputadora[x+i][y] = true;
                            }                
                            agregado = true;        
                            continuar = false;
                        } else {
                            agregado = false;
                            continuar = false;
                        }
                        
                    }
                    System.out.println(toStringTablero(tableroComputadora));
                    continuar = false;
                } else if (respuesta == 2) {
                    orientacion = 2;
                    if (x == 9) {
                        int diff = (y)-longitud, suma = y+longitud;
                        //Verificamos si se puede agregar el barco al tablero con la orientacion
                        //seleccionada.
                        if (diff <= -6) {
                            agregado = false;
                            break;
                        }
                        if (suma >= 16) {
                            agregado = false;
                            break;
                        }

                        if (!seSobrepone(tableroComputadora, x, y, longitud, orientacion)) {
                            for (int i = 0; i < longitud; i ++) {
                                    tableroComputadora[x][y+i] = true;
                            }    
                            agregado = true;
                            continuar = false;
                        } else {
                            agregado = false;
                            continuar = false;
                        }
                        
                    } else if (x == 0) {
                        int diff = (y)-longitud, suma = y+longitud;
                        //Verificamos si se puede agregar el barco al tablero con la orientacion
                        //seleccionada.
                        if (diff <= -6) {
                            agregado = false;
                            break;
                        }
                        if (suma >= 16) {
                            agregado = false;
                            break;
                        }

                        if (!seSobrepone(tableroComputadora, x, y, longitud, orientacion)) {
                            for (int i = 0; i < longitud; i ++) {
                                tableroComputadora[x][y-i] = true;
                            }
                            agregado = true;
                            continuar = false;
                        } else {
                            agregado = false;
                            continuar = false;
                        }

                        
                    } else {
                        int diff = (y)-longitud, suma = y+longitud;
                        //Verificamos si se puede agregar el barco al tablero con la orientacion
                        //seleccionada.
                        if (diff <= -6 && tableroComputadora[y+1][y] != false) {
                            agregado = false;
                            break;
                        }
                        if (suma >= 16) {
                            agregado = false;
                            break;
                        }
                        
                        if (!seSobrepone(tableroComputadora, x, y, longitud, orientacion)) {
                            for (int i = 0; i < longitud; i ++) {
                                tableroComputadora[x][y+i] = true;
                            }
                            agregado = true;
                            continuar = false;
                        } else {
                            agregado = false;
                            continuar = false;
                        }
                    }
                    System.out.println(toStringTablero(tableroComputadora));
                    continuar = false;
                } 
            }
        } else {
            while (tableroComputadora[x][y] != true) {
                tableroComputadora[x][y] = true;
            }
            System.out.println(toStringTablero(tableroComputadora));
        }
    }

    public void eliminaBarco (ArrayList<Barco> list, int n) {
        Barco bar = list.get(n);
        int v = bar.getCantidad();
        if(v == 5){
            puntos += 500;
        }else if(v == 4){
            puntos += 400;
        }else if(v == 3){
            puntos += 300;
        }else if(v == 2){
            puntos += 200;
        }else if(v == 1){
            puntos += 100;
        }
        v--;
        if(v == 0){
            return;
        }else{
            bar.setCantidad(v);
            list.add(bar);
        }
    }

    public void crear(boolean[][] b, boolean[][] a) {
        for (int i= 0; i < b.length; i++) {
            for (int j = 0; j < b[i].length; j++) {
                b[i][j] = false;
                a[i][j] = false;
            }
        }
    }

    public String toStringTablero(boolean[][] tablero) {
        String tableroCadena = "";
        tableroCadena += "\n 1  2  3  4  5  6  7  8  9  10";
        tableroCadena += "\n╠══╬══╬══╬══╬══╬══╬══╬══╬══╬══╣";
        for (int i = 0; i < tablero.length; i++) {
            tableroCadena += (i+1)+"\n";
            for (int j = 0; j < tablero[i].length; j++) {
                //Ejemplo
                if (tablero[i][j] == false)
                    tableroCadena +=  "║ ▒";
                    //tableroCadena +=  "\t║    ";
                else 
                    tableroCadena +=  "║ " + "█";
            }
            tableroCadena += "║\n╠══╬══╬══╬══╬══╬══╬══╬══╬══╬══╣";
        }
        return tableroCadena + "\n";
    }
    public static void main(String[] args) {
        Submarino sub = new Submarino();
        try {
            sub.jugar();
        } catch (NoRequiereTableroException nrte) {
            System.err.println("Error");
        }
    }
    
    private void menu() {

    }

    @Override
    public void turnoUsuarioInvitado() {

    }
    
    @Override
    public void turnoComputadora() {
        int x = 0, y = 0;
        
        x = rd.nextInt(10);
        y = rd.nextInt(10);
        System.out.println("Coordenadas propuestas x = " + x + " y = " + y);

        if (tableroComputadora[y][x] == true) {
            tableroComputadora[y][x] = false;
            System.out.println("Te dio la computadora");
            acertadosCpu ++;
        } else {
            System.out.println("Fallo");
        }
    }

    @Override
    public void turnoUsuario() {
        int x = 0, y = 0;
        System.out.println("Dame las coordenadas de disparo");
        x = solicitaCoordenadaX();
        y = solicitaCoordenadaY();
        if (tableroComputadora[--y][--x] == true) {
            tableroComputadora[y][x] = false;
            System.out.println("Buen disparo");
            acertadosUser ++;
        } else {
            System.out.println("Fallaste");
        }
    }

    @Override
    public void guardaPuntuacion() {
        
    }

    @Override
    public void creaTablero() throws NoRequiereTableroException {
        crear(tableroUsuario, tableroComputadora);
    }

    private int solicitaCoordenadaX() {
        int x = 0;
        do {
            System.out.println("Coordenada x:" );
            x = sc.nextInt();
            if (x < 1 || x > 10) {
                System.out.println("Numero fuera de rango");
            } else {
                return x;
            }

        } while (true);
    }

    private int solicitaCoordenadaY() {
        int y = 0;
        do {
            System.out.println("Coordenada y:" );
            y = sc.nextInt();
            if (y < 1 || y > 10) {
                System.out.println("Numero fuera de rango");
            } else {
                return y;
            }
        } while (true);
    }

    @Override
    public void jugar() throws NoRequiereTableroException {
        crearBarcos();
        //Ubicacion de los barcos para el mapa del usuario
        
        System.out.println("Esta es tu lista de barcos");
        System.out.println(usuario.toString());
        int x = 0, y = 0, i = 0;
         
        while (!copiaUsuario.isEmpty()) {
            Barco temporal = copiaUsuario.get(i);
            do {
                System.out.println("Dame las coordenadas para el barco de longitud " + temporal.longitud);
                x = solicitaCoordenadaX();
                y = solicitaCoordenadaY();
            } while (tableroUsuario[y-1][x-1] != false);
            coordenadasUsuario((y-1), (x-1) ,temporal.longitud);
            if (agregado)
                copiaUsuario.remove(temporal);
        } 


       /*  x = 0;
        y = 0;
        while (!copiaComputadora.isEmpty()) {
            Barco temporal = copiaComputadora.get(i);
            do {
                x = rd.nextInt(10);
                y = rd.nextInt(10);
            } while (tableroUsuario[x][y] != false);
            coordenadasCpu(y, x ,temporal.longitud);
            if (agregado) {
                copiaComputadora.remove(temporal);
                numeroBarcos++;
                System.out.println(toStringTablero(tableroComputadora));
            }
        } */

        
        /* while (acertadosCpu != total || acertadosUser != total) {
            turnoUsuario();
            turnoComputadora();
        }
        if (acertadosCpu == 15)
            System.out.println("Perdiste");
        else 
            System.out.println("Ganaste"); */
    }

    @Override
    public boolean juegoTerminado() {
        if (usuario.isEmpty() || computadora.isEmpty()) 
            return true;
        return false;
    }

    @Override
    public void muestraPuntuaciones() {
        System.out.println(puntos);        
    }

    @Override
    public void muestraTablero() {
        System.out.println(toStringTablero(tirosUsuario));
    }


}
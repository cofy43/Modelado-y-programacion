import java.util.Scanner;
import java.util.Random;

public class Gato extends Juego implements Tablero{
    String[][] tablero = new String[3][3];
    Scanner sc = new Scanner(System.in);
    Random rd = new Random();
    String us = "", cpu = "";
    
    @Override
    public String toString() {
        String tableroCadena = "";
        tableroCadena += "\n \t╠═══════╬═══════╬═══════╣";
        for (int i = 0; i < tablero.length; i++) {
            tableroCadena += "\n";
            for (int j = 0; j < tablero[i].length; j++) {
                //Ejemplo
                if (tablero[i][j] == null)
                    tableroCadena +=  "\t║ ["+(j+1)+","+(i+1)+"]";
                    //tableroCadena +=  "\t║    ";
                else 
                    tableroCadena +=  "\t║   "+tablero[i][j] ;
            }
            tableroCadena += "\t║\n \t╠═══════╬═══════╬═══════╣";
        }
        return tableroCadena + "\n";
    }
    
    public boolean turno() {
        int i = (int)Math.random();
        if (i% 2 == 0) 
            return true;
        return false;
    }

    public void llenaTablero(int x, int y, String turno) {
        tablero[x][y] = turno;
        String s = toString();
        System.out.println(s);
    }

    @Override
    public void jugar() throws NoRequiereTableroException {
        //Si es verdadero entonces es turno del jugador
        System.out.print(toString());
        System.out.print("1) x\n");
        System.out.print("2) o\n");
        System.out.print("¿Cual quieres pendejo? ");
        int respuesta = 0;
        boolean continuar = true, vaPrimero = false;
        while (continuar) {
            respuesta = sc.nextInt();
            switch (respuesta) {
                case 1: us ="x";
                        cpu = "o";
                        continuar = false;
                    break;
                case 2: us ="x";
                        cpu = "o";
                        continuar = false;
                    break;
                default : System.out.println("Respuesta erronea ");
            }
        }
        int i = 0;
        continuar = false;
        while (continuar || i == 4) {
            turnoUsuario();
            if (hayGanador(us)) {
                System.out.println("Ganaste");
                continuar = false;
            } 
            i++;
            turnoComputadora();
            if (hayGanador(cpu)) {
                System.out.println("Gano la computadora");
                continuar = false;
            }
        }
        System.out.println("Nadie gano jeje");
    }


    private boolean hayGanador(String jugada) {
        if (tablero[0][0]!= null && tablero[1][1] != null && tablero[2][2] != null) {
            if (tablero[0][0].equals(jugada) && tablero[1][1].equals(jugada)
                && tablero[2][2].equals(jugada)) {    
                return true;
            } else {
                return false;
            } 
        }
        if (tablero[0][2] != null && tablero[1][1] != null && tablero[2][0] != null) {
            if (tablero[0][2].equals(jugada) && tablero[1][1].equals(jugada)
            && tablero[2][0].equals(jugada)) {
                return true;
            } else {
                return false;
            }
        }

        if (tablero[0][0] != null && tablero[0][1] != null && tablero[0][2] != null) {
            if (tablero[0][0].equals(jugada) && tablero[0][1].equals(jugada)
                && tablero[0][2].equals(jugada)) {
                return true;
            } else {
                return false;
            }
        }

        if (tablero[1][0] != null && tablero[1][1] != null && tablero[1][2] != null) {
            if (tablero[1][0].equals(jugada) && tablero[1][1].equals(jugada)
                && tablero[1][2].equals(jugada)) {
                return true;
            } else {
                return false;
            }
        }

        if (tablero[2][0] != null && tablero[2][1] != null && tablero[2][2] != null) {
            if (tablero[2][0].equals(jugada) && tablero[2][1].equals(jugada)
            && tablero[2][2].equals(jugada)) {
                return true;
            } else {
                return false;
            }
        }

        if (tablero[0][0] != null && tablero[0][1] != null && tablero[0][2] != null) {
            if (tablero[0][0].equals(jugada) && tablero[0][1].equals(jugada)
            && tablero[0][2].equals(jugada)) {
                return true;
            } else {
                return false;
            }
        }
            
        if (tablero[0][1] != null && tablero[1][1] != null && tablero[2][1] != null) {
            if (tablero[0][1].equals(jugada) && tablero[1][1].equals(jugada)
            && tablero[2][1].equals(jugada)) {
                return true;
            } else {
                return false;
            }
        } 

        if (tablero[0][2] != null && tablero[1][2] != null && tablero[2][2] != null) {
            if (tablero[0][2].equals(jugada) && tablero[1][2].equals(jugada)
                && tablero[2][2].equals(jugada)) {
                return true; 
            } else {
            return false;
            }
        }

        return false;
    }

    public static void main(String[] args) {
        Gato g = new Gato();
        try {
            g.jugar();
        } catch (NoRequiereTableroException nrt) {
            System.out.println("Error");    
        }
    }

    @Override
    public void turnoComputadora() {
        int x, y;
        do {
             x = rd.nextInt(3);
             y = rd.nextInt(3);    
        } while (tablero[y][x] != null);
        System.out.println("La compu jugo " + (x+1) +" " + (y+1));
        llenaTablero(y, x, cpu);
    }

    @Override
    public void turnoUsuario() {
        int x = 0, y = 0;
        do {
            System.out.println("Dame la coordena x");
            x = sc.nextInt();
            while (x <1 || x > 3) {
                System.out.println("Coordenada erronea");
                x = sc.nextInt();
            }
            System.out.println("Dame la coordena y");
            y = sc.nextInt();
            while (y <1 || y > 3) {
                System.out.println("Coordenada erronea");
                y = sc.nextInt();
            }
        } while(tablero[y-1][x-1] != null);
        
        llenaTablero(y-1, x-1, us);
        /*
        while (sc.hasNextLine()) {
            x = sc.nextInt();
            y = sc.nextInt();
        }
         String res = sc.nextLine();
        if (res.length() > 3) {
            System.out.println("Coordenada erronea");
            res = sc.nextLine();
        }
        int a = Integer.parseInt(res.substring(0));
        int b = Integer.parseInt(res.substring(2));
        System.out.println("Coordenada a " + a + "Coordenada b " + b);
 */
        //int a = Integer.valueOf(res.substring(0, 1));
    }

    @Override
    public void turnoUsuarioInvitado() {
        
    }

    @Override
    public void guardaPuntuacion() {
        
    }

    @Override
    public void muestraPuntuaciones() {
        
    }

    @Override
    public void muestraTablero() {

    }

    @Override
    public void creaTablero() throws NoRequiereTableroException {
        for (int i = 0; i < tablero.length; i++) {
            for (int j = 0; j < tablero[i].length; j++) {
                tablero[i][j] = "";
            }
        }
    }
}
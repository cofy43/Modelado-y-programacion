import java.util.Random;
import java.util.Scanner;


public class Pipati extends Juego{
    boolean turno = false;
    int computadora=0;
    int respuesta=0;
    @Override
    public void turnoComputadora() {
        Random r = new Random();
        computadora = r.nextInt(4);
        if (computadora == 0)
            computadora ++;
    }

    public void menu() {
        System.out.println("1) Piedra");
        System.out.println("2) Papel");
        System.out.println("3) Tijera");
    }

    @Override
    public boolean juegoTerminado() {
        if (computadora != 0 && respuesta != 0)
            return true;
        return false;
    }

    public void ganador (int i, int j) {
        if (i == 1 && j == 3) {
            System.out.println("Ganaste");
        } else if (i == 1 && j == 2) {
            System.out.println("Perdiste");
        } else if (i == 2 && j ==3) {
            System.out.println("Perdiste");
        } else if (i == 2 && j == 1) {
            System.out.println("Ganaste");
        } else if (i == 3 && j == 1) {
            System.out.println("Perdiste");
        } else {
            System.out.println("Ganaste");
        }

    }

    public void eleleccion(int i) {
        System.out.println("La computadora eligio");
        if (i == 1) {
            System.out.println("Piedra");
        } else if (i == 2) {
            System.out.println("Papel");
        } else {
            System.out.println("Tijera");
        }
    }

    @Override
    public void jugar() throws NoRequiereTableroException {
        turnoUsuario();
        turnoComputadora();
        eleleccion(computadora);
        if (computadora == respuesta) {
            jugar();
        } else {
            ganador(respuesta, computadora);
        }
        if (juegoTerminado())
            return;
    }
    
    @Override
    public void turnoUsuario() {
        Scanner sc = new Scanner(System.in);
        boolean cont = true;
        while (cont) {
            menu();
            respuesta =sc.nextInt();
            if (respuesta < 0) {
                System.out.println("Eleccion invalida");
                menu();
            } else if (respuesta > 3) {
                System.out.println("Eleccion invalida");
                menu();
            } else {
                cont = false;
            }

        }
    }
    public static void main(String[] args) {
        Pipati p = new Pipati();
        try {
            p.jugar();
        } catch (NoRequiereTableroException n) {
            System.err.println("Error");
        }
        //System.out.printlncomputadora);
    }

    @Override
    public void muestraPuntuaciones() {
        
    }

    @Override
    public void creaTablero() throws NoRequiereTableroException {
        
    }

    @Override
    public void guardaPuntuacion() {
        
    }

    @Override
    public void muestraTablero() {
        //super.muestraTablero();
    }


    @Override
    public void turnoUsuarioInvitado() {
        
    }
}

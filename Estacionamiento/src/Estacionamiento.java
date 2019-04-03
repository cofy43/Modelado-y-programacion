package src;

import java.util.Scanner;

public class Estacionamiento extends FabricaDeVehiculos{

    public Estacionamiento() {
    }
    
    public static void main(String[] args) {
        FabricaDeVehiculos fv = new FabricaDeVehiculos();
        Scanner sc = new Scanner(System.in);
        System.out.println("Introduce el tiempo de simulacion");
        int tiempo = sc.nextInt();
        fv.simulacion(tiempo);
    }
    
}
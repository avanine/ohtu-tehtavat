package ohtu.kivipaperisakset;

import java.util.Scanner;

public class Paaohjelma {

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        KPSTehdas tehdas = new KPSTehdas();
        KiviPaperiSakset peli;

        while (true) {
            System.out.println("\nValitse pelataanko"
                    + "\n (a) ihmistä vastaan "
                    + "\n (b) tekoälyä vastaan"
                    + "\n (c) parannettua tekoälyä vastaan"
                    + "\nmuilla valinnoilla lopetetaan");

            String vastaus = scanner.nextLine();
            if (vastaus.equals("a") || vastaus.equals("b") || vastaus.equals("c")) {
                peli = tehdas.luoPeli(vastaus);
            } else {
                break;
            }
            peli.pelaa();

        }

    }
}

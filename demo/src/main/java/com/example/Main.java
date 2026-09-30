package com.example;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Quanti cavalli vuoi far correre? (Massimo 5): ");
        int numeroCavalli = scanner.nextInt();
        int percorsoGara = 20; 
        
        String[] nomiDisponibili = {"Fulmine", "Freccia", "Furia", "Saetta", "Uragano", "Tornado"};
        
        // Creiamo una lista "Sincronizzata". Questo evita che due thread inseriscano il nome 
        // nello stesso identico momento creando errori o sovrascritture nella memoria.
        List<String> classifica = Collections.synchronizedList(new ArrayList<>());
        
        List<Cavallo> listaCavalli = new ArrayList<>();

        for (int i = 0; i < numeroCavalli; i++) {
            String nomeCavallo = (i < nomiDisponibili.length) ? nomiDisponibili[i] : "Cavallo-" + (i + 1);
            
            // Passiamo anche la lista della classifica a ogni cavallo
            Cavallo nuovoCavallo = new Cavallo(nomeCavallo, percorsoGara, classifica);
            listaCavalli.add(nuovoCavallo);
        }

        System.out.println("\nPRONTI... PARTENZA... VIA!\n");

        for (Cavallo c : listaCavalli) {
            c.start();
        }

        // FONDAMENTALE: Diciamo al Main di aspettare che TUTTI i cavalli abbiano finito di correre
        for (Cavallo c : listaCavalli) {
            try {
                c.join(); // Il Main si mette in pausa finché il thread 'c' non termina il suo run()
            } catch (InterruptedException e) {
                System.out.println("Errore nell'attesa dei thread.");
            }
        }

        System.out.println("\n=========================================");
        System.out.println("         🏁 GARA TERMINATA! 🏁          ");
        System.out.println("=========================================");
        
        // Il primo elemento della lista (indice 0) è il vincitore
        System.out.println("🏆 IL VINCITORE È: " + classifica.get(0).toUpperCase() + " 🏆\n");
        
        System.out.println("--- CLASSIFICA FINALE ---");
        for (int i = 0; i < classifica.size(); i++) {
            System.out.println((i + 1) + "° Posto: " + classifica.get(i));
        }
        System.out.println("=========================================");
        
        scanner.close();
    }
}
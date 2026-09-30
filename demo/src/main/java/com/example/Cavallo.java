package com.example;

import java.util.List;
import java.util.Random;

public class Cavallo extends Thread {
    private String nome;
    private int percorsoTotale;
    private int distanzaPercorsa;
    private Random random;

    private List<String> classificaCondivisa;

    public Cavallo(String nome, int percorsoTotale, List<String> classificaCondivisa) {
        this.nome = nome;
        this.percorsoTotale = percorsoTotale;
        this.distanzaPercorsa = 0;
        this.random = new Random();
        this.classificaCondivisa = classificaCondivisa;
    }

    @Override
    public void run() {
        // Il ciclo continua finché il cavallo non arriva al traguardo
        while (distanzaPercorsa < percorsoTotale) {
            
            distanzaPercorsa++;
            System.out.println("[" + nome + "] ha percorso " + distanzaPercorsa + " metri.");

            // Controllo se il cavallo ha raggiunto il traguardo
            if (distanzaPercorsa >= percorsoTotale) {
                classificaCondivisa.add(nome);
                break;
            }

            try {
                int tempoSleep = random.nextInt(401) + 400;
                Thread.sleep(tempoSleep);
            } catch (InterruptedException e) {
                System.out.println("Il thread è stato interrotto");
            }
        }
    }
}
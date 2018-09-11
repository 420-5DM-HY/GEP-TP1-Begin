package com.example.arnau.tictactoe;



public class EventsConsole implements EvenementsTTT {

    @Override
    public void auTourDe(Joueur joueur) {
        System.out.println("C'est le tour de " + joueur.toString() + ".");
    }

    @Override
    public void aGagne(Joueur joueur) {
        System.out.println(joueur.toString() + " a gagné!");
        Main.gagne = true;
    }

    @Override
    public void cartePleine() {
        System.out.println("La carte est pleine, match nul!");
        Main.gagne = true;
    }

    @Override
    public void positionDejaOccuppee() {
        System.out.println("Cette position est déja occupée, réessayez.");
    }
}
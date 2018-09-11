package com.example.arnau.tictactoe;

import java.util.Scanner;

public class Main {
    public static boolean gagne = false;

    public static void main(String[] args) {
        Logique l = new Logique(new EventsConsole(), Joueur.X);

        while(true){
            while (!gagne) {
                System.out.println("Entrez un nombre de 1 à 9");
                Scanner in = new Scanner(System.in);
                int num = in.nextInt();
                try {
                    l.jouePosition(num);
                    System.out.println(" -------------");
                    System.out.println(" | " + getPositionString(l,1) + " | " + getPositionString(l,2) + " | " + getPositionString(l,3) + " | ");
                    System.out.println(" -------------");
                    System.out.println(" | " + getPositionString(l,4) + " | " + getPositionString(l,5) + " | " + getPositionString(l,6) + " | ");
                    System.out.println(" -------------");
                    System.out.println(" | " + getPositionString(l,7) + " | " + getPositionString(l,8) + " | " + getPositionString(l,9) + " | ");
                    System.out.println(" -------------");
                } catch (PositionInvalide pos) {
                    System.out.println("Position invalide, réessayez.");
                }
            }
            l.resetBoard();
            gagne = false;
        }
    }

    private static String getPositionString(Logique l, int position){
        try{
            Joueur j = l.getJoueur(position);
            if (j == null)
                return String.valueOf(position);
            return j.toString();
        } catch(PositionInvalide pos){

        }
        return "";
    }
}


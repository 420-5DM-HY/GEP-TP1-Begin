package com.example.casualspy.tictactoeandroid;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class Robot {
    Logique l;

    Joueur[] board;

    Robot(Logique l) {
        this.l = l;
        board = new Joueur[]{l.A1, l.A2, l.A3, l.B1, l.B2, l.B3, l.C1, l.C2, l.C3};
    }

    public int trouveMeilleureSolution() throws PositionInvalide {
        int[] grades = new int[9];
        for (int i = 0; i < board.length; i++){
            grades[i] = findGrade(board, l.joueurActif);
        }
        boolean hasFail = false;
        int pos = 0;
        for(int i = 0; i < grades.length; i++){
            if(grades[i] == 1)
                return i + 1;
            if(grades[i] == 0)
                pos = i;
        }
        return pos + 1;
    }

    public int findGrade(Joueur[] board, Joueur joueurActif) throws PositionInvalide {
        int[] grades = new int[9];
        for (int i = 0; i < 9; i++){
            Logique.Result r = getResult(i, board, joueurActif);
            if(r == Logique.Result.OWon)
                return 1;
            else if(r == Logique.Result.XWon || r == Logique.Result.AlreadyUsed)
                return -1;
            else if(r == Logique.Result.Tie)
                return 0;
            Joueur[] newBoard = board.clone();
            newBoard[i] = joueurActif;
            grades[i] = findGrade(newBoard, joueurActif == Joueur.X ? Joueur.O : Joueur.X);
        }
        boolean hasFail = false;
        for(int i = 0; i < grades.length; i++)
        {
            if(grades[i] == 1)
                return 1;
            if (grades[i] == -1)
                hasFail = true;
        }
        if(hasFail)
            return -1;
        return 0;
    }

    public Logique.Result getResult(int position, Joueur[] board, Joueur joueurActif) throws PositionInvalide {
        if (position < 1 || position > 9)
            throw new PositionInvalide();
        Joueur j = board[position];
        if (j == null) {

            board[position] = joueurActif;

            //Validation des lignes horizontales et verticales
            for (int i = 0; i < 3; i++) {
                Joueur[] ligneHorizontale = new Joueur[3];
                Joueur[] ligneVerticale = new Joueur[3];
                for (int k = 0; k < 3; k++) {
                    ligneHorizontale[k] = board[3 * i + k + 1];
                    ligneVerticale[k] = board[3 * k + i + 1];
                }
                if (ligneHorizontale[0] == ligneHorizontale[1] && ligneHorizontale[1] == ligneHorizontale[2] && ligneHorizontale[0] != null || ligneVerticale[0] == ligneVerticale[1] && ligneVerticale[1] == ligneVerticale[2] && ligneVerticale[0] != null) {
                    return joueurActif == Joueur.X ? Logique.Result.XWon : Logique.Result.OWon;
                }
            }

            //Validation des diagonales
            if (board[1] == board[5] && board[5] == board[9] && board[1] != null || board[3] == board[5] && board[5] == board[7] && board[3] != null) {
                return joueurActif == Joueur.X ? Logique.Result.XWon : Logique.Result.OWon;
            }

            //Vérification de grille pleine
            {
                boolean plein = true;
                for (int i = 1; i <= 9; i++) {
                    if (board[i] == null) {
                        plein = false;
                        break;
                    }
                }
                if (plein) {
                    return Logique.Result.Tie;
                }
            }
            return Logique.Result.Incomplete;
        }
        return Logique.Result.AlreadyUsed;
    }

    public static int[] RandomizeArray(int[] array){
        Random rgen = new Random();  // Random number generator

        for (int i=0; i<array.length; i++) {
            int randomPosition = rgen.nextInt(array.length);
            int temp = array[i];
            array[i] = array[randomPosition];
            array[randomPosition] = temp;
        }

        return array;
    }
}

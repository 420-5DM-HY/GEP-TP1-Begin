package com.example.casualspy.tictactoeandroid;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * Ce programme lance des parties et affiche le compte de parties gagnées et nulles.
 */
public class VerificationExhaustive implements EvenementsTTT {

    static int NbVictoireX = 0;
    static int NbVictoireO = 0;
    static int NbMatchNul = 0;

    static int[] ToutesLesCases = { 1,2,3,4,5,6,7,8,9};

    // États de la logique de jeu via ITicTacToeEventListener

    boolean matchNul;
    boolean gagne;

    HashSet<Integer> casesLibres;
    List<Integer> positionsJouéesParX;
    List<Integer> positionsJouéesParO;

    Logique logique;
    Robot robot;

    /**
     * Conçu pour être appelé récursiveemnt
     * On vérifie toute les possibilités de jeu à partir de l'état fourni
     *
     * @param positionsDejaJouéesParX
     * @throws PositionInvalide
     */
    public VerificationExhaustive(int[] positionsDejaJouéesParX) throws PositionInvalide {

        // initialisation
        matchNul = false;
        gagne = false;

        // on commence avec un jeu vide et on va rejouer la partie
        casesLibres = new HashSet<Integer>();


        this.positionsJouéesParX = new ArrayList<Integer>();
        this.positionsJouéesParO = new ArrayList<Integer>();

        // Nouvelle partie
        // Le reste du code suppose que X commence et IA est activé
        logique = new Logique(this,Joueur.X);
        robot = new Robot(logique);

        // Rejoue l'ébauche de partie reçue
        for (int position : positionsDejaJouéesParX)
        {
            positionsJouéesParX.add(position);
            logique.jouePosition(position);

            if(!gagne && !matchNul) {
                int r = robot.trouveMeilleureSolution();
                positionsJouéesParO.add(r);
                logique.jouePosition(r);
            }
        }

        for(int c : ToutesLesCases){
            if(caseVide(c)){
                casesLibres.add(Integer.valueOf(c));
            }
        }

        // Continue la partie ou affiche le bilan si terminé
        if (!casesLibres.isEmpty() && !gagne)
        {
            // Crée des scénarios de jeu pour chaque position restante
            for (int position : casesLibres)
            {
                List<Integer> Scenario = new ArrayList<Integer>();
                for(int p:positionsDejaJouéesParX){
                    Scenario.add(p);
                }
                Scenario.add(position);

                int[] suite = new int[Scenario.size()];
                for(int i=0;i<Scenario.size();i++){
                    suite[i]=Scenario.get(i);
                }
                System.out.print(".");
                new VerificationExhaustive(suite);
            }
        }
    }

    /**
     * Main : Lanceur de l'application
     * @param args : aucun argument nécessaire
     */
    public static void main(String[] args) throws PositionInvalide {

        // Lance des parties à partir de chaque position de la grille de jeu
        for (int position : ToutesLesCases)
        {
            int[] pointDeDepart = {position};

            new VerificationExhaustive( pointDeDepart);
        }
        System.out.println("Matchs nuls : " + NbMatchNul);
        System.out.println("Nombre de victoire de X : " + NbVictoireX);
        System.out.println("Nombre de victoire de O : " + NbVictoireO);

        if(NbVictoireX>0){
            System.out.println("\n\nIl vous reste du travail à faire :-(");
        }
        else{
            System.out.println("\n\nFélicitations, votre AI est infaillible!");
        }
    }


    @Override
    public void auTourDe(Joueur joueur) {
    }

    @Override
    public void aGagne(Joueur joueur) {
        if (Joueur.X == joueur)
        {
            NbVictoireX++;
            System.out.println("\nÇa ne devrait jamais arriver, mais votre AI à perdu!");
            System.out.println();
            // Énumère la séquence pour le joueur X de la partie qui vient de terminer
            System.out.print(" Jeu du vérificateur : X =");
            for(int position :  positionsJouéesParX){
                System.out.print(" "+position);
            }
            System.out.println();
            // Énumère la séquence pour le joueur O (AI) de la partie qui vient de terminer
            System.out.print("Réponses de votre AI : O = ");
            for (int position : positionsJouéesParO)
            {
                System.out.print(" "+position);
            }
            System.out.println();
        }
        if (Joueur.O == joueur)
        {
            NbVictoireO++;
        }
        gagne = true;
    }

    @Override
    public void cartePleine() {
        matchNul = true;
        NbMatchNul++;
    }

    @Override
    public void positionDejaOccuppee() {
        System.out.println("\npositionDejaOccuppee??? Votre AI se pile sur les pieds...");
        System.out.print(" Jeu du vérificateur : X =");
        for(int position :  positionsJouéesParX){
            System.out.print(" "+position);
        }
        System.out.println();
        // Énumère la séquence pour le joueur O (AI) de la partie qui vient de terminer
        System.out.print("Réponses de votre AI : O = ");
        for (int position : positionsJouéesParO)
        {
            System.out.print(" "+position);
        }
    }

    private boolean caseVide(int p){
        switch (p)
        {
            case 1: return logique.A1 == null;
            case 2: return logique.A2 == null;
            case 3: return logique.A3 == null;
            case 4: return logique.B1 == null;
            case 5: return logique.B2 == null;
            case 6: return logique.B3 == null;
            case 7: return logique.C1 == null;
            case 8: return logique.C2 == null;
            case 9: return logique.C3 == null;
        };
        return false;
    }

}

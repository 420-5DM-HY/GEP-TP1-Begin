package com.example.casualspy.tictactoeandroid;

public class Logique extends Modele {
    @Override
    public Joueur getJoueur(int position) throws PositionInvalide {
        switch (position) {
            case 1:
                return A1;
            case 2:
                return A2;
            case 3:
                return A3;
            case 4:
                return B1;
            case 5:
                return B2;
            case 6:
                return B3;
            case 7:
                return C1;
            case 8:
                return C2;
            case 9:
                return C3;
        }
        throw new PositionInvalide();
    }

    private void setJoueur(int position, Joueur joueur) {
        switch (position) {
            case 1:
                A1 = joueur;
                break;
            case 2:
                A2 = joueur;
                break;
            case 3:
                A3 = joueur;
                break;
            case 4:
                B1 = joueur;
                break;
            case 5:
                B2 = joueur;
                break;
            case 6:
                B3 = joueur;
                break;
            case 7:
                C1 = joueur;
                break;
            case 8:
                C2 = joueur;
                break;
            case 9:
                C3 = joueur;
                break;
        }
    }

    public void resetBoard() {
        for (int i = 1; i <= 9; i++)
            setJoueur(i, null);
    }

    @Override
    public void jouePosition(int position) throws PositionInvalide {
        switch (getResult(position)) {
            case AlreadyUsed:
                interfaceUtilisateur.positionDejaOccuppee();
                break;
            case Incomplete:
                interfaceUtilisateur.auTourDe(joueurActif);
                break;
            case OWon:
                interfaceUtilisateur.aGagne(Joueur.O);
                break;
            case XWon:
                interfaceUtilisateur.aGagne(Joueur.X);
                break;
            case Tie:
                interfaceUtilisateur.cartePleine();
                break;
        }
    }

    public Logique(EvenementsTTT interfaceUtilisateur, Joueur joueurActif) {
        super(interfaceUtilisateur, joueurActif);
        interfaceUtilisateur.auTourDe(joueurActif);
    }

    public Result getResult(int position) throws PositionInvalide {
        if (position < 1 || position > 9)
            throw new PositionInvalide();
        Joueur j = getJoueur(position);
        if (j == null) {

            setJoueur(position, joueurActif);

            //Validation des lignes horizontales et verticales
            for (int i = 0; i < 3; i++) {
                Joueur[] ligneHorizontale = new Joueur[3];
                Joueur[] ligneVerticale = new Joueur[3];
                for (int k = 0; k < 3; k++) {
                    ligneHorizontale[k] = getJoueur(3 * i + k + 1);
                    ligneVerticale[k] = getJoueur(3 * k + i + 1);
                }
                if (ligneHorizontale[0] == ligneHorizontale[1] && ligneHorizontale[1] == ligneHorizontale[2] && ligneHorizontale[0] != null || ligneVerticale[0] == ligneVerticale[1] && ligneVerticale[1] == ligneVerticale[2] && ligneVerticale[0] != null) {
                    return joueurActif == Joueur.X ? Result.XWon : Result.OWon;
                }
            }

            //Validation des diagonales
            if (getJoueur(1) == getJoueur(5) && getJoueur(5) == getJoueur(9) && getJoueur(1) != null || getJoueur(3) == getJoueur(5) && getJoueur(5) == getJoueur(7) && getJoueur(3) != null) {
                return joueurActif == Joueur.X ? Result.XWon : Result.OWon;
            }

            //Vérification de grille pleine
            {
                boolean plein = true;
                for (int i = 1; i <= 9; i++) {
                    if (getJoueur(i) == null) {
                        plein = false;
                        break;
                    }
                }
                if (plein) {
                    return Result.Tie;
                }
            }


            switch (joueurActif.toString()) {
                case "X":
                    joueurActif = Joueur.O;
                    return Result.Incomplete;
                case "O":
                    joueurActif = Joueur.X;
                    return Result.Incomplete;
            }
        }
        return Result.AlreadyUsed;
    }

    public enum Result {
        Incomplete,
        XWon,
        OWon,
        Tie,
        AlreadyUsed
    }
}

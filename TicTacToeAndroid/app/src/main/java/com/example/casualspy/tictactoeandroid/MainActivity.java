package com.example.casualspy.tictactoeandroid;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements EvenementsTTT {
    public static boolean reset = false;

    ImageView grille;
    ImageView A1;
    ImageView A2;
    ImageView A3;
    ImageView B1;
    ImageView B2;
    ImageView B3;
    ImageView C1;
    ImageView C2;
    ImageView C3;
    public static Logique l;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        grille = findViewById(R.id.imageView);
        A1 = findViewById(R.id.imageA1);
        A2 = findViewById(R.id.imageA2);
        A3 = findViewById(R.id.imageA3);
        B1 = findViewById(R.id.imageB1);
        B2 = findViewById(R.id.imageB2);
        B3 = findViewById(R.id.imageB3);
        C1 = findViewById(R.id.imageC1);
        C2 = findViewById(R.id.imageC2);
        C3 = findViewById(R.id.imageC3);
        if(l == null)
            l = new Logique(this, Joueur.X);
        A1.setImageResource(getImage(l.A1));    
        A2.setImageResource(getImage(l.A2));
        A3.setImageResource(getImage(l.A3));
        B1.setImageResource(getImage(l.B1));
        B2.setImageResource(getImage(l.B2));
        B3.setImageResource(getImage(l.B3));
        C1.setImageResource(getImage(l.C1));
        C2.setImageResource(getImage(l.C2));
        C3.setImageResource(getImage(l.C3));

        A1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                try {
                    resetBoard();
                    l.jouePosition(1);
                    if(l.A1 != null)
                        A1.setImageResource(l.A1 == Joueur.X ? R.drawable.x : R.drawable.o);
                } catch(PositionInvalide pos){
                    positionInvalide();
                }
            }
        });
        A2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                try {
                    resetBoard();
                    l.jouePosition(2);
                    if(l.A2 != null)
                        A2.setImageResource(l.A2 == Joueur.X ? R.drawable.x : R.drawable.o);
                } catch(PositionInvalide pos){
                    positionInvalide();
                }
            }
        });
        A3.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                try {
                    resetBoard();
                    l.jouePosition(3);
                    if(l.A3 != null)
                        A3.setImageResource(l.A3 == Joueur.X ? R.drawable.x : R.drawable.o);
                } catch(PositionInvalide pos){
                    positionInvalide();
                }
            }
        });
        B1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                try {
                    resetBoard();
                    l.jouePosition(4);
                    if(l.B1 != null)
                        B1.setImageResource(l.B1 == Joueur.X ? R.drawable.x : R.drawable.o);
                } catch(PositionInvalide pos){
                    positionInvalide();
                }
            }
        });
        B2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                try {
                    resetBoard();
                    l.jouePosition(5);
                    if(l.B2 != null)
                        B2.setImageResource(l.B2 == Joueur.X ? R.drawable.x : R.drawable.o);
                } catch(PositionInvalide pos){
                    positionInvalide();
                }
            }
        });
        B3.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                try {
                    resetBoard();
                    l.jouePosition(6);
                    if(l.B3 != null)
                        B3.setImageResource(l.B3 == Joueur.X ? R.drawable.x : R.drawable.o);
                } catch(PositionInvalide pos){
                    positionInvalide();
                }
            }
        });
        C1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                try {
                    resetBoard();
                    l.jouePosition(7);
                    if(l.C1 != null)
                        C1.setImageResource(l.C1 == Joueur.X ? R.drawable.x : R.drawable.o);
                } catch(PositionInvalide pos){
                    positionInvalide();
                }
            }
        });
        C2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                try {
                    resetBoard();
                    l.jouePosition(8);
                    if(l.C2 != null)
                        C2.setImageResource(l.C2 == Joueur.X ? R.drawable.x : R.drawable.o);
                } catch(PositionInvalide pos){
                    positionInvalide();
                }
            }
        });
        C3.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                try {
                    resetBoard();
                    l.jouePosition(9);
                    if(l.C3 != null)
                        C3.setImageResource(l.C3 == Joueur.X ? R.drawable.x : R.drawable.o);
                } catch(PositionInvalide pos){
                    positionInvalide();
                }
            }
        });
    }

    @Override
    public void auTourDe(Joueur joueur) {
        Toast.makeText(this, "Au tour de " + joueur.toString(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void aGagne(Joueur joueur) {
        Toast.makeText(this, joueur.toString() + " a gagné!", Toast.LENGTH_SHORT).show();
        reset = true;
    }

    @Override
    public void cartePleine() {
        Toast.makeText(this, "La carte est pleine, match nul!",Toast.LENGTH_SHORT).show();
        reset = true;
    }

    @Override
    public void positionDejaOccuppee() {
        Toast.makeText(this, "Cette position est déja occupée, réessayez.", Toast.LENGTH_SHORT).show();
    }

    public void positionInvalide(){
        Toast.makeText(this, "Position invalide, réessayez.", Toast.LENGTH_SHORT).show();
    }

    public void resetBoard(){
        if (reset){
            l.resetBoard();
            A1.setImageResource(R.drawable.vide);
            A2.setImageResource(R.drawable.vide);
            A3.setImageResource(R.drawable.vide);
            B1.setImageResource(R.drawable.vide);
            B2.setImageResource(R.drawable.vide);
            B3.setImageResource(R.drawable.vide);
            C1.setImageResource(R.drawable.vide);
            C2.setImageResource(R.drawable.vide);
            C3.setImageResource(R.drawable.vide);
            reset = false;
        }
    }

    public int getImage(Joueur j){
        if(j == null)
            return R.drawable.vide;
        return j == Joueur.X ? R.drawable.x : R.drawable.o;
    }
}

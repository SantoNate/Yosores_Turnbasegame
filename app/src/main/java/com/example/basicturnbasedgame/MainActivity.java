package com.example.basicturnbasedgame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

import pl.droidsonroids.gif.GifImageView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    //Hero stats
    String heroName = "Kael \"The one true Invoker\"";
    int heroMinDPT = 80;
    int heroMaxDPT = 150;
    int heroHP = 2000;

    //Monster stats
    String monsterName = "Wraith King";
    int monsterMinDPT = 30;
    int monsterMaxDPT = 130;
    int monsterHP = 1000;

    //Declarations
    TextView txtHeroName, txtHeroHP, txtHeroDPT, txtMonsterName, txtMonsterHP, txtMonsterDPT, txtCombatLog;
    Button nextTurn;
    
    int turn = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtCombatLog = findViewById(R.id.txtCombatLog);
        nextTurn = findViewById(R.id.btnAttack);
        nextTurn.setOnClickListener(this);

        txtHeroName = findViewById(R.id.heroTxt);
        txtHeroHP = findViewById(R.id.txtHeroHP);
        txtHeroDPT = findViewById(R.id.txtHeroDmg);

        txtMonsterName = findViewById(R.id.monsterTxt);
        txtMonsterHP = findViewById(R.id.txtMonstrHP);
        txtMonsterDPT = findViewById(R.id.txtMonstrDmg);

        txtHeroName.setText(heroName);
        txtHeroHP.setText(String.valueOf(heroHP));
        txtHeroDPT.setText("Dmg per turn: "+ heroMinDPT+ " - "+ heroMaxDPT);

        txtMonsterName.setText(monsterName);
        txtMonsterHP.setText(String.valueOf(monsterHP));
        txtMonsterDPT.setText("Dmg per turn "+ monsterMinDPT+ " - "+ monsterMaxDPT);

    }

    @Override
    public void onClick (View v) {

        Random rand = new Random();
        int heroDPT = rand.nextInt(heroMaxDPT - heroMinDPT) + heroMinDPT;
        int monsterDPT = rand.nextInt(monsterMaxDPT - monsterMinDPT) + monsterMinDPT;

        ImageView cancerhero = findViewById(R.id.cancerhero);
        ImageView invovo = findViewById(R.id.invovo );

        txtHeroHP.setText(String.valueOf(heroHP));
        txtMonsterHP.setText(String.valueOf(monsterHP));



        switch (v.getId()) {
            case R.id.btnAttack:
                if (turn%2 == 1) {
                    invovo.setVisibility(View.VISIBLE);
                    cancerhero.setVisibility(View.VISIBLE);
                    monsterHP = Math.max(0, monsterHP - heroDPT);
                    txtCombatLog.setText("Player dealt "+ heroDPT+ " dmg to "+ monsterName);
                    txtMonsterHP.setText(String.valueOf(monsterHP));
                    nextTurn.setText("Enemy's turn\nPress to proceed");
                    turn++;
                    if (monsterHP == 0) {
                        cancerhero.setVisibility(View.INVISIBLE);
                        turn = 1;
                        heroHP = 1000;
                        monsterHP = 950;
                        txtCombatLog.setText("Invoker dealt "+ heroDPT+ " dmg to "+ monsterName+ ". Winner, winner, chicken dinner.");
                        nextTurn.setText("Reset");
                    }
                }
                else if (turn%2 != 1) {
                    invovo.setVisibility(View.VISIBLE);
                    cancerhero.setVisibility(View.VISIBLE);
                    heroHP = Math.max(0, heroHP - monsterDPT);
                    txtCombatLog.setText(monsterName+ " dealt "+ monsterDPT+ " dmg to "+ heroName);
                    txtHeroHP.setText(String.valueOf(heroHP));
                    nextTurn.setText("Attack");
                    turn++;
                    if (heroHP == 0) {
                        invovo.setVisibility(View.INVISIBLE);
                        turn = 1;
                        heroHP = 1000;
                        monsterHP = 950;
                        txtCombatLog.setText(monsterName+ " dealt "+ monsterDPT+ " dmg to "+ heroName+ ". Get rekt looser lol.");
                        nextTurn.setText("Reset");
                    }
                }
                break;
        }


    }
}
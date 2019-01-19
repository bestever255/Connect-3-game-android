package com.example.bestever.connect3;

import android.animation.ObjectAnimator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    // 0 = blue , 1 = red
    int activePlayer = 0;

    boolean gameIsActive = true;

    // 2 means unPlayed
    int[] gameState = {2,2,2,2,2,2,2,2,2};

    boolean flag = false;
    int [][] winningPositions = {{0 , 1 , 2} , {3 , 4 , 5} , {6 , 7 , 8} , {0 , 3 , 6} , {1 , 4, 7} , {2 , 5 , 8} , { 0 , 4 , 8} , {2 , 4 , 6}};


    public void DropIn(View view){
        ImageView counter = (ImageView)view;
        counter.getTag();

        int tappedCounter = Integer.parseInt(counter.getTag().toString());

        if(gameState[tappedCounter] == 2 && gameIsActive) {

            gameState[tappedCounter] = activePlayer;

            counter.setTranslationY(-1000f);

            if (activePlayer == 0) {

                counter.setImageResource(R.drawable.blue);

                activePlayer = 1;

            } else {
                counter.setImageResource(R.drawable.red);

                activePlayer = 0;
            }

            counter
                    .animate()
                    .translationYBy(1000f)
                    .setDuration(300);


            for(int[] winningPosition : winningPositions ){

                if(gameState[winningPosition[0]] == gameState[winningPosition[1]]
                        && gameState[winningPosition[1]] == gameState[winningPosition[2]]
                        && gameState[winningPosition[0]] != 2){

//                    if(activePlayer == 1){
//                        Toast.makeText(MainActivity.this , "Blue Won!" , Toast.LENGTH_LONG).show();
//                    }else if(activePlayer == 0){
//                        Toast.makeText(MainActivity.this , "Red Won!" , Toast.LENGTH_LONG).show();
//
//                    }

                    // someone Has Won!

                    gameIsActive = false;

                    TextView winnerMessage  = (TextView)findViewById(R.id.winnerMessage);

                    if(activePlayer == 0){
                        flag = true;
                    }else if(activePlayer == 1){
                        flag = false;
                    }

                    if(flag){
                        winnerMessage.setText("Red Has Won!");
                    }else{
                        winnerMessage.setText("Blue Has Won");

                    }

                    LinearLayout layout = (LinearLayout)findViewById(R.id.playAgainLayout);
                    layout.setVisibility(View.VISIBLE);
                    layout.animate().rotation(360).setDuration(1000);



                }else{
                    boolean gameIsOver = true;

                    for(int counterState : gameState){
                        if(counterState == 2)gameIsOver = false;
                    }
                    if(gameIsOver){
                        LinearLayout layout = (LinearLayout)findViewById(R.id.playAgainLayout);
                        layout.setVisibility(View.VISIBLE);
                        layout.animate().rotation(360).setDuration(1000);


                        TextView winnerMessage  = (TextView)findViewById(R.id.winnerMessage);

                        winnerMessage.setText("It's a Draw!");
                    }
                }


            }

        }
    }

    public void playAgain(View view){

        Button btn = (Button)findViewById(R.id.PlayAgain);


        ObjectAnimator.ofFloat(btn, "alpha", 1f).setDuration(100).start();


        gameIsActive = true;

        LinearLayout layout = (LinearLayout)findViewById(R.id.playAgainLayout);

        layout.setVisibility(View.INVISIBLE);


        activePlayer = 0;

         for(int i = 0 ; i < gameState.length ; i++){
             gameState[i] = 2;
         }

        GridLayout gridLayout = (GridLayout)findViewById(R.id.gridLayout);

         for(int i = 0 ; i < gridLayout.getChildCount(); i++){
             ((ImageView)gridLayout.getChildAt(i)).setImageResource(0);
         }


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}

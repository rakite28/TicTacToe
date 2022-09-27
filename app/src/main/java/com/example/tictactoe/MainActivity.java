package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;
import androidx.gridlayout.widget.GridLayout;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {
    //0 for yellow 1 for red 2 for empty
    int activePlayer=0;
    int[] gameState={2,2,2,2,2,2,2,2,2};
    int[][] winningPositions= {{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};
    int tappedCounter=0;
    boolean gameActive=true;

    public void dropIn(View view){
        ImageView counter = (ImageView)view;
        tappedCounter = Integer.parseInt(counter.getTag().toString());
        if(gameActive&&gameState[tappedCounter]==2){
            gameState[tappedCounter]=activePlayer;
            counter.setTranslationY(-1500);
            if(activePlayer==0){
                counter.setImageResource(R.drawable.yellow);
                activePlayer=1;
            }
            else {
                counter.setImageResource(R.drawable.red);
                activePlayer=0;
            }

            counter.animate().translationYBy(1500).rotation(3600).setDuration(300);
            String winner="";
            TextView winnerTextView = (TextView)findViewById(R.id.winnerTextView);
            Button playAgainButton = (Button)findViewById(R.id.playAgainButton);
            for(int[] winningPosition:winningPositions){
                if(gameState[winningPosition[0]]==gameState[winningPosition[1]]&&gameState[winningPosition[1]]==gameState[winningPosition[2]]&&gameState[winningPosition[0]]!=2){
                    //somebody has won
                    gameActive=false;
                    if(activePlayer==1){
                        winner="Yellow";
                    }
                    else{
                        winner="Red";
                    }


                    winnerTextView.setVisibility(View.VISIBLE);
                    playAgainButton.setVisibility(View.VISIBLE);
                    winnerTextView.setText(winner+" Has Won");

                }
                else if(gameState[0]!=2&&gameState[1]!=2&&gameState[2]!=2&&gameState[3]!=2&&gameState[4]!=2&&gameState[5]!=2&&gameState[6]!=2&&gameState[7]!=2&&gameState[8]!=2){
                    winnerTextView.setVisibility(View.VISIBLE);
                    playAgainButton.setVisibility(View.VISIBLE);
                    winnerTextView.setText("Nobody Has Won");
                }

            }




        }
    }
    public void playAgain(View view){

        TextView winnerTextView = (TextView)findViewById(R.id.winnerTextView);
        Button playAgainButton = (Button)findViewById(R.id.playAgainButton);
        winnerTextView.setVisibility(View.INVISIBLE);
        playAgainButton.setVisibility(View.INVISIBLE);
        gameActive=true;
        for(int i=0;i<gameState.length;i++){
            gameState[i]=2;
        }
        Log.i("Info","before grid");
        Log.i("Info","after grid");
        GridLayout gridLayout = (GridLayout)findViewById(R.id.gridLayout);
        for(int i = 0;i<gridLayout.getChildCount();i++){
            ImageView counter =(ImageView)gridLayout.getChildAt(i);
            counter.setImageResource(0);
            counter.setImageDrawable(null);
        }
        activePlayer=0;


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
package com.codellion.connectfour;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.security.PublicKey;

public class MainActivity extends AppCompatActivity {

    //0 is yellow, 1 is red, 2 is empty
    int[] gameState = {2, 2, 2, 2, 2, 2, 2, 2, 2, 2};
    int[][] winningPositions = {{1,2,3}, {4,5,6}, {7,8,9}, {1,4,7}, {2,5,8}, {3,6,9}, {1,5,9}, {3,5,7}};
    byte activePlayer = 0;
    boolean gameActive = true;
    int gridCounter = 0;

    public void dropInView(View view){
        ImageView counter = (ImageView) view;
        gridCounter++;
        Log.i("TAG:", counter.getTag().toString());
        Log.i("TAG2:", Integer.toString(gridCounter));
        int tappedCounter = Integer.parseInt(counter.getTag().toString());
        if(gameState[tappedCounter] == 2 && gridCounter !=9 &&gameActive) {
            gameState[tappedCounter] = activePlayer;
            counter.setTranslationY(-1500);
            if (activePlayer == 0) {
                counter.setImageResource(R.drawable.yellow);
                activePlayer = 1;
            } else {
                counter.setImageResource(R.drawable.red);
                activePlayer = 0;
            }
            counter.animate().translationYBy(1500).rotation(3600).setDuration(500);
            for (int[] winningPosition : winningPositions) {
                if ((gameState[winningPosition[0]] == gameState[winningPosition[1]]) &&
                        (gameState[winningPosition[1]] == gameState[winningPosition[2]]) &&
                        (gameState[winningPosition[0]] != 2) && gridCounter !=9) {
                    gameActive = false;
                    String winner;
                    if(activePlayer == 1){
                        winner = "Yellow has won!";
                        gridCounter = 0;
                    }
                    else
                    {
                        winner = "Red has won!";
                        gridCounter = 0;
                    }
                    Button playAgainButton = (Button) findViewById(R.id.playAgain);
                    TextView result = (TextView) findViewById(R.id.result);
                    result.setText(winner);
                    playAgainButton.setVisibility(View.VISIBLE);
                    result.setVisibility(View.VISIBLE);
                }
            }
        }
        else if(gridCounter == 9){
            gridCounter = 0;
            Button playAgainButton = (Button) findViewById(R.id.playAgain);
            TextView result = (TextView) findViewById(R.id.result);
            String winner = "It's a tie";
            result.setText(winner);
            playAgainButton.setVisibility(View.VISIBLE);
            result.setVisibility(View.VISIBLE);
        }

    }

    public void playAgain(View view){
        gridCounter = 0;
        Button playAgainButton = (Button) findViewById(R.id.playAgain);
        TextView result = (TextView) findViewById(R.id.result);
        playAgainButton.setVisibility(View.INVISIBLE);
        result.setVisibility(View.INVISIBLE);

        androidx.gridlayout.widget.GridLayout gridView = findViewById(R.id.gridLayout);
        for(int i = 0; i < gridView.getChildCount(); i++){
            ImageView counter = (ImageView) gridView.getChildAt(i);
            counter.setImageDrawable(null);
        }

        for (int i = 0; i < gameState.length; i++){
            gameState[i] = 2;
        }
        activePlayer = 0;
        gameActive = true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}

package com.example.rockpaperscissors;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class game extends AppCompatActivity {
    private Handler mHandler = new Handler();

    ImageButton userSelectionImg, robSelectionImg, playagain, back;
    TextView userScoreTxt, robScoreTxt;
    Button resetBtn;

    int userScore = 0, robScore = 0;

    boolean next = false;

    Random random;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        userSelectionImg  = findViewById(R.id.userSelectionImg);
        robSelectionImg = findViewById(R.id.robSelectionImg);
        userScoreTxt = findViewById(R.id.userScoreTxt);
        robScoreTxt = findViewById(R.id.robScoreTxt);
        resetBtn = findViewById(R.id.resetBtn);

        userSelectionImg.setBackgroundResource(R.drawable.transparent);
        robSelectionImg.setBackgroundResource(R.drawable.transparent);

        random = new Random();

        playagain = findViewById(R.id.playagain);
        back = findViewById(R.id.back);

    }

    public void resetBtn(View view) {

        userScore = 0;
        robScore = 0;
        userSelectionImg.setBackgroundResource(R.drawable.transparent);
        robSelectionImg.setBackgroundResource(R.drawable.transparent);
        setRobScore(robScore);
        setUserScore(userScore);
    }

    private Runnable mShowDialog = new Runnable() {
        @Override
        public void run() {
            showDialog();
            showDialog2();
            showDialog3();
        }
    };

    private void showDialog() {
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.popup);

        ImageView playAgain = dialog.findViewById(R.id.playagain);

        playAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                next = false;
                userSelectionImg.setBackgroundResource(R.drawable.transparent);
                robSelectionImg.setBackgroundResource(R.drawable.transparent);
            }
        });
        dialog.show();
    }

    private void showDialog2() {
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.popup1);

        ImageView playAgain = dialog.findViewById(R.id.playagain);

        playAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                next = false;
                dialog.dismiss();
                userSelectionImg.setBackgroundResource(R.drawable.transparent);
                robSelectionImg.setBackgroundResource(R.drawable.transparent);
            }
        });
        dialog.show();
    }

    private void showDialog3() {
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.popup2);

        ImageView playAgain = dialog.findViewById(R.id.playagain);

        playAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                next = false;
                userSelectionImg.setBackgroundResource(R.drawable.transparent);
                robSelectionImg.setBackgroundResource(R.drawable.transparent);
            }
        });
        dialog.show();
    }

    public void rpsButtonSelected(View view) {

    if (!next) {
        int userSelection = Integer.parseInt(view.getTag().toString());
        Log.i(TAG, "rpsButtonSelected: " + userSelection);
        matchGame(userSelection);
        next = true;
         }
    }

    public void back(View view){

        Intent neke = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(neke);

    }

    private void matchGame(int userSelection) {

        int low = 1;
        int high = 3;

        int robSelection = random.nextInt(high) + low;

        if (userSelection == robSelection) {
            //izenaceno
            mHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    showDialog();
                }
            }, 700);
        } else if ((userSelection - robSelection) % 3 == 1) {
            //igralec je zmago
            mHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    showDialog2();
                    userScore++;
                }
            }, 700);
        } else {
            //robot je zmago
            mHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    showDialog3();
                    robScore++;
                }
            }, 700);
        }

            switch (userSelection) {
                case 1:
                    userSelectionImg.setBackgroundResource(R.drawable.rock);
                    break;
                case 2:
                    userSelectionImg.setBackgroundResource(R.drawable.paper);
                    break;
                case 3:
                    userSelectionImg.setBackgroundResource(R.drawable.scissors);
                    break;
            }


        switch (robSelection) {
            case 1:
                robSelectionImg.setBackgroundResource(R.drawable.rock);
                break;
            case 2:
                robSelectionImg.setBackgroundResource(R.drawable.paper);
                break;
            case 3:
                robSelectionImg.setBackgroundResource(R.drawable.scissors);
                break;
        }

        setUserScore(userScore);
        setRobScore(robScore);

    }

    private void setUserScore(int userScore) {

        userScoreTxt.setText(String.valueOf(userScore));

    }

    private void setRobScore(int robScore) {

        robScoreTxt.setText(String.valueOf(robScore));

    }

}
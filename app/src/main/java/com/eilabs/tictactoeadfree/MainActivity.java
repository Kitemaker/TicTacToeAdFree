package com.eilabs.tictactoeadfree;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RadioButton;
import android.widget.Switch;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private int clickCounter=0;
    private static final String TAG = MainActivity.class.getSimpleName();
    private String textGrid[]= {"1","1","1","1","1","1","1","1","1"};
    private  boolean gameOver=false;
    private String WinCombo="0-0-0";
    private boolean startWithX=true;
    private int scoreX=0;
    private int scoreO=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void OnTileClicked(View view)
    {
        if (gameOver == false) {
            TextView tview = (TextView) view;
            if (tview.getText().equals(getString(R.string.text_empty))) {
                clickCounter = clickCounter + 1;

                switch (clickCounter) {
                    case 1:
                    case 3:
                    case 5:
                    case 7:
                    case 9:
                        if(startWithX) {
                            WriteX(tview);
                        }
                        else {
                            WriteZero(tview);
                        }
                        break;

                    case 2:
                    case 4:
                    case 6:
                    case 8:
                        if(startWithX) {
                            WriteZero(tview);
                        }
                        else {
                            WriteX(tview);
                        }
                        break;

                    default:
                        Log.d(TAG, "Invalid Count");
                        break;

                }
                if (clickCounter == 5 || clickCounter == 7 || clickCounter == 9) {
                    if (startWithX == true) {
                        checkForGame(getString(R.string.text_cross));
                    } else {
                        checkForGame(getString(R.string.text_zero));
                    }
                } else if (clickCounter == 6 || clickCounter == 8)

                {
                    if (startWithX == true) {
                        checkForGame(getString(R.string.text_zero));
                    } else {
                        checkForGame(getString(R.string.text_cross));
                    }
                }
            }
        }
    }

    public void checkForGame(String tbvalue)
    {

        TextView resultView=(TextView)(findViewById( R.id.textViewResult))  ;
        try
        {
            if (textGrid[0].equals(tbvalue) && textGrid[1].equals(tbvalue) && textGrid[2].equals(tbvalue)) {
                gameOver = true;
                WinCombo="0-1-2";
            } else if (textGrid[3].equals(tbvalue) && textGrid[4].equals(tbvalue) && textGrid[5].equals(tbvalue)) {
                gameOver = true;
                WinCombo="3-4-5";
            } else if (textGrid[6].equals(tbvalue) && textGrid[7].equals(tbvalue) && textGrid[8].equals(tbvalue)) {
                gameOver = true;
                WinCombo="6-7-8";
            } else if (textGrid[0].equals(tbvalue) && textGrid[3].equals(tbvalue) && textGrid[6].equals(tbvalue)) {
                gameOver = true;
                WinCombo="0-3-6";
            } else if (textGrid[1].equals(tbvalue) && textGrid[4].equals(tbvalue) && textGrid[7].equals(tbvalue)) {
                gameOver = true;
                WinCombo="1-4-7";
            } else if (textGrid[2].equals(tbvalue) && textGrid[5].equals(tbvalue) && textGrid[8].equals(tbvalue)) {
                gameOver = true;
                WinCombo="2-5-8";
            } else if (textGrid[0].equals(tbvalue) && textGrid[4].equals(tbvalue) && textGrid[8].equals(tbvalue)) {
                gameOver = true;
                WinCombo="0-4-8";
            } else if (textGrid[2].equals(tbvalue) && textGrid[4].equals(tbvalue) && textGrid[6].equals(tbvalue)) {
                gameOver = true;
                WinCombo="2-4-6";
            } else {
                gameOver = false;
            }

            if (gameOver == true) {
                resultView.setText("Player \' " + tbvalue + "\' Wins");
                resultView.setBackgroundColor(0xffb8ffe9);
                colorTextBox(WinCombo);
                if(tbvalue==getString(R.string.text_cross))
                {
                    scoreX=scoreX+1;
                }
                if(tbvalue==getString(R.string.text_zero))
                {
                    scoreO=scoreO+1;
                }

                UpdateScore();


            }
            if(clickCounter>=9 && gameOver==false)
            {
                resultView.setText(getString(R.string.text_GameOver));
            }
        }
        catch (Exception e)
        {
            Log.d(TAG,e.getMessage().toString());
        }

    }

    private void UpdateScore() {
        TextView textViewScX=(TextView)findViewById(R.id.textViewScoreX);
        TextView textViewScrO=(TextView)findViewById(R.id.textViewScoreO);
        textViewScX.setText(Integer.toString(scoreX));
        textViewScrO.setText(Integer.toString(scoreO));
    }

    public void WriteX(TextView view)
    {
        view.setText(getString(R.string.text_cross));
        String tboxName[]=getResources().getResourceName(view.getId()).split("_");

        textGrid[Integer.valueOf(tboxName[1])]=getString(R.string.text_cross);
        // Log.d(TAG,"Value of TextBox"+getResources().getResourceName(view.getId())+ "X");
    }
    public void WriteZero(TextView view)
    {
        view.setText(getString(R.string.text_zero));
        String tboxName[]=getResources().getResourceName(view.getId()).split("_");

        textGrid[Integer.valueOf(tboxName[1])]=getString(R.string.text_zero);
        //  Log.d(TAG,"Value of TextBox"+getResources().getResourceName(view.getId())+ "0");

    }

    public void ResetScore(View view)
    {
        scoreX = 0;
        scoreO =0;
        UpdateScore();
    }

    public void ClearAll(View view)
    {
        TextView resultView=(TextView)(findViewById( R.id.textViewResult))  ;
        resultView.setText(R.string.text_empty);
        resultView.setBackgroundColor(Color.TRANSPARENT);
        clickCounter=0;
        TextView tview;
        gameOver=false;
        for(int i=0;i<9;i++)
        {
            textGrid[i]=getString(R.string.text_empty);
        }

        tview=(TextView)findViewById(R.id.textView_0);
        tview.setText(R.string.text_empty);
        tview=(TextView)findViewById(R.id.textView_1);
        tview.setText(R.string.text_empty);
        tview=(TextView)findViewById(R.id.textView_2);
        tview.setText(R.string.text_empty);
        tview=(TextView)findViewById(R.id.textView_3);
        tview.setText(R.string.text_empty);
        tview=(TextView)findViewById(R.id.textView_4);
        tview.setText(R.string.text_empty);
        tview=(TextView)findViewById(R.id.textView_5);
        tview.setText(R.string.text_empty);
        tview=(TextView)findViewById(R.id.textView_6);
        tview.setText(R.string.text_empty);
        tview=(TextView)findViewById(R.id.textView_7);
        tview.setText(R.string.text_empty);
        tview=(TextView)findViewById(R.id.textView_8);
        tview.setText(R.string.text_empty);


    }
    public void colorTextBox(String winCombo)
    {TextView tbView;

        switch (winCombo)
        {
            case "0-1-2":
                // tbView=(TextView)findViewById(R.id.textView_0).setBackground;
                break;
            case "3-4-5":
                break;
            case "6-7-8":
                break;
            case "0-3-6":
                break;
            case "1-4-7":
                break;
            case "2-5-8":
                break;
            case "0-4-8":
                break;
            case "2-4-6":

        }
    }
    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.radioButtonX:
                if (checked)
                    startWithX=true;
                break;
            case R.id.radioButtonO:
                if (checked)
                    startWithX=false;
                break;
        }
    }

}

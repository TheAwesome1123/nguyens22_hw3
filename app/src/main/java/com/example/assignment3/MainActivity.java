package com.example.assignment3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.gridlayout.widget.GridLayout;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import java.util.Random;

/**
 * Represents the game 15 Squares.
 * By Samuel Nguyen, 11/2/19
 * Note: the GridLayout feature was found by searching for it in the palette in activity_main.xml.
 * Note: moving pieces does not work fully, and crashes can occur.
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Creates objects and sets listeners
        TextView win = (TextView) findViewById(R.id.winMessage);
        Button button1 = (Button) findViewById(R.id.button1);
        Button button2 = (Button) findViewById(R.id.button2);
        Button button3 = (Button) findViewById(R.id.button3);
        Button button4 = (Button) findViewById(R.id.button4);
        Button button5 = (Button) findViewById(R.id.button5);
        Button button6 = (Button) findViewById(R.id.button6);
        Button button7 = (Button) findViewById(R.id.button7);
        Button button8 = (Button) findViewById(R.id.button8);
        Button button9 = (Button) findViewById(R.id.button9);
        Button button10 = (Button) findViewById(R.id.button10);
        Button button11 = (Button) findViewById(R.id.button11);
        Button button12 = (Button) findViewById(R.id.button12);
        Button button13 = (Button) findViewById(R.id.button13);
        Button button14 = (Button) findViewById(R.id.button14);
        Button button15 = (Button) findViewById(R.id.button15);
        Button empty = (Button) findViewById(R.id.buttonEmpty);
        Button reset = (Button) findViewById(R.id.resetButton);

        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);
        button4.setOnClickListener(this);
        button5.setOnClickListener(this);
        button6.setOnClickListener(this);
        button7.setOnClickListener(this);
        button8.setOnClickListener(this);
        button9.setOnClickListener(this);
        button10.setOnClickListener(this);
        button11.setOnClickListener(this);
        button12.setOnClickListener(this);
        button13.setOnClickListener(this);
        button14.setOnClickListener(this);
        button15.setOnClickListener(this);
        empty.setOnClickListener(this);
        reset.setOnClickListener(this);

        randomize();

        // Changes color of win message depending on current state
        int rightSpot = Color.argb(255, 0, 130, 0);
        win.setTextColor(Color.argb(0, 0, 130, 0));

        if(checkIfRightSpot() == 16) {
            win.setTextColor(rightSpot);
        }
    }

    /** External Citation
     * Date: 2 November 2019
     * Problem: I needed to iterate through the GridLayout.
     * Resource: https://stackoverflow.com/questions/25279904/android-get-all-children-of-a-gridlayout
     * Solution: I used getChildCount() and getChildAt().
     */

    /** External Citation
     * Date: 2 November 2019
     * Problem: I needed to know how to randomize Button locations.
     * Resource: https://stackoverflow.com/questions/31235763/random-children-placement-in-gridlayout
     * Solution: I used removeViewAt() and addView().
     */

    /**
     * Randomizes the location of each button
     */
    public void randomize() {
        GridLayout layout = (GridLayout) findViewById(R.id.gameGrid);
        Random generator = new Random();

        // Removes each child and places it at new index
        for(int i = 0; i < layout.getChildCount(); i++) {
            int newNum = generator.nextInt(layout.getChildCount());
            View button = layout.getChildAt(i);
            layout.removeViewAt(i);
            layout.addView(button, newNum);
        }
        checkIfRightSpot();
    }

    /**
     * Takes action depending on which button is pressed
     * @param v: the view being pressed
     */
    public void onClick (View v) {
        if(v.getId() == R.id.resetButton) {
            randomize();
        }
        else if(v.getId() != R.id.buttonEmpty) {
            movePiece(v);
        }
    }

    /**
     * Moves clicked Button to index of empty Button, and vice versa
     * @param v: the View that was clicked
     */
    public void movePiece(View v) {
        GridLayout layout = findViewById(R.id.gameGrid);
        Button empty = findViewById(R.id.buttonEmpty);
        // Iterate through GridLayout and only take action if "current" View is the clicked Button
        for(int i = 0; i < layout.getChildCount(); i++) {
            View current = layout.getChildAt(i);
            if(current == v) {
                // Top left corner
                if(i == 0) {
                    if(layout.getChildAt(i + 1) == empty) {
                        swapButtons(layout, current, empty, i, i + 1);
                    }
                    else if(layout.getChildAt(i + 4) == empty) {
                        swapButtons(layout, current, empty, i, i + 4);
                    }
                }
                // Top right corner
                else if(i == 3) {
                    if(layout.getChildAt(i - 1) == empty) {
                        swapButtons(layout,current, empty, i, i - 1);
                    }
                    else if(layout.getChildAt(i + 4) == empty) {
                        swapButtons(layout, current, empty, i, i + 4);
                    }
                }
                // Bottom left corner
                else if(i == 12) {
                    if(layout.getChildAt(i - 4) == empty) {
                        swapButtons(layout, current, empty, i, i - 4);
                    }
                    else if(layout.getChildAt(i + 1) == empty) {
                        swapButtons(layout, current, empty, i, i + 1);
                    }
                }
                // Bottom right corner
                else if(i == 15) {
                    if(layout.getChildAt(i - 1) == empty) {
                        swapButtons(layout, current, empty, i, i - 1);
                    }
                    else if(layout.getChildAt(i - 4) == empty) {
                        swapButtons(layout, current, empty, i, i - 4);
                    }
                }
                // Top edge
                else if(i == 1 || i == 2) {
                    if(layout.getChildAt(i - 1) == empty) {
                        swapButtons(layout, current, empty, i, i - 1);
                    }
                    else if(layout.getChildAt(i + 1) == empty) {
                        swapButtons(layout, current, empty, i, i + 1);
                    }
                    else if(layout.getChildAt(i + 4) == empty) {
                        swapButtons(layout, current, empty, i, i + 4);
                    }
                }
                // Left edge
                else if(i == 4 || i == 8) {
                    if(layout.getChildAt(i + 1) == empty) {
                        swapButtons(layout, current, empty, i, i + 1);
                    }
                    else if(layout.getChildAt(i + 4) == empty) {
                        swapButtons(layout, current, empty, i, i + 4);
                    }
                    else if(layout.getChildAt(i - 4) == empty) {
                        swapButtons(layout, current, empty, i, i - 4);
                    }
                }
                // Right edge
                else if(i == 7 || i == 11) {
                    if(layout.getChildAt(i - 1) == empty) {
                        swapButtons(layout, current, empty, i, i - 1);
                    }
                    else if(layout.getChildAt(i + 4) == empty) {
                        swapButtons(layout, current, empty, i, i + 4);
                    }
                    else if(layout.getChildAt(i - 4) == empty) {
                        swapButtons(layout, current, empty, i, i - 4);
                    }
                }
                // Bottom edge
                else if(i == 13 || i == 14) {
                    if(layout.getChildAt(i + 1) == empty) {
                        swapButtons(layout, current, empty, i, i + 1);
                    }
                    else if(layout.getChildAt(i - 4) == empty) {
                        swapButtons(layout, current, empty, i, i - 4);
                    }
                    else if(layout.getChildAt(i - 1) == empty) {
                        swapButtons(layout, current, empty, i, i - 1);
                    }
                }
                // Middle
                else {
                    if(layout.getChildAt(i + 1) == empty) {
                        swapButtons(layout, current, empty, i, i + 1);
                    }
                    else if(layout.getChildAt(i - 1) == empty) {
                        swapButtons(layout, current, empty, i, i - 1);
                    }
                    else if(layout.getChildAt(i + 4) == empty) {
                        swapButtons(layout, current, empty, i, i + 4);
                    }
                    else if(layout.getChildAt(i - 4) == empty) {
                        swapButtons(layout, current, empty, i, i - 4);
                    }
                }
            }
        }
    }

    /**
     * Swaps the clicked and empty Buttons
     * @param layout: the GridLayout being used
     * @param button: the clicked Button
     * @param empty: the empty Button
     * @param buttonIndex: the index of the clicked Button before swapping
     * @param emptyIndex: the index of the empty Button before swapping
     */
    public void swapButtons(GridLayout layout, View button, View empty, int buttonIndex, int emptyIndex) {
        layout.removeView(button);
        layout.removeView(empty);
        layout.addView(empty, buttonIndex);
        layout.addView(button, emptyIndex);
    }

    /**
     * Checks if all buttons are in the spot that wins the game for the player
     * @return total number of Buttons in right spot
     */
    public int checkIfRightSpot() {
        GridLayout layout = findViewById(R.id.gameGrid);
        int totalRight = 0;
        Button button1 = (Button) findViewById(R.id.button1);
        Button button2 = (Button) findViewById(R.id.button2);
        Button button3 = (Button) findViewById(R.id.button3);
        Button button4 = (Button) findViewById(R.id.button4);
        Button button5 = (Button) findViewById(R.id.button5);
        Button button6 = (Button) findViewById(R.id.button6);
        Button button7 = (Button) findViewById(R.id.button7);
        Button button8 = (Button) findViewById(R.id.button8);
        Button button9 = (Button) findViewById(R.id.button9);
        Button button10 = (Button) findViewById(R.id.button10);
        Button button11 = (Button) findViewById(R.id.button11);
        Button button12 = (Button) findViewById(R.id.button12);
        Button button13 = (Button) findViewById(R.id.button13);
        Button button14 = (Button) findViewById(R.id.button14);
        Button button15 = (Button) findViewById(R.id.button15);
        Button empty = (Button) findViewById(R.id.buttonEmpty);

        Button[] buttons = new Button[16];
        buttons[0] = button1;
        buttons[1] = button2;
        buttons[2] = button3;
        buttons[3] = button4;
        buttons[4] = button5;
        buttons[5] = button6;
        buttons[6] = button7;
        buttons[7] = button8;
        buttons[8] = button9;
        buttons[9] = button10;
        buttons[10] = button11;
        buttons[11] = button12;
        buttons[12] = button13;
        buttons[13] = button14;
        buttons[14] = button15;
        buttons[15] = empty;

        // Sets text to green if the button is in the right spot
        // https://www.rapidtables.com/web/color/green-color.html
        for(int i =0; i < buttons.length; i++) {
            View currentButton = layout.getChildAt(i);
            if(currentButton == buttons[i]) {
                buttons[i].setTextColor(Color.argb(255, 0, 130, 0));
                totalRight++;
            }
            else {
                buttons[i].setTextColor(Color.BLACK);
            }
        }
        return totalRight;
    }
}

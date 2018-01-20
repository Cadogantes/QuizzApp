package com.example.android.quizzapp;

import android.app.FragmentManager;
import android.app.MediaRouteButton;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    //view declarations
    TextView dummy1;
    TextView question;
    ImageView creature_image;
    EditText name_field;
    RelativeLayout mainLayout;
    Button button_1;
    Button button_2;
    Button button_3;
    LinearLayout summaryExplanations;
    TextView summary;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //views initialization
        dummy1 = (TextView) findViewById(R.id.dummy_view_1);
        question = (TextView) findViewById(R.id.text_view);
        creature_image = (ImageView) findViewById(R.id.question_image);
        name_field = (EditText) findViewById(R.id.name_field);
        mainLayout = (RelativeLayout) findViewById(R.id.main_layout);
        button_1 = findViewById(R.id.button_1);
        button_2 = findViewById(R.id.button_2);
        button_3 = findViewById(R.id.button_3);
        summaryExplanations = (LinearLayout) findViewById(R.id.summary_layout);
        summary = (TextView) findViewById(R.id.text_view);

    }

    /* this variable will indicate in which phase of the quiz we currently are*/
    int state = 0;
    int score;
    String username;
    /* these variables will determine which questions have been answered correctly*/
    boolean[] correct_answer = new boolean[5];    //this is a tricky part. I use array to be able to quickly check all of them later

    //these functions will decide what will be the consequence of pushing buttons
    //Also - here we decide which answers are correct
    public void button_1_action(View view) {
        if (state == 0) {
            if (checkName(view) == true)  //if user has input name go to question 1
                toQuestion1(view);
        } else if (state == 1) {
            score++;
            correct_answer[0] = true;
            toQuestion2(view);
        } else if (state == 2) {
            toQuestion3(view);
        } else if (state == 3) {
            toQuestion4(view);
        } else if (state == 4) {
            toQuestion5(view);
        } else if (state == 5) {
            score++;
            correct_answer[4] = true;
            toSummary(view);
        } else if (state == 6) {
            toQuestion1(view);
        }
    }

    public void button_2_action(View view) {
        if (state == 1) {
            toQuestion2(view);
        } else if (state == 2) {
            score++;
            correct_answer[1] = true;
            toQuestion3(view);
        } else if (state == 3) {
            toQuestion4(view);
        } else if (state == 4) {
            score++;
            correct_answer[3] = true;
            toQuestion5(view);
        } else if (state == 5) {
            score++;
            correct_answer[4] = true;
            toSummary(view);
        }

    }

    public void button_3_action(View view) {
        if (state == 3) {
            score++;
            correct_answer[2] = true;
            toQuestion4(view);
        } else if (state == 5) {
            toSummary(view);
        }
    }

    //these methods are called when app is changing state
    public void toQuestion1(View view) {
        //updating overall state
        state = 1;
        score = 0;
        for (int i = 0; i < 5; i++)
            correct_answer[i] = false; //we can try to fill the test again so we might need to reinitilaze our list of coorect answers

        //hide explanations
        summaryExplanations.setVisibility(View.GONE);

        //display question
        question.setText(getString(R.string.question_text_1));

        //make name input field disappear
        name_field.setVisibility(View.GONE);

        //show image
        creature_image.setImageResource(R.drawable.question_1);
        creature_image.setVisibility(View.VISIBLE);

        //modify buttons to display questions
        button_1.setText(getString(R.string.question_1_answer_1));
        button_2.setText(getString(R.string.question_1_answer_2));
        button_2.setVisibility(View.VISIBLE);

        //this section will remove dummy text views that I've used to position elements nicely on screen
        LinearLayout.LayoutParams param = new LinearLayout.LayoutParams(
                RelativeLayout.LayoutParams.MATCH_PARENT,
                RelativeLayout.LayoutParams.MATCH_PARENT,
                0f  //I remove it by setting its weight to 0
        );
        dummy1.setLayoutParams(param);

        //now we should also remove weight from RelativeLayout so it would as we don't need it anymore
        mainLayout.setLayoutParams(param);
    }

    public void toQuestion2(View view) {
        //updating overall state
        state = 2;
        //show image
        creature_image.setImageResource(R.drawable.question_2);

        //modify buttons to display questions
        button_1.setText(getString(R.string.question_2_answer_1));
        button_2.setText(getString(R.string.question_2_answer_2));

    }

    public void toQuestion3(View view) {
        //updating overall state
        state = 3;
        //show image
        creature_image.setImageResource(R.drawable.question_3);
        //display question
        question.setText(getString(R.string.question_text_3));

        //modify buttons to display questions
        button_1.setText(getString(R.string.question_3_answer_1));
        button_2.setText(getString(R.string.question_3_answer_2));

        button_3.setText(getString(R.string.question_3_answer_3));
        button_3.setVisibility(View.VISIBLE);
    }

    public void toQuestion4(View view) {
        //updating overall state
        state = 4;
        //show image
        creature_image.setImageResource(R.drawable.question_4);
        //display question
        question.setText(getString(R.string.question_text_4));

        //modify buttons to display questions
        button_1.setText(getString(R.string.question_4_answer_1));
        button_2.setText(getString(R.string.question_4_answer_2));
        button_3.setVisibility(View.GONE);
    }

    public void toQuestion5(View view) {
        //updating overall state
        state = 5;
        //show image
        creature_image.setImageResource(R.drawable.question_5);
        //display question
        question.setText(getString(R.string.question_text_2));

        //modify buttons to display questions
        button_1.setText(getString(R.string.question_5_answer_1));
        button_2.setText(getString(R.string.question_5_answer_2));
        button_3.setText(getString(R.string.question_5_answer_3));
        button_3.setVisibility(View.VISIBLE);
    }

    public void toSummary(View view) {
        //updating overall state
        state = 6;
        //hide image
        creature_image.setVisibility(View.GONE);

        //display score summary
        String comment = "";
        if (score < 1) comment = getString(R.string.comment_1);
        else if (score < 3) comment = getString(R.string.comment_2);
        else if (score < 4) comment = getString(R.string.comment_3);
        else if (score >= 4) comment = getString(R.string.comment_4);
        summary.setText(String.format(getResources().getString(R.string.summary), username, score, comment));    //this convoluted code passes username, score and comment to @string/summary

        //modify buttons
        button_1.setText(getString(R.string.restart_quiz));
        button_2.setVisibility(View.GONE);
        button_3.setVisibility(View.GONE);

        //display explanations to questions that were answered incorrectly
        summaryExplanations.setVisibility(View.VISIBLE); //first we make vertical linear layout containing explanations visible

        //then we make each explanation visible or not according to answers given by user
        for (int i = 0; i < 5; i++) {
            String explanation = "explanation_question_" + (i + 1);   //indexes in array start at 0 while we index questions from 1
            LinearLayout explanation_view = (LinearLayout) findViewById(getResources().getIdentifier(explanation, "id", getPackageName())); //thanks StackOverflow!
            if (correct_answer[i] == false)
                explanation_view.setVisibility(View.VISIBLE);
            else
                explanation_view.setVisibility(View.GONE);
        }
    }

    /*This function checks if name has been entered*/
    public boolean checkName(View view) {
        username = name_field.getText().toString();
        if (username.matches("")) {
            question.setText(getString(R.string.name_prompt));
            return false;
        } else return true;
    }
}

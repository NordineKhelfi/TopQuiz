package com.example.norma.topquiz;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.norma.topquiz.model.Question;
import com.example.norma.topquiz.model.QuestionBank;

import java.util.ArrayList;
import java.util.Arrays;

public class GameActivity extends AppCompatActivity implements View.OnClickListener {

    public static final String EXTRA_SCORE = "Extra score";
    public static final String EXTRA_NUMBER_OF_QUESTIONS = "Extra number of Q";

    QuestionBank questionBank;
    Question question;

    public static final int const_numberOfQuestion = 10;
    public int iNumberOfQuestions;
    int mScore = 0;

    boolean bDontTouch = true;

    ProgressBar progressBar;
    TextView tv_question;
    Button bAnswer1;
    Button bAnswer2;
    Button bAnswer3;
    Button bAnswer4;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        progressBar = (ProgressBar) findViewById(R.id.pb_Progress);
        tv_question = (TextView) findViewById(R.id.activity_game_question_text);
        bAnswer1 = (Button) findViewById(R.id.activity_game_answer1_btn);
        bAnswer2 = (Button) findViewById(R.id.activity_game_answer2_btn);
        bAnswer3 = (Button) findViewById(R.id.activity_game_answer3_btn);
        bAnswer4 = (Button) findViewById(R.id.activity_game_answer4_btn);

        questionBank = generateQuestions();
        //iNumberOfQuestions = questionBank.getNumberOfQuestions();
        iNumberOfQuestions = const_numberOfQuestion;
        question = questionBank.getQuestion();
        progressBar.setMax(iNumberOfQuestions);
        progressBar.setProgress(0);

        displayQuestion(question);

        bAnswer1.setOnClickListener(this);
        bAnswer2.setOnClickListener(this);
        bAnswer3.setOnClickListener(this);
        bAnswer4.setOnClickListener(this);

        bAnswer1.setTag(0);
        bAnswer2.setTag(1);
        bAnswer3.setTag(2);
        bAnswer4.setTag(3);

    }


    private void displayQuestion(Question question) {

        tv_question.setText(question.getQuestion());
        bAnswer1.setText(question.getChoiceList().get(0));
        bAnswer2.setText(question.getChoiceList().get(1));
        bAnswer3.setText(question.getChoiceList().get(2));
        bAnswer4.setText(question.getChoiceList().get(3));

    }


    QuestionBank generateQuestions() {


        ArrayList<Question> lProphetQuestions = new ArrayList<Question>();

        lProphetQuestions.add(new Question("Name the prophet who built the ark?",
                Arrays.asList("Ibrâhîm",
                        "Nûh",
                        "Mûsa",
                        "Muhammad"),
                1, getResources().getString(R.string.correct_answer_1)));

        lProphetQuestions.add(new Question("For how many years did Nûh call his people to Tawheed?",
                Arrays.asList("950 years",
                        "13 years",
                        "60 years",
                        "100 years"),
                0, getResources().getString(R.string.correct_answer_2)));

        lProphetQuestions.add(new Question("Name the prophet who was swallowed by a big fish?",
                Arrays.asList("Sâlih",
                        "Yûnus",
                        "Yûsuf",
                        "Dhul-kifl"),
                1, getResources().getString(R.string.correct_answer_3)));

        lProphetQuestions.add(new Question("Name the prophet who spoke when he was a baby in the cradle?",
                Arrays.asList("Zakariyyâ",
                        "Ismâ'îl",
                        "Îsâ",
                        "Sulaimân"),
                2, getResources().getString(R.string.correct_answer_4)));

        lProphetQuestions.add(new Question("Name the prophet who was given control over the Jinns?",
                Arrays.asList("Îsâ",
                        "Sulaimân",
                        "Sâlih",
                        "Dâwûd"),
                1, getResources().getString(R.string.correct_answer_5)));

        lProphetQuestions.add(new Question("Name the book given to the prophet Dâwûd?",
                Arrays.asList("Qur'ân",
                        "Tawrât",
                        "Injeel",
                        "Zabûr"),
                3, getResources().getString(R.string.correct_answer_6)));

        lProphetQuestions.add(new Question("Name the prophet who Allâh made with His two Hands?",
                Arrays.asList("Âdam",
                        "Hûd",
                        "Ishâq",
                        "Shu`aîb"),
                0, getResources().getString(R.string.correct_answer_7)));

        lProphetQuestions.add(new Question("What did Allâh teach Âdam?",
                Arrays.asList("Names of the Prophets",
                        "Names of everything",
                        "Names of the Angels",
                        ""),
                1, getResources().getString(R.string.correct_answer_8)));

        lProphetQuestions.add(new Question("What was the name of the father of the prophet Ibrâhîm?",
                Arrays.asList("Âzar",
                        "Abdullah",
                        "",
                        ""),
                0, getResources().getString(R.string.correct_answer_9)));

        lProphetQuestions.add(new Question("Name the prophet who Allâh taught the interpretation of dreams?",
                Arrays.asList("Hûd",
                        "Shu`aîb",
                        "Yûsuf",
                        "Dhul-kifl"),
                2, getResources().getString(R.string.correct_answer_10)));

        lProphetQuestions.add(new Question("What was the name of the father of the prophet Yûsuf?",
                Arrays.asList("Ya`qûb",
                        "Hûd",
                        "AlYasa`",
                        "Sâlih"),
                0, getResources().getString(R.string.correct_answer_11)));


        lProphetQuestions.add(new Question("Name the one who called upon Allah for a child, Then Allah gave him a son called Yahyâ?",
                Arrays.asList("Hârûn",
                        "Zakariyyâ",
                        "Âdam",
                        "Dâwûd"),
                1, getResources().getString(R.string.correct_answer_12)));

        lProphetQuestions.add(new Question("Name the prophet who Muhammad ﷺ met in the 4th heaven?",
                Arrays.asList("Idrîs",
                        "Îsa",
                        "Ishâq",
                        "Âdam"),
                0, getResources().getString(R.string.correct_answer_13)));

        lProphetQuestions.add(new Question("Name the prophet who could heal a person that was born blind by Allah's Permission?",
                Arrays.asList("Ibrâhîm",
                        "Îsa",
                        "Mûsa",
                        "Yahyâ"),
                1, getResources().getString(R.string.correct_answer_14)));


        lProphetQuestions.add(new Question("Name the two sons of the prophet Ibrâhîm?",
                Arrays.asList("Ayûb & Ilyes",
                        "Ismâ'îl & Ishâq",
                        "Dâwûd & Sulaymân",
                        ""),
                1, getResources().getString(R.string.correct_answer_15)));

        //return new QuestionBank(Arrays.asList(question1, question2, question3, question4, question5));
        return new QuestionBank(lProphetQuestions);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {

        return bDontTouch && super.dispatchTouchEvent(ev);
    }

    @Override
    public void onClick(View v) {
        //Toast.makeText(this, "This is the Button " + v.getTag(), Toast.LENGTH_SHORT).show();
        int responseIndex = (int) v.getTag();

        //Checking the answer
        if (responseIndex == question.getAnswerIndex()) {
            Toast.makeText(this, "Congratulations !!!", Toast.LENGTH_SHORT).show();
            mScore++;
        } else {
            Toast.makeText(this, "Try again ..", Toast.LENGTH_SHORT).show();
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Correction")
                    .setMessage(question.getChoiceList().get(question.getAnswerIndex()) + ".\n\n" + question.getCorrectAnswer()).setIcon(R.drawable.ic_mode_edit_black_36dp)
                    .create().show();
        }

        bDontTouch = false;


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //GAME OVER
                if (--iNumberOfQuestions == 0) {
                    //Toast.makeText(this, "Your score : " + String.valueOf(mScore), Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent();
                    intent.putExtra(EXTRA_SCORE, mScore);
                    intent.putExtra(EXTRA_NUMBER_OF_QUESTIONS, const_numberOfQuestion);
                    setResult(RESULT_OK, intent);
                    finish();
                } else {
                    //NEXT QUESTION
                    question = questionBank.getQuestion();
                    displayQuestion(question);
                    //progressBar.setProgress(progressBar.getProgress() + 1); //Increment the pgBar 2 sec after the click

                }
                bDontTouch = true;
            }
        }, 2000);

        //Increment the progress bar instantly with the click
        progressBar.setProgress(progressBar.getProgress() + 1);


    }
}

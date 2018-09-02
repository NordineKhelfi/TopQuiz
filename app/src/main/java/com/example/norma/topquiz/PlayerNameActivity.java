package com.example.norma.topquiz;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.norma.topquiz.model.User;

public class PlayerNameActivity extends AppCompatActivity {

    private static final int GAME_ACTIVITY_REQUEST_CODE = 0;
    private static final String PREFERENCE_NAME = "fisrt name";
    private static final String PREFERENCE_SCORE = "score";

    SharedPreferences preferences;
    int idTopic;

    ProgressBar pbScore;
    TextView tvPercentage;
    TextView tvWelcome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        idTopic = getIntent().getIntExtra(MainMenuActivity.EXTRA_TOPIC, 0);

        final EditText etName;
        final Button bPlay;
        final User user = new User();
        final Intent intent = new Intent(PlayerNameActivity.this, GameActivity.class);


        tvWelcome = (TextView) findViewById(R.id.tvWelcome);
        etName = (EditText) findViewById(R.id.etName);
        bPlay = (Button) findViewById(R.id.bPlay);
        pbScore = (ProgressBar) findViewById(R.id.pb_Score);
        tvPercentage = (TextView) findViewById(R.id.tvPercentage);


        etName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                bPlay.setEnabled(etName.getText().length() > 0);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        if(preferences.contains(PREFERENCE_NAME) && preferences.contains(PREFERENCE_SCORE)){
            tvWelcome.setText("Welcome back " + preferences.getString(PREFERENCE_NAME, null) + "\nYour last score was " + String.valueOf(preferences.getInt(PREFERENCE_SCORE, 0)) + ", will you do better ..?");
            etName.setText(preferences.getString(PREFERENCE_NAME, null));
            etName.setSelection(etName.getText().toString().length());
        }

        bPlay.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                user.setFirstName(etName.getText().toString());
                preferences.edit().putString(PREFERENCE_NAME, user.getFirstName()).apply();
                //startActivity(intent);
                startActivityForResult(intent, GAME_ACTIVITY_REQUEST_CODE);
            }
        });

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == GAME_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK){
            int score = data.getIntExtra(GameActivity.EXTRA_SCORE, 0);
            int numberOfQuestions = data.getIntExtra(GameActivity.EXTRA_NUMBER_OF_QUESTIONS, 1);
            preferences.edit().putInt(PREFERENCE_SCORE, score).apply();

            pbScore.setVisibility(View.VISIBLE);
            pbScore.setMax(numberOfQuestions);
            pbScore.setProgress(score);


            tvPercentage.setVisibility(View.VISIBLE);
            tvPercentage.setText(String.valueOf(Math.round(((double)score / (double)numberOfQuestions) * 100)) + " % GOOD ANSWERS");
            //pbScore.setProgress(score * 10, true);

            tvWelcome.setText("You have " + String.valueOf(score) + " correct answers.\nPlay again ?");

            //Toast.makeText(this, "Score : " + String.valueOf(score), Toast.LENGTH_SHORT).show();
            //Toast.makeText(this, "numOfQ : " + String.valueOf(numberOfQuestions), Toast.LENGTH_SHORT).show();

            ImageButton ibProphets = (ImageButton) findViewById(R.id.ibProphets);
            ImageButton ibMuhammad = (ImageButton) findViewById(R.id.ibMuhammad);
            ImageButton ibQuran = (ImageButton) findViewById(R.id.ibQuran);
            ImageButton ibSalat = (ImageButton) findViewById(R.id.ibSalat);

            //100 % of Good Answers
            if(score == numberOfQuestions && preferences.getBoolean(String.valueOf(idTopic), true)){


                switch(idTopic){
                    case R.id.ibProphets :
                        preferences.edit().putBoolean(String.valueOf(R.id.ibMuhammad), true).apply();
                        preferences.edit().putBoolean(String.valueOf(R.id.ibProphets), false).apply();
                        break;
                    case R.id.ibMuhammad :
                        preferences.edit().putBoolean(String.valueOf(R.id.ibQuran), true).apply();
                        preferences.edit().putBoolean(String.valueOf(R.id.ibMuhammad), false).apply();
                        break;
                    case R.id.ibQuran :
                        preferences.edit().putBoolean(String.valueOf(R.id.ibSalat), true).apply();
                        preferences.edit().putBoolean(String.valueOf(R.id.ibQuran), false).apply();
                        break;
                    case R.id.ibSalat :

                        break;

                    case 0 :
                        Toast.makeText(this, "ISSUE : idTopic = 0 !!!!!", Toast.LENGTH_SHORT).show();
                }

                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("Congratulations !")
                        .setIcon(R.drawable.ic_diamond)
                        .setMessage("You have unlocked the next topic.")
                        .setPositiveButton("Great", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        }).setOnDismissListener(new DialogInterface.OnDismissListener() {
                                                    @Override
                                                    public void onDismiss(DialogInterface dialog) {
                                                        finish();
                                                    }
                                                })
                        .create().show();

            }

        }
    }

}

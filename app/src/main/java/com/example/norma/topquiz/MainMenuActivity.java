package com.example.norma.topquiz;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;

public class MainMenuActivity extends AppCompatActivity {

    public static final String EXTRA_TOPIC = "extra topic";
    public ImageButton ibProphets;
    public ImageButton ibMuhammad;
    public ImageButton ibQuran;
    public ImageButton ibSalat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        ibProphets = (ImageButton) findViewById(R.id.ibProphets);
        ibMuhammad = (ImageButton) findViewById(R.id.ibMuhammad);
        ibQuran = (ImageButton) findViewById(R.id.ibQuran);
        ibSalat = (ImageButton) findViewById(R.id.ibSalat);

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        if (!preferences.contains(String.valueOf(R.id.ibProphets)))
            preferences.edit().putBoolean(String.valueOf(R.id.ibProphets), true).apply();
        //resetGame();


        // ***************************  Check which levels are unlocked ******************************
        ibMuhammad.setImageResource(preferences.contains(String.valueOf(R.id.ibMuhammad)) ? R.drawable.ib_selector : R.drawable.mosque_bw);
        ibQuran.setImageResource(preferences.contains(String.valueOf(R.id.ibQuran)) ? R.drawable.ib_selector : R.drawable.mosque_bw);
        ibSalat.setImageResource(preferences.contains(String.valueOf(R.id.ibSalat)) ? R.drawable.ib_selector : R.drawable.mosque_bw);

    }

    @Override
    protected void onResume() {
        super.onResume();

        // ***************************  Check which levels are unlocked ******************************
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        ibMuhammad.setImageResource(preferences.contains(String.valueOf(R.id.ibMuhammad)) ? R.drawable.ib_selector : R.drawable.mosque_bw);
        ibQuran.setImageResource(preferences.contains(String.valueOf(R.id.ibQuran)) ? R.drawable.ib_selector : R.drawable.mosque_bw);
        ibSalat.setImageResource(preferences.contains(String.valueOf(R.id.ibSalat)) ? R.drawable.ib_selector : R.drawable.mosque_bw);
    }

    public void onTopicClick(View view) {

        if (PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).contains(String.valueOf(view.getId()))) {

            Intent intent = new Intent(MainMenuActivity.this, PlayerNameActivity.class);
            intent.putExtra(EXTRA_TOPIC, view.getId());
            startActivity(intent);
        } else {

            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("This topic is locked")
                    .setIcon(R.drawable.ic_mecca)
                    .setMessage("You must reach 100% of good answers at the previous topic first")
                    .setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    })
                    .create().show();
        }

    }

    public void resetGame() {

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        preferences.edit().remove(String.valueOf(R.id.ibMuhammad)).apply();
        preferences.edit().remove(String.valueOf(R.id.ibQuran)).apply();
        preferences.edit().remove(String.valueOf(R.id.ibSalat)).apply();

    }
}

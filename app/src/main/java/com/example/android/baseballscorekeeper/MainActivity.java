package com.example.android.baseballscorekeeper;

import android.support.v4.widget.TextViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.lang.reflect.Array;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.T;
import static android.icu.lang.UCharacter.GraphemeClusterBreak.V;
import static com.example.android.baseballscorekeeper.R.id.away_error_btn;
import static com.example.android.baseballscorekeeper.R.id.away_hit_btn;
import static com.example.android.baseballscorekeeper.R.id.away_out_btn;
import static com.example.android.baseballscorekeeper.R.id.away_rs_btn;
import static com.example.android.baseballscorekeeper.R.id.away_score;
import static com.example.android.baseballscorekeeper.R.id.homeField;
import static com.example.android.baseballscorekeeper.R.id.home_error_btn;
import static com.example.android.baseballscorekeeper.R.id.home_hit_btn;
import static com.example.android.baseballscorekeeper.R.id.home_out_btn;
import static com.example.android.baseballscorekeeper.R.id.home_rs_btn;

public class MainActivity extends AppCompatActivity {
    EditText homeField;
    EditText awayField;
    TextView homeTeamScore;
    TextView awayTeamScore;
    TextView homeTeamBox;
    TextView awayTeamBox;
    TextView homeBoxRuns;
    TextView awayBoxRuns;
    TextView homeBoxHits;
    TextView awayBoxHits;
    TextView homeBoxErrors;
    TextView awayBoxErrors;
    int homeScore = 0;
    int homeHits = 0;
    int homeErrors = 0;
    int awayScore = 0;
    int awayHits = 0;
    int awayErrors = 0;
    int numOuts = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        homeField = (EditText) findViewById(R.id.homeField);
        awayField = (EditText) findViewById(R.id.awayField);
        homeTeamBox = (TextView) findViewById(R.id.boxscore_home_team);
        awayTeamBox = (TextView) findViewById(R.id.boxscore_away_team);
        homeTeamScore = (TextView) findViewById(R.id.home_score);
        awayTeamScore = (TextView) findViewById(R.id.away_score);
        homeBoxRuns = (TextView) findViewById(R.id.boxscore_home_runs);
        awayBoxRuns = (TextView) findViewById(R.id.boxscore_away_runs);
        homeBoxHits = (TextView) findViewById(R.id.boxscore_home_hits);
        awayBoxHits = (TextView) findViewById(R.id.boxscore_away_hits);
        homeBoxErrors = (TextView) findViewById(R.id.boxscore_home_errors);
        awayBoxErrors = (TextView) findViewById(R.id.boxscore_away_errors);
        homeField.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                homeTeamBox.setText(homeField.getText().toString());
            }
        });

        awayField.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                awayTeamBox.setText(awayField.getText().toString());
            }
        });

    }

    public void incrementStats(View view) {
        switch (view.getId()) {
            case home_rs_btn:
                homeScore = homeScore + 1;
                homeTeamScore.setText(homeScore + "");
                homeBoxRuns.setText(homeScore + "");
                break;

            case away_rs_btn:
                awayScore = awayScore + 1;
                awayTeamScore.setText(awayScore + "");
                awayBoxRuns.setText(awayScore + "");
                break;
            case R.id.home_hit_btn:
                homeHits = homeHits + 1;
                homeBoxHits.setText(homeHits + "");
                break;

            case R.id.away_hit_btn:
                awayHits = awayHits + 1;
                awayBoxHits.setText(awayHits + "");
                break;
            case home_error_btn:
                homeErrors = homeErrors + 1;
                homeBoxErrors.setText(homeErrors + "");
                break;

            case R.id.away_error_btn:
                awayErrors = awayErrors + 1;
                awayBoxErrors.setText(awayErrors + "");
                break;
        }
    }

    public static String ordinal(int i) {
        String[] sufixes = new String[]{"th", "st", "nd", "rd", "th", "th", "th", "th", "th", "th"};
        switch (i % 100) {
            case 11:
            case 12:
            case 13:
                return i + "th";
            default:
                return i + sufixes[i % 10];

        }
    }

    public void recordOut(View view) {
        numOuts = numOuts + 1;
        RadioButton oneOut = (RadioButton) findViewById(R.id.one_out);
        RadioButton twoOut = (RadioButton) findViewById(R.id.two_out);
        Button homeHitBtn = (Button) findViewById(home_hit_btn);
        Button homeRSBtn = (Button) findViewById(home_rs_btn);
        Button homeOutBtn = (Button) findViewById(home_out_btn);
        Button awayErrorBtn = (Button) findViewById(away_error_btn);
        Button awayHitBtn = (Button) findViewById(away_hit_btn);
        Button awayRSBtn = (Button) findViewById(away_rs_btn);
        Button awayOutBtn = (Button) findViewById(away_out_btn);
        Button homeErrorBtn = (Button) findViewById(home_error_btn);

        switch (numOuts % 3) {
            case 1:

                oneOut.setChecked(true);
                break;
            case 2:

                twoOut.setChecked(true);
                break;
        }

        if (numOuts % 3 == 0) {
            oneOut.setChecked(false);
            twoOut.setChecked(false);
        }
        int inningOutCount = (int) numOuts / 3;
        String inningHalf;
        if (inningOutCount % 2 == 0) {
            inningHalf = "Top";

            awayHitBtn.setVisibility(View.VISIBLE);
            awayRSBtn.setVisibility(View.VISIBLE);
            awayOutBtn.setVisibility(View.VISIBLE);
            homeErrorBtn.setVisibility(View.VISIBLE);

            homeHitBtn.setVisibility(View.GONE);
            homeRSBtn.setVisibility(View.GONE);
            homeOutBtn.setVisibility(View.GONE);
            awayErrorBtn.setVisibility(View.GONE);


        } else {
            inningHalf = "Bottom";
            homeHitBtn.setVisibility(View.VISIBLE);
            homeRSBtn.setVisibility(View.VISIBLE);
            homeOutBtn.setVisibility(View.VISIBLE);
            awayErrorBtn.setVisibility(View.VISIBLE);

            awayHitBtn.setVisibility(View.GONE);
            awayRSBtn.setVisibility(View.GONE);
            awayOutBtn.setVisibility(View.GONE);
            homeErrorBtn.setVisibility(View.GONE);

        }
        String inningNum = ordinal(inningOutCount);
        TextView inningLabel = (TextView) findViewById(R.id.inning_label);
        inningLabel.setText(inningHalf + " " + inningNum);
    }

}

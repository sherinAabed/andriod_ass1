package com.example.try333;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;


public class LessonActivity1 extends AppCompatActivity {

    private RadioButton Tans, Fans;
    private RadioGroup grpRadio;
    private Button btnSubmit;
    private TextView txtResult;
    private CheckBox checkboxOptionA ;
    private CheckBox checkboxOptionB;
    private CheckBox checkboxOptionC;
    private CheckBox saveInfoCheckbox;
   private Button btnSubmit2 = findViewById(R.id.btnSubmit2);
    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupViews();

    }
    private void setupViews() {
        Tans = findViewById(R.id.Tans);
        Fans = findViewById(R.id.Fans);
        grpRadio = findViewById(R.id.grpRadio);
        btnSubmit = findViewById(R.id.btnSubmit);

        checkboxOptionA = findViewById(R.id.checkboxOptionA);
        checkboxOptionB = findViewById(R.id.checkboxOptionB);
        checkboxOptionC = findViewById(R.id.checkboxOptionC);
        btnSubmit2 = findViewById(R.id.btnSubmit2);

        saveInfoCheckbox=findViewById(R.id.saveInfoCheckbox);

        preferences = PreferenceManager.getDefaultSharedPreferences(this);
        editor = preferences.edit();
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Check if the save information checkbox is checked
                boolean saveInfo = saveInfoCheckbox.isChecked();

                int id = grpRadio.getCheckedRadioButtonId();
                if (id == -1) {
                    Toast.makeText(LessonActivity1.this, "Choose an answer", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    RadioButton rd = findViewById(id);
                    boolean isCorrect = rd == Tans;
                    if (saveInfo) {
                        saveAnswer("radio_answer", isCorrect);
                    }
                    showMessage(isCorrect ? "Very good!" : "Try again");
                }
            }
        });

        btnSubmit2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Check if the save information checkbox is checked
                boolean saveInfo = saveInfoCheckbox.isChecked();

                boolean isCorrect = checkboxOptionA.isChecked() && !checkboxOptionB.isChecked() && !checkboxOptionC.isChecked();

                if (isCorrect) {
                    showMessage("Correct! Well done!");
                    if (saveInfo) {
                        saveAnswer("checkbox_answer", isCorrect);
                    }

                } else {
                    showMessage("Incorrect. Please try again.");
                }
            }
        });
    }

    private void saveAnswer(String key, boolean value) {
        editor.putBoolean(key, value);
        editor.apply();
    }

    private void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}

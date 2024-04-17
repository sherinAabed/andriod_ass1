
package com.example.try333;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    public static final String NAME = "NAME";
    public static final String PASS = "PASS";
    public static final String FLAG = "FLAG";
    private boolean flag = false;
    private EditText edtName;
    private EditText edtPassword;
    private CheckBox chk;
    private SharedPreferences prefs;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupViews();
        setupSharedPrefs();
        checkPrefs();
    }

    private void checkPrefs() {
        flag = prefs.getBoolean(FLAG, false);

        if(flag){
            String name = prefs.getString(NAME, "");
            String password = prefs.getString(PASS, "");
            edtName.setText(name);
            edtPassword.setText(password);
            chk.setChecked(true);
        }
    }

    private void setupSharedPrefs() {
        prefs= PreferenceManager.getDefaultSharedPreferences(this);
        editor = prefs.edit();
    }

    private void setupViews() {
        edtName = findViewById(R.id.edtName);
        edtPassword = findViewById(R.id.edtPassword);
        chk = findViewById(R.id.chk);
    }

    public void btnLoginOnClick(View view) {
        String name = edtName.getText().toString();
        String password = edtPassword.getText().toString();
        Intent intent=new Intent(this,MainActivity2.class);
        if(chk.isChecked()){
            if(!flag) {
                editor.putString(NAME, name);
                editor.putString(PASS, password);
                editor.putBoolean(FLAG, true);
                editor.commit();
            }

            if(name!=null&&password!=null) {
                intent.putExtra("username", name);
                startActivity(intent);
            }
            if(name==null||password==null) {
                Toast.makeText(this, "please enter your name and password", Toast.LENGTH_SHORT).show();

            }

        }

    }
}
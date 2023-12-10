package dev.SMB116.threadpermier;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private EditText inputNumber;
    private TextView tempsExecution;
    private Button btnCalculer;
    private ListView listePremier;

    private NombrePremierRunnable nombrePremierRunnable;
    private NombrePremier nombrePremier;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inputNumber = findViewById(R.id.inputNumber);
        tempsExecution = findViewById(R.id.tempsExecution);
        btnCalculer = findViewById(R.id.btnCalculer);
        listePremier = findViewById(R.id.listePremier);

        btnCalculer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculerNombrePremier();
            }
        });

    }//onCreate


    private void calculerNombrePremier(){
        String  input = inputNumber.getText().toString();
        if (input.isEmpty()){
            inputNumber.setError("Veuillez entrer un nombre");
            inputNumber.requestFocus();
            return;
        }
        int nombreMax = Integer.parseInt(input);
        NombrePremier nombrefini = nombrePremierRunnable.doInBackground(nombreMax);
        tempsExecution.setText(String.valueOf(nombrefini.getTempsExecution()));

    }//calculerNombrePremier


}//MAIN
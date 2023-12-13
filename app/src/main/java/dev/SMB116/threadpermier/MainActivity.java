package dev.SMB116.threadpermier;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
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

    //Element de la vue
    private EditText etInputNumber;
    private TextView tvTempsExecution;
    private Button btnCalculer;
    private ListView lvListePremier;

    private Button btnScroll;



    //Element de la classe
    private ArrayList<Integer>  listPremier;
    private ArrayAdapter<Integer> adapter;
    private float tempsExecution;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Initialisation des elements de la vue
        etInputNumber = findViewById(R.id.inputNumber);
        tvTempsExecution = findViewById(R.id.tempsExecution);
        btnCalculer = findViewById(R.id.btnCalculer);
        lvListePremier = findViewById(R.id.listePremier);

        btnScroll = findViewById(R.id.btnScroll);

        //Initialisation des elements de la classe
        listPremier = new ArrayList<>();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listPremier);
        lvListePremier.setAdapter(adapter);



        btnCalculer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                listPremier.clear();

                int nombreMax = Integer.parseInt(etInputNumber.getText().toString());
                NombrePremierRunnable nombrePremierRunnable = new NombrePremierRunnable(listPremier, lvListePremier, tvTempsExecution, adapter);
                nombrePremierRunnable.execute(nombreMax);



            }//onClick


        });//btnCalculer.setOnClickListener

        btnScroll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lvListePremier.smoothScrollToPosition(listPremier.size());
            }
        });//btnScroll.setOnClickListener


    }//onCreate





}//MAIN
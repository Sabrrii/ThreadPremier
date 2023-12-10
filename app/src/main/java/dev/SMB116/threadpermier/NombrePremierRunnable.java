package dev.SMB116.threadpermier;

import android.os.AsyncTask;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class NombrePremierRunnable extends AsyncTask<Integer, Integer, NombrePremier> {
    private NombrePremier nombrePremier;

    @Override
    protected NombrePremier doInBackground(Integer... integers) {
        int nombreMax = integers[0];
        float tempsDebut = System.currentTimeMillis();

        List<Integer> listePremier = new ArrayList<>();

        for (int i = 1; i <= nombreMax; i++) {
            if (nombrePremier.estPremier(i)){
                listePremier.add(i);
                updateProgress(i);
            }
        }
        float tempsFin = System.currentTimeMillis()-tempsDebut;
        return new NombrePremier(listePremier, tempsFin);
    }//doInBackground


    protected  void updateProgress(Integer... values){
        nombrePremier.updateListNombrePremier(values[0]);
    }//publishProgress






}

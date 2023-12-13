package dev.SMB116.threadpermier;

import android.os.AsyncTask;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class NombrePremierRunnable extends AsyncTask<Integer, Integer, ArrayList<Integer>> {
    private List<Integer> listePremier;
    private ListView listeNbPremier;
    private TextView tempsExecution;
    private ArrayAdapter<Integer> adapter;

    private  long tempDebut;

    public NombrePremierRunnable(List<Integer> listePremier, ListView listeNbPremier, TextView tempsExecution, ArrayAdapter<Integer> adapter) {
        this.listePremier = listePremier;
        this.listeNbPremier = listeNbPremier;
        this.tempsExecution = tempsExecution;
        this.adapter = adapter;
    }



    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        tempDebut = System.currentTimeMillis();
    }//onPreExecute

    @Override
    protected  ArrayList<Integer> doInBackground(Integer... integers) {
        int nombreMax = integers[0];
        ArrayList<Integer> listPremier = new ArrayList<>();
        for (int i = 1; i <= nombreMax; i++) {
            if (estPremier(i)){
                listPremier.add(i);
                publishProgress(i);
            }
        }
        
        return listPremier;

    }//doInBackground


    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
        listePremier.add(values[0]);
        adapter.notifyDataSetChanged();
    }//onProgressUpdate


    @Override
    protected void onPostExecute(ArrayList<Integer> integers) {
        super.onPostExecute(integers);
        long tempsFin = System.currentTimeMillis();
        float temps = (tempsFin - tempDebut);
        tempsExecution.setText(String.valueOf(temps));
    }//onPostExecute

    public boolean estPremier(int nombre){
        for (int i = 2; i < nombre; i++) {
            if (nombre % i == 0){
                return false;
            }
        }
        return true;
    }//estPremier





}//NombrePremierRunnable

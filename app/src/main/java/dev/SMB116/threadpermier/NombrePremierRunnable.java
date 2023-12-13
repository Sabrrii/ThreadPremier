package dev.SMB116.threadpermier;

import android.os.AsyncTask;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;

public class NombrePremierRunnable extends AsyncTask<Integer, Integer, ArrayList<Integer>> {
    private ArrayList<Integer> listePremier;
    private ListView listeNbPremier;
    private TextView tempsExecution;
    private ArrayAdapter<Integer> adapter;

    private long tempDebut;

    public NombrePremierRunnable(ArrayList<Integer> listePremier, ListView listeNbPremier, TextView tempsExecution, ArrayAdapter<Integer> adapter) {
        this.listePremier = listePremier;
        this.listeNbPremier = listeNbPremier;
        this.tempsExecution = tempsExecution;
        this.adapter = adapter;
    }//Constructeur

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        tempDebut = System.currentTimeMillis();
    }//onPreExecute

    @Override
    protected ArrayList<Integer> doInBackground(Integer... integers) {
        int nombreMax = integers[0];
        ArrayList<Integer> listPremier = cribleEratosthene(nombreMax);

        // Ajouter les nombres premiers à la liste
        for (int i = 0; i < listPremier.size(); i++) {
            publishProgress(listPremier.get(i));
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

    /**
     * Utilise l'algorithme du crible d'Ératosthène pour trouver tous les nombres premiers jusqu'à nombreMax.
     *
     * @param nombreMax Le nombre maximum auquel  trouver les nombres premiers.
     * @return Une liste contenant tous les nombres premiers jusqu'à nombreMax.
     */
    public ArrayList<Integer> cribleEratosthene(int nombreMax) {
        //Initialise un tableau de booleen a true
        //considere que tous les nombres sont premiers
        boolean[] estPremier = new boolean[nombreMax + 1];
        Arrays.fill(estPremier, true);

        ArrayList<Integer> listPremier = new ArrayList<>();

        //Commence a 2 car 0 et 1 ne sont pas premiers
        // Et "crible"(met a false) les multiples de chaque nombre premier
        for (int p = 2; p * p <= nombreMax; p++) {
            if (estPremier[p]) {
                // Si p est premier, alors tous ses multiples ne le sont pas donc passe a false
                for (int i = p * p; i <= nombreMax; i += p) {
                    estPremier[i] = false;
                }
            }//if
        }//for crible

        // Ajouter les nombres premiers à la liste
        for (int i = 2; i <= nombreMax; i++) {
            if (estPremier[i]) {
                listPremier.add(i);
            }
        }//for ajout

        return listPremier;
    }//cribleEratosthene

}//NombrePremierRunnable

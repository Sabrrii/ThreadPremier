package dev.SMB116.threadpermier;

import java.util.List;

public class NombrePremier {
    private List<Integer> listePremier;
    private float tempsExecution;

    public NombrePremier(List<Integer> listePremier, float tempsExecution) {
        this.listePremier = listePremier;
        this.tempsExecution = tempsExecution;
    }

    public List<Integer> getListePremier() {
        return listePremier;
    }

    public float getTempsExecution() {
        return tempsExecution;
    }

    public boolean estPremier(int nombre){
        for (int i = 2; i < nombre; i++) {
            if (nombre % i == 0){
                return false;
            }
        }
        return true;
    }

    public void updateListNombrePremier(int nombre){
        listePremier.add(nombre);
    }

}

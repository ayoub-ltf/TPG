import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;

public class GestionTPG {
    public static final int NBKM = 300000;
    public static final String BUS = "Bus";
    ArrayList<Vehicule> lstVehicules;

    public GestionTPG() {
        this.lstVehicules = lireDonnees();
    }

    public int calculerNbreBusIter(){
        int nbreBus = 0;
        for (Vehicule element : lstVehicules) {
            if (element instanceof Bus){
                nbreBus += 1;
            }
        }
        return nbreBus;
    }

    public int calculerNbreBus2(){
        int nbreBus = 0;
        for (int i = 0; i < lstVehicules.size(); i++) {
            // Vehicule v = lstVehicules.get(i)
            if (lstVehicules.get(i) instanceof  Bus){
                nbreBus += 1;
            }
        }
        return nbreBus;
    }

    public HashMap<String, Integer> calculerRepartitionType(){
        HashMap<String, Integer> dicoType = new HashMap<>();

        int nbreBus = 0;
        int nbTrolley = 0;

        for (int i = 0; i < lstVehicules.size(); i++) {
            Vehicule v = lstVehicules.get(i);
            if (v instanceof  Bus){
                nbreBus = nbreBus + 1;
            } else if (v instanceof Trolley) {
                nbTrolley++;
            }
        }

        dicoType.put(BUS, nbreBus);
        dicoType.put("Trolley", nbTrolley);

        return dicoType;
    }

    public void afficherCoutEntretien() {
        for (Vehicule v : lstVehicules) {
            System.out.println(v.afficherCout());
        }
    }

    public void affichageNbreBus(){
        //System.out.println("Le nombre total de bus : " + calculerNbreBusIter());
        HashMap<String, Integer> dicoType = calculerRepartitionType();

        for (String s : dicoType.keySet()) {
            System.out.println(s + " : "+ dicoType.get(s));
        }
    }

    public int calculerSommeAchat() {
        int somme = 0;
        for (Vehicule vehicule : this.lstVehicules) {
            somme += vehicule.getPrixAchat();
        }
        return somme ;
    }

    public void afficherSommeAchat() {
        System.out.println("Le total de prix d'achat est : " + this.calculerSommeAchat());
    }

    @Override
    public String toString() {
        String affichage = "";
        for (Vehicule v : this.lstVehicules) {
            affichage += v + "\n";
        }
        return affichage;
    }

    public  Vehicule trouveVehiculeMin(){
        Vehicule min = this.lstVehicules.get(0);
        for (Vehicule vehicule : this.lstVehicules) {
            if(vehicule.getKmAuCompteur() < min.getKmAuCompteur()){
                min = vehicule;
            }
        }
        return min;
    }

    public void afficherVehiculeMin(){
        System.out.println("Le véhicule avec le moins de kil est " + this.trouveVehiculeMin());
    }




    public ArrayList<Vehicule> plusde300000(){
        ArrayList<Vehicule> vehicules = new ArrayList<>();

        for (int i = 0; i < lstVehicules.size(); i++) {
            if (lstVehicules.get(i).getKmAuCompteur() > NBKM){
                vehicules.add(lstVehicules.get(i));
            }
        }
        return vehicules;
    }

    public void affichagePlusde300000(){
        ArrayList<Vehicule> vehicules = plusde300000();

        for (Vehicule vehicule : vehicules) {
            System.out.println(vehicule.toString());
        }
    }

    public void afficherCoutAnnuelEntretient(){
        String total = "";
        for (int i = 0; i < lstVehicules.size(); i++) {
            total =lstVehicules.get(i).simulerCoutEntretien();
            System.out.println(total);
        }
    }


    public  ArrayList<Vehicule> lireDonnees() {
        String url = "jdbc:sqlite:tpg.sqlite";
        ArrayList<Vehicule> lstVehiculeDB = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection(url)) {
            String queryVehicules = "SELECT * FROM vehicules";
            try (PreparedStatement stmtVehicules = conn.prepareStatement(queryVehicules)) {
                try (ResultSet rsVehicules = stmtVehicules.executeQuery()) {
                    while (rsVehicules.next()) {
                        int prix = rsVehicules.getInt("prix_achat");
                        int nbPassagers = rsVehicules.getInt("nb_passagers");

                        String dateStr = rsVehicules.getString("date_acquisition");
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
                        LocalDate dateAchat = LocalDate.parse(dateStr,formatter);

                        int km = rsVehicules.getInt("km");
                        int tensionConso = rsVehicules.getInt("tension_conso");
                        String moteurSecours = rsVehicules.getString("moteur_secours");
                        String typeVehicule = rsVehicules.getString("type_vehicule");

                        boolean moteurSecoursB = false ;
                        if ("oui".equals(moteurSecours)) {
                            moteurSecoursB = true;
                        }

                        Vehicule v ;
                        if(typeVehicule.equals("trolley")){
                            v = new Trolley(nbPassagers, prix, dateAchat, tensionConso, moteurSecoursB);
                        }
                        else {
                            v = new Bus(nbPassagers, prix, dateAchat);
                        }

                        v.setKmAuCompteur(km);
                        lstVehiculeDB.add(v);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lstVehiculeDB;
    }
}

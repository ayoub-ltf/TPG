import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class GestionTPG {

    ArrayList<Vehicule>lstVehicules;

    public GestionTPG(ArrayList<Vehicule> lstVehicules) {
        this.lstVehicules = lstVehicules;
    }

    public int calculerSommeAchat(){
        int somme = 0;
        for (Vehicule vehicule : lstVehicules) {
            somme += vehicule.getPrixAchat();
        }
        return somme;
    }



    private static ArrayList<Vehicule> getOperationsListFromDB() {
        // URL de connexion à la base de données SQLite
        String url = "jdbc:sqlite:tpg.sqlite";

        //créationd d'une arraylist vide
        ArrayList<Vehicule> lstVehicules = new ArrayList<>();
        // Connexion et manipulation de la base de données
        try(Connection conn = DriverManager.getConnection(url)) {
            // Si la connexion est établie avec succès
            if (conn != null) {
                //System.out.println("Connexion à la base de données réussie !");

                // Lecture des données avec PreparedStatement
                String selectSQL = "SELECT * FROM vehicules";
                try (PreparedStatement stmt = conn.prepareStatement(selectSQL)) {
                    try (ResultSet rs = stmt.executeQuery()) {
                        // parcours du résultat et ajout des opérations à la liste retournée
                        while (rs.next()) {
                            int matricule = rs.getInt("matricule");
                            String marque = rs.getString("marque");
                            int nbPassagersMax = rs.getInt("nb_passagers");
                            int prixAchat = rs.getInt("prix_achat");
                            int kmAuCompteur = rs.getInt("km");
                            int tensionNeccessaire = rs.getInt("tension_conso");
                            String  moteurThermique = rs.getString("moteur_secours");
                            boolean moteurThermiqueB = false;
                            if("oui".equals(moteurThermique)) {
                                moteurThermiqueB = true;}


                            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");// formatter la date d'abord
                            String dateStr = rs.getString("date_acquisition"); // en deux étapes pour la date
                            LocalDate dateAcquisition = LocalDate.parse(dateStr,formatter);


                            String typeVehicule = rs.getString("type_vehicule");

                            Vehicule v = null;
                            if (typeVehicule.equals("trolley")){
                                v = new Trolley(nbPassagersMax,prixAchat,dateAcquisition,tensionNeccessaire,moteurThermiqueB);
                            }
                            else{
                                v = new Bus(nbPassagersMax,prixAchat,dateAcquisition);
                            }

                            lstVehicules.add(v);

                            //ajout des opérations
                            Vehicule vehicule = new Vehicule(nbPassagersMax, prixAchat,dateAcquisition);
                            lstVehicules.add(vehicule);

                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        //return la liste remplie d'opérations
        return lstVehicules;

    }
}

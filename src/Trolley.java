import java.time.LocalDate;


public class Trolley extends Vehicule {
    public static final int COUT_TROLLEY = 8000;
    public static final int KMSERVICE_TROLLEY = 200000;
    public static final int SUPP_MOTEUR_SECOURS = 1200;
    public static final int COUT_ENTRETIEN_ANNUEL = 2000;
    private int tensionNeccessaire; // nb de Volt nécessaires pour propulser le Trolley à pleine charge
    private boolean moteurThermique; // le Trolley est-il équipé d'un moteur thermique (Diesel) de secours?
    public Trolley(int nbPassagersMax, long prixAchat, LocalDate dateAcquisition, int tensionNeccessaire, boolean moteurThermique) {
        super(nbPassagersMax, prixAchat, dateAcquisition);
        this.tensionNeccessaire = tensionNeccessaire;
        this.moteurThermique = moteurThermique;
    }

    @Override
    public String toString() {
        return super.toString() + "est un trolley avec tension : " +  this.tensionNeccessaire + ", moteur : " + this.moteurThermique + " son coût d'entretien est " + this.calculerCoutEntretien();
    }

    public String afficherCout() {
        return super.afficherCout() + " de type trolley, le coût d'entretien est de " + this.calculerCoutEntretien();
    }

    public int calculerNbEntretien() {
        return  this.getKmAuCompteur() / KMSERVICE_TROLLEY;
    }

    public int calculerCoutEntretien() {
        int coutBase = (COUT_TROLLEY + calculerCoutEntretienSiege()) * this.calculerNbEntretien();

        return this.moteurThermique ? coutBase + (SUPP_MOTEUR_SECOURS * calculerNbEntretien()) : coutBase;

       /* traduction du if ternaire :
        if (this.moteurThermique) {
            return coutBase + 1200  * calculerNbEntretien();
        } else {
            return coutBase;
        }*/

    }
    public String simulerCoutEntretien(){
        int cout = 0;
        int anneeActuelle = LocalDate.now().getYear();
        int nbreAnnee = anneeActuelle - getDateAcquisition().getYear();

        for (int i = 0; i < nbreAnnee; i++) {
            if (nbreAnnee > 1){
                cout += COUT_ENTRETIEN_ANNUEL;
            }
        }
        int total = cout + calculerCoutEntretien();



        return "Il y a " + nbreAnnee+ " années. Ce qui fait un cout de " + cout + " pour un total de :"+ total;
    }


}

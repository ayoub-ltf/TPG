import java.time.LocalDate;


public class Bus extends Vehicule{
    public static final int KMSERVICE = 50000;
    public static final int COUT_ENTRETIEN_BUS = 6500;

    public static final int COUT_ENTRETIEN_ANNUEL = 1500;
    private double consommation; //consommation de diesel pour 100km
    public Bus(int nbPassagersMax, long prixAchat, LocalDate dateAcquisition) {
        super(nbPassagersMax, prixAchat, dateAcquisition);
        consommation = 0;
    }

    public int calculerNbEntretien() {
        return  this.getKmAuCompteur() / KMSERVICE;
    }

    public int calculerCoutEntretien() {
        return (COUT_ENTRETIEN_BUS + (calculerCoutEntretienSiege())) * this.calculerNbEntretien();
    }

    @Override
    public String toString() {
        return super.toString() + " son coût d'entretien est " + this.calculerCoutEntretien() + " est un bus";
    }

    public String afficherCout() {
        return super.afficherCout() + " de type bus, le coût d'entretien est de " + this.calculerCoutEntretien();
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

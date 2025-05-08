import java.time.LocalDate;

public class Vehicule
{
    public static final int COUT_SIEGE = 150;
    private int nbPassagersMax;

    public long getPrixAchat() {
        return prixAchat;
    }

    private long prixAchat;

    public int getKmAuCompteur() {
        return kmAuCompteur;
    }

    public int calculerCoutEntretienSiege() {
        return this.getNbPassagersMax() * COUT_SIEGE;
    }



    private int kmAuCompteur;
    private LocalDate dateAcquisition;

    public void setKmAuCompteur(int kmAuCompteur) {
        if(kmAuCompteur > 0)
            this.kmAuCompteur = kmAuCompteur;
        else
            throw new IllegalArgumentException();
    }

    public int getNbPassagersMax()
    {
        return nbPassagersMax;
    }

    public void setNbPassagersMax(int nbPassagersMax)
    {
        this.nbPassagersMax = nbPassagersMax;
    }

    public Vehicule(int nbPassagersMax, long prixAchat, LocalDate dateAcquisition)
    {
        this.nbPassagersMax = nbPassagersMax;
        this.prixAchat = prixAchat;
        this.dateAcquisition = dateAcquisition;



    }

    public LocalDate getDateAcquisition() {
        return dateAcquisition;
    }

    public String simulerCoutEntretien(){
        int anneeActuelle = LocalDate.now().getYear();
        int nbreAnnee = anneeActuelle - getDateAcquisition().getYear();

        return "";
    }

    public String afficherCout() {
        return "Pour un véhicule avec km : " + this.getKmAuCompteur() + " et passagers " + this.getNbPassagersMax();
    }

    @Override
    public String toString()
    {
        return String.format("Véhicule (prix: CHF %d, nb. passagers max: %d) avec km : %d,  de type: ", prixAchat, nbPassagersMax, kmAuCompteur);
    }
}
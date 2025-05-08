import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class TestLignes {

    public static void main(String[] args) {
        GestionTPG gtp = new GestionTPG();

        System.out.println(gtp);

        System.out.println("--------------QUESTION 2 ---------------------");
        gtp.affichageNbreBus(); // Affichage nombre de bus et trolley

        System.out.println("--------------QUESTION 3 ---------------------");
        gtp.afficherSommeAchat();

        System.out.println("--------------QUESTION 5 ---------------------");
        gtp.affichagePlusde300000();

        System.out.println("--------------QUESTION 6 ---------------------");
        gtp.afficherVehiculeMin();

        System.out.println("--------------QUESTION 7 ---------------------");
        gtp.afficherCoutEntretien();
        System.out.println("--------------partie 2 ---------------------");
        gtp.afficherCoutAnnuelEntretient();


    }








}

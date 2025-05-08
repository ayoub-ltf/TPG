import javax.crypto.spec.PSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class TestLignes {

    public static void main(String[] args) {
        System.out.println("Hello World!");
        HashMap<String, String> hm = new HashMap<>();
        hm.put("prenom", "Jaqcue");
        hm.put("nom", "Truc");


        hm.put("prenom", "Jacques");

        for (String k : hm.keySet()) {
            System.out.println(k + " : " + hm.get(k));
        }

        System.out.println(hm.values());

    }














}

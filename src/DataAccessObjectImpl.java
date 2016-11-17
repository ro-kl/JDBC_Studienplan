import edu.whs.gdb.ApplicationException;
import edu.whs.gdb.DataAccessObject;
import edu.whs.gdb.entity.Modul;
import edu.whs.gdb.entity.Praktikumsteilnahme;
import edu.whs.gdb.entity.Student;
import edu.whs.gdb.entity.Studienrichtung;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JPanel;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;

/**
 * @author Roman Kluth
 */
public class DataAccessObjectImpl implements DataAccessObject {

    private final String DRIVER = "org.apache.derby.jdbc.EmbeddedDriver";
    private final String URL = "jdbc:derby:C:\\Users\\roman\\Desktop\\gdb-Aufgabe6-Bibliotheken\\gdb-praktikum-javaDB_10.10\\gdb-praktikum";
    private final String SHUTDOWN_URL = "jdbc:derby:C:\\Users\\roman\\Desktop\\gdb-Aufgabe6-Bibliotheken\\gdb-praktikum-javaDB_10.10\\gdb-praktikum;shutdown=true";
    private Connection con;

    public DataAccessObjectImpl() {
        System.out.println("DataAccessObjectImpl()");
        try {
            Class.forName(DRIVER).newInstance();
        } catch (Exception e) {
            System.out.println("Fehler beim laden der JDBC-Derby Bridge!");
        }
        try {
            con = DriverManager.getConnection(URL);
            System.out.println("Mit der DB verbunden.");
        } catch (SQLException e) {
            System.out.println("Es konnte keine Verbindung zur Datenbank "
                    + "hergestellt werden!");
        }

        // Tabelle für Studenten erstellen
//        try {
//            Statement select = con.createStatement();
//            // Student wird erstellt
//            select.executeUpdate("CREATE TABLE STUDENT(Matrikel VARCHAR(9) not NULL, Name VARCHAR(20)"
//                    + ", Vorname VARCHAR(20), Adresse VARCHAR(200), SKuerzel VARCHAR(3),"
//                    + " PRIMARY KEY ( Matrikel ))");
//            System.out.println("Tabelle Student wurde erstellt");
//            select.close();
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
        // Tabelle für Praktikumsteilnahme erstellen
//        try {
//            Statement select = con.createStatement();
//            // Praktikumsteilnahme wird erstellt
//            select.executeUpdate("CREATE TABLE PRAKTIKUMSTEILNAHME(Matrikel VARCHAR(9) not NULL, "
//                    + "MKUERZEL VARCHAR(5) not NULL"
//                    + ", SEMESTER VARCHAR(7) not NULL, TESTAT boolean,"
//                    + " PRIMARY KEY ( Matrikel, MKUERZEL, SEMESTER ))");
//            System.out.println("Tabelle Praktikumsteilnahme wurde erstellt");
//            select.close();
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
    }

    @Override
    public List<List<String>> getStudienverlaufsplan(Studienrichtung sr) throws ApplicationException {
        System.out.println("getStudienverlaufsplan()");
        List<List<String>> zeilen = new ArrayList<>();
        List<String> zeile = new ArrayList<>();

        zeile.add("Studienverlaufsplan\n" + sr.toString());
        zeile.add("1. Semester");
        zeile.add("2. Semester");
        zeile.add("3. Semester");
        zeile.add("4. Semester");
        zeile.add("5. Semester");
        zeile.add("6. Semester");
        zeile.add(" ");
        zeilen.add(zeile);

        zeile = new ArrayList<>();

        zeile.add("Kategorie");
        zeile.add("Mod V Ü P Cr");
        zeile.add("Mod V Ü P Cr");
        zeile.add("Mod V Ü P Cr");
        zeile.add("Mod V Ü P Cr");
        zeile.add("Mod V Ü P Cr");
        zeile.add("Mod V Ü P Cr");
        zeile.add("Summe");
        zeilen.add(zeile);

        try {
            // hier commit??
            Statement select = con.createStatement();
            ResultSet result = select.executeQuery("SELECT K.NAME, "
                    + "K.KKUERZEL, SEM, M.MKUERZEL, VL, UB, PR, Credits\n"
                    + "FROM VERLAUFSPLAN V, MODUL M, KATEGORIE K\n"
                    + "WHERE V.MKUERZEL = M.MKUERZEL\n"
                    + "AND M.KKUERZEL = K.KKUERZEL\n"
                    + "AND SKUERZEL = '" + sr.getKuerzel() + "'");
            System.out.println("sr: " + sr.getKuerzel() + ", " + sr.getName());
            // Spalten summen pro Semester
            int summeSemester1 = 0;
            int summeSemester2 = 0;
            int summeSemester3 = 0;
            int summeSemester4 = 0;
            int summeSemester5 = 0;
            int summeSemester6 = 0;

            
            // Platzhalter einsetzen für jedes Feld
            
            // Zeile Informatik
            String[] zeileIN = new String[8];
            for (int i = 0; i < 8; i++) {
                zeileIN[i] = " ";
            }
            // letzte Spalte, Summe
            int summeIN = 0;
            
            // Zeile Spezieller Anwendungsbereich Medien
            String[] zeileAM = new String[8];
            for (int i = 0; i < 8; i++) {
                zeileAM[i] = " ";
            }
            int summeAM = 0;

            // Zeile Soft Skills
            String[] zeileSS = new String[8];
            for (int i = 0; i < 8; i++) {
                zeileSS[i] = " ";
            }
            int summeSS = 0;

            // Zeile Bachelor-Arbeit und externe Praxisphase
            String[] zeileBP = new String[8];
            for (int i = 0; i < 8; i++) {
                zeileBP[i] = " ";
            }
            int summeBP = 0;

            // Zeile Math. u. naturwiss.-techn. Grundlagen
            String[] zeileMN = new String[8];
            for (int i = 0; i < 8; i++) {
                zeileMN[i] = " ";
            }
            int summeMN = 0;

            // Zeile Wahlpflicht
            String[] zeileWAHL = new String[8];
            for (int i = 0; i < 8; i++) {
                zeileWAHL[i] = " ";
            }
            int summeWAHL = 0;

            // Zeile Spezieller Anwendungsbereich Wirtschaft
            String[] zeileAW = new String[8];
            for (int i = 0; i < 8; i++) {
                zeileAW[i] = " ";
            }
            int summeAW = 0;

            while (result.next()) {
                if (result.getString(2).equals("IN")) {
                    // Wenn die erste Spalte noch nicht belegt ist, K.NAME + K.KUERZEL
                    if (zeileIN[0].equals(" ")) {
                        zeileIN[0] = result.getString(1) + "(" + result.getString(2) + ")";
                    }

                    // Modul wird hinzugefügt
                    // Zeile[Semester] += M.MKuerzel  + " " + VL + ... + Credits
                    zeileIN[result.getInt(3)] += result.getString(4)
                            + " " + result.getInt(5)
                            + " " + result.getInt(6)
                            + " " + result.getInt(7)
                            + " " + result.getInt(8)
                            + "\n";
                    // Summe der Stunden VL + UE + PR (44)
                    summeIN += result.getInt(5) + result.getInt(6) + result.getInt(7);
                }

                
                if (result.getString(2).equals("AM")) {
                    if (zeileAM[0].equals(" ")) {
                        zeileAM[0] = result.getString(1) + "(" + result.getString(2) + ")";
                    }

                    zeileAM[result.getInt(3)] += result.getString(4)
                            + " " + result.getInt(5)
                            + " " + result.getInt(6)
                            + " " + result.getInt(7)
                            + " " + result.getInt(8)
                            + "\n";
                    summeAM += result.getInt(5) + result.getInt(6) + result.getInt(7);
                }

                if (result.getString(2).equals("SS")) {
                    if (zeileSS[0].equals(" ")) {
                        zeileSS[0] = result.getString(1) + "(" + result.getString(2) + ")";
                    }

                    zeileSS[result.getInt(3)] += result.getString(4)
                            + " " + result.getInt(5)
                            + " " + result.getInt(6)
                            + " " + result.getInt(7)
                            + " " + result.getInt(8)
                            + "\n";
                    summeSS += result.getInt(5) + result.getInt(6) + result.getInt(7);
                }

                if (result.getString(2).equals("BP")) {
                    if (zeileBP[0].equals(" ")) {
                        zeileBP[0] = result.getString(1) + "(" + result.getString(2) + ")";
                    }

                    zeileBP[result.getInt(3)] += result.getString(4)
                            + " " + result.getInt(5)
                            + " " + result.getInt(6)
                            + " " + result.getInt(7)
                            + " " + result.getInt(8)
                            + "\n";
                    summeBP += result.getInt(5) + result.getInt(6) + result.getInt(7);
                }

                if (result.getString(2).equals("MN")) {
                    if (zeileMN[0].equals(" ")) {
                        zeileMN[0] = result.getString(1) + "(" + result.getString(2) + ")";
                    }

                    zeileMN[result.getInt(3)] += result.getString(4)
                            + " " + result.getInt(5)
                            + " " + result.getInt(6)
                            + " " + result.getInt(7)
                            + " " + result.getInt(8)
                            + "\n";
                    summeMN += result.getInt(5) + result.getInt(6) + result.getInt(7);
                }

                if (result.getString(2).equals("WAHL")) {
                    if (zeileWAHL[0].equals(" ")) {
                        zeileWAHL[0] = result.getString(1) + "(" + result.getString(2) + ")";
                    }

                    zeileWAHL[result.getInt(3)] += result.getString(4)
                            + " " + result.getInt(5)
                            + " " + result.getInt(6)
                            + " " + result.getInt(7)
                            + " " + result.getInt(8)
                            + "\n";
                    summeWAHL += result.getInt(5) + result.getInt(6) + result.getInt(7);
                }

                if (result.getString(2).equals("AW")) {
                    if (zeileAW[0].equals(" ")) {
                        zeileAW[0] = result.getString(1) + "(" + result.getString(2) + ")";
                    }

                    zeileAW[result.getInt(3)] += result.getString(4)
                            + " " + result.getInt(5)
                            + " " + result.getInt(6)
                            + " " + result.getInt(7)
                            + " " + result.getInt(8)
                            + "\n";
                    summeAW += result.getInt(5) + result.getInt(6) + result.getInt(7);
                }

                // switch(Semester)
                switch (result.getInt(3)) {
                    // Sem = 1
                    case 1:
                        // Summe (Sem 1) += VL + UE + PR
                        summeSemester1 += result.getInt(5)
                                + result.getInt(6)
                                + result.getInt(7);
                        break;
                    case 2:
                        summeSemester2 += result.getInt(5)
                                + result.getInt(6)
                                + result.getInt(7);
                        break;
                    case 3:
                        summeSemester3 += result.getInt(5)
                                + result.getInt(6)
                                + result.getInt(7);
                        break;
                    case 4:
                        summeSemester4 += result.getInt(5)
                                + result.getInt(6)
                                + result.getInt(7);
                        break;
                    case 5:
                        summeSemester5 += result.getInt(5)
                                + result.getInt(6)
                                + result.getInt(7);
                        break;
                    case 6:
                        summeSemester6 += result.getInt(5)
                                + result.getInt(6)
                                + result.getInt(7);
                        break;
                }
            }

            // Bisher alle in Arrays, jetzt in List
            // Zeile Math. u. naturwiss.-techn. Grundlagen wird aufgenommen, falls etwas enthalten ist!
            if (!zeileMN[0].equals(" ")) {
                // Kategorie + Semesterinhalte + Summe, 0-7
                // Letzte Spalte muss noch gesetzt werden
                zeileMN[7] = "" + summeMN;
                zeile = Arrays.asList(zeileMN);
                zeilen.add(zeile);
            }

            // Zeile Informatik wird aufgenommen, falls etwas enthalten ist!
            if (!zeileIN[0].equals(" ")) {
                zeileIN[7] = "" + summeIN;
                zeile = Arrays.asList(zeileIN);
                zeilen.add(zeile);
            }

            // Zeile Spezieller Anwendungsbereich Medien wird aufgenommen, falls etwas enthalten ist!
            if (!zeileAM[0].equals(" ")) {
                zeileAM[7] = "" + summeAM;
                zeile = Arrays.asList(zeileAM);
                zeilen.add(zeile);
            }

            // Zeile Soft Skills wird aufgenommen, falls etwas enthalten ist!
            if (!zeileSS[0].equals(" ")) {
                zeileSS[7] = "" + summeSS;
                zeile = Arrays.asList(zeileSS);
                zeilen.add(zeile);
            }

            // Zeile Wahlpflicht wird aufgenommen, falls etwas enthalten ist!
            if (!zeileWAHL[0].equals(" ")) {
                zeileWAHL[7] = "" + summeWAHL;
                zeile = Arrays.asList(zeileWAHL);
                zeilen.add(zeile);
            }

            // Zeile Bachelor-Arbeit und externe Praxisphase wird aufgenommen, falls etwas enthalten ist!
            if (!zeileBP[0].equals(" ")) {
                zeileBP[7] = "" + summeBP;
                zeile = Arrays.asList(zeileBP);
                zeilen.add(zeile);
            }

            // Zeile Spezieller Anwendungsbereich Wirtschaft wird aufgenommen, falls etwas enthalten ist!
            if (!zeileAW[0].equals(" ")) {
                zeileAW[7] = "" + summeAW;
                zeile = Arrays.asList(zeileAW);
                zeilen.add(zeile);
            }

            // Letzte Spalte
            zeile = new ArrayList<>();
            zeile.add("Summe SWS");
            zeile.add("" + summeSemester1);
            zeile.add("" + summeSemester2);
            zeile.add("" + summeSemester3);
            zeile.add("" + summeSemester4);
            zeile.add("" + summeSemester5);
            zeile.add("" + summeSemester6);
            zeile.add("" + (summeIN + summeAM + summeSS + summeBP + summeMN + summeWAHL + summeAW));
            zeilen.add(zeile);

            result.close();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            // rollback?
        }

        return zeilen;

    }

    @Override
    public void addStudent(String matrikel, String name, String vorname, String adresse, String srKuerzel) throws ApplicationException {
        try {
            con.setAutoCommit(false);
            Statement select = con.createStatement();

            // Alle Matrikelnummer holen
            ResultSet result = select.executeQuery("SELECT Matrikel FROM Student");

            while (result.next()) {
                // Exestiert diese MAtrikelnummer schon?
                if (result.getString(1).equals(matrikel)) {
                    throw new ApplicationException("Matrikelnummer ist bereits vorhanden!");
                }
            }

            // alle löschen
//            select.executeUpdate("DELETE FROM Student");
//            System.out.println("del");

            // Student wird eingefuegt
            select.executeUpdate("INSERT INTO Student(Matrikel, Name, Vorname, Adresse, SKuerzel)"
                    + " values ('" + matrikel + "', '" + name + "', '" + vorname
                    + "', '" + adresse + "', '" + srKuerzel + "')");

            System.out.println("Student eingefügt:\n" + matrikel + "', '" + name + "', '" + vorname
                    + "', '" + adresse + "', '" + srKuerzel);

            result.close();
            select.close();
            con.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            JFrame frm = new JFrame();
            frm.setTitle("Fehler in der DB");
            frm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frm.setSize(300,200);
            frm.setLocation(50,50);
            frm.setVisible(true);
            if (con != null) {
                try {
                    System.out.println("Daten werden wiederhergestellt...");
                    con.rollback();
                    System.out.println("Daten wurden wiederhergestellt.");
                    con.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        } finally {
            try {
                con.setAutoCommit(true);
            } catch (SQLException se) {
                System.out.println("AutoCommit konnte nicht eingeschaltet werden.");
                se.printStackTrace();
            }
        }
    }

    @Override
    public Collection<Student> getAllStudent() {
        System.out.println("getAllStudent()");
        ArrayList<Student> studenten = new ArrayList<>();

        
        try 
        {
            
            // autoCommit(false) bei einer einfachen Abfrage oder nur bei insert, create,...?
            Statement select = con.createStatement();
            ResultSet result = select.executeQuery("SELECT * FROM Student ORDER BY Matrikel");

            while (result.next()) {
                studenten.add(new StudentImpl(
                        result.getString(1), result.getString(2),
                        result.getString(3), result.getString(4), result.getString(5)));
            }

            result.close();
            select.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return studenten;
    }

    @Override
    public boolean enroll(String matrikel, String name, String vorname, String adresse, String srKuerzel, Modul modul, String semester) throws ApplicationException {
        System.out.println("enroll()");

        boolean bereitsAufgenommen = false;

        try {
            con.setAutoCommit(false);
            // Prüfung ob Modul im Studiengang ist und ob ein Praktikum vorhanden ist
            Statement select = con.createStatement();
            ResultSet result = select.executeQuery("SELECT SKuerzel, Modulname "
                    + "FROM Verlaufsplan V, Modul M "
                    + "WHERE V.MKuerzel = M.MKuerzel");

            boolean modulStimmt = false;
            while (result.next() && !modulStimmt) {
                // bis kürzel & Modulname stimmen
                modulStimmt = result.getString(1).equals(srKuerzel)
                        && result.getString(2).equals(modul.getName());
            }

            // bis result leer und kein treffer
            if (!modulStimmt) {
                throw new ApplicationException("Modul ist nicht in dem Studiengang enthalten!");
            }

            // Kein Praktikum vorhanden
            if (modul.getPraktikum() == 0) {
                throw new ApplicationException("Dieses Modul sieht kein Praktikum vor!");
            }
            // hier ein commit??
            con.commit();

            // Ist schon in der Tabelle
            boolean istAngemeldet = false;
            select = con.createStatement();
            result = select.executeQuery("SELECT Matrikel, MKuerzel, Semester FROM Praktikumsteilnahme");

            while (result.next() && !istAngemeldet) {
                // prüft, ob der Eintrag schon in der Tabelle ist
                istAngemeldet = result.getString(1).equals(matrikel)
                        && result.getString(2).equals(modul.getKuerzel())
                        && result.getString(3).equals(semester);
            }

            // commit???
            con.commit();

            if (istAngemeldet) {
                throw new ApplicationException("Student ist bereits angemeldet.");
            } else {
                // wird hinzugefügt
                select = con.createStatement();
                select.executeUpdate("INSERT INTO Praktikumsteilnahme("
                        + "Matrikel, MKuerzel, Semester) values ("
                        + "'" + matrikel + "', '" + modul.getKuerzel() + "',"
                        + "'" + semester + "')");

            }

            result.close();
            select.close();
            con.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            if (con != null) {
                try {
                    System.out.println("Daten werden wiederhergestellt...");
                    con.rollback();
                    System.out.println("Daten wurden wiederhergestellt.");
                    con.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        } finally {
            try {
                con.setAutoCommit(true);
            } catch (SQLException se) {
                System.out.println("AutoCommit konnte nicht eingeschaltet werden.");
                se.printStackTrace();
            }
        }
        return !bereitsAufgenommen;
    }

    @Override
    public void setTestate(Collection<Praktikumsteilnahme> testate) {
        System.out.println("setTestate()");
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        try {
            // start der TA
            con.setAutoCommit(false);
            // ArrayList
            Praktikumsteilnahme pTeilnahme;
            // Über alle Teilnehmer interieren
            Iterator<Praktikumsteilnahme> it = testate.iterator();

            while (it.hasNext()) {
                pTeilnahme = it.next();
                boolean testat = (pTeilnahme.isTestat());

                Statement select = con.createStatement();

                select.executeUpdate("UPDATE Praktikumsteilnahme SET "
                        + "Testat = " + testat + " "
                        + "WHERE Matrikel = '" + pTeilnahme.getStudent().getMatrikel() + "'"
                        + "AND MKuerzel = '" + pTeilnahme.getModul().getKuerzel() + "'"
                        + "AND Semester = '" + pTeilnahme.getSemester() + "'");
                if (testat == true) {
                    System.out.println("Praktikum bestanden: " + pTeilnahme.getStudent().getName());
                }
            }
            con.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            if (con != null) {
                try {
                    System.out.println("Daten werden wiederhergestellt...");
                    con.rollback();
                    System.out.println("Daten wurden wiederhergestellt.");
                    con.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        } finally {
            try {
                con.setAutoCommit(true);
            } catch (SQLException se) {
                System.out.println("AutoCommit konnte nicht eingeschaltet werden.");
                se.printStackTrace();
            }
        }

    }

    @Override
    public JPanel getChart(int type, Object parameter1, Object parameter2) throws ApplicationException {
        System.out.println("getChart()");
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        JFreeChart chart = null;
        PreparedStatement preStmtSRichtungen;
        PreparedStatement preStmtTestabnahmen;

        // ANTEIL_TESTATABNAHMEN, 1
//        try {
            // autoCommit auf false
            
            // SEM als String
            if (!(parameter1 instanceof String)) {

                throw new ApplicationException("Falscher Parametertyp. Erwartet: String.");

            // 2. Parameter muss null sein    
            } else if (parameter2 != null) {

                throw new ApplicationException("Falscher Parametertyp. Erwartet: Leeres Objekt.");

            }

            String semester = (String) parameter1;

            // Studienrichtung
            ResultSet rsSRichtungen;

            // Testate
            ResultSet rsTestabnahmen;

            // TODO:
            // createBarChart(...)
            // commit nicht vergessen
            
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }

        return new ChartPanel(chart);
    }

    @Override
    public Collection<Studienrichtung> getAllStudienrichtung() {

        System.out.println("getAllStudienrichtungen()");
        // neue ArrayList
        ArrayList<Studienrichtung> studienrichtungen = new ArrayList<>();

        try {
            con.setAutoCommit(false);
            Statement select = con.createStatement();
            ResultSet result = select.executeQuery("SELECT * FROM STUDIENRICHTUNG");
            while (result.next()) {
                studienrichtungen.add(new StudienrichtungImpl(result.getString(1), result.getString(2)));
            }
            result.close();
            select.close();
            con.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            if (con != null) {
                try {
                    System.out.println("Daten werden wiederhergestellt...");
                    con.rollback();
                    System.out.println("Daten wurden wiederhergestellt.");
                    con.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        } finally {
            try {
                con.setAutoCommit(true);
            } catch (SQLException se) {
                System.out.println("AutoCommit konnte nicht eingeschaltet werden.");
                se.printStackTrace();
            }
        }
        return studienrichtungen;

    }

    @Override
    public Collection<Modul> getAllModul() {
        System.out.println("getAllModul()");

        ArrayList<Modul> module = new ArrayList<>();

        try {
            con.setAutoCommit(false);
            Statement select = con.createStatement();
            ResultSet result = select.executeQuery("SELECT * FROM Modul ORDER BY Modulname");

            while (result.next()) {
                module.add(new ModulImpl(result.getString(1), result.getString(2),
                        result.getInt(3), result.getInt(4), result.getInt(5),
                        result.getInt(6), result.getString(7)));
            }

            result.close();
            select.close();
            con.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            if (con != null) {
                try {
                    System.out.println("Daten werden wiederhergestellt...");
                    con.rollback();
                    System.out.println("Daten wurden wiederhergestellt.");
                    con.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        } finally {
            try {
                con.setAutoCommit(true);
            } catch (SQLException se) {
                System.out.println("AutoCommit konnte nicht eingeschaltet werden.");
                se.printStackTrace();
            }
        }

        return module;
    }

    @Override
    public Collection<Praktikumsteilnahme> getAllPraktikumsteilnahme(Modul modul, String semester) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.

        System.out.println("getAllPraktikumsteilnahme()");

        // neue ArrayList wo alle Teilnehmer gespeichert werden
        ArrayList<Praktikumsteilnahme> praktikumsteilnehmer = new ArrayList<>();

        try {
            con.setAutoCommit(false);
            // Tabelle mit allen Teilnehmern P, für SEM, MOD
            if (modul != null && !semester.equals("")) {
                Statement select = con.createStatement();
                ResultSet result = select.executeQuery("SELECT S.Matrikel, S.Name, "
                        + "S.Vorname, S.Adresse, S.SKuerzel, M.Modulname, Testat, Semester "
                        + "FROM Praktikumsteilnahme P, Student S, Modul M "
                        + "WHERE P.Matrikel = S.Matrikel "
                        + "AND P.MKuerzel = M.MKuerzel "
                        + "AND M.Modulname = '" + modul.getName() + "'"
                        + "AND Semester = '" + semester + "'");

                while (result.next()) {

                    // Student wird erstellt, nicht über addStudent (für DB)
                    StudentImpl student = new StudentImpl(
                            result.getString(1), result.getString(2),
                            result.getString(3), result.getString(4), result.getString(5));
                    praktikumsteilnehmer.add(new PraktikumsteilnahmeImpl(
                            student, modul, semester, result.getInt(7)));

                }
                result.close();
            }
            con.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            if (con != null) {
                try {
                    System.out.println("Daten werden wiederhergestellt...");
                    con.rollback();
                    System.out.println("Daten wurden wiederhergestellt.");
                    con.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        } finally {
            try {
                con.setAutoCommit(true);
            } catch (SQLException se) {
                System.out.println("AutoCommit konnte nicht eingeschaltet werden.");
                se.printStackTrace();
            }
        }

        return praktikumsteilnehmer;
    }

    @Override
    public void close() throws ApplicationException {
        System.out.println("close()");
        try {
            con.close();
            DriverManager.getConnection(SHUTDOWN_URL);
            System.out.println("Verbindung zur DB geschlossen.");
        } catch (SQLException e) {
            // ErrorCode 45000 -> derbyDB schon offen
            if (e.getErrorCode() != 45000) {
                System.out.println("Die Datenbankverbindung konnte nicht beendet "
                        + "werden!");
                System.out.println(e.getMessage());
            }
        }
    }

}

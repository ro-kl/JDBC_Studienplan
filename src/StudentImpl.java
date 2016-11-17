/**
 * @author roman
 */
public class StudentImpl implements edu.whs.gdb.entity.Student {

    private String matrikel;
    private String name;
    private String vorname;
    private String adresse;
    private String sKuerzel;

    public StudentImpl(String matrikel, String name, String vorname, String adresse,
            String sKurzel) {
        this.matrikel = matrikel;
        this.name = name;
        this.vorname = vorname;
        this.adresse = adresse;
        this.sKuerzel = sKurzel;
    }

    @Override
    public String getMatrikel() {
        return matrikel;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getVorname() {
        return vorname;
    }

    @Override
    public String getAdresse() {
        return adresse;
    }

    @Override
    public String getStudienrichtungKuerzel() {
        return sKuerzel;
    }

    @Override
    public String toString() {
        return name + " " + vorname;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        boolean istGleich = false;

        if (obj instanceof StudentImpl) {
            istGleich = matrikel.equals(((StudentImpl) obj).getMatrikel());
        }

        return istGleich;
    }

}

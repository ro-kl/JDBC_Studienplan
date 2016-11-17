import java.util.Objects;

/**
 * @author Roman Kluth
 */
public class StudienrichtungImpl implements edu.whs.gdb.entity.Studienrichtung {
    
    private String sKuerzel;
    private String name;
    
    public StudienrichtungImpl(String sKuerzel, String name) {
        System.out.println("Neue Studienrichtung für: " + sKuerzel + ", " + name);
        this.sKuerzel = sKuerzel;
        this.name = name;
    }

    @Override
    public String getKuerzel() {
        return sKuerzel;
    }

    @Override
    public String getName() {
        return name;
    }
    
    @Override
    public boolean equals(java.lang.Object obj) {
        boolean istGleich = false;
        if(obj instanceof StudienrichtungImpl) {
            StudienrichtungImpl studienrichtung = (StudienrichtungImpl) obj;
            istGleich = this.sKuerzel.equals(studienrichtung.getKuerzel())
                    && this.name.equals(studienrichtung.getName());
        }
        return istGleich;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + Objects.hashCode(this.sKuerzel);
        hash = 29 * hash + Objects.hashCode(this.name);
        return hash;
    }

    @Override
    public String toString() {
        return name + " (" + sKuerzel + ")";
    }
}

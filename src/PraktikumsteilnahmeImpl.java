import edu.whs.gdb.entity.Modul;
import edu.whs.gdb.entity.Student;
import java.util.Objects;


/**
 * @author roman
 */
public class PraktikumsteilnahmeImpl implements edu.whs.gdb.entity.Praktikumsteilnahme {
    Student student;
    Modul modul;
    String semester;
    int testat;
    
    public PraktikumsteilnahmeImpl(Student student, Modul modul, String semester, int testat) {
        this.student = student;
        this.modul = modul;
        this.semester = semester;
        this.testat = testat;
    }
    
    @Override
    public Student getStudent() {
        return student;
    }

    @Override
    public Modul getModul() {
        return modul;
    }

    @Override
    public String getSemester() {
        return semester;
    }

    @Override
    public boolean isTestat() {
        return testat > 0;
    }

    @Override
    public void setTestat(boolean testat) {
        if(testat) {
            this.testat = 1;
        } else {
            this.testat = 0;
        }
    }

    @Override
    public String toString() {
        return "" + student.toString() + "\n" + modul.toString() + "\n"
                + semester + this.isTestat();
    }

    @Override
    public boolean equals(Object obj) {
        boolean istGueltig = false;
        
        if(obj instanceof PraktikumsteilnahmeImpl) {
            PraktikumsteilnahmeImpl pTeilnahme = (PraktikumsteilnahmeImpl)obj;
            istGueltig = pTeilnahme.getModul().equals(modul)
                    && pTeilnahme.getStudent().equals(student)
                    && pTeilnahme.getSemester().equals(semester);
        }
        return istGueltig;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 47 * hash + Objects.hashCode(this.student);
        hash = 47 * hash + Objects.hashCode(this.modul);
        hash = 47 * hash + Objects.hashCode(this.semester);
        return hash;
    }
  
}


import java.util.Objects;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Roman Kluth
 */
public class ModulImpl implements edu.whs.gdb.entity.Modul {
    String mKuerzel;
    String modulname;
    int vl;
    int ub;
    int pr;
    int credits;
    String kKuerzel;
    
    public ModulImpl(String mKuerzel, String modulname, int vl, int ub, int pr,
            int credits, String kKuerzel) {
        this.mKuerzel = mKuerzel;
        this.modulname = modulname;
        this.vl = vl;
        this.ub = ub;
        this.pr = pr;
        this.credits = credits;
        this.kKuerzel = kKuerzel;
    }

    @Override
    public String getKuerzel() {
        return mKuerzel;
    }

    @Override
    public String getName() {
        return modulname;
    }

    @Override
    public int getVorlesung() {
        return vl;
    }

    @Override
    public int getUebung() {
        return ub;
    }

    @Override
    public int getPraktikum() {
        return pr;
    }

    @Override
    public int getCredits() {
        return credits;
    }
    
    @Override
    public String toString() {
        return modulname + " (" + mKuerzel + ")";
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 59 * hash + Objects.hashCode(this.mKuerzel);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ModulImpl other = (ModulImpl) obj;
        if (!Objects.equals(this.mKuerzel, other.mKuerzel)) {
            return false;
        }
        return true;
    }
    
}


import edu.whs.gdb.GUIFactory;
import javax.swing.JFrame;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Roman Kluth
 */
public class Main {
   
    
    
    public static void main (String args[]){
        System.out.println("main");
    
        JFrame frame = GUIFactory.createMainFrame("Informationssystem", new DataAccessObjectImpl());
        frame.setVisible(true);
        
    }
    
}

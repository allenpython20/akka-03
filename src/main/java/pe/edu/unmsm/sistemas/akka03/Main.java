/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.edu.unmsm.sistemas.akka03;

/**
 *
 * @author allen
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String[] actores = new String[1];
        actores[0] = Soldado.class.getName();
        akka.Main.main(actores);

    }
    
}

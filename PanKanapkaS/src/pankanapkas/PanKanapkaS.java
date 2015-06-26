/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pankanapkas;

/**
 *
 * @author mtrzonkowski
 */
public class PanKanapkaS {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ServerUDP server = new ServerUDP();
        server.run();
    }
    
}
